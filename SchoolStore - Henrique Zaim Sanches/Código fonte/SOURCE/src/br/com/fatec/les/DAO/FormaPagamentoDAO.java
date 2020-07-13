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
import br.com.fatec.les.model.pagamento.FormaPagamento;
import br.com.fatec.les.model.pagamento.cartao.PagamentoCartao;
import br.com.fatec.les.model.pagamento.cupom.PagamentoCupom;

public class FormaPagamentoDAO implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	PagamentoCartaoDao pagamentoCartaoDao = new PagamentoCartaoDao();
	PagamentoCupomDao pagamentoCupomDao = new PagamentoCupomDao();

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
				
				for(PagamentoCupom  pagamentoCupom : formaPagamento.getPagamentosCupom()) {
					pagamentoCupom.setFormaPagamento(formaPagamento);
					pagamentoCupomDao.salvar(pagamentoCupom);
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
		FormaPagamento formaPagamento = (FormaPagamento) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		List<ADominio> formasPagamento = new ArrayList<ADominio>();
		List<ADominio> pagamentosCartaoEntidade = new ArrayList<ADominio>();
		List<ADominio> pagamentosCupomEntidade = new ArrayList<ADominio>();
		List<PagamentoCartao> pagamentosCartao = new ArrayList<PagamentoCartao>(); 
		List<PagamentoCupom> pagamentosCupom = new ArrayList<PagamentoCupom>(); 
		
		ResultSet rs = null;
		PreparedStatement pstm = null;

		String sql = "SELECT "
				+ "fpag_id,"
				+ "fpag_valorTotal "
				+ " FROM tb_formaPagamento "
				+ " WHERE fpag_id = " + formaPagamento.getId() + "";

		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			FormaPagamento f = new FormaPagamento();
			PagamentoCartao pc = new PagamentoCartao();
			PagamentoCupom p = new PagamentoCupom();

			if(rs.next()) {
				f.setId(rs.getLong("fpag_id"));
				f.setValorTotal(rs.getFloat("fpag_valorTotal"));
				
				pc.setFormaPagamento(f);
				p.setFormaPagamento(f);
				
				pagamentosCartaoEntidade.addAll(pagamentoCartaoDao.consultar(pc));
				if(!pagamentosCartaoEntidade.isEmpty()) {
					for(ADominio item : pagamentosCartaoEntidade) {
						pagamentosCartao.add((PagamentoCartao)item);
					}
				}
				f.setPagamentosCartao(pagamentosCartao);
				
				pagamentosCupomEntidade.addAll(pagamentoCupomDao.consultar(p));
				if(!pagamentosCupomEntidade.isEmpty()) {
					for(ADominio item : pagamentosCupomEntidade) {
						pagamentosCupom.add((PagamentoCupom)item);
					}
				}
				f.setPagamentosCupom(pagamentosCupom);
				

				formasPagamento.add(f);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		return formasPagamento;
	}

}
