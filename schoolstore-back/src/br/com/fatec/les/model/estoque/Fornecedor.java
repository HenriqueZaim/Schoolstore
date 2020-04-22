package br.com.fatec.les.model.estoque;

import br.com.fatec.les.model.assets.EntidadeNomeada;

public class Fornecedor extends EntidadeNomeada{

	private String cnpj;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
