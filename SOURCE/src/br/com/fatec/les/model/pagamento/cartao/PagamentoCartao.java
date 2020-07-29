package br.com.fatec.les.model.pagamento.cartao;

import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.pagamento.FormaPagamento;

public class PagamentoCartao extends ADominio{

	private float valorTotalCartao;
	private CartaoCredito cartaoCredito;
	private FormaPagamento formaPagamento;
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public float getValorTotalCartao() {
		return valorTotalCartao;
	}
	public void setValorTotalCartao(float valorTotalCartao) {
		this.valorTotalCartao = valorTotalCartao;
	}
	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}
	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}


}