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

public class EstadoDao implements IDao{
	
	private Connection conexao = null;
	private String mensagem = null;
	CidadeDao cidadeDao = new CidadeDao();
	
	public EstadoDao() {
		conexao = ConexaoFactory.getConnection();
	}

	@Override
	public String salvar(EntidadeDominio entidadeDominio) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletar(EntidadeDominio entidadeDominio) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String atualizar(EntidadeDominio entidadeDominio) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(IDominio entidade) throws SQLException {
		Estado estado = (Estado) entidade;
		
		List<EntidadeDominio> entidadeEstados = new ArrayList<EntidadeDominio>();
		List<EntidadeDominio> entidadeCidades = new ArrayList<EntidadeDominio>();
		List<Cidade> cidades = new ArrayList<Cidade>();
		List<Estado> estados = new ArrayList<Estado>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "est_id, "
				+ "est_nome, "
				+ "est_sigla "
				+ " FROM tb_estado WHERE est_ativo = 1 ";
		if(estado.getId() != null) {
			sql += "AND est_id = " + estado.getId();
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Estado e = new Estado();
			Cidade c = new Cidade();
			
			while(rs.next()) {
				e = new Estado();
				c = new Cidade();
				
				entidadeCidades = new ArrayList<EntidadeDominio>();
				cidades = new ArrayList<Cidade>();
				
				e.setId(Long.parseLong(rs.getString("est_id")));
				e.setNome(rs.getString("est_nome"));
				e.setSigla(rs.getString("est_sigla"));
				c.setEstado(e);
				
				entidadeCidades.addAll(cidadeDao.consultar(c));
				
				for(EntidadeDominio cidade : entidadeCidades) {
					cidades.add((Cidade)cidade);
				}
				
				e.setCidades(cidades);
		
				estados.add(e);
			}
			
			entidadeEstados.addAll(estados);
			
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
//		finally {
//			ConexaoFactory.closeConnection(conexao, pstm, rs);
//		}
		
		return entidadeEstados;
	}

}
