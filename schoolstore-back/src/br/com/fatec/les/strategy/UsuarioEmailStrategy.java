package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;

public class UsuarioEmailStrategy implements IStrategy {
	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
		if(cliente.getUsuario().getEmail().isEmpty() ||
				cliente.getUsuario().getEmail() == null) {
			return "E-mail é obrigatório";
		}
		return "";
	}
}
