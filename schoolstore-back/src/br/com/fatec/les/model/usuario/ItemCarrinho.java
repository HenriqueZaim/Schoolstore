package br.com.fatec.les.model.usuario;

import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.produto.Produto;

public class ItemCarrinho extends ADominio{

	private Produto produto;
	private int quantidade;
	private Carrinho carrinho;
	
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
