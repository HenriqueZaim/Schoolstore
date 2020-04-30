package br.com.fatec.les;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.les.DAO.CarrinhoDao;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.usuario.Carrinho;
import br.com.fatec.les.model.usuario.ItemCarrinho;

public class Teste {

	public static void main(String[] args) {
		CarrinhoDao carrinhoDao = new CarrinhoDao();
		Carrinho carrinho = new Carrinho();
		
		carrinho.setId(2L);
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		try {
			carrinho = (Carrinho) carrinhoDao.consultar(carrinho).get(0);
			for(ItemCarrinho i : carrinho.getItensCarrinho()) {
				System.out.println(i.getProduto().getNome());
			}

		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
