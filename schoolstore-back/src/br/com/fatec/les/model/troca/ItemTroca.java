package br.com.fatec.les.model.troca;

import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.produto.Produto;

public class ItemTroca extends ADominio{

	private Produto produto;
	private Troca troca;
	private int quantidade;
	
	public Troca getTroca() {
		return troca;
	}
	public void setTroca(Troca troca) {
		this.troca = troca;
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
