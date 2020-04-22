package br.com.fatec.les.model.usuario;

import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.Imagem;

public class Usuario extends EntidadeDominio{

	private String email;
	private String senha;
	private Imagem imagem;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Imagem getImagem() {
		return imagem;
	}
	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	
}
