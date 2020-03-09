package br.com.fatec.les.command;

import br.com.fatec.les.facade.Result;
import br.com.fatec.les.model.IDominio;

public interface ICommand {
	public Result execute(IDominio iDominio); 
}
