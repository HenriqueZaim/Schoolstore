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
import br.com.fatec.les.model.produto.Produto;

public class ProdutoVH implements IViewHelper{

	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		Produto produto = new Produto();
		
		if(request.getParameter("txtProdutoId") != null) {
			produto.setId(Long.parseLong(request.getParameter("txtProdutoId")));
		}
		
		return produto;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tarefa = request.getParameter("tarefa");

		if(tarefa.equals("consultarProdutos")) {

			List<Produto> produtos = new ArrayList<Produto>();
			Resultado result = new Resultado();
			
			result = (Resultado)request.getAttribute("resultado");
			
			for(ADominio p : result.getEntidades()) {
				Produto produto = (Produto) p;
				produtos.add(produto);
			}
			
			
			String json = new Gson().toJson(produtos);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		if(tarefa.equals("consultarProduto")) {
			List<Produto> produtos = new ArrayList<Produto>();
			Resultado result = new Resultado();
			
			result = (Resultado)request.getAttribute("resultado");
			
			for(ADominio p : result.getEntidades()) {
				Produto produto = (Produto) p;
				produtos.add(produto);
			}
			
			Produto prod = produtos.get(0);

			request.setAttribute("produto", prod);
			request.getRequestDispatcher("produtoVisualizar.jsp").
			forward(request, response);
		}
		
	}

}
