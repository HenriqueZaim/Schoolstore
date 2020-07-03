package br.com.fatec.les.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.config.Imagem;

public class ImagemDao implements IDao{

	private Connection conexao = null;
	private Mensagem mensagem;
	
	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		Imagem imagem = (Imagem) entidade;
		conexao = ConexaoFactory.getConnection();
		ResultSet rs;
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_imagem "
				+ "("
				+ "ima_nome, "
				+ "ima_descricao, "
				+ "ima_caminho, "
				+ "ima_ativo, "
				+ "ima_dataHoraCriacao"
				+ ") "
				+ " VALUES ( ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, imagem.getFoto());
			pstm.setString(2, imagem.getDescricao());
			pstm.setString(3, imagem.getCaminho());
			pstm.executeUpdate();
			
			rs = pstm.getGeneratedKeys();
			if (rs.next()){
				mensagem.setMensagem(Integer.toString(rs.getInt(1)));
				mensagem.setMensagemStatus(MensagemStatus.OPERACAO);
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

	@Override
	public Mensagem deletar(ADominio entidade) throws SQLException {
		Imagem imagem = (Imagem) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		String sql = "UPDATE tb_imagem SET "
				+ "ima_ativo = false"
				+ " WHERE ima_id = " + imagem.getId() + "";
		
		PreparedStatement pstm = null;
		
		try {		
			pstm = conexao.prepareStatement(sql);
			pstm.executeUpdate();
			mensagem.setMensagem("Imagem deletada com sucesso!");
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
		Imagem imagem = (Imagem) entidade;
		mensagem = new Mensagem();
		conexao = ConexaoFactory.getConnection();
		String sql = "UPDATE tb_imagem SET "
				+ "ima_nome = ?, "
				+ "ima_descricao = ?,"
				+ "ima_caminho = ? "
				+ " WHERE ima_id = ? "
				+ " AND ima_ativo = 1";
		
		PreparedStatement pstm = null;
		
		try {		
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, imagem.getFoto());
			pstm.setString(2, imagem.getDescricao());
			pstm.setString(3, imagem.getCaminho());
			pstm.setLong(4, imagem.getId());
			pstm.executeUpdate();
			mensagem.setMensagem("Imagem atualizada com sucesso!");
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
		Imagem imagem = (Imagem) entidade;
		conexao = ConexaoFactory.getConnection();
		List<ADominio> imagens = new ArrayList<ADominio>();
				
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT "
				+ "ima_id, "
				+ "ima_nome, "
				+ "ima_descricao,"
				+ "ima_caminho "
				+ " FROM tb_imagem WHERE ima_ativo = 1 ";
		if(imagem.getId() != null) {
			sql += "AND ima_id = " + imagem.getId();
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Imagem i = new Imagem();
			
			while(rs.next()) {
				i = new Imagem();
				
				i.setId(Long.parseLong(rs.getString("ima_id")));
				i.setFoto(rs.getString("ima_nome"));
				i.setDescricao(rs.getString("ima_descricao"));
				i.setCaminho(rs.getString("ima_caminho"));

				imagens.add(i);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return imagens;
	}

}
