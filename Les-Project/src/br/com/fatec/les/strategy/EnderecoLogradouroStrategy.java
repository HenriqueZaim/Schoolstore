package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;

public class EnderecoLogradouroStrategy implements IStrategy{

	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
		if(cliente.getEndereco().getLogradouro().isEmpty() || 
				cliente.getEndereco().getLogradouro() == null) {
			return "Logradouro é obrigatório";
		}
		return "";
	}

}
