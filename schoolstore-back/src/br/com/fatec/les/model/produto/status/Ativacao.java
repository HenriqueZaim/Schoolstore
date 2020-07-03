package br.com.fatec.les.model.produto.status;

import br.com.fatec.les.model.config.EntidadeDominio;
import br.com.fatec.les.model.produto.Produto;

public class Ativacao extends EntidadeDominio{

	private String descricao;
	private StatusAtivacao statusAtivacao;
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
	public StatusAtivacao getStatusAtivacao() {
		return statusAtivacao;
	}
	public void setStatusAtivacao(StatusAtivacao statusAtivacao) {
		this.statusAtivacao = statusAtivacao;
	}
	
}
