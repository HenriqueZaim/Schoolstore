package br.com.fatec.les.model.pagamento;

import java.util.List;

import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.pagamento.cartao.PagamentoCartao;
import br.com.fatec.les.model.pagamento.cupom.PagamentoCupom;

public class FormaPagamento extends ADominio{

	private float valorTotal;
	private List<PagamentoCartao> pagamentosCartao;
	private List<PagamentoCupom> pagamentosCupom;
	
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public List<PagamentoCartao> getPagamentosCartao() {
		return pagamentosCartao;
	}
	public void setPagamentosCartao(List<PagamentoCartao> pagamentosCartao) {
		this.pagamentosCartao = pagamentosCartao;
	}
	public List<PagamentoCupom> getPagamentosCupom() {
		return pagamentosCupom;
	}
	public void setPagamentosCupom(List<PagamentoCupom> pagamentosCupom) {
		this.pagamentosCupom = pagamentosCupom;
	}
	
}
