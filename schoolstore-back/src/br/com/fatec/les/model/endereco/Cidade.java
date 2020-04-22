package br.com.fatec.les.model.endereco;

import br.com.fatec.les.model.assets.EntidadeNomeada;

public class Cidade extends EntidadeNomeada{

	private Estado estado;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
