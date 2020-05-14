package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pagamento.FormaPagamento;

public class FormaPagamentoVH implements IViewHelper{
	
	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		FormaPagamento formaPagamento = new FormaPagamento();
		PagamentoCartaoVH pagamentoCartaoVH = new PagamentoCartaoVH();
		
		formaPagamento.setPagamentosCartao(pagamentoCartaoVH.getEntidades(request));
//		formaPagamento.setPagamentoCupom(pagamentoCupom);
//		formaPagamento.setValorTotal(valorTotal);
		
		return formaPagamento;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}