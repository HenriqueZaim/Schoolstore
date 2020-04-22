package br.com.fatec.les.model.pagamento.cupom;

import java.util.List;

public class PagamentoCupom {

	private float valorTotalCupom;
	private List<Cupom> cupons;
	
	public float getValorTotalCupom() {
		return valorTotalCupom;
	}
	public void setValorTotalCupom(float valorTotalCupom) {
		this.valorTotalCupom = valorTotalCupom;
	}
	public List<Cupom> getCupons() {
		return cupons;
	}
	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}
}
