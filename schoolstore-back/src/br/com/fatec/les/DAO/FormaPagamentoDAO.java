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
import br.com.fatec.les.model.pagamento.FormaPagamento;
import br.com.fatec.les.model.pagamento.cartao.PagamentoCartao;

public class FormaPagamentoDAO implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	PagamentoCartaoDao pagamentoCartaoDao = new PagamentoCartaoDao();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		FormaPagamento formaPagamento = (FormaPagamento) entidade;
		conexao = ConexaoFactory.getConnection();
		ResultSet rs;
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_formaPagamento "
				+ "("
				+ "fpag_valorTotal "
				+ ") "
				+ " VALUES ( ? )";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setFloat(1, formaPagamento.getValorTotal());
			pstm.executeUpdate();
				
			rs = pstm.getGeneratedKeys();
			if (rs.next()){
				mensagem.setMensagem(Integer.toString(rs.getInt(1)));
				mensagem.setMensagemStatus(MensagemStatus.OPERACAO);
				
				formaPagamento.setId(Long.parseLong(mensagem.getMensagem()));
				for(PagamentoCartao pagamentoCartao : formaPagamento.getPagamentosCartao()) {
					pagamentoCartao.setFormaPagamento(formaPagamento);
					pagamentoCartaoDao.salvar(pagamentoCartao);
				}
				
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
