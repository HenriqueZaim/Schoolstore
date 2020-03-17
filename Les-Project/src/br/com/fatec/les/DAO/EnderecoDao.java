package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.model.Endereco;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;

public class EnderecoDao implements IDao{
	
	private Connection conexao = null;
	private String mensagem = null;
	
	public EnderecoDao() {
		conexao = ConexaoFactory.getConnection();
	}

	@Override
	public String salvar(EntidadeDominio entidadeDominio) throws SQLException {
		Endereco endereco = (Endereco) entidadeDominio;
		ResultSet rs;
		String sql = "INSERT INTO enderecos "
				+ "("
				+ "end_logradouro, "
				+ "end_bairro, "
				+ "end_cep, "
				+ "end_numero, "
				+ "end_complemento, "
				+ "end_referencia, "
				+ "end_favorito, "
				+ "end_cid_id, "
				+ "end_ativo, "
				+ "end_dataHoraCriacao"
				+ ") "
				+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, true, NOW())";
		String sql2 = "SELECT MAX(end_id) FROM enderecos";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, endereco.getLogradouro());
			pstm.setString(2, endereco.getBairro());
			pstm.setString(3, endereco.getCep());
			pstm.setInt(4, endereco.getNumero());
			pstm.setString(5, endereco.getComplemento());
			pstm.setString(6, endereco.getReferencia());
			pstm.setBoolean(7, endereco.isFavorito());
			pstm.setLong(8, endereco.getCidade().getId());
			pstm.executeUpdate();
			
			pstm = conexao.prepareStatement(sql2);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString("MAX(end_id)"));
				return rs.getString("MAX(end_id)");
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm);
//		}
		return null;
	}

	@Override
	public String deletar(EntidadeDominio entidadeDominio) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String atualizar(EntidadeDominio entidadeDominio) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
