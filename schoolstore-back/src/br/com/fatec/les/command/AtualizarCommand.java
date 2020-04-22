package br.com.fatec.les.command;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;

public class AtualizarCommand extends AbstractCommand{
	@Override
	public Resultado execute(IDominio iDominio) {
		return facade.atualizar((EntidadeDominio) iDominio);
	}
}
