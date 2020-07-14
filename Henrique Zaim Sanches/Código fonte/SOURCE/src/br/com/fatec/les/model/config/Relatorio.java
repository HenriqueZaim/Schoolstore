package br.com.fatec.les.model.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Relatorio extends EntidadeDominio{

	private LocalDate dataInicio;
	private LocalDate dataFim;

	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
	
}
