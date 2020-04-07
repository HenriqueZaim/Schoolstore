package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.Endereco;
import br.com.fatec.les.model.IDominio;

public class EnderecoBairroStrategy implements IStrategy{

	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
//		if(cliente.getEndereco().getBairro().isEmpty() || 
//				cliente.getEndereco().getBairro() == null) {
//			return "Bairro é obrigatório";
//		}
		return "";
	}

}
