package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.facade.Result;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.Estado;
import br.com.fatec.les.model.IDominio;

public class EstadoVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Estado estado = new Estado();
		return estado;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String operacao = (String) request.getAttribute("operacao");
		
		if(operacao.equals("cadastroCliente")) {
			
			List<Estado> estados = new ArrayList<Estado>();
			Result result = new Result();
			
			result = (Result)request.getAttribute("resultado");
			
			for(EntidadeDominio c : result.getEntidades()) {
				Estado estado = (Estado) c;
				
				estados.add(estado);
			}
			
			request.setAttribute("estados", estados);
			request.getRequestDispatcher("clienteCadastro.jsp").
			forward(request, response);
		}
		
	}

}
