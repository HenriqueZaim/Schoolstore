package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.Estado;
import br.com.fatec.les.model.IDominio;

public class EstadoVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Estado estado = new Estado();
		
		estado.setId(Long.parseLong(request.getParameter("txtEstadoId")));
		
		return estado;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
