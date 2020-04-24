package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.assets.Imagem;
import br.com.fatec.les.model.usuario.Cliente;
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
		
		usuario.setAdmin(false);
		usuario.setEmail(request.getParameter("txtEmail"));
		usuario.setSenha(request.getParameter("txtSenha"));	
		usuario.setImagem((Imagem)imagemVh.getEntidade(request));
		return usuario;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    
			Resultado resultado = new Resultado();
			
			resultado = (Resultado)request.getAttribute("resultado");
			if(resultado.getEntidades().isEmpty() || resultado.getEntidades() == null) {
				Mensagem mensagem = new Mensagem();
				mensagem.setMensagem("Login e/ou senha inválido(s)");
				mensagem.setMensagemStatus(MensagemStatus.ERRO);
				
				ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
				mensagens.add(mensagem);
				
				resultado = new Resultado();
				resultado.setMensagens(mensagens);
				
				request.setAttribute("resultado", resultado);
				request.getRequestDispatcher("usuarioLogin.jsp").
				forward(request, response);
			}else {
				Usuario usuario = (Usuario) resultado.getEntidades().get(0);
			     
	            HttpSession session = request.getSession();
	            
	            session.invalidate();
	            session = request.getSession();
	            session.setMaxInactiveInterval(15*60);
	            
	            session.setAttribute("status", "on");
	            session.setAttribute("usuario", usuario);

	            response.sendRedirect("clienteMenu.jsp");
			}
   
	}
}
