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
import br.com.fatec.les.model.pedido.Pedido;

public class PedidoDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	FreteDao freteDao = new FreteDao();
	FormaPagamentoDAO formaPagamentoDao = new FormaPagamentoDAO();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		Pedido pedido = (Pedido) entidade;
		conexao = ConexaoFactory.getConnection();
		ResultSet rs;
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_pedido "
				+ "("
				+ "ped_valor, "
				+ "ped_statusPedido, "
				+ "ped_cli_id, "
				+ "ped_fre_id, "
				+ "ped_fpag_id, "
				+ "ped_ativo, "
				+ "ped_dataHoraCriacao "
				+ ") "
				+ " VALUES ( ?, ?, ?, ?, ?, true, NOW())";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setFloat(1, pedido.getValor());
			pstm.setString(2, pedido.getStatusPedido().toString());
			pstm.setLong(3, pedido.getCliente().getId());
			
			mensagem = freteDao.salvar(pedido.getFrete());
			if(mensagem.getMensagemStatus() == MensagemStatus.ERRO) {
				throw new SQLException();
			}
			String id = mensagem.getMensagem();
			
			pstm.setLong(4, Long.parseLong(id));
			
			mensagem = formaPagamentoDao.salvar(pedido.getFormaPagamento());
			if(mensagem.getMensagemStatus() == MensagemStatus.ERRO) {
				throw new SQLException();
			}
			id = mensagem.getMensagem();
			
			pstm.setLong(5, Long.parseLong(id));
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
		// TODO Auto-generated method stub
		return null;
	}

}
