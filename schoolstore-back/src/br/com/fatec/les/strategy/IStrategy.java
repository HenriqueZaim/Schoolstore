package br.com.fatec.les.strategy;

import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.assets.IDominio;

public interface IStrategy {
	public Mensagem execute(IDominio iDominio);
}
