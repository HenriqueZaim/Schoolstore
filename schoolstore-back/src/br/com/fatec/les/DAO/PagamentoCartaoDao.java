package br.com.fatec.les.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.les.database.ConexaoFactory;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.ADominio;
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
			pstm.setFloat(1, pagamentoCartao.getValorTotalCartao());	// TODO: TROCAR A MERDA DO NOME
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
