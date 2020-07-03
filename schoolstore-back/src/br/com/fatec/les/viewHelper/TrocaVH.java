package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.config.ADominio;
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
		}else if(tarefa.equals("consultarTrocas")) {
			troca.setCliente((Cliente)clienteVH.getEntidade(request));
		}else if(tarefa.equals("alterarStatusTroca")) { 
			troca.setId(Long.parseLong(request.getParameter("txtTrocaId")));
			troca.setStatusTroca(StatusTroca.valueOf(request.getParameter("txtStatusTroca")));
			if(troca.getStatusTroca() == StatusTroca.TROCADO) {
				troca.setPedido((Pedido)pedidoVH.getEntidade(request));
				troca.setCliente((Cliente)clienteVH.getEntidade(request));
				troca.setItensTroca(itemTrocaVH.getEntidades(request));
			}
		}else if(tarefa.equals("excluirTroca")) {
			troca.setId(Long.parseLong(request.getParameter("txtTrocaId")));
		}
		return troca;
	}
	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tarefa = request.getParameter("tarefa");

		if(tarefa.equals("efetuarTroca")) {
			request.getRequestDispatcher("trocaLista.jsp").
			forward(request, response);
		}else if(tarefa.equals("consultarTrocas")) {
			Resultado resultado = new Resultado();
			
			resultado = (Resultado)request.getAttribute("resultado");
			
			String json = new Gson().toJson(resultado);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}else if(tarefa.equals("alterarStatusTroca") || tarefa.equals("excluirTroca")) { 
			request.getRequestDispatcher("trocaLista.jsp").
			forward(request, response);
		}
		
	}
}
