package br.com.fatec.les.facade;

import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.model.EntidadeDominio;

public class Result {

	private ArrayList<String> mensagem;
	private List<EntidadeDominio> entidades;
	
	public ArrayList<String> getMensagem() {
		return mensagem;
	}
	public void setMensagem(ArrayList<String> mensagem) {
		this.mensagem = mensagem;
	}
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
}
