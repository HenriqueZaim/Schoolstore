package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pagamento.cupom.Cupom;
import br.com.fatec.les.model.usuario.Usuario;

public class CupomDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		Cupom cupom = (Cupom) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_cupom "
				+ "("
				+ "cup_valor, "
				+ "cup_usu_id, "
				+ "cup_ativo, "
				+ "cup_dataHoraCriacao,"
				+ "cup_dataHoraVencimento,"
				+ "cup_cupomPromocional "
				+ ") "
				+ " VALUES ( ?, ?, true, NOW(), NOW(), ?)";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setFloat(1, cupom.getValor());
			pstm.setLong(2, cupom.getUsuario().getId());
			pstm.setBoolean(3, cupom.isCupomPromocional());
			pstm.executeUpdate();

			mensagem.setMensagem("Novo cupom adicionado");
			mensagem.setMensagemStatus(MensagemStatus.OPERACAO);
					
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
		Cupom cupom = (Cupom) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "UPDATE tb_cupom SET "
				+ "cup_ativo = false "			
				+ " WHERE cup_id = ?";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setLong(1, cupom.getId());

			pstm.executeUpdate();
			mensagem.setMensagem("Cupom promocional excluído com sucesso!");
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
		Cupom cupom = (Cupom) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "UPDATE tb_cupom SET "
				+ "cup_ativo = true "			
				+ " WHERE cup_id = ?";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setLong(1, cupom.getId());

			pstm.executeUpdate();
			mensagem.setMensagem("Cupom restaurado com sucesso!");
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
		Cupom cupom = (Cupom) entidade;
		conexao = ConexaoFactory.getConnection();
		List<ADominio> cupons = new ArrayList<ADominio>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT "
				+ "cup_id, "
				+ "cup_valor,"
				+ "cup_cupomPromocional,"
				+ "cup_dataHoraVencimento, "
				+ "cup_usu_id"
				+ " FROM tb_cupom WHERE cup_ativo = 1";
		if(cupom.getUsuario() != null && cupom.getUsuario().getId() != null) {
			sql += " AND cup_usu_id = " + cupom.getUsuario().getId();
		}else if(cupom.getId() != null) {
			sql += " AND cup_id = " + cupom.getId();
		}else {
			sql += " AND cup_cupomPromocional = true"; 
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Usuario u = new Usuario();
			Cupom c = new Cupom();
			
			while(rs.next()) {
				u = new Usuario();
				c = new Cupom();
				
				c.setId(rs.getLong("cup_id"));
				c.setValor(rs.getFloat("cup_valor"));
				c.setCupomPromocional(rs.getBoolean("cup_cupomPromocional"));
				c.setDataHoraVencimento(rs.getObject("cup_dataHoraVencimento",LocalDateTime.class));
				
				u.setId(rs.getLong("cup_usu_id"));
				c.setUsuario(u);
				
				cupons.add(c);
			}
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return cupons;
	}

	
}
