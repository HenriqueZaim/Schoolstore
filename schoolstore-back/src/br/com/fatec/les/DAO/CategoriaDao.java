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
import br.com.fatec.les.model.produto.Categoria;
import br.com.fatec.les.model.produto.Produto;

public class CategoriaDao implements IDao{
	
    private Connection conexao = null;
    
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
		List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "cat_id, "
				+ "cat_nome "
				+ " FROM tb_categoria "
				+ " WHERE cat_ativo = 1 "
				+ " AND cat_pro_id = " + produto.getId() + "";

		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			while(rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setNome(rs.getString("cat_nome"));
				categoria.setId(rs.getLong("cat_id"));
				categorias.add(categoria);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return categorias;
	}

}