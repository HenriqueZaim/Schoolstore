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
import br.com.fatec.les.model.pagamento.FormaPagamento;
import br.com.fatec.les.model.pagamento.cupom.Cupom;
import br.com.fatec.les.model.pagamento.cupom.PagamentoCupom;

public class PagamentoCupomDao implements IDao{
	
	private Connection conexao = null;
	private Mensagem mensagem;
	CupomDao cupomDao = new CupomDao();

	@Override
	public Mensagem salvar(ADominio entidade) throws SQLException {
		PagamentoCupom pagamentoCupom = (PagamentoCupom) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		String sql = "INSERT INTO tb_pagamentoCupom"
				+ "("
				+ "pcu_valorTotalCupom, "
				+ "pcu_cup_id, "
				+ "pcu_fpag_id "
				+ ") "
				+ " VALUES ( ? , ? , ?)";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setFloat(1, pagamentoCupom.getValorTotalCupom());
			pstm.setLong(2, pagamentoCupom.getCupom().getId());
			pstm.setLong(3, pagamentoCupom.getFormaPagamento().getId());
			pstm.executeUpdate();
			
			cupomDao.deletar(pagamentoCupom.getCupom());

			mensagem.setMensagem("Pagamento realizado com sucesso!");
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
		PagamentoCupom pagamentoCupom = (PagamentoCupom) entidade;
		conexao = ConexaoFactory.getConnection();
		mensagem = new Mensagem();
		
		List<ADominio> pagamentosCupom = new ArrayList<ADominio>();
		
		ResultSet rs = null;
		PreparedStatement pstm = null;

		String sql = "SELECT "
				+ "pcu_id,"
				+ "pcu_valorTotalCupom,"
				+ "pcu_cup_id,"
				+ "pcu_fpag_id "
				+ " FROM tb_pagamentoCupom "
				+ " WHERE pcu_fpag_id = " + pagamentoCupom.getFormaPagamento().getId() + "";

		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();

			PagamentoCupom pc = new PagamentoCupom();
			Cupom cp = new Cupom();
			FormaPagamento pf = new FormaPagamento();
			

			while(rs.next()) {
				pc = new PagamentoCupom();
				cp = new Cupom();
				pf = new FormaPagamento();
				
				pc.setId(rs.getLong("pcu_id"));
				pc.setValorTotalCupom(rs.getFloat("pcu_valorTotalCupom"));
				
				cp.setId(rs.getLong("pcu_cup_id"));
				pc.setCupom(cp);
				
				pf.setId(rs.getLong("pcu_fpag_id"));
				pc.setFormaPagamento(pf);
				
				pagamentosCupom.add(pc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConexaoFactory.closeConnection(conexao, pstm, rs);
		}
		return pagamentosCupom;
	}

}
