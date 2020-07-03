package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.estoque.Fornecedor;

public class FornecedorDao implements IDao{
	
	private Connection conexao = null;

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public Mensagem deletar(ADominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public Mensagem atualizar(ADominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public List<ADominio> consultar(ADominio entidade) throws SQLException {
		Fornecedor fornecedor = (Fornecedor) entidade;
		conexao = ConexaoFactory.getConnection();
		List<ADominio> fornecedores = new ArrayList<ADominio>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "for_id, "
				+ "for_cnpj, "
				+ "for_nome "
				+ " FROM tb_fornecedor "
				+ " WHERE for_id = " + fornecedor.getId() + "";

		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			if(rs.next()) {
				Fornecedor f = new Fornecedor();
				f.setId(rs.getLong("for_id"));
				f.setCnpj(rs.getString("for_cnpj"));
				f.setNome(rs.getString("for_nome"));
				fornecedores.add(f);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return fornecedores;
	}

}
