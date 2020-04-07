package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;

public class EnderecoCepStrategy implements IStrategy{

	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
//		if(cliente.getEndereco().getCep().isEmpty() || 
//				cliente.getEndereco().getCep() == null) {
//			return "CEP é obrigatório";
//		}
		return "";
	}

}
