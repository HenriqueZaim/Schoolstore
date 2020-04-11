package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Imagem;

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
				+ "ima_nome, "
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
		Imagem imagem = (Imagem) entidadeDominio;
		String sql = "UPDATE tb_imagem SET "
				+ "ima_ativo = false"
				+ " WHERE ima_id = " + imagem.getId() + "";
		
		PreparedStatement pstm = null;
		
		try {		
			pstm = conexao.prepareStatement(sql);
			pstm.executeUpdate();
			mensagem = "Imagem deletada com sucesso";
		}catch(SQLException e) {
			mensagem = e.getMessage();
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm);
//		}
		
		return mensagem;
	}

	@Override
	public String atualizar(EntidadeDominio entidadeDominio) throws SQLException {
		Imagem imagem = (Imagem) entidadeDominio;
		String sql = "UPDATE tb_imagem SET "
				+ "ima_nome = ?, "
				+ "ima_descricao = ? "
				+ " WHERE ima_id = " + imagem.getId() + " AND ima_ativo = 1";
		
		PreparedStatement pstm = null;
		
		try {		
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, imagem.getFoto());
			pstm.setString(2, imagem.getDescricao());
			pstm.setLong(3, imagem.getId());
			pstm.executeUpdate();
			mensagem = "Imagem atualizada com sucesso";
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
		Imagem imagem = (Imagem) entidade;
		
		List<EntidadeDominio> imagens = new ArrayList<EntidadeDominio>();
				
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT "
				+ "ima_id, "
				+ "ima_nome, "
				+ "ima_descricao "
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

				imagens.add(i);
			}
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm, rs);
//		}
		
		return imagens;
	}

}
