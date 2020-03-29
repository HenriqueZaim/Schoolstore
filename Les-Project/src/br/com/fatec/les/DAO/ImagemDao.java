package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Imagem;
import br.com.fatec.les.model.Usuario;

public class ImagemDao implements IDao{

	private Connection conexao = null;
	private String mensagem = null;
	
	public ImagemDao() {
		conexao = ConexaoFactory.getConnection();
	}
	
	@Override
	public String salvar(EntidadeDominio entidadeDominio) throws SQLException {
		Imagem imagem = (Imagem) entidadeDominio;
		ResultSet rs;
		String sql = "INSERT INTO tb_imagem "
				+ "("
				+ "ima_foto, "
				+ "ima_descricao, "
				+ "ima_ativo, "
				+ "ima_dataHoraCriacao"
				+ ") "
				+ " VALUES ( ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, imagem.getFoto());
			pstm.setString(2, imagem.getDescricao());
			pstm.executeUpdate();
			
			rs = pstm.getGeneratedKeys();
			if (rs.next()){
				return Integer.toString(rs.getInt(1));
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
