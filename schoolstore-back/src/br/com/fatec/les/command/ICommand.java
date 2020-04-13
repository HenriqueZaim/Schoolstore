package br.com.fatec.les.command;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.IDominio;

public interface ICommand {
	public Resultado execute(IDominio iDominio); 
}
