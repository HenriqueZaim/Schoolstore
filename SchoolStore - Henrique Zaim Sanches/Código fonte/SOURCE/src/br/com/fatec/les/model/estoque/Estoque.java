package br.com.fatec.les.model.estoque;

import java.util.List;

import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.produto.Produto;

public class Estoque extends ADominio{
	private Produto produto;
	private int quantidadeTotal;
	private List<ItemEstoque> itensEstoque;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidadeTotal() {
		return quantidadeTotal;
	}
	public void setQuantidadeTotal(int quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	public List<ItemEstoque> getItensEstoque() {
		return itensEstoque;
	}
	public void setItensEstoque(List<ItemEstoque> itensEstoque) {
		this.itensEstoque = itensEstoque;
	}
}
