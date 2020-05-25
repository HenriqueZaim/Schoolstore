package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pagamento.cartao.CartaoCredito;
import br.com.fatec.les.model.pagamento.cartao.PagamentoCartao;

public class PagamentoCartaoVH implements IViewHelper{
	
	public ArrayList<PagamentoCartao> getEntidades(HttpServletRequest request){
		PagamentoCartao pagamentoCartao = new PagamentoCartao();
		CartaoCredito cartaoCredito = new CartaoCredito();
		ArrayList<PagamentoCartao> pagamentosCartao = new ArrayList<PagamentoCartao>();
		
		float valorTotalCupom = Float.parseFloat(request.getParameter("txtValorTotalCupom"));
		float valorTotal = Float.parseFloat(request.getParameter("txtSubTotal"));
		float valorTotalCartao = valorTotal - valorTotalCupom;
				
		if(request.getParameterValues("txtCartaoCreditoId") == null) {
			return pagamentosCartao;
		}else {
			String[] cartoesForm = request.getParameterValues("txtCartaoCreditoId");
			if(valorTotalCartao > 0) { // Está usando cartão de crédito
				valorTotalCartao /= cartoesForm.length;
			}
			for(int i = 0 ; i < cartoesForm.length ; i++) {
				pagamentoCartao = new PagamentoCartao();
				cartaoCredito = new CartaoCredito();
				cartaoCredito.setId(Long.parseLong(request.getParameterValues("txtCartaoCreditoId")[i]));
				pagamentoCartao.setCartaoCredito(cartaoCredito);
				pagamentoCartao.setValorTotalCartao(valorTotalCartao);
				
				pagamentosCartao.add(pagamentoCartao);
			}
		}
		return pagamentosCartao;
	}
		
	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		return null;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}