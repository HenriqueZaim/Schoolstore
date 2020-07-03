package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.endereco.Endereco;
import br.com.fatec.les.model.pedido.Frete;

public class FreteDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		Frete frete = (Frete) entidade;
		conexao = ConexaoFactory.getConnection();
		ResultSet rs;
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_frete "
				+ "("
				+ "fre_valor, "
				+ "fre_previsaoEmDias, "
				+ "fre_end_id, "
				+ "fre_ativo, "
				+ "fre_dataHoraCriacao "
				+ ") "
				+ " VALUES ( ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setFloat(1, frete.getValor());
			pstm.setInt(2, frete.getPrevisaoEmDias()); 
			pstm.setLong(3, frete.getEndereco().getId());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mensagem atualizar(ADominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ADominio> consultar(ADominio entidade) throws SQLException {
		Frete frete = (Frete) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		EnderecoDao enderecoDao = new EnderecoDao();
		
		List<ADominio> fretes = new ArrayList<ADominio>();
		
		ResultSet rs = null;
		PreparedStatement pstm = null;

		String sql = "SELECT "
				+ "fre_id, "
				+ "fre_valor, "
				+ "fre_previsaoEmDias, "
				+ "fre_end_id "
				+ " FROM tb_frete "
				+ " WHERE fre_ativo = 1 ";
		if(frete.getId() != null) {
			sql += " AND fre_id = " + frete.getId() + "";
		}

		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			Frete f = new Frete();
			Endereco e = new Endereco();

			if(rs.next()) {
				f = new Frete();
				e = new Endereco();
				
				f.setId(rs.getLong("fre_id"));
				f.setValor(rs.getFloat("fre_valor"));
				f.setPrevisaoEmDias(rs.getInt("fre_previsaoEmDias"));
				
				e.setId(rs.getLong("fre_end_id"));
				f.setEndereco((Endereco)enderecoDao.consultar(e).get(0));

				fretes.add(f);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		return fretes;
	}
}
