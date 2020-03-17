package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;

public class EnderecoReferenciaStrategy implements IStrategy{

	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
		if(!cliente.getEndereco().getReferencia().isEmpty() &&
				cliente.getEndereco().getReferencia() != null && 
				cliente.getEndereco().getReferencia().length() >= 100) {
			return "Referência de endereço muito grande";
		}
		return "";
	}

}
