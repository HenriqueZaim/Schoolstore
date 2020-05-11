package br.com.fatec.les.strategy;

import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.usuario.Cliente;

public class UsuarioEmailStrategy implements IStrategy {
	@Override
	public Mensagem execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
		Mensagem mensagem = new Mensagem();
		if(cliente.getUsuario().getEmail().isEmpty() ||
				cliente.getUsuario().getEmail() == null) {
			mensagem.setMensagem("Insira um e-mail de login");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}
		return mensagem;
	}
}
