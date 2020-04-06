package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;

public class ClienteTelefoneStrategy implements IStrategy{
	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
		if(cliente.getNumeroTelefone().isEmpty() || cliente.getNumeroTelefone() == null) {
			return "Número de telefone é obrigatório";
		}
		return "";
	}
}
