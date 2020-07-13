package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.endereco.Estado;

public class EstadoVH implements IViewHelper{

	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		Estado estado = new Estado();
		return estado;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			List<Estado> estados = new ArrayList<Estado>();
			Resultado result = new Resultado();
			
			result = (Resultado)request.getAttribute("resultado");
			
			for(ADominio c : result.getEntidades()) {
				Estado estado = (Estado) c;
				estados.add(estado);
			}
			
			String json = new Gson().toJson(estados);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		
	}

}
