package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import br.com.fatec.les.facade.Result;
import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.Endereco;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;

public class ClienteVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Cliente cliente = new Cliente();
		EnderecoVH enderecoVH = new EnderecoVH();
		UsuarioVH usuarioVH = new UsuarioVH();
		
		String tarefa = request.getParameter("tarefa");
		
		if(tarefa.equals("atualizarCliente") ||
				tarefa.equals("deletarCliente") || 
				tarefa.equals("editaCliente")) {
			cliente.setId(Long.parseLong(request.getParameter("txtClienteId")));
		}

		cliente.setNome(request.getParameter("txtNome"));
		cliente.setNumeroDocumento(request.getParameter("txtNumeroDocumento"));
		cliente.setNumeroTelefone(request.getParameter("txtNumeroTelefone"));
		cliente.setUsuario((Usuario)usuarioVH.getEntidade(request));		
		cliente.setEnderecos(enderecoVH.getEntidades(request));

		return cliente;	
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String operacao = (String) request.getAttribute("operacao");

		if(operacao.equals("consultarCliente")) {
			
			List<Cliente> clientes = new ArrayList<Cliente>();
			Result result = new Result();
			
			result = (Result)request.getAttribute("resultado");
			
			for(EntidadeDominio c : result.getEntidades()) {
				Cliente user = (Cliente) c;
				clientes.add(user);
			}
			
			request.setAttribute("clientes", clientes);
			request.getRequestDispatcher("clientesLista.jsp").
			forward(request, response);
		}
		else if(operacao.equals("editaCliente")) {
			List<Cliente> clientes = new ArrayList<Cliente>();
			Result result = new Result();
			
			result = (Result)request.getAttribute("resultado");
			
			for(EntidadeDominio c : result.getEntidades()) {
				Cliente user = (Cliente) c;
				clientes.add(user);
			}
			
			Cliente cliente = clientes.get(0);
			
			
			request.setAttribute("cliente", cliente);
			request.getRequestDispatcher("clienteEditar.jsp").
			forward(request, response);
		}else {
//			response.sendRedirect("clienteMenu.jsp");
			request.getRequestDispatcher("clienteMenu.jsp").
			forward(request, response);
		}
		
	}

}
