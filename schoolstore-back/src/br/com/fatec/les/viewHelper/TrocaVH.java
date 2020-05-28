package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pedido.Pedido;
import br.com.fatec.les.model.troca.StatusTroca;
import br.com.fatec.les.model.troca.Troca;
import br.com.fatec.les.model.usuario.Cliente;

public class TrocaVH implements IViewHelper{
	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		String tarefa = request.getParameter("tarefa");
		Troca troca = new Troca();

		ClienteVH clienteVH = new ClienteVH();
		PedidoVH pedidoVH = new PedidoVH();
		ItemTrocaVH itemTrocaVH = new ItemTrocaVH();

		if(tarefa.equals("efetuarTroca")) {
			troca.setStatusTroca(StatusTroca.EMPROCESSAMENTO);
			troca.setCliente((Cliente)clienteVH.getEntidade(request));
			troca.setPedido((Pedido)pedidoVH.getEntidade(request));
			troca.setItensTroca(itemTrocaVH.getEntidades(request));
		}
		return troca;
	}
	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("clienteMenu.jsp").
		forward(request, response);
	}
}
