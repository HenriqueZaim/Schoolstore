package br.com.fatec.les.strategy;

import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.model.config.ADominio;

public interface IStrategy {
	public Mensagem execute(ADominio ADominio);
}
