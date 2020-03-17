package br.com.fatec.les.model;

public class Imagem extends EntidadeDominio{
	private String foto;
	private String descricao;
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}