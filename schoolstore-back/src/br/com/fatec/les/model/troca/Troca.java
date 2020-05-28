package br.com.fatec.les.model.troca;

import java.util.List;

import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.pedido.Pedido;
import br.com.fatec.les.model.usuario.Cliente;

public class Troca extends EntidadeDominio{
	
	private Pedido pedido;
	private StatusTroca statusTroca;
	private Cliente cliente;
	private List<ItemTroca> itensTroca;

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public StatusTroca getStatusTroca() {
		return statusTroca;
	}
	public void setStatusTroca(StatusTroca statusTroca) {
		this.statusTroca = statusTroca;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ItemTroca> getItensTroca() {
		return itensTroca;
	}
	public void setItensTroca(List<ItemTroca> itensTroca) {
		this.itensTroca = itensTroca;
	}

}
