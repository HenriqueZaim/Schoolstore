package br.com.fatec.les.model.usuario;

import java.util.List;

import br.com.fatec.les.model.assets.EntidadeNomeada;
import br.com.fatec.les.model.endereco.Endereco;

public class Cliente extends EntidadeNomeada{

	private String numeroTelefone;
	private String numeroDocumento;
	private List<Endereco> enderecos;
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	private Usuario usuario;
	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



}
