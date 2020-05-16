package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pagamento.FormaPagamento;
import br.com.fatec.les.model.pedido.Frete;
import br.com.fatec.les.model.pedido.ItemPedido;
import br.com.fatec.les.model.pedido.Pedido;
import br.com.fatec.les.model.usuario.Cliente;

public class PedidoDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	FreteDao freteDao = new FreteDao();
	FormaPagamentoDAO formaPagamentoDao = new FormaPagamentoDAO();
	ItemPedidoDao itemPedidoDao = new ItemPedidoDao();

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
				+ "ped_dataHoraCriacao "
				+ ") "
				+ " VALUES ( ?, ?, ?, ?, ?, true, NOW())";
		
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
			
			rs = pstm.getGeneratedKeys();
			if (rs.next()){
				mensagem.setMensagem(Integer.toString(rs.getInt(1)));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mensagem atualizar(ADominio entidade) throws SQLException {
		return null;
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
				+ "ped_valor, "
				+ "ped_fpag_id, "
				+ "ped_fre_id, "
				+ "ped_cli_id "
				+ " FROM tb_pedido "
				+ " WHERE ped_ativo = 1 ";
		if(pedido.getCliente().getId() != null) {
			sql += " AND ped_cli_id = " + pedido.getCliente().getId() + "";
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
				c.setId(rs.getLong("ped_cli_id"));
				
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
