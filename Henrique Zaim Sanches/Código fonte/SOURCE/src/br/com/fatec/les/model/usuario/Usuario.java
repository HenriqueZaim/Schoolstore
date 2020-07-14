package br.com.fatec.les.model.usuario;

import java.util.List;

import br.com.fatec.les.model.config.EntidadeDominio;
import br.com.fatec.les.model.config.Imagem;
import br.com.fatec.les.model.pagamento.cupom.Cupom;

public class Usuario extends EntidadeDominio{

	private String email;
	private String senha;
	private Imagem imagem;
	private boolean admin;
	private List<Cupom> cupons;
	

	public List<Cupom> getCupons() {
		return cupons;
	}
	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
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
