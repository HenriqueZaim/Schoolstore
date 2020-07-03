package br.com.fatec.les.model.produto.status;

import br.com.fatec.les.model.config.EntidadeDominio;
import br.com.fatec.les.model.produto.Produto;

public class Inativacao extends EntidadeDominio{

	private String descricao;
	private StatusInativacao statusInativacao;
	private Produto produto;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public StatusInativacao getStatusInativacao() {
		return statusInativacao;
	}
	public void setStatusInativacao(StatusInativacao statusInativacao) {
		this.statusInativacao = statusInativacao;
	}
}
