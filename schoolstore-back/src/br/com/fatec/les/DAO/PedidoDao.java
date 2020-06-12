package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.endereco.Endereco;
import br.com.fatec.les.model.estoque.Estoque;
import br.com.fatec.les.model.pagamento.FormaPagamento;
import br.com.fatec.les.model.pagamento.cartao.CartaoCredito;
import br.com.fatec.les.model.pagamento.cupom.Cupom;
import br.com.fatec.les.model.pagamento.cupom.PagamentoCupom;
import br.com.fatec.les.model.pedido.Frete;
import br.com.fatec.les.model.pedido.ItemPedido;
import br.com.fatec.les.model.pedido.Pedido;
import br.com.fatec.les.model.pedido.StatusPedido;
import br.com.fatec.les.model.usuario.Cliente;
import br.com.fatec.les.model.usuario.ItemCarrinho;

public class PedidoDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	FreteDao freteDao = new FreteDao();
	FormaPagamentoDAO formaPagamentoDao = new FormaPagamentoDAO();
	ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
	ClienteDao clienteDao = new ClienteDao();
	CupomDao cupomDao = new CupomDao();
	EstoqueDao estoqueDao = new EstoqueDao();
	ItemCarrinhoDao itemCarrinhoDao = new ItemCarrinhoDao();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		Pedido pedido = (Pedido) entidade;
		conexao = ConexaoFactory.getConnection();
		ResultSet rs;
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_pedido "
				+ "("
				+ "ped_valor, "
				+ "ped_statusPedido, "
				+ "ped_cli_id, "
				+ "ped_fre_id, "
				+ "ped_fpag_id, "
				+ "ped_ativo, "
				+ "ped_dataHoraCriacao,"
				+ "ped_dataHoraAtualizacao "
				+ ") "
				+ " VALUES ( ?, ?, ?, ?, ?, true, NOW(), NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setFloat(1, pedido.getValor());
			pstm.setString(2, pedido.getStatusPedido().toString());
			pstm.setLong(3, pedido.getCliente().getId());
			
			mensagem = freteDao.salvar(pedido.getFrete());
			if(mensagem.getMensagemStatus() == MensagemStatus.ERRO) {
				throw new SQLException();
			}
			String id = mensagem.getMensagem();
			
			pstm.setLong(4, Long.parseLong(id));
			
			mensagem = formaPagamentoDao.salvar(pedido.getFormaPagamento());
			if(mensagem.getMensagemStatus() == MensagemStatus.ERRO) {
				throw new SQLException();
			}
			id = mensagem.getMensagem();
			
			pstm.setLong(5, Long.parseLong(id));
			pstm.executeUpdate();
			
			if(pedido.getFormaPagamento().getPagamentosCartao().isEmpty()) {
				if(pedido.getFormaPagamento().getValorTotal() > pedido.getValor()) {
					Cupom cupom = new Cupom();
					cupom.setValor(pedido.getFormaPagamento().getValorTotal() - pedido.getValor());
					cupom.setUsuario(pedido.getCliente().getUsuario());
					cupomDao.salvar(cupom);
				}
			}
			
			rs = pstm.getGeneratedKeys();
			if (rs.next()){
				id = Integer.toString(rs.getInt(1));
				ItemCarrinho itemCarrinho = new ItemCarrinho();
				
				for(ItemPedido item : pedido.getItensPedido()) {
					pedido.setId(Long.parseLong(id));
					item.setPedido(pedido);
					itemPedidoDao.salvar(item);
					
					itemCarrinho = new ItemCarrinho();
					itemCarrinho.setProduto(item.getProduto());
					itemCarrinhoDao.deletar(itemCarrinho);
					
				}
				
				mensagem.setMensagem("Pedido feito com sucesso! Acompanhe o status de seu pedido na página 'Meus Pedidos'");
				mensagem.setMensagemStatus(MensagemStatus.OPERACAO);
			}				
		}catch(SQLException e){
			mensagem.setMensagem("Ocorreu um erro durante a operação. Tente novamente ou consulte a equipe de desenvolvimento.");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm);
		}
		return mensagem;
	}

	@Override
	public Mensagem deletar(ADominio entidade) throws SQLException {
		Pedido pedido  = (Pedido) entidade;
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setPedido(pedido);
		Cupom cupom = new Cupom();
		Estoque estoque = new Estoque();
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql;
		
		PreparedStatement pstm = null;
		
		try {
			
			Pedido pedidoAux = new Pedido();
			pedidoAux = (Pedido) consultar(pedido).get(0);
			conexao = null;
			conexao = ConexaoFactory.getConnection();
			
			if(!pedidoAux.getItensPedido().isEmpty() && pedido.getItensPedido() != null) {
				if(!pedido.getItensPedido().isEmpty() && pedido.getItensPedido() != null) {
					for(ItemPedido item : pedido.getItensPedido()) {
						boolean flag = false;
						for(ItemPedido itemAux : pedidoAux.getItensPedido()) {
							if(item.getId() == itemAux.getId()) {
								itemPedidoDao.atualizar(item);
								ItemPedido itemPedi = (ItemPedido)itemPedidoDao.consultar(item).get(0);
								if(itemPedi.getQuantidade() <= 0 ) {
									itemPedidoDao.deletar(item);
								}
								estoque.setQuantidadeTotal(item.getQuantidade());	
								estoque.setProduto(item.getProduto());
								estoqueDao.atualizar(estoque);
								flag = true;
							}
						}
						if(!flag) {
							estoque.setQuantidadeTotal(item.getQuantidade());	
							estoque.setProduto(item.getProduto());
							estoqueDao.atualizar(estoque);
							itemPedidoDao.deletar(item);
						}
					}
				}else {
					for(ItemPedido item : pedidoAux.getItensPedido()) {
						estoque.setQuantidadeTotal(item.getQuantidade());	
						estoque.setProduto(item.getProduto());
						estoqueDao.atualizar(estoque);
						itemPedidoDao.deletar(item);
					}
				}
			}
			
			ArrayList<ADominio> itens = new ArrayList<ADominio>();
			itens.addAll(itemPedidoDao.consultar(itemPedido));
			
			if(itens.isEmpty()) {
				sql = "UPDATE tb_pedido SET "
						+ "ped_statusPedido = ?,"
						+ "ped_ativo = false, "
						+ "ped_dataHoraAtualizacao = NOW() "			
						+ " WHERE ped_id = ?";
				pstm = conexao.prepareStatement(sql);
				pstm.setString(1, StatusPedido.ATUALIZADO.toString());
				pstm.setLong(2, pedido.getId());
				mensagem.setMensagem("Pedido cancelado com sucesso!");
			}else {
				sql = "UPDATE tb_pedido SET "
						+ "ped_statusPedido = ?,"
						+ "ped_valor = ped_valor - ?,"
						+ "ped_dataHoraAtualizacao = NOW() "			
						+ " WHERE ped_id = ?";		
				
				pstm = conexao.prepareStatement(sql);
				pstm.setString(1, StatusPedido.ATUALIZADO.toString());
				pstm.setLong(3, pedido.getId());
				pstm.setFloat(2, pedido.getValor());
				mensagem.setMensagem("Pedido atualizado com sucesso! Aguarde pela aprovação do administrador!");
			}
			pstm.executeUpdate();
			
			cupom.setUsuario(pedido.getCliente().getUsuario());
			cupom.setValor(pedido.getValor());
			cupomDao.salvar(cupom);

			mensagem.setMensagemStatus(MensagemStatus.SUCESSO);
		}catch(SQLException e) {
			mensagem.setMensagem("Ocorreu um erro durante a operação. Tente novamente ou consulte a equipe de desenvolvimento.");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm);
		}
		
		return mensagem;
	}

	@Override
	public Mensagem atualizar(ADominio entidade) throws SQLException {
		Pedido pedido  = (Pedido) entidade;
		Estoque estoque = new Estoque();
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "UPDATE tb_pedido SET "
					+ "ped_statusPedido = ?,"
					+ "ped_dataHoraAtualizacao = NOW() "			
					+ " WHERE ped_id = ?";
		
		
		PreparedStatement pstm = null;
		
		if(pedido.getStatusPedido() == StatusPedido.APROVADO) {
			for(ItemPedido item : pedido.getItensPedido()) {
				estoque = new Estoque();
				estoque.setQuantidadeTotal(item.getQuantidade());	
				estoque.setProduto(item.getProduto());
				estoqueDao.deletar(estoque);
			}
		
		}
		

		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, pedido.getStatusPedido().toString());
			pstm.setLong(2, pedido.getId());

			pstm.executeUpdate();
			mensagem.setMensagem("Pedido atualizado com sucesso!");
			mensagem.setMensagemStatus(MensagemStatus.SUCESSO);
		}catch(SQLException e) {
			mensagem.setMensagem("Ocorreu um erro durante a operação. Tente novamente ou consulte a equipe de desenvolvimento.");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm);
		}
		
		return mensagem;
	}

	@Override
	public List<ADominio> consultar(ADominio entidade) throws SQLException {
		Pedido pedido = (Pedido) entidade;
		conexao = ConexaoFactory.getConnection();
		
		List<ADominio> pedidos = new ArrayList<ADominio>();

		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "ped_id, "
				+ "ped_valor,"
				+ "ped_statusPedido, "
				+ "ped_fpag_id, "
				+ "ped_fre_id, "
				+ "ped_cli_id,"
				+ "ped_dataHoraAtualizacao,"
				+ "ped_dataHoraCriacao,"
				+ "ped_ativo "
				+ " FROM tb_pedido "
				+ " WHERE ped_ativo = 1 ";
		if(pedido.getCliente() != null && pedido.getCliente().getId() != null) {
			sql += " AND ped_cli_id = " + pedido.getCliente().getId() + "";
		}
		if(pedido.getId() != null) {
			sql += " AND ped_id = " + pedido.getId() + "";
		}
		
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Pedido p = new Pedido();
			Frete f = new Frete();
			Cliente c = new Cliente();
			FormaPagamento fp = new FormaPagamento();
			ItemPedido itemPedido = new ItemPedido();
			
			List<ADominio> itensPedidoEntidade = new ArrayList<ADominio>();
			List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();

			while(rs.next()) {
				p = new Pedido();
				f = new Frete();
				c = new Cliente();
				fp = new FormaPagamento();
				
				itemPedido = new ItemPedido();
				itensPedidoEntidade = new ArrayList<ADominio>();
				itensPedido = new ArrayList<ItemPedido>();
				
				p.setId(rs.getLong("ped_id"));
				p.setValor(rs.getFloat("ped_valor"));
				p.setStatusPedido(StatusPedido.valueOf(rs.getString("ped_statusPedido")));
				p.setDataHoraCriacao(rs.getObject("ped_dataHoraCriacao", LocalDateTime.class));
				p.setDataHoraAtualizacao(rs.getObject("ped_dataHoraAtualizacao", LocalDateTime.class));
				p.setAtivo(rs.getBoolean("ped_ativo"));
				
				c.setId(rs.getLong("ped_cli_id"));
				p.setCliente((Cliente)clienteDao.consultar(c).get(0));
				
				f.setId(rs.getLong("ped_fre_id"));
				p.setFrete((Frete)freteDao.consultar(f).get(0));
				
				fp.setId(rs.getLong("ped_fpag_id"));
				p.setFormaPagamento((FormaPagamento)formaPagamentoDao.consultar(fp).get(0));
				
				itemPedido.setPedido(p);
				itensPedidoEntidade.addAll(itemPedidoDao.consultar(itemPedido));
				if(!itensPedidoEntidade.isEmpty()) {
					for(ADominio item : itensPedidoEntidade) {
						itensPedido.add((ItemPedido)item);
					}
				}
				p.setItensPedido(itensPedido);

				pedidos.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return pedidos;
	}

}
