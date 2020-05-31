package br.com.fatec.les.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.produto.Produto;
import br.com.fatec.les.model.usuario.Carrinho;
import br.com.fatec.les.model.usuario.ItemCarrinho;

public class ItemCarrinhoDao implements IDao{
	
    private Connection conexao = null;
    private Mensagem mensagem;
    ProdutoDao produtoDao = new ProdutoDao();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		ItemCarrinho itemCarrinho = (ItemCarrinho) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_itemCarrinho "
				+ "("
				+ "icar_quantidade, "
				+ "icar_car_id, "
				+ "icar_pro_id "
				+ ")"
				+ " VALUES ( ?, ?, ?)";
		
		PreparedStatement pstm = null;
		
		try {
		
			pstm = conexao.prepareStatement(sql);
			pstm.setFloat(1, itemCarrinho.getQuantidade());
			pstm.setLong(2, itemCarrinho.getCarrinho().getId());
			pstm.setLong(3, itemCarrinho.getProduto().getId());
			pstm.executeUpdate();

			mensagem.setMensagem("Deu bom");
			mensagem.setMensagemStatus(MensagemStatus.SUCESSO);
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
		ItemCarrinho itemCarrinho = (ItemCarrinho) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();

		String sql = "DELETE FROM tb_itemCarrinho ";
		
		if(itemCarrinho.getCarrinho() != null && itemCarrinho.getCarrinho().getId() != null) {
			sql += " WHERE icar_car_id = " + itemCarrinho.getCarrinho().getId() + "";
		}else if(itemCarrinho.getProduto() != null && itemCarrinho.getProduto().getId() != null) {
			sql += " WHERE icar_pro_id = " + itemCarrinho.getProduto().getId() + "";
		}else {
			mensagem.setMensagem("Operação proibida");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
			return mensagem;
		}
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);

			pstm.executeUpdate();
			mensagem.setMensagem("Itens do Carrinho deletados com sucesso!");
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
		ItemCarrinho itemCarrinho = (ItemCarrinho) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "UPDATE tb_itemCarrinho SET "
				+ "icar_quantidade = icar_quantidade + 1 "
				+ " WHERE icar_pro_id = ?";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);			
			pstm.setLong(1, itemCarrinho.getProduto().getId());

			pstm.executeUpdate();
			mensagem.setMensagem("Produto adicionado com sucesso!");
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
		ItemCarrinho itemCarrinho = (ItemCarrinho) entidade;
		conexao = ConexaoFactory.getConnection();

		List<ADominio> itensCarrinho = new ArrayList<ADominio>();
		List<ADominio> produtoEntidade = new ArrayList<ADominio>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "icar_quantidade, "
				+ "icar_pro_id "
				+ " FROM tb_itemCarrinho WHERE icar_car_id = " + itemCarrinho.getCarrinho().getId() + "";
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			ItemCarrinho ic = new ItemCarrinho();
			Produto p = new Produto();

			while(rs.next()) {
				ic = new ItemCarrinho();
				p = new Produto();
				
				produtoEntidade = new ArrayList<ADominio>();
				
				ic.setQuantidade(Integer.parseInt(rs.getString("icar_quantidade")));
				p.setId(Long.parseLong(rs.getString("icar_pro_id")));

				produtoEntidade.addAll(produtoDao.consultar(p));
				
				ic.setProduto((Produto) produtoEntidade.get(0));
				
				itensCarrinho.add(ic);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return itensCarrinho; // Vai retornar o carrinho, com os itens e seus dados
	}

}
