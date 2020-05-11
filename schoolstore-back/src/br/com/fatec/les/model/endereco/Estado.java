package br.com.fatec.les.model.endereco;

import java.util.List;

import br.com.fatec.les.model.assets.EntidadeNomeada;

public class Estado extends EntidadeNomeada{

	private String sigla;
	private List<Cidade> cidades;
	
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
}
