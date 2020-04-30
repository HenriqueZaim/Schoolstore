package br.com.fatec.les;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.DAO.CarrinhoDao;
import br.com.fatec.les.DAO.ProdutoDao;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.produto.Categoria;
import br.com.fatec.les.model.produto.Produto;
import br.com.fatec.les.model.usuario.Carrinho;
import br.com.fatec.les.model.usuario.ItemCarrinho;

public class Teste {

	public static void main(String[] args) {
		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = new Produto();
		List<EntidadeDominio> produtos = new ArrayList<EntidadeDominio>();
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		try {
			produtos.addAll(produtoDao.consultar(produto));
			Produto prod = new Produto();
			for(EntidadeDominio p : produtos) {
				prod = (Produto) p;

				for(Categoria cat : prod.getCategorias()) {
					if(cat.getNome().equals("Mesa")) {
						System.out.println(prod.getNome());
					}
				}
				
			}

		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
