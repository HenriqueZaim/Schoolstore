package br.com.fatec.les.model.pagamento.cartao;

import java.util.List;

public class PagamentoCartao {

	private float valorTotalCartao;
	private List<CartaoCredito> cartoesCredito;
	
	public float getValorTotalCartao() {
		return valorTotalCartao;
	}
	public void setValorTotalCartao(float valorTotalCartao) {
		this.valorTotalCartao = valorTotalCartao;
	}
	public List<CartaoCredito> getCartoesCredito() {
		return cartoesCredito;
	}
	public void setCartoesCredito(List<CartaoCredito> cartoesCredito) {
		this.cartoesCredito = cartoesCredito;
	}

}