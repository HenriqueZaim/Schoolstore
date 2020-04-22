package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.endereco.Estado;

public class EstadoVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Estado estado = new Estado();
		return estado;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			List<Estado> estados = new ArrayList<Estado>();
			Resultado result = new Resultado();
			
			result = (Resultado)request.getAttribute("resultado");
			
			for(EntidadeDominio c : result.getEntidades()) {
				Estado estado = (Estado) c;
				estados.add(estado);
			}
			
			String json = new Gson().toJson(estados);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		
	}

}
