package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fatec.les.facade.Result;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Imagem;
import br.com.fatec.les.model.Usuario;

public class UsuarioVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Usuario usuario = new Usuario();
		
		if(request.getParameter("tarefa").equals("atualizarCliente") ||
				request.getParameter("tarefa").equals("deletarCliente") || 
				request.getParameter("tarefa").equals("editaCliente")) {
			usuario.setId(Long.parseLong(request.getParameter("txtUsuarioId")));
		}
		
		usuario.setEmail(request.getParameter("txtEmail"));
		usuario.setSenha(request.getParameter("txtSenha"));	
//		usuario.setImagem((Imagem)imagemVH.getEntidade(request));
		
		return usuario;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    
	}
}
