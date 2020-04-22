package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.assets.Imagem;
import br.com.fatec.les.model.usuario.Usuario;

public class UsuarioVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Usuario usuario = new Usuario();
		ImagemVH imagemVh = new ImagemVH();
		String tarefa = request.getParameter("tarefa");
		
		if(tarefa.equals("atualizarCliente") ||
				tarefa.equals("deletarCliente") || 
				tarefa.equals("editaCliente")) {
			usuario.setId(Long.parseLong(request.getParameter("txtUsuarioId")));
		}
		
		usuario.setEmail(request.getParameter("txtEmail"));
		usuario.setSenha(request.getParameter("txtSenha"));	
		usuario.setImagem((Imagem)imagemVh.getEntidade(request));
		return usuario;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    
	}
}
