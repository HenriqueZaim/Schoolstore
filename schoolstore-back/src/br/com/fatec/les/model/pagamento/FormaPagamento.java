package br.com.fatec.les.model.pagamento;

import br.com.fatec.les.model.pagamento.cartao.PagamentoCartao;
import br.com.fatec.les.model.pagamento.cupom.PagamentoCupom;

public class FormaPagamento {

	private float valorTotal;
	private PagamentoCartao pagamentoCartao;
	private PagamentoCupom pagamentoCupom;
	
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public PagamentoCartao getPagamentoCartao() {
		return pagamentoCartao;
	}
	public void setPagamentoCartao(PagamentoCartao pagamentoCartao) {
		this.pagamentoCartao = pagamentoCartao;
	}
	public PagamentoCupom getPagamentoCupom() {
		return pagamentoCupom;
	}
	public void setPagamentoCupom(PagamentoCupom pagamentoCupom) {
		this.pagamentoCupom = pagamentoCupom;
	}
}
