package br.com.fatec.les.strategy;

import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.usuario.Cliente;

public class ClienteTelefoneStrategy implements IStrategy{
	@Override
	public Mensagem execute(ADominio aDominio) {
		Cliente cliente = (Cliente) aDominio;
		Mensagem mensagem = new Mensagem();
		if(cliente.getNumeroTelefone().isEmpty() || cliente.getNumeroTelefone() == null) {
			mensagem.setMensagem("Insira um número de telefone");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}
		return mensagem;
	}
}
