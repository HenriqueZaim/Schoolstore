package br.com.fatec.les.facade;

import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.model.assets.EntidadeDominio;

public class Resultado {

	private ArrayList<Mensagem> mensagens;
	private List<EntidadeDominio> entidades;
	

	public ArrayList<Mensagem> getMensagens() {
		return mensagens;
	}
	public void setMensagens(ArrayList<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
}
