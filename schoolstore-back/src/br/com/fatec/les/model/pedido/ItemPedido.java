package br.com.fatec.les.model.pedido;

import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.produto.Produto;

public class ItemPedido extends ADominio{
	
	private int quantidade;
	private Produto produto;
	private Pedido pedido;
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
