package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import br.com.fatec.les.DAO.ClienteDao;
import br.com.fatec.les.facade.Mensagem;
import br.com.fatec.les.facade.MensagemStatus;
import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.assets.Imagem;
import br.com.fatec.les.model.usuario.Cliente;
import br.com.fatec.les.model.usuario.Usuario;

public class UsuarioVH implements IViewHelper{

	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		Usuario usuario = new Usuario();
		ImagemVH imagemVh = new ImagemVH();
		String tarefa = request.getParameter("tarefa");
		
		if(tarefa.equals("atualizarCliente") ||
				tarefa.equals("deletarCliente") || 
				tarefa.equals("editaCliente")) {
			usuario.setId(Long.parseLong(request.getParameter("txtUsuarioId")));
		}
		
		if(tarefa.equals("efetuarPedido") || tarefa.equals("consultarCupons") || tarefa.equals("efetuarTroca") || tarefa.equals("efetuarCancelamento") | tarefa.equals("alterarStatusPedido")  ) {
			if(request.getParameter("txtUsuarioId") != null)
				usuario.setId(Long.parseLong(request.getParameter("txtUsuarioId")));
			
			return usuario;
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
	            
	            if(!usuario.isAdmin()) {
	            	try{
	            		ClienteDao clienteDao = new ClienteDao();
			            Cliente cliente = new Cliente();
			            cliente.setUsuario(usuario);
			            cliente = (Cliente) clienteDao.consultar(cliente).get(0);
			            session.setAttribute("cliente", cliente);
	            	}catch(SQLException e) {
	            		
	            	}
	            }
	            
	            session.setAttribute("status", "on");
	            session.setAttribute("usuario", usuario);

	            response.sendRedirect("clienteMenu.jsp");
			}
   
	}
}
