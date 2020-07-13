package br.com.fatec.les.model.pedido;

import java.util.List;

import br.com.fatec.les.model.config.EntidadeDominio;
import br.com.fatec.les.model.pagamento.FormaPagamento;
import br.com.fatec.les.model.usuario.Cliente;

public class Pedido extends EntidadeDominio{

	private StatusPedido statusPedido;
	private float valor;
	private Frete frete;
	private List<ItemPedido> itensPedido;
	private FormaPagamento formaPagamento;
	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
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