package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.model.Cidade;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.Estado;
import br.com.fatec.les.model.IDominio;

public class CidadeDao implements IDao{
	
	private Connection conexao = null;
	
	
	public CidadeDao() {
		conexao = ConexaoFactory.getConnection();
	}

	@Override
	public String salvar(EntidadeDominio entidadeDominio) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public String deletar(EntidadeDominio entidadeDominio) throws SQLException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public String atualizar(EntidadeDominio entidadeDominio) throws SQLException {
		throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
		Cidade cidade = (Cidade) entidade;
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
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm, rs);
//		}
		
		return cidades;
	}

}
