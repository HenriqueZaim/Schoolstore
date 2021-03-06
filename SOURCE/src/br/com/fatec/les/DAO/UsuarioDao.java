package br.com.fatec.les.DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.config.Imagem;
import br.com.fatec.les.model.pagamento.cupom.Cupom;
import br.com.fatec.les.model.usuario.Usuario;

public class UsuarioDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	ImagemDao imagemDao = new ImagemDao();
	CupomDao cupomDao = new CupomDao();
	
	@Override
	public Mensagem atualizar(ADominio entidade) throws SQLException {
		Usuario usuario  = (Usuario) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		PreparedStatement pstm = null;
		
		try {
			if(usuario.getSenha() != null) {
				String sql = "UPDATE tb_usuario SET "
						+ "usu_senha = ? "
						+ " WHERE usu_id = ?";
				pstm = conexao.prepareStatement(sql);
				pstm.setString(1, usuario.getSenha());
				pstm.setLong(2, usuario.getId());
				pstm.executeUpdate();
				mensagem.setMensagem("Senha atualizada com sucesso!");
				mensagem.setMensagemStatus(MensagemStatus.SUCESSO);
			}else {
				String sql = "UPDATE tb_usuario SET "
						+ "usu_email = ? "
						+ " WHERE usu_id = ?";
				
				if(usuario.getImagem().getId() == null) {
					mensagem = imagemDao.atualizar(usuario.getImagem());
					if(mensagem.getMensagemStatus() == MensagemStatus.ERRO)
						return mensagem;
				}
				pstm = conexao.prepareStatement(sql);
				pstm.setString(1, usuario.getEmail());
				pstm.setLong(2, usuario.getId());
				pstm.executeUpdate();
				mensagem.setMensagem("Usuário atualizado com sucesso!");
				mensagem.setMensagemStatus(MensagemStatus.SUCESSO);
			}

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
		Usuario usuario = (Usuario) entidade;
		conexao = ConexaoFactory.getConnection();
		List<ADominio> usuarios = new ArrayList<ADominio>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT "
				+ "usu_id, "
				+ "usu_senha, "
				+ "usu_email, "
				+ "usu_ima_id,"
				+ "usu_admin,"
				+ "usu_ativo,"
				+ "usu_dataHoraCriacao,"
				+ "usu_dataHoraAtualizacao "
				+ " FROM tb_usuario WHERE usu_ativo = 1 ";
		if(usuario.getId() != null) {
			sql += " AND usu_id = " + usuario.getId();
		}
		if(usuario.getEmail() != null) {
			sql += " AND usu_email = '" + usuario.getEmail() + "' ";
		}
		if(usuario.getSenha() != null) {
			sql += " AND usu_senha = '" + usuario.getSenha() + "' ";
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Usuario u = new Usuario();
			Imagem i = new Imagem();
			Cupom ct = new Cupom();
			List<ADominio> cuponsEntidade = new ArrayList<ADominio>();
			List<Cupom> cupons = new ArrayList<Cupom>();
			
			while(rs.next()) {
				u = new Usuario();
				i = new Imagem();
				ct = new Cupom();
				cuponsEntidade = new ArrayList<ADominio>();
				cupons = new ArrayList<Cupom>();
				
				u.setId(Long.parseLong(rs.getString("usu_id")));
				u.setEmail(rs.getString("usu_email"));
				u.setSenha(rs.getString("usu_senha"));
				u.setAdmin(rs.getBoolean("usu_admin"));
				i.setId(rs.getLong("usu_ima_id"));
				u.setImagem((Imagem)imagemDao.consultar(i).get(0));
				u.setAtivo(rs.getBoolean("usu_ativo"));
				u.setDataHoraCriacao(rs.getObject("usu_dataHoraCriacao", LocalDateTime.class));
				u.setDataHoraAtualizacao(rs.getObject("usu_dataHoraAtualizacao", LocalDateTime.class));
				
				ct.setUsuario(u);
				cuponsEntidade.addAll(cupomDao.consultar(ct));
				for(ADominio item : cuponsEntidade) {
					cupons.add((Cupom)item);
				}
				u.setCupons(cupons);
				
				usuarios.add(u);
			}
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return usuarios;
	}
	
	@Override
	public Mensagem deletar(ADominio entidade) throws SQLException {
		Usuario usuario = (Usuario) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		String sql = "UPDATE tb_usuario SET "
				+ "usu_ativo = false"
				+ " WHERE usu_id = " + usuario.getId() + " AND usu_id != 1 ";
		
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
		finally {
			ConexaoFactory.closeConnection(conexao, pstm);
		}
		
		return mensagem;
	}
	
	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		Usuario usuario = (Usuario) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		ResultSet rs;
		String sql = "INSERT INTO tb_usuario "
				+ "("
				+ "usu_email, "
				+ "usu_senha, "
				+ "usu_ima_id, "
				+ "usu_admin, "
				+ "usu_ativo, "
				+ "usu_dataHoraCriacao"
				+ ") "
				+ " VALUES ( ?, ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			String idImagem = imagemDao.salvar(usuario.getImagem()).getMensagem();
			
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, usuario.getEmail());
			pstm.setString(2, usuario.getSenha());
			pstm.setInt(3, Integer.parseInt(idImagem));
			pstm.setBoolean(4, usuario.isAdmin());
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
		finally {
			ConexaoFactory.closeConnection(conexao, pstm);
		}
		return mensagem;
	}
}
