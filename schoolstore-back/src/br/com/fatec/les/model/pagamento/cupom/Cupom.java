package br.com.fatec.les.model.pagamento.cupom;

import java.time.LocalDateTime;

import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.usuario.Usuario;

public class Cupom extends EntidadeDominio{

	private float valor;
	private Usuario usuario;
	private boolean cupomPromocional;
	private LocalDateTime dataHoraVencimento;

	public boolean isCupomPromocional() {
		return cupomPromocional;
	}

	public void setCupomPromocional(boolean cupomPromocional) {
		this.cupomPromocional = cupomPromocional;
	}

	public LocalDateTime getDataHoraVencimento() {
		return dataHoraVencimento;
	}

	public void setDataHoraVencimento(LocalDateTime dataHoraVencimento) {
		this.dataHoraVencimento = dataHoraVencimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}
