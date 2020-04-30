package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.usuario.Carrinho;
import br.com.fatec.les.model.usuario.Cliente;

public class CarrinhoVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Carrinho carrinho = new Carrinho();
		ItemCarrinhoVH itemCarrinhoVH = new ItemCarrinhoVH();
		String tarefa = request.getParameter("tarefa");
		if(tarefa.equals("deletarCliente") || tarefa.equals("consultarCarrinho")) {
			carrinho.setId(Long.parseLong(request.getParameter("txtCarrinhoId")));
		}else if(tarefa.equals("atualizarCarrinho")) {
			carrinho.setId(Long.parseLong(request.getParameter("txtCarrinhoId")));
			carrinho.setSubTotal(request.getParameter("txtValorTotal") == null ?
							null : Float.parseFloat(request.getParameter("txtValorTotal")));
			carrinho.setItensCarrinho(itemCarrinhoVH.getEntidades(request));
			if(carrinho.getItensCarrinho().isEmpty()) {
				carrinho.setValidade(null);
			}else {
				LocalDateTime validade = LocalDateTime.now();
				validade.plusDays(10);
				carrinho.setValidade(validade);
			}
		}
		else {
			return carrinho;
		}

		return carrinho;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tarefa = request.getParameter("tarefa");
		
		if(tarefa.equals("consultarCarrinho")) {
			request.getRequestDispatcher("clienteCarrinho.jsp").
			forward(request, response);
		}else {
			request.getRequestDispatcher("clienteMenu.jsp").
			forward(request, response);
		}
	}

}
