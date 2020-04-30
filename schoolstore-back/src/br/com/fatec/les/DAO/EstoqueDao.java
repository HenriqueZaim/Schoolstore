package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.estoque.Estoque;
import br.com.fatec.les.model.produto.Produto;

public class EstoqueDao implements IDao{
	 
	private Connection conexao = null;
	ItemEstoqueDao itemEstoqueDao = new ItemEstoqueDao();

	@Override
	public Mensagem salvar(EntidadeDominio entidadeDominio) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");

	}

	@Override
	public Mensagem deletar(EntidadeDominio entidadeDominio) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");

	}

	@Override
	public Mensagem atualizar(EntidadeDominio entidadeDominio) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");

	}

	@Override
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
		Produto produto = (Produto) entidade;
		conexao = ConexaoFactory.getConnection();
		List<EntidadeDominio> estoques = new ArrayList<EntidadeDominio>();

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
