package br.com.fatec.les.command;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.config.ADominio;

public interface ICommand {
	public Resultado execute(ADominio aDominio); 
}
