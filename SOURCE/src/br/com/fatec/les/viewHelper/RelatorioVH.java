package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;

import com.google.gson.Gson;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.config.Relatorio;

public class RelatorioVH implements IViewHelper{

	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		Relatorio relatorio = new Relatorio();

		relatorio.setDataInicio(request.getParameter("txtDataInicio") == "" ? null : LocalDate.parse(request.getParameter("txtDataInicio")));
		relatorio.setDataFim(request.getParameter("txtDataFim") == "" ? null : LocalDate.parse(request.getParameter("txtDataFim")));
		
		return relatorio;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Resultado resultado = new Resultado();
		resultado = (Resultado)request.getAttribute("resultado");
		
		String json = new Gson().toJson(resultado);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}

	
}
