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
		
//		if(!tarefa.contentEquals("cadastrarCliente")) {
//			cliente.setId(request.getParameter("txtClienteId") == "" ? 0 : Long.parseLong(request.getParameter("txtClienteId")));
//		}
		if(request.getParameter("tarefa").equals("atualizarCliente") ||
				request.getParameter("tarefa").equals("deletarCliente") || 
				request.getParameter("tarefa").equals("editaCliente")) {
			cliente.setId(Long.parseLong(request.getParameter("txtClienteId")));
		}

//		cliente.setDataNascimento(LocalDate.parse(request.getParameter("txtDataNascimento")));
		cliente.setNome(request.getParameter("txtNome"));
		cliente.setNumeroDocumento(request.getParameter("txtNumeroDocumento"));
		cliente.setNumeroTelefone(request.getParameter("txtNumeroTelefone"));
		cliente.setUsuario((Usuario)usuarioVH.getEntidade(request));
		cliente.setEndereco((Endereco)enderecoVH.getEntidade(request));
		
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
		}else if(operacao.equals("cadastrarCliente")) {
			Result resultado = new Result();
			resultado.setMensagem((ArrayList<String>) request.getAttribute("mensagens"));
			if(resultado.getMensagem().size() > 1) {
				request.getRequestDispatcher("index.jsp").
				forward(request, response);
			}else {
				request.getRequestDispatcher("clienteMenu.jsp").
				forward(request, response);
			}
		}
		else {
			request.getRequestDispatcher("clienteMenu.jsp").
			forward(request, response);
		}
		
	}

}
