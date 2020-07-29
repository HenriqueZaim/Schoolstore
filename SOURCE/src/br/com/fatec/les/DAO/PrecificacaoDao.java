package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.produto.Precificacao;

public class PrecificacaoDao implements IDao{
	
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
		Precificacao precificacao = (Precificacao) entidade;
		conexao = ConexaoFactory.getConnection();
		List<ADominio> precificacoes = new ArrayList<ADominio>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "pre_id, "
				+ "pre_percentual,"
				+ "pre_ativo,"
				+ "pre_dataHoraCriacao,"
				+ "pre_dataHoraAtualizacao "
				+ " FROM tb_precificacao "
				+ " WHERE pre_ativo = 1 "
				+ " AND pre_id = " + precificacao.getId() + "";

		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			if(rs.next()) {
				Precificacao p = new Precificacao();
				p.setId(rs.getLong("pre_id"));
				p.setPercentual(rs.getFloat("pre_percentual"));
				p.setAtivo(rs.getBoolean("pre_ativo"));
				p.setDataHoraCriacao(rs.getObject("pre_dataHoraCriacao", LocalDateTime.class));
				p.setDataHoraAtualizacao(rs.getObject("pre_dataHoraAtualizacao", LocalDateTime.class));
				precificacoes.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return precificacoes;
	}

}
