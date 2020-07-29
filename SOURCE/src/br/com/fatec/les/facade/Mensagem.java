package br.com.fatec.les.facade;

public class Mensagem {

	private String mensagem;
	private MensagemStatus mensagemStatus;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public MensagemStatus getMensagemStatus() {
		return mensagemStatus;
	}
	public void setMensagemStatus(MensagemStatus mensagemStatus) {
		this.mensagemStatus = mensagemStatus;
	}
	
	
}
