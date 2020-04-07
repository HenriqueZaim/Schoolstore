package br.com.fatec.les.strategy;

import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;

public class EnderecoEstadoStrategy implements IStrategy{

	@Override
	public String execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
//		if(cliente.getEndereco().getCidade().getEstado().getId() == null) {
//			return "Selecione o estado de residência";
//		}
		return "";
	}

}
