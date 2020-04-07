package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;

public class EnderecoNumeroStrategy implements IStrategy{

	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;	
//		if(cliente.getEndereco().getNumero() == 0) {
//			return "Número residencial é obrigatório";
//		}
		return "";
	}

}
