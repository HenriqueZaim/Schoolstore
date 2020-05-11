package br.com.fatec.les.model.troca;

import java.util.List;

import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.pedido.Pedido;
import br.com.fatec.les.model.usuario.Cliente;

public class Troca extends EntidadeDominio{

	private Pedido pedido;
	private StatusTroca statusTroca;
	private Cliente cliente;
	private List<ItemTroca> itensTroca;
}
