package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.pagamento.cartao.PagamentoCartao;

public class PagamentoCartaoVH implements IViewHelper{
	
	public PagamentoCartao getEntidadeSimples(HttpServletRequest request) {
		PagamentoCartao pagamentoCartao = new PagamentoCartao();
		CartaoCreditoVH cartaoCreditoVH = new CartaoCreditoVH();
		
//		pagamentoCartao.setValorTotalCartao(Float.parseFloat(request.getParameter("txtValorTotalCartao")));
		pagamentoCartao.setCartoesCredito(cartaoCreditoVH.getEntidades(request)); 
		
		return pagamentoCartao;
	}
	
	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		return null;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}