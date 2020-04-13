package br.com.fatec.les.strategy;

import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;

public class ClienteNomeStrategy implements IStrategy{
	@Override
	public Mensagem execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
		Mensagem mensagem = new Mensagem();
		if(cliente.getNome().isEmpty() || cliente.getNome() == null) {
			mensagem.setMensagem("Insira seu nome completo");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}
		return mensagem;
	}
}
