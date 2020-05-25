package br.com.fatec.les.model.pagamento.cupom;

import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.usuario.Usuario;

public class Cupom extends EntidadeDominio{

	private float valor;
	private Usuario usuario;

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
