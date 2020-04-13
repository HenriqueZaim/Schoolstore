package br.com.fatec.les.strategy;

import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.IDominio;

public class UsuarioSenhaStrategy implements IStrategy{
	@Override
	public Mensagem execute(IDominio iDominio) {
		Cliente cliente = (Cliente) iDominio;
		Mensagem mensagem= new Mensagem();
		if(cliente.getUsuario().getSenha().isEmpty() || 
				cliente.getUsuario().getSenha() == null) {
			mensagem.setMensagem("Insira uma senha de login");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}

		return mensagem;
	}
}
