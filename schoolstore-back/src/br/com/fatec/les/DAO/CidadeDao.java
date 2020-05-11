package br.com.fatec.les.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.endereco.Cidade;
import br.com.fatec.les.model.endereco.Estado;

public class CidadeDao implements IDao{
	
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
		Cidade cidade = (Cidade) entidade;
		conexao = ConexaoFactory.getConnection();
		List<EntidadeDominio> cidades = new ArrayList<EntidadeDominio>();
		EstadoDao estadoDao = new EstadoDao();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "cid_id, "
				+ "cid_nome, "
				+ "cid_est_id "
				+ " FROM tb_cidade "
				+ " WHERE cid_ativo = 1 ";
		if(cidade.getId() != null) {
			sql += " AND cid_id = " + cidade.getId();
		}
		if(cidade.getEstado() != null) {
			sql += " AND cid_est_id = " + cidade.getEstado().getId();
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Cidade c = new Cidade();
			Estado e = new Estado();

			while(rs.next()) {
				c = new Cidade();
				e = new Estado();
				
				c.setId(Long.parseLong(rs.getString("cid_id")));
				c.setNome(rs.getString("cid_nome"));
				
				if(cidade.getId() != null) {
					e.setId(Long.parseLong(rs.getString("cid_est_id")));
					c.setEstado((Estado)estadoDao.consultar(e).get(0));
				}else if(cidade.getEstado() != null) {
					e.setId(cidade.getEstado().getId());
					c.setEstado(e);
				}else {
					throw new RuntimeException();
				}

				cidades.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return cidades;
	}

}
