package br.com.fatec.les.model.usuario;

import java.util.List;

import br.com.fatec.les.model.assets.EntidadeNomeada;
import br.com.fatec.les.model.endereco.Endereco;
import br.com.fatec.les.model.pagamento.cartao.CartaoCredito;
import br.com.fatec.les.model.pedido.Pedido;

public class Cliente extends EntidadeNomeada{

	private String numeroTelefone;
	private String numeroDocumento;
	private Carrinho carrinho;
	private List<CartaoCredito> cartoesCredito;
	private List<Pedido> pedidos;
	private List<Endereco> enderecos;
	
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public List<CartaoCredito> getCartoesCredito() {
		return cartoesCredito;
	}
	public void setCartoesCredito(List<CartaoCredito> cartoesCredito) {
		this.cartoesCredito = cartoesCredito;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	private Usuario usuario;
	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



}
