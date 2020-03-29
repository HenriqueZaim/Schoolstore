package br.com.fatec.les.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import br.com.fatec.les.command.AtualizarCommand;
import br.com.fatec.les.command.ConsultarCommand;
import br.com.fatec.les.command.DeletarCommand;
import br.com.fatec.les.command.ICommand;
import br.com.fatec.les.command.SalvarCommand;
import br.com.fatec.les.facade.Result;
import br.com.fatec.les.model.Endereco;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;
import br.com.fatec.les.viewHelper.ClienteVH;
import br.com.fatec.les.viewHelper.IViewHelper;
import br.com.fatec.les.viewHelper.UsuarioVH;

@WebServlet(name = "servletController", urlPatterns = "/cliente")
public class ServletController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Map<String, ICommand> commandMap;
    private static Map<String, IViewHelper> vhMap;
    private static String tarefa = null;
    private static IViewHelper vhCorrespondente;
    
    public ServletController() {
    	vhMap = new HashMap<String, IViewHelper>();
    	
    	vhMap.put("cadastrarCliente", new ClienteVH());
    	vhMap.put("consultarCliente", new ClienteVH());
    	vhMap.put("atualizarCliente", new ClienteVH());
    	vhMap.put("deletarCliente", new ClienteVH());
    	vhMap.put("editaCliente", new ClienteVH());
    	
    	commandMap = new HashMap<String, ICommand>();
    	
    	commandMap.put("cadastrarCliente", new SalvarCommand());
    	commandMap.put("editaCliente", new ConsultarCommand());
    	commandMap.put("deletarCliente", new DeletarCommand());
    	commandMap.put("consultarCliente", new ConsultarCommand());
    	commandMap.put("atualizarCliente", new AtualizarCommand());
	}
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
        	Result resultado;
        	tarefa = request.getParameter("tarefa");
        	           
            vhCorrespondente = vhMap.get(tarefa);
            IDominio entidadeCorrespondente = vhCorrespondente.getEntidade(request);
            ICommand commandCorrespondente = commandMap.get(tarefa);
            resultado = commandCorrespondente.execute(entidadeCorrespondente);
            
            request.setAttribute("resultado", resultado);
            request.setAttribute("operacao", tarefa);
            request.setAttribute("mensagens", resultado.getMensagem());
            
            vhCorrespondente.setEntidade(request, response);
            
        }catch(Exception e){
            System.err.println(e.toString());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
