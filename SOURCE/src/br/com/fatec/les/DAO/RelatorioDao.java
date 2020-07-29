package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.config.Relatorio;
import br.com.fatec.les.model.pedido.Pedido;
import br.com.fatec.les.model.pedido.StatusPedido;

public class RelatorioDao implements IDao{
	
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
		Relatorio relatorio = (Relatorio) entidade;
		conexao = ConexaoFactory.getConnection();

		List<ADominio> pedidos = new ArrayList<ADominio>();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "SELECT "
				+ "ped_id, "
				+ "ped_statusPedido, "
				+ "ped_dataHoraAtualizacao,"
				+ "ped_dataHoraCriacao,"
				+ "ped_ativo "
				+ " FROM tb_pedido "
				+ " WHERE (ped_ativo = 1 OR ped_ativo = 0)";
		if(relatorio.getDataFim() != null && relatorio.getDataInicio() != null) { // se ele escolher o período
			String inicioPeriodo = "'" + relatorio.getDataInicio().getYear() + "-" + relatorio.getDataInicio().getMonthValue() + "-" + relatorio.getDataInicio().getDayOfMonth() + " 00:00:00'";
			String fimPeriodo = "'" + relatorio.getDataFim().getYear() + "-" + relatorio.getDataFim().getMonthValue() + "-" + relatorio.getDataFim().getDayOfMonth() + " 23:59:59'";
			sql += " AND ped_dataHoraAtualizacao BETWEEN " + inicioPeriodo + " AND " + fimPeriodo + " ";
		}else if(relatorio.getDataInicio() != null){ // se somente se houver data de início
			String inicioPeriodo = "'" + relatorio.getDataInicio().getYear() + "-" + relatorio.getDataInicio().getMonthValue() + "-" + relatorio.getDataInicio().getDayOfMonth() + " 00:00:00'";
			sql += " AND ped_dataHoraAtualizacao >= " + inicioPeriodo + " ";
		}else if(relatorio.getDataFim() != null){
			String fimPeriodo = "'" + relatorio.getDataFim().getYear() + "-" + relatorio.getDataFim().getMonthValue() + "-" + relatorio.getDataFim().getDayOfMonth() + " 23:59:59'";
			sql += " AND ped_dataHoraAtualizacao <= " + fimPeriodo + " ";
		}else { // Primeira busca, somente do ano atual
			sql += " AND YEAR(ped_dataHoraAtualizacao) = " + LocalDate.now().getYear() + " ";
		}
				
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Pedido p = new Pedido();

			while(rs.next()) {
				p = new Pedido();

				p.setId(rs.getLong("ped_id"));
				p.setStatusPedido(StatusPedido.valueOf(rs.getString("ped_statusPedido")));
				p.setDataHoraCriacao(rs.getObject("ped_dataHoraCriacao", LocalDateTime.class));
				p.setDataHoraAtualizacao(rs.getObject("ped_dataHoraAtualizacao", LocalDateTime.class));
				p.setAtivo(rs.getBoolean("ped_ativo"));

				pedidos.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		
		return pedidos;
	}

}
