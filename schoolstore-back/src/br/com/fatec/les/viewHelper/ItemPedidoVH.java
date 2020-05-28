package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pedido.ItemPedido;
import br.com.fatec.les.model.produto.Produto;

public class ItemPedidoVH implements IViewHelper{
	
	public ArrayList<ItemPedido> getEntidades(HttpServletRequest request){
		ArrayList<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
		ItemPedido itemPedido = new ItemPedido();
		Produto produto = new Produto();
		String tarefa = request.getParameter("tarefa");

		if(request.getParameterValues("txtProdutoId") == null) {
			return itensPedido;
		}else {
			String[] produtos = request.getParameterValues("txtProdutoId");
			if(tarefa.equals("efetuarPedido")) {
				for(int i = 0 ; i < produtos.length ; i++) {
					itemPedido = new ItemPedido();
					produto = new Produto();
					
					produto.setId(Long.parseLong(request.getParameterValues("txtProdutoId")[i]));			
					itemPedido.setProduto(produto);
					itemPedido.setQuantidade(Integer.parseInt(request.getParameterValues("txtQuantidadeProduto")[i]));
					
					itensPedido.add(itemPedido);
				}
			}else {
				for(int i = 0 ; i < produtos.length ; i++) {
					itemPedido = new ItemPedido();
					produto = new Produto();
					
					produto.setId(Long.parseLong(request.getParameterValues("txtProdutoId")[i]));
					itemPedido.setId(Long.parseLong(request.getParameterValues("txtItemPedidoId")[i]));
					itemPedido.setProduto(produto);
					itemPedido.setQuantidade(Integer.parseInt(request.getParameterValues("txtQuantidadeProduto")[i]));

					itensPedido.add(itemPedido);
				}
			}
		}
		return itensPedido;
	}

	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}