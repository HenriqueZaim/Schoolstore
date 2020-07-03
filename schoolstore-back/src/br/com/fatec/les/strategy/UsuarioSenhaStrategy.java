package br.com.fatec.les.strategy;

import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.usuario.Cliente;

public class UsuarioSenhaStrategy implements IStrategy{
	@Override
	public Mensagem execute(ADominio aDominio) {
		Cliente cliente = (Cliente) aDominio;
		Mensagem mensagem= new Mensagem();
		if(cliente.getUsuario().getSenha().isEmpty() || 
				cliente.getUsuario().getSenha() == null) {
			mensagem.setMensagem("Insira uma senha de login");
			mensagem.setMensagemStatus(MensagemStatus.ERRO);
		}

		return mensagem;
	}
}
