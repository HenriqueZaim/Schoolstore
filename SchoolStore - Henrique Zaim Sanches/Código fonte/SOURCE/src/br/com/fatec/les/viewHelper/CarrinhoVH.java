package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.produto.Produto;
import br.com.fatec.les.model.usuario.Carrinho;
import br.com.fatec.les.model.usuario.ItemCarrinho;

public class CarrinhoVH implements IViewHelper{

	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		Carrinho carrinho = new Carrinho();
		ItemCarrinhoVH itemCarrinhoVH = new ItemCarrinhoVH();
		
		String tarefa = request.getParameter("tarefa");
		
		if(tarefa.equals("deletarCliente") || tarefa.equals("consultarCarrinho") || tarefa.equals("listarItensCarrinho")) {
			carrinho.setId(Long.parseLong(request.getParameter("txtCarrinhoId")));
		}else if(tarefa.equals("atualizarCarrinho") || tarefa.equals("efetuarPagamento")) {
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
		}else if(tarefa.equals("adicionarProduto")) {
			ItemCarrinho itemCarrinho = new ItemCarrinho();
			List<ItemCarrinho> itensCarrinho = new ArrayList<ItemCarrinho>();
			Produto produto = new Produto();
			
			produto.setId(Long.parseLong(request.getParameter("txtProdutoId")));
			
			itemCarrinho.setProduto(produto);
			itemCarrinho.setQuantidade(1);
			itensCarrinho.add(itemCarrinho);
			
			carrinho.setItensCarrinho(itensCarrinho);
			carrinho.setId(Long.parseLong(request.getParameter("txtCarrinhoId")));
			carrinho.setSubTotal(Float.parseFloat(request.getParameter("txtSubTotal")));

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
		}else if(tarefa.equals("efetuarPagamento")) {
			response.sendRedirect("pagamento.jsp");
		}else if(tarefa.equals("listarItensCarrinho")) {
			Carrinho carrinho = new Carrinho();
			
			Resultado result = new Resultado();
			
			result = (Resultado)request.getAttribute("resultado");
			
			carrinho = (Carrinho) result.getEntidades().get(0);
			
			String json = new Gson().toJson(carrinho);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}else {
			request.getRequestDispatcher("clienteMenu.jsp").
			forward(request, response);
		}
	}

}