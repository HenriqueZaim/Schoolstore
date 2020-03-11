package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.facade.Result;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.Genero;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;

public class UsuarioVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Usuario usuario = new Usuario();
		
		if(request.getParameter("tarefa").equals("atualizarUsuario") ||
				request.getParameter("tarefa").equals("deletarUsuario") || 
				request.getParameter("tarefa").equals("editaUsuario")) {
			usuario.setId(Long.parseLong(request.getParameter("txtId")));
		}
		
		usuario.setAtivo(true);
		usuario.setNome(request.getParameter("txtNome"));
		usuario.setEmail(request.getParameter("txtEmail"));
		usuario.setGenero(Genero.MASCULINO);
		usuario.setNumeroTelefone(request.getParameter("txtTelefone"));
		usuario.setNumeroDocumento(request.getParameter("txtDocumento"));
		usuario.setSenha(request.getParameter("txtSenha"));	
		
		return usuario;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    
		String operacao = (String) request.getAttribute("operacao");

		if(operacao.equals("consultarUsuario")) {
			
			List<Usuario> usuarios = new ArrayList<Usuario>();
			Result result = new Result();
			
			result = (Result)request.getAttribute("resultado");
			
			for(EntidadeDominio u : result.getEntidades()) {
				Usuario user = (Usuario) u;
				usuarios.add(user);
			}
			
			
			request.setAttribute("usuarios", usuarios);
			request.getRequestDispatcher("usuariosLista.jsp").
			forward(request, response);
		}
		else if(operacao.equals("editaUsuario")) {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			Result result = new Result();
			
			result = (Result)request.getAttribute("resultado");
			
			for(EntidadeDominio u : result.getEntidades()) {
				Usuario user = (Usuario) u;
				usuarios.add(user);
			}
			
			Usuario u = usuarios.get(0);
			
			
			request.setAttribute("usuario", u);
			request.getRequestDispatcher("usuarioEditar.jsp").
			forward(request, response);
		}
		else {
			request.getRequestDispatcher("usuarioMenu.jsp").
			forward(request, response);
		}
	}
}
