package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.troca.ItemTroca;
import br.com.fatec.les.model.troca.Troca;

public class TrocaDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	ItemTrocaDao itemTrocaDao = new ItemTrocaDao();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		Troca troca = (Troca) entidade;
		conexao = ConexaoFactory.getConnection();
		ResultSet rs;
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_troca "
				+ "("
				+ "tro_cli_id, "
				+ "tro_ped_id,"
				+ "tro_statusTroca, "
				+ "tro_ativo, "
				+ "tro_dataHoraCriacao "
				+ ") "
				+ " VALUES ( ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setLong(1, troca.getCliente().getId());
			pstm.setLong(2, troca.getPedido().getId());
			pstm.setString(3, troca.getStatusTroca().toString());
			

			pstm.executeUpdate();
						
			rs = pstm.getGeneratedKeys();
			if (rs.next()){
				String id = Integer.toString(rs.getInt(1));
				Troca trocaAux = new Troca();
				for(ItemTroca item : troca.getItensTroca()) {
					trocaAux.setId(Long.parseLong(id));
					item.setTroca(trocaAux);
					itemTrocaDao.salvar(item);
				}
				
				mensagem.setMensagem("Solicitação para troca realizada com sucesso! Acompanhe o status da troca na página 'Minhas Solicitações'");
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
		// TODO Auto-generated method stub
		return null;
	}

}
