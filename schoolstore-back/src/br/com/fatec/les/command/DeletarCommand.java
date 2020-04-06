package br.com.fatec.les.command;

import br.com.fatec.les.facade.Result;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.IDominio;

public class DeletarCommand extends AbstractCommand{
	@Override
	public Result execute(IDominio iDominio) {
		return facade.deletar((EntidadeDominio) iDominio);
	}
}
