package br.com.fatec.les.DAO;

import java.util.ArrayList;
import java.util.List;


import java.sql.*;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.usuario.Carrinho;
import br.com.fatec.les.model.usuario.ItemCarrinho;

public class CarrinhoDao implements IDao{
	
    private Connection conexao = null;
    private Mensagem mensagem;
    ItemCarrinhoDao itemCarrinhoDao = new ItemCarrinhoDao();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
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
			pstm.setFloat(1, 0);
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
	public Mensagem deletar(ADominio entidade) throws SQLException {
		Carrinho carrinho  = (Carrinho) entidade;
		ItemCarrinho itemCarrinho = new ItemCarrinho();
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();

		String sql = "UPDATE tb_carrinho SET "
				+ "car_ativo = false,"
				+ "car_subTotal = 0"
				+ " WHERE car_id = " + carrinho.getId() + "";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			
			itemCarrinho.setCarrinho(carrinho);

			if(itemCarrinhoDao.deletar(itemCarrinho).getMensagemStatus() == MensagemStatus.ERRO) {
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
	public Mensagem atualizar(ADominio entidade) throws SQLException {
		Carrinho carrinho  = (Carrinho) entidade;
		ItemCarrinho itemCarrinho = new ItemCarrinho();
		itemCarrinho.setCarrinho(carrinho);
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "UPDATE tb_carrinho SET "
				+ "car_subTotal = ?, "
				+ "car_validade = NOW() + INTERVAL 10 DAY "		
				+ " WHERE car_id = ?";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			
			Carrinho carrinhoAux = new Carrinho();
			carrinhoAux = (Carrinho)consultar(carrinho).get(0);
			if(carrinho.getValidade() != null) {
				itemCarrinhoDao.deletar(itemCarrinho);
				pstm.setFloat(1, carrinho.getSubTotal());
			}else {
				Float valorFinal;
				if(!carrinho.getItensCarrinho().isEmpty()) {
					valorFinal = carrinhoAux.getSubTotal() + carrinho.getSubTotal();
				}else {
					valorFinal = carrinho.getSubTotal();
				}
				
				pstm.setFloat(1, valorFinal);
			}
			
			pstm.setLong(2, carrinho.getId());
			
			if(!carrinho.getItensCarrinho().isEmpty() && carrinho.getItensCarrinho() != null) {
				if(!carrinhoAux.getItensCarrinho().isEmpty() && carrinhoAux.getItensCarrinho() != null) {
					for(ItemCarrinho itemBanco : carrinhoAux.getItensCarrinho()) {
						for(ItemCarrinho item : carrinho.getItensCarrinho()) {
							if(itemBanco.getProduto().getId() == item.getProduto().getId()) {
								itemCarrinhoDao.atualizar(item);
							}
						}
					}
				}
				for(ItemCarrinho item : carrinho.getItensCarrinho()) {
					item.setCarrinho(carrinho);
					itemCarrinhoDao.salvar(item);
				}
			}else {
				ItemCarrinho itemCa = new ItemCarrinho();
				itemCa.setCarrinho(carrinho);
				itemCarrinhoDao.deletar(itemCa);
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
	public List<ADominio> consultar(ADominio entidade) throws SQLException {
		Carrinho carrinho = (Carrinho) entidade;
		ItemCarrinho itemCarrinho = new ItemCarrinho();
		itemCarrinho.setCarrinho(carrinho);
		conexao = ConexaoFactory.getConnection();
		List<ADominio> carrinhos = new ArrayList<ADominio>();

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
				
				c.setId(rs.getLong("car_id"));
				c.setSubTotal(rs.getFloat("car_subTotal"));
//				c.setValidade(LocalDateTime.parse(rs.getString("car_validade")));
				
				List<ADominio> itemsBanco = new ArrayList<ADominio>();
				List<ItemCarrinho> itemsCarrinho = new ArrayList<ItemCarrinho>();
				itemsBanco.addAll(itemCarrinhoDao.consultar(itemCarrinho)); 
				for(ADominio item : itemsBanco)
					itemsCarrinho.add((ItemCarrinho)item);
				
				c.setItensCarrinho(itemsCarrinho);

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
