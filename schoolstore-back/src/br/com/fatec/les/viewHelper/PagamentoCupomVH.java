package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pagamento.cartao.CartaoCredito;
import br.com.fatec.les.model.pagamento.cartao.PagamentoCartao;
import br.com.fatec.les.model.pagamento.cupom.Cupom;
import br.com.fatec.les.model.pagamento.cupom.PagamentoCupom;

public class PagamentoCupomVH implements IViewHelper{
	
	public ArrayList<PagamentoCupom> getEntidades(HttpServletRequest request){
		PagamentoCupom pagamentoCupom = new PagamentoCupom();
		Cupom cupom = new Cupom();
		ArrayList<PagamentoCupom> pagamentosCupom = new ArrayList<PagamentoCupom>();
		
		if(request.getParameterValues("txtCupomId") == null) {
			return pagamentosCupom;
		}else {
			String[] cuponsForm = request.getParameterValues("txtCupomId");
			for(int i = 0 ; i < cuponsForm.length ; i++) {
				pagamentoCupom = new PagamentoCupom();
				cupom = new Cupom();
				cupom.setId(Long.parseLong(request.getParameterValues("txtCupomId")[i]));
				cupom.setValor(Float.parseFloat(request.getParameterValues("txtValorCupom")[i]));
				pagamentoCupom.setValorTotalCupom(Float.parseFloat(request.getParameterValues("txtValorCupom")[i]));
				pagamentoCupom.setCupom(cupom);
				pagamentosCupom.add(pagamentoCupom);
			}
		}
		return pagamentosCupom;
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
