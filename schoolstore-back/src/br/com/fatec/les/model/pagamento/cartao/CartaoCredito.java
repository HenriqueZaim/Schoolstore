package br.com.fatec.les.model.pagamento.cartao;

import br.com.fatec.les.model.assets.EntidadeDominio;

public class CartaoCredito extends EntidadeDominio{

	private String numero;
	private String codigo;
	private String nomeImpresso;
	private boolean favorito;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNomeImpresso() {
		return nomeImpresso;
	}
	public void setNomeImpresso(String nomeImpresso) {
		this.nomeImpresso = nomeImpresso;
	}
	public boolean isFavorito() {
		return favorito;
	}
	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}
}
