package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.fatec.les.facade.Resultado;
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
		}else if(tarefa.equals("consultarPedidos")) {
			pedido.setCliente((Cliente) clienteVH.getEntidade(request));
		}else if(tarefa.equals("alterarStatusPedido")) {
			pedido.setId(Long.parseLong(request.getParameter("txtPedidoId")));
			pedido.setStatusPedido(StatusPedido.valueOf(request.getParameter("txtStatusPedido")));
		}
		return pedido;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tarefa = request.getParameter("tarefa");

		if(tarefa.equals("consultarPedidos")) {
			Resultado resultado = new Resultado();
			
			resultado = (Resultado)request.getAttribute("resultado");
			
			String json = new Gson().toJson(resultado);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			
		}else if(tarefa.equals("efetuarPedido")) {
			request.getRequestDispatcher("clienteMenu.jsp").
			forward(request, response);
		}else if(tarefa.equals("alterarStatusPedido")) {
			request.getRequestDispatcher("pedidoLista.jsp").
			forward(request, response);
		}
		
	}

}