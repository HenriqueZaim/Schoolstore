package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.pagamento.FormaPagamento;

public class FormaPagamentoVH implements IViewHelper{
	
	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		FormaPagamento formaPagamento = new FormaPagamento();
		PagamentoCartaoVH pagamentoCartaoVH = new PagamentoCartaoVH();
		PagamentoCupomVH pagamentoCupomVH = new PagamentoCupomVH();
		String tarefa = request.getParameter("tarefa");
		if(tarefa.equals("efetuarCancelamento")) {
			formaPagamento.setPagamentosCupom(pagamentoCupomVH.getEntidades(request));
		}else {
			formaPagamento.setValorTotal(Float.parseFloat(request.getParameter("txtSubTotal"))); // subtrair com o valor do pedido
			formaPagamento.setPagamentosCartao(pagamentoCartaoVH.getEntidades(request));
			formaPagamento.setPagamentosCupom(pagamentoCupomVH.getEntidades(request));
		}

		return formaPagamento;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}