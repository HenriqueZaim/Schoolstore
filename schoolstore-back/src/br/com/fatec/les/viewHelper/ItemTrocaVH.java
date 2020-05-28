package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.produto.Produto;
import br.com.fatec.les.model.troca.ItemTroca;

public class ItemTrocaVH implements IViewHelper{
	
	public ArrayList<ItemTroca> getEntidades(HttpServletRequest request){
		ArrayList<ItemTroca> itensTroca = new ArrayList<ItemTroca>();
		ItemTroca itemTroca = new ItemTroca();
		Produto produto = new Produto();

		if(request.getParameterValues("txtProdutoId") == null) {
			return itensTroca;
		}else {
			String[] produtos = request.getParameterValues("txtProdutoId");
			for(int i = 0 ; i < produtos.length ; i++) {
				itemTroca = new ItemTroca();
				produto = new Produto();

				produto.setId(Long.parseLong(request.getParameterValues("txtProdutoId")[i]));
				itemTroca.setProduto(produto);
				itemTroca.setQuantidade(Integer.parseInt(request.getParameterValues("txtQuantidadeProduto")[i]));

				itensTroca.add(itemTroca);
			}

		}
		return itensTroca;
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
