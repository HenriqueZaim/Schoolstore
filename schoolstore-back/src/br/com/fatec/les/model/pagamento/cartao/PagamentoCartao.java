package br.com.fatec.les.model.pagamento.cartao;

public class PagamentoCartao {

	private float valorTotalCartao;
	private CartaoCredito cartaoCredito;
	
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
