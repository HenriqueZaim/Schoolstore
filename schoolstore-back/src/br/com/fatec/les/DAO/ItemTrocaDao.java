package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.produto.Produto;
import br.com.fatec.les.model.troca.ItemTroca;
import br.com.fatec.les.model.troca.Troca;

public class ItemTrocaDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	ProdutoDao produtoDao = new ProdutoDao();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		ItemTroca itemTroca = (ItemTroca) entidade;
		
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_itemTroca "
				+ "("
				+ "itro_quantidade, "
				+ "itro_pro_id, "
				+ "itro_tro_id "
				+ ") "
				+ " VALUES ( ?, ?, ? )";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setInt(1, itemTroca.getQuantidade());
			pstm.setLong(2, itemTroca.getProduto().getId());
			pstm.setLong(3, itemTroca.getTroca().getId());
			
			pstm.executeUpdate();

			mensagem.setMensagem("Operação bem sucedida!");
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
		ItemTroca itemTroca = (ItemTroca) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		List<ADominio> itensTroca = new ArrayList<ADominio>();
		
		ResultSet rs = null;
		PreparedStatement pstm = null;

		String sql = "SELECT "
				+ "itro_id,"
				+ "itro_quantidade,"
				+ "itro_pro_id,"
				+ "itro_tro_id "
				+ " FROM tb_itemTroca "
				+ " WHERE itro_tro_id = " + itemTroca.getTroca().getId() + "";

		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			ItemTroca it = new ItemTroca();
			Produto pr = new Produto();
			Troca tr = new Troca(); 

			while(rs.next()) {
				it = new ItemTroca();
				pr = new Produto();
				tr = new Troca(); 
				
				it.setId(rs.getLong("itro_id"));
				it.setQuantidade(rs.getInt("itro_quantidade"));
				
				tr.setId(rs.getLong("itro_tro_id"));
				it.setTroca(tr);
				
				pr.setId(rs.getLong("itro_pro_id"));
				it.setProduto((Produto)produtoDao.consultar(pr).get(0));
				
				itensTroca.add(it);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		return itensTroca;
	}

	
}
