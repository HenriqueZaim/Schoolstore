package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;

public class ClienteDataNascimentoStrategy implements IStrategy{

	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
		if(cliente.getDataNascimento() == null) {
			return "Data de nascimento é obrigatório";
		}
		return "";
	}

}
