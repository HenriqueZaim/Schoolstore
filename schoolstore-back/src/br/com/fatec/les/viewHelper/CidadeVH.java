package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.Cidade;
import br.com.fatec.les.model.IDominio;

public class CidadeVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Cidade cidade = new Cidade();
		
		cidade.setId(Long.parseLong((String) request.getAttribute("txtCidadeIdAtual")));
		
		return cidade;
		
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
