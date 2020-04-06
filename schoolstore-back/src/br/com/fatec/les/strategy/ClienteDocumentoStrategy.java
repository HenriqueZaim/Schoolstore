package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;

public class ClienteDocumentoStrategy implements IStrategy{
	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
		if(cliente.getNumeroDocumento().isEmpty() || cliente.getNumeroDocumento() == null) {
			return "Número de documento é obrigatório";
		}
		return "";
	}
}
