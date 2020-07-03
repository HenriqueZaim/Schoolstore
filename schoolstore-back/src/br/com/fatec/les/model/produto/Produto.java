package br.com.fatec.les.model.produto;

import java.util.List;

import br.com.fatec.les.model.config.EntidadeNomeada;
import br.com.fatec.les.model.config.Imagem;
import br.com.fatec.les.model.estoque.Estoque;

public class Produto extends EntidadeNomeada{

	private float preco;
	private String descricao;
	private String codBarras;
	private List<Categoria> categorias;
	private Imagem imagem;
	private Estoque estoque;
	private Precificacao precificacao;
	
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCodBarras() {
		return codBarras;
	}
	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategoria(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	public Imagem getImagem() {
		return imagem;
	}
	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	public Precificacao getPrecificacao() {
		return precificacao;
	}
	public void setPrecificacao(Precificacao precificacao) {
		this.precificacao = precificacao;
	}
}
