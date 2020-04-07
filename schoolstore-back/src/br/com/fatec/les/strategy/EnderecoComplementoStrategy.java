package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;

public class EnderecoComplementoStrategy implements IStrategy{

	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
//		if(!cliente.getEndereco().getComplemento().isEmpty() &&
//				cliente.getEndereco().getComplemento() != null && 
//				cliente.getEndereco().getComplemento().length() >= 100) {
//			return "Complemento de endereço muito grande";
//		}
		return "";
	}

}
