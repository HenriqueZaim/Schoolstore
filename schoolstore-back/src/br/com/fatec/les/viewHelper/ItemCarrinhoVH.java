package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.produto.Produto;
import br.com.fatec.les.model.usuario.ItemCarrinho;

public class ItemCarrinhoVH implements IViewHelper{
	
	public ArrayList<ItemCarrinho> getEntidades(HttpServletRequest request){
		ArrayList<ItemCarrinho> itensCarrinho = new ArrayList<ItemCarrinho>();
		ItemCarrinho itemCarrinho = new ItemCarrinho();
		Produto produto = new Produto();
		
		if(request.getParameterValues("txtProdutoId") == null) {
			return itensCarrinho;
		}else {
			String[] produtos = request.getParameterValues("txtProdutoId");
			for(int i = 0 ; i < produtos.length ; i++) {
				itemCarrinho = new ItemCarrinho();
				produto = new Produto();
				itemCarrinho.setQuantidade(Integer.parseInt(request.getParameterValues("txtQuantidade")[i]));
				produto.setId(Long.parseLong(request.getParameterValues("txtProdutoId")[i]));
				itemCarrinho.setProduto(produto);
				
				itensCarrinho.add(itemCarrinho);
			}
		}
		return itensCarrinho;
	}

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
