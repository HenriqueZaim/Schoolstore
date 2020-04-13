package br.com.fatec.les.command;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.IDominio;

public class DeletarCommand extends AbstractCommand{
	@Override
	public Resultado execute(IDominio iDominio) {
		return facade.deletar((EntidadeDominio) iDominio);
	}
}
