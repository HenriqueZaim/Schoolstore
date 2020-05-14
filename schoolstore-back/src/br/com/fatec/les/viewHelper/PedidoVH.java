package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pagamento.FormaPagamento;
import br.com.fatec.les.model.pedido.Frete;
import br.com.fatec.les.model.pedido.Pedido;
import br.com.fatec.les.model.pedido.StatusPedido;
import br.com.fatec.les.model.usuario.Cliente;

public class PedidoVH implements IViewHelper{

	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		String tarefa = request.getParameter("tarefa");
		Pedido pedido = new Pedido();
		FreteVH freteVH = new FreteVH();
		ClienteVH clienteVH = new ClienteVH();
		ItemPedidoVH itemPedidoVH = new ItemPedidoVH();
		FormaPagamentoVH formaPagamentoVH = new FormaPagamentoVH();
		
		if(tarefa.equals("efetuarPedido")) {
			pedido.setStatusPedido(StatusPedido.EMPROCESSAMENTO);
			pedido.setValor(Float.parseFloat(request.getParameter("txtSubTotal")));
			pedido.setFrete((Frete)freteVH.getEntidade(request));
			pedido.setCliente((Cliente) clienteVH.getEntidade(request));
			pedido.setItensPedido(itemPedidoVH.getEntidades(request));
			pedido.setFormaPagamento((FormaPagamento)formaPagamentoVH.getEntidade(request));
		}
		return pedido;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}