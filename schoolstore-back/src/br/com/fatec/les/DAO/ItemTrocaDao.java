package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pedido.ItemPedido;
import br.com.fatec.les.model.troca.ItemTroca;

public class ItemTrocaDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;

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
		// TODO Auto-generated method stub
		return null;
	}

	
}
