package br.com.fatec.les.model.usuario;

import java.time.LocalDateTime;
import java.util.List;

import br.com.fatec.les.model.config.EntidadeDominio;

public class Carrinho extends EntidadeDominio{

	private List<ItemCarrinho> itensCarrinho;
	private float subTotal;
	private LocalDateTime validade;
	
	public List<ItemCarrinho> getItensCarrinho() {
		return itensCarrinho;
	}
	public void setItensCarrinho(List<ItemCarrinho> itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}
	public float getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	public LocalDateTime getValidade() {
		return validade;
	}
	public void setValidade(LocalDateTime validade) {
		this.validade = validade;
	}
}
