package br.com.fatec.les.strategy;

import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;

public class UsuarioDocumentoStrategy implements IStrategy{
	@Override
	public String execute(IDominio iDominio) {
		Usuario usuario = (Usuario) iDominio;
		if(usuario.getNumeroDocumento().isEmpty()) {
			return "Número de documento obrigatório";
		}
		return "";
	}
}
