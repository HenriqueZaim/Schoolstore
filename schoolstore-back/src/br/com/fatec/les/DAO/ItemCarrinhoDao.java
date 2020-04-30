package br.com.fatec.les.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.produto.Produto;
import br.com.fatec.les.model.usuario.Carrinho;
import br.com.fatec.les.model.usuario.ItemCarrinho;

public class ItemCarrinhoDao implements IDao{
	
    private Connection conexao = null;
    private Mensagem mensagem;
    ProdutoDao produtoDao = new ProdutoDao();

	@Override
	public Mensagem salvar(EntidadeDominio entidadeDominio) throws SQLException {
		Carrinho carrinho = (Carrinho) entidadeDominio;
		mensagem = new Mensagem();
		ResultSet rs;
		
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
			pstm.setFloat(1, carrinho.getItensCarrinho().get(0).getQuantidade());
			pstm.setLong(2, carrinho.getId());
			pstm.setLong(3, carrinho.getItensCarrinho().get(0).getProduto().getId());
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
	public Mensagem deletar(EntidadeDominio entidadeDominio) throws SQLException {
		Carrinho carrinho  = (Carrinho) entidadeDominio;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();

		String sql = "DELETE FROM tb_itemCarrinho "
				+ " WHERE icar_car_id = " + carrinho.getId() + "";
		
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
	public Mensagem atualizar(EntidadeDominio entidadeDominio) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
		Carrinho carrinho = (Carrinho) entidade;
		conexao = ConexaoFactory.getConnection();

		List<ItemCarrinho> itensCarrinho = new ArrayList<ItemCarrinho>();
		List<EntidadeDominio> produtoEntidade = new ArrayList<EntidadeDominio>();
		List<EntidadeDominio> carrinhos = new ArrayList<EntidadeDominio>(); // apenas para retorno

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "icar_quantidade, "
				+ "icar_pro_id "
				+ " FROM tb_itemCarrinho WHERE icar_car_id = " + carrinho.getId() + "";
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			ItemCarrinho ic = new ItemCarrinho();
			Produto p = new Produto();

			while(rs.next()) {
				ic = new ItemCarrinho();
				p = new Produto();
				
				produtoEntidade = new ArrayList<EntidadeDominio>();
				
				ic.setQuantidade(Integer.parseInt(rs.getString("icar_quantidade")));
				p.setId(Long.parseLong(rs.getString("icar_pro_id")));

				produtoEntidade.addAll(produtoDao.consultar(p));
				
				ic.setProduto((Produto) produtoEntidade.get(0));
				
				itensCarrinho.add(ic);
			}
			carrinho.setItensCarrinho(itensCarrinho);
			carrinhos.add(carrinho);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return carrinhos; // Vai retornar o carrinho, com os itens e seus dados
	}

}
