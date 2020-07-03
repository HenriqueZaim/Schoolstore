package br.com.fatec.les.command;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.config.EntidadeDominio;

public class SalvarCommand extends AbstractCommand{
	@Override
	public Resultado execute(ADominio aDominio) {
		return facade.salvar((EntidadeDominio)aDominio);
	}
}
