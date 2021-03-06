package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.pagamento.cupom.Cupom;
import br.com.fatec.les.model.usuario.Usuario;

public class CupomVH implements IViewHelper {

	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		Cupom cupom = new Cupom();
		String tarefa = request.getParameter("tarefa");
		UsuarioVH usuarioVH = new UsuarioVH();

		if (tarefa.equals("consultarCupons")) {
			cupom.setUsuario((Usuario) usuarioVH.getEntidade(request));
		}
		if(tarefa.equals("inativarCupom")) {
			cupom.setId(Long.parseLong(request.getParameter("txtCupomId")));
		}
		if(tarefa.equals("adicionarCupomPromocional")) {
			cupom.setValor(Float.parseFloat(request.getParameter("txtValorCupom")));
			cupom.setCupomPromocional(true);
		}

		return cupom;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tarefa = request.getParameter("tarefa");
		
		if(tarefa.equals("adicionarCupomPromocional")) {
			request.getRequestDispatcher("cuponsLista.jsp").
			forward(request, response);
		}else if(tarefa.equals("inativarCupom")) {
			request.getRequestDispatcher("cuponsLista.jsp").
			forward(request, response);
		}else {
			Resultado resultado = new Resultado();
			resultado = (Resultado) request.getAttribute("resultado");

			String json = new Gson().toJson(resultado);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}



	}

}
