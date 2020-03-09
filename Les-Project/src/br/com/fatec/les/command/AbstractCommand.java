package br.com.fatec.les.command;

import br.com.fatec.les.facade.Facade;
import br.com.fatec.les.facade.IFacade;

public abstract class AbstractCommand implements ICommand{
	public IFacade facade = new Facade();
}
