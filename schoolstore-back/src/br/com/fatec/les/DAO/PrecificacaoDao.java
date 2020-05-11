package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.produto.Precificacao;

public class PrecificacaoDao implements IDao{
	
    private Connection conexao = null;
    
	@Override
	public Mensagem salvar(EntidadeDominio entidadeDominio) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public Mensagem deletar(EntidadeDominio entidadeDominio) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public Mensagem atualizar(EntidadeDominio entidadeDominio) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
		Precificacao precificacao = (Precificacao) entidade;
		conexao = ConexaoFactory.getConnection();
		List<EntidadeDominio> precificacoes = new ArrayList<EntidadeDominio>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "pre_id, "
				+ "pre_percentual "
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
