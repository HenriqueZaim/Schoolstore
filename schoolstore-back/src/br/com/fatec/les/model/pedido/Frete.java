package br.com.fatec.les.model.pedido;

import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.endereco.Endereco;

public class Frete extends EntidadeDominio{

	private float valor;
	private int previsaoEmDias;
	private Endereco endereco;
	
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getPrevisaoEmDias() {
		return previsaoEmDias;
	}
	public void setPrevisaoEmDias(int previsaoEmDias) {
		this.previsaoEmDias = previsaoEmDias;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
