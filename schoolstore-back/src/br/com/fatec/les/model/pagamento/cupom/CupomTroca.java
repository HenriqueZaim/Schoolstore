package br.com.fatec.les.model.pagamento.cupom;

import br.com.fatec.les.model.usuario.Usuario;

public class CupomTroca extends Cupom{

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
