package br.com.fatec.les.model.config;

import java.time.LocalDateTime;

public class EntidadeDominio extends ADominio{
	private boolean ativo;
	private LocalDateTime dataHoraCriacao;
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public LocalDateTime getDataHoraCriacao() {
		return dataHoraCriacao;
	}
	public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}
}
