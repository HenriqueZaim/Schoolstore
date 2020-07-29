package br.com.fatec.les.DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.endereco.Cidade;
import br.com.fatec.les.model.endereco.Estado;

public class EstadoDao implements IDao{
	
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
		Estado estado = (Estado) entidade;
		CidadeDao cidadeDao = new CidadeDao();
		conexao = ConexaoFactory.getConnection();
		
		List<ADominio> entidadeEstados = new ArrayList<ADominio>();
		List<ADominio> entidadeCidades = new ArrayList<ADominio>();
		List<Cidade> cidades = new ArrayList<Cidade>();
		List<Estado> estados = new ArrayList<Estado>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "est_id, "
				+ "est_nome, "
				+ "est_sigla,"
				+ "est_ativo,"
				+ "est_dataHoraCriacao,"
				+ "est_dataHoraAtualizacao "
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
				
				e.setId(Long.parseLong(rs.getString("est_id")));
				e.setNome(rs.getString("est_nome"));
				e.setSigla(rs.getString("est_sigla"));
				e.setAtivo(rs.getBoolean("est_ativo"));
				e.setDataHoraCriacao(rs.getObject("est_dataHoraCriacao", LocalDateTime.class));
				e.setDataHoraAtualizacao(rs.getObject("est_dataHoraAtualizacao", LocalDateTime.class));
				
				if(estado.getId() == null) {
					c = new Cidade();
					
					entidadeCidades = new ArrayList<ADominio>();
					cidades = new ArrayList<Cidade>();
					
					c.setEstado(e);
					
					entidadeCidades.addAll(cidadeDao.consultar(c));
					
					for(ADominio cidade : entidadeCidades) {
						cidades.add((Cidade)cidade);
					}
					
					e.setCidades(cidades);
				}

				estados.add(e);
			}
			
			entidadeEstados.addAll(estados);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return entidadeEstados;
	}

}
