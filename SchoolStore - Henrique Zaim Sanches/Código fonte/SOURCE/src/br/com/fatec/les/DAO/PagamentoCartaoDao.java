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
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.pagamento.FormaPagamento;
import br.com.fatec.les.model.pagamento.cartao.CartaoCredito;
import br.com.fatec.les.model.pagamento.cartao.PagamentoCartao;

public class PagamentoCartaoDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		PagamentoCartao pagamentoCartao = (PagamentoCartao) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_pagamentoCartao "
				+ "("
				+ "pca_valorTotalCartao, "
				+ "pca_ccr_id, "
				+ "pca_fpag_id "
				+ ") "
				+ " VALUES ( ?, ?, ?)";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setFloat(1, pagamentoCartao.getValorTotalCartao());	
			pstm.setLong(2, pagamentoCartao.getCartaoCredito().getId());
			pstm.setLong(3, pagamentoCartao.getFormaPagamento().getId());
			
			pstm.executeUpdate();

			mensagem.setMensagem("Tudo certo por aqui");
			mensagem.setMensagemStatus(MensagemStatus.SUCESSO);				
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
		throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public Mensagem atualizar(ADominio entidade) throws SQLException {
		throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public List<ADominio> consultar(ADominio entidade) throws SQLException {
		PagamentoCartao pagamentoCartao = (PagamentoCartao) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		List<ADominio> pagamentosCartao = new ArrayList<ADominio>();
		
		ResultSet rs = null;
		PreparedStatement pstm = null;

		String sql = "SELECT "
				+ "pca_id,"
				+ "pca_valorTotalCartao,"
				+ "pca_ccr_id,"
				+ "pca_fpag_id "
				+ " FROM tb_pagamentoCartao "
				+ " WHERE pca_fpag_id = " + pagamentoCartao.getFormaPagamento().getId() + "";

		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			PagamentoCartao pc = new PagamentoCartao();
			CartaoCredito cc = new CartaoCredito();
			FormaPagamento pf = new FormaPagamento();
			

			while(rs.next()) {
				pc = new PagamentoCartao();
				cc = new CartaoCredito();
				pf = new FormaPagamento();
				
				pc.setId(rs.getLong("pca_id"));
				pc.setValorTotalCartao(rs.getFloat("pca_valorTotalCartao"));
				
				cc.setId(rs.getLong("pca_ccr_id"));
				pc.setCartaoCredito(cc);
				
				pf.setId(rs.getLong("pca_fpag_id"));
				pc.setFormaPagamento(pf);
				
				pagamentosCartao.add(pc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		return pagamentosCartao;
	}

}
