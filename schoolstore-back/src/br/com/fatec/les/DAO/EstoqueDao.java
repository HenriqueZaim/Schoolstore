package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.estoque.Estoque;
import br.com.fatec.les.model.pedido.Pedido;
import br.com.fatec.les.model.produto.Produto;

public class EstoqueDao implements IDao{
	 
	private Connection conexao = null;
	private Mensagem mensagem;
	ItemEstoqueDao itemEstoqueDao = new ItemEstoqueDao();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");

	}

	@Override
	public Mensagem deletar(ADominio entidade) throws SQLException {
		Estoque estoque = (Estoque) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "UPDATE tb_estoque SET "
				+ "sto_quantidadeTotal = sto_quantidadeTotal - ? "			
				+ " WHERE sto_pro_id = ?";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setInt(1, estoque.getQuantidadeTotal());
			pstm.setLong(2, estoque.getProduto().getId());

			pstm.executeUpdate();
			mensagem.setMensagem("Estoque atualizado com sucesso!");
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
		Estoque estoque = (Estoque) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "UPDATE tb_estoque SET "
				+ "sto_quantidadeTotal = sto_quantidadeTotal + ? "			
				+ " WHERE sto_pro_id = ?";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setInt(1, estoque.getQuantidadeTotal());
			pstm.setLong(2, estoque.getProduto().getId());

			pstm.executeUpdate();
			mensagem.setMensagem("Estoque atualizado com sucesso!");
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
		Produto produto = (Produto) entidade;
		conexao = ConexaoFactory.getConnection();
		List<ADominio> estoques = new ArrayList<ADominio>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "sto_id, "
				+ "sto_quantidadeTotal "
				+ " FROM tb_estoque "
				+ " WHERE sto_pro_id = " + produto.getId() + "";

		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			if(rs.next()) {
				Estoque estoque = new Estoque();
				Produto aux  = new Produto();
				estoque.setQuantidadeTotal(rs.getInt("sto_quantidadeTotal"));
//				estoque.setProduto(produto);
				long idSto = rs.getLong("sto_id");
				
				Produto aux2 = new Produto();
				aux2 = (Produto) itemEstoqueDao.consultarItem(idSto).get(0);
				estoque.setItensEstoque(aux2.getEstoque().getItensEstoque());
				
				aux.setEstoque(estoque);
				estoques.add(aux);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return estoques;
	}

}
