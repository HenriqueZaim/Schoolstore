package br.com.fatec.les.strategy;

import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;

public class UsuarioEmailStrategy implements IStrategy {
	@Override
	public String execute(IDominio iDominio) {
		Usuario usuario = (Usuario) iDominio;
		if(usuario.getEmail().isEmpty()) {
			return "E-mail obrigatório";
		}
		return "";
	}
}
