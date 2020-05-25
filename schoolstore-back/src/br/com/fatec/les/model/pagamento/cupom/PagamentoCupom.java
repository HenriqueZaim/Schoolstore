package br.com.fatec.les.model.pagamento.cupom;

import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pagamento.FormaPagamento;

public class PagamentoCupom extends ADominio{

	private float valorTotalCupom;
	private Cupom cupom;
	private FormaPagamento formaPagamento;
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Cupom getCupom() {
		return cupom;
	}
	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
	public float getValorTotalCupom() {
		return valorTotalCupom;
	}
	public void setValorTotalCupom(float valorTotalCupom) {
		this.valorTotalCupom = valorTotalCupom;
	}

}
