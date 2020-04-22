package br.com.fatec.les.model.pedido;

import java.util.List;

import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.pagamento.FormaPagamento;

public class Pedido extends EntidadeDominio{

	private StatusPedido statusPedido;
	private float valor;
	private Frete frete;
	private List<ItemPedido> itensPedido;
	private FormaPagamento formaPagamento;
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public Frete getFrete() {
		return frete;
	}
	public void setFrete(Frete frete) {
		this.frete = frete;
	}
	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}
	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
	
}
