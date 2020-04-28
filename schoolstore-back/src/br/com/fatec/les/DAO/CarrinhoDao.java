package br.com.fatec.les.DAO;

import java.util.ArrayList;
import java.util.List;


import java.sql.*;
import java.time.LocalDateTime;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.usuario.Carrinho;
import br.com.fatec.les.model.usuario.ItemCarrinho;

public class CarrinhoDao implements IDao{
	
    private Connection conexao = null;
    private Mensagem mensagem;
    ItemCarrinhoDao itemCarrinhoDao = new ItemCarrinhoDao();

    public CarrinhoDao() {
        conexao = ConexaoFactory.getConnection();
    }

	@Override
	public Mensagem salvar(EntidadeDominio entidadeDominio) throws SQLException {
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		ResultSet rs;
		
		String sql = "INSERT INTO tb_carrinho "
				+ "("
				+ "car_subTotal, "
				+ "car_validade, "
				+ "car_ativo, "
				+ "car_dataHoraCriacao "
				+ ")"
				+ " VALUES ( ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
		
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, null);
			pstm.setString(2, null);
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
	public Mensagem deletar(EntidadeDominio entidadeDominio) throws SQLException {
		Carrinho carrinho  = (Carrinho) entidadeDominio;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();

		String sql = "UPDATE tb_carrinho SET "
				+ "car_ativo = false"
				+ " WHERE car_id = " + carrinho.getId() + "";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			
			Carrinho aux = new Carrinho();
			aux.setId(carrinho.getId());
			// pra excluir tudo, só vou mandar o id do carrinho
			if(itemCarrinhoDao.deletar(aux).getMensagemStatus() == MensagemStatus.ERRO) {
				throw new SQLException();
			}

			pstm.executeUpdate();
			mensagem.setMensagem("Carrinho deletado com sucesso!");
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
	public Mensagem atualizar(EntidadeDominio entidadeDominio) throws SQLException {
		Carrinho carrinho  = (Carrinho) entidadeDominio;
		Carrinho aux = new Carrinho();
		List<ItemCarrinho> itensCarrinho = new ArrayList<ItemCarrinho>();
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "UPDATE tb_carrinho SET "
				+ "car_subTotal = ?, "
				+ "car_validade = "+ carrinho.getValidade() +" "				
				+ " WHERE car_id = ?";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setFloat(1, carrinho.getSubTotal());
			pstm.setLong(2, carrinho.getId());
			
//			preciso pegar todos os itens desse carrinho no banco
			List<EntidadeDominio> itemsBanco = new ArrayList<EntidadeDominio>();
			itemsBanco.addAll(itemCarrinhoDao.consultar(entidadeDominio)); // Consultar os itens pelo id do carrinho: retorna 1 carrinho apenas
			aux = (Carrinho) itemsBanco.get(0);
		
			for(ItemCarrinho i : aux.getItensCarrinho()) { // items do banco
				boolean flag = false;
				for(ItemCarrinho j : carrinho.getItensCarrinho() ) { // items do front
					if(i.getProduto().getId() == j.getProduto().getId()) {
						flag = true;
						if(i.getQuantidade() != j.getQuantidade()) {
							Carrinho c = new Carrinho();
							itensCarrinho = new ArrayList<ItemCarrinho>();
							itensCarrinho.add(j);
							c.setId(carrinho.getId());
							c.setItensCarrinho(itensCarrinho);
							itemCarrinhoDao.atualizar(c);
						}
					}else {
						// não é este item
						
					}
				}
				if(!flag) { // não achei esse item no front
					Carrinho c = new Carrinho();
					itensCarrinho = new ArrayList<ItemCarrinho>();
					itensCarrinho.add(i);
					c.setId(carrinho.getId());
					c.setItensCarrinho(itensCarrinho);
					itemCarrinhoDao.deletar(c);
				}
			}
						
			pstm.executeUpdate();
			mensagem.setMensagem("Carrinho atualizado com sucesso!");
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
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
		// Devo ter sempre o id do carrinho aqui
		Carrinho carrinho = (Carrinho) entidade;
		conexao = ConexaoFactory.getConnection();
		List<EntidadeDominio> carrinhos = new ArrayList<EntidadeDominio>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "car_id, "
				+ "car_subTotal, "
				+ "car_validade "
				+ " FROM tb_carrinho "
				+ " WHERE car_ativo = 1 "
				+ " AND car_id = " + carrinho.getId() + "";
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			if(rs.next()) {
				Carrinho c = new Carrinho();
				Carrinho aux = new Carrinho();
				
				c.setId(Long.parseLong(rs.getString("car_id")));
				c.setSubTotal(Float.parseFloat(rs.getString("car_subTotal")));
//				c.setValidade(LocalDateTime.parse(rs.getString("car_validade")));
				
				List<EntidadeDominio> itemsBanco = new ArrayList<EntidadeDominio>();
				itemsBanco.addAll(itemCarrinhoDao.consultar(c)); 
				aux = (Carrinho) itemsBanco.get(0);
				
				c.setItensCarrinho(aux.getItensCarrinho());

				carrinhos.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return carrinhos;
	}

}
