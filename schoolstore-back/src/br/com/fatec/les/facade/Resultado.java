package br.com.fatec.les.facade;

import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.model.assets.ADominio;

public class Resultado {

	private ArrayList<Mensagem> mensagens;
	private List<ADominio> entidades;
	

	public ArrayList<Mensagem> getMensagens() {
		return mensagens;
	}
	public void setMensagens(ArrayList<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	public List<ADominio> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<ADominio> entidades) {
		this.entidades = entidades;
	}
}
