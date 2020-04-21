package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Imagem;
import br.com.fatec.les.model.Usuario;

public class UsuarioDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	ImagemDao imagemDao = new ImagemDao();
	
	public UsuarioDao() {
		conexao = ConexaoFactory.getConnection();
	}

	@Override
	public Mensagem atualizar(EntidadeDominio entidadeDominio) throws SQLException {
		Usuario usuario  = (Usuario) entidadeDominio;
		mensagem = new Mensagem();
		String sql = "UPDATE tb_usuario SET "
				+ "usu_email = ? "
				+ " WHERE usu_id = ?";
		
		PreparedStatement pstm = null;
		
		try {
			mensagem = imagemDao.atualizar(usuario.getImagem());
			if(mensagem.getMensagemStatus() == MensagemStatus.ERRO)
				return mensagem;
			
			mensagem = new Mensagem();
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, usuario.getEmail());
			pstm.setLong(2, usuario.getId());
			pstm.executeUpdate();
			mensagem.setMensagem("Usuário atualizado com sucesso!");
			mensagem.setMensagemStatus(MensagemStatus.SUCESSO);
		}catch(SQLException e) {
			mensagem.setMensagem("Ocorreu um erro durante a operação. Tente novamente ou consulte a equipe de desenvolvimento.");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
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
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT "
				+ "usu_id, "
				+ "usu_senha, "
				+ "usu_email, "
				+ "usu_ima_id "
				+ " FROM tb_usuario WHERE usu_ativo = 1 ";
		if(usuario.getId() != null) {
			sql += "AND usu_id = " + usuario.getId();
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Usuario u = new Usuario();
			Imagem i = new Imagem();
			
			while(rs.next()) {
				u = new Usuario();
				i = new Imagem();
				
				u.setId(Long.parseLong(rs.getString("usu_id")));
				u.setEmail(rs.getString("usu_email"));
				u.setSenha(rs.getString("usu_senha"));
				i.setId(Long.parseLong(rs.getString("usu_ima_id")));
				u.setImagem((Imagem)imagemDao.consultar(i).get(0));
				
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
	public Mensagem deletar(EntidadeDominio entidadeDominio) throws SQLException {
		Usuario usuario = (Usuario) entidadeDominio;
		mensagem = new Mensagem();
		String sql = "UPDATE tb_usuario SET "
				+ "usu_ativo = false"
				+ " WHERE usu_id = " + usuario.getId() + "";
		
		PreparedStatement pstm = null;
		
		try {
			if(imagemDao.deletar(usuario.getImagem()) == null)
				return null;
			
			pstm = conexao.prepareStatement(sql);
			pstm.executeUpdate();
			mensagem.setMensagem("Usuário deletado com sucesso!");
			mensagem.setMensagemStatus(MensagemStatus.SUCESSO);
		}catch(SQLException e) {
			mensagem.setMensagem("Ocorreu um erro durante a operação. Tente novamente ou consulte a equipe de desenvolvimento.");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm);
//		}
		
		return mensagem;
	}
	
	@Override
	public Mensagem salvar(EntidadeDominio entidadeDominio) throws SQLException {
		Usuario usuario = (Usuario) entidadeDominio;
		mensagem = new Mensagem();
		ResultSet rs;
		String sql = "INSERT INTO tb_usuario "
				+ "("
				+ "usu_email, "
				+ "usu_senha, "
				+ "usu_ima_id, "
				+ "usu_ativo, "
				+ "usu_dataHoraCriacao"
				+ ") "
				+ " VALUES ( ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			String idImagem = imagemDao.salvar(usuario.getImagem()).getMensagem();
			
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, usuario.getEmail());
			pstm.setString(2, usuario.getSenha());
			pstm.setInt(3, Integer.parseInt(idImagem));
			pstm.executeUpdate();
			
			rs = pstm.getGeneratedKeys();
			if (rs.next()){
				mensagem.setMensagem(Integer.toString(rs.getInt(1)));
				mensagem.setMensagemStatus(MensagemStatus.OPERACAO);
				return mensagem;
			}
						
		}catch(SQLException e){
			mensagem.setMensagem("Ocorreu um erro durante a operação. Tente novamente ou consulte a equipe de desenvolvimento.");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm);
//		}
		return mensagem;
	}
}
