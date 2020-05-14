package br.com.fatec.les.command;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.assets.EntidadeDominio;

public class DeletarCommand extends AbstractCommand{
	@Override
	public Resultado execute(ADominio aDominio) {
		return facade.deletar((EntidadeDominio) aDominio);
	}
}
