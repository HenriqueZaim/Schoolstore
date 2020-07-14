package br.com.fatec.les.model.produto;

import br.com.fatec.les.model.config.EntidadeDominio;

public class Precificacao extends EntidadeDominio{

	private float percentual;

	public float getPercentual() {
		return percentual;
	}

	public void setPercentual(float percentual) {
		this.percentual = percentual;
	}
}
