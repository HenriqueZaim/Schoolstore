package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;

public class UsuarioDao implements IDao{
	
	private Connection conexao = null;
	private String mensagem = null;
	
	public UsuarioDao() {
		conexao = ConexaoFactory.getConnection();
	}

	@Override
	public String atualizar(EntidadeDominio entidadeDominio) throws SQLException {
		Usuario usuario  = (Usuario) entidadeDominio;
		String sql = "UPDATE usuarios SET "
				+ "usu_senha = '" + usuario.getSenha() + "',"
				+ "usu_email = '" + usuario.getEmail() + "',"
				+ " WHERE usu_id = " + usuario.getId() + "";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.executeUpdate();
			mensagem = "Usuário atualizado com sucesso";
		}catch(SQLException e) {
			mensagem = e.getMessage();
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm);
//		}
		
		return mensagem;
	}

	@Override
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
		Usuario usuario = (Usuario) entidade;
		
		List<EntidadeDominio> usuarios = new ArrayList<EntidadeDominio>();
		Usuario u = new Usuario();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT "
				+ "usu_id, "
				+ "usu_nome, "
				+ "usu_email, "
				+ "usu_numeroTelefone, "
				+ "usu_numeroDocumento "
				+ " FROM usuarios WHERE usu_ativo = 1 ";
		if(usuario.getId() != null) {
			sql += "AND usu_id = " + usuario.getId();
		}
		
		// TODO: Fazer verificação de filtro aqui
		
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				u = new Usuario();
				u.setId(Long.parseLong(rs.getString("usu_id")));
				u.setEmail(rs.getString("usu_email"));
				
				usuarios.add(u);
			}
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm, rs);
//		}
		
		return usuarios;
	}
	
	@Override
	public String deletar(EntidadeDominio entidadeDominio) throws SQLException {
		Usuario usuario  = (Usuario) entidadeDominio;
		String sql = "UPDATE usuarios SET "
				+ "usu_ativo = false"
				+ " WHERE usu_id = " + usuario.getId() + "";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.executeUpdate();
			mensagem = "Usuário deletado com sucesso";
		}catch(SQLException e) {
			mensagem = e.getMessage();
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm);
//		}
		
		return mensagem;
	}
	
	@Override
	public String salvar(EntidadeDominio entidadeDominio) throws SQLException {
		Usuario usuario = (Usuario) entidadeDominio;
		ResultSet rs;
		String sql = "INSERT INTO usuarios "
				+ "("
				+ "usu_email, "
				+ "usu_senha, "
				+ "usu_ativo, "
				+ "usu_dataHoraCriacao"
				+ ") "
				+ " VALUES ( ?, ?, true, NOW())";
		String sql2 = "SELECT MAX(usu_id) FROM usuarios";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, usuario.getEmail());
			pstm.setString(2, usuario.getSenha());
			pstm.executeUpdate();
			
			pstm = conexao.prepareStatement(sql2);
			rs = pstm.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("MAX(usu_id)"));
				return rs.getString("MAX(usu_id)");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm);
//		}
		return null;
	}
}
