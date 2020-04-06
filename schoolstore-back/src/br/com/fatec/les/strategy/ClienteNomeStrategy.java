package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;

public class ClienteNomeStrategy implements IStrategy{
	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
		if(cliente.getNome().isEmpty() || cliente.getNome() == null) {
			return "Nome é obrigatório";
		}
		return "";
	}
}
