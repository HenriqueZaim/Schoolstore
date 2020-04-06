package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;

public class UsuarioSenhaStrategy implements IStrategy{
	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
		if(cliente.getUsuario().getSenha().isEmpty() || 
				cliente.getUsuario().getSenha() == null) {
			return "Senha é obrigatório";
		}
		
		if(cliente.getUsuario().getSenha().length() < 8) {
			return "Senha fraca - tamanho mínimo de 8 caracteres";
		}
		return "";
	}
}
