package br.com.fatec.les.model.pagamento.cupom;

import java.time.LocalDateTime;

public class CupomPromocional extends Cupom{

	private LocalDateTime validade;

	public LocalDateTime getValidade() {
		return validade;
	}

	public void setValidade(LocalDateTime validade) {
		this.validade = validade;
	}
	
}
