package br.com.fatec.les.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.command.AtualizarCommand;
import br.com.fatec.les.command.ConsultarCommand;
import br.com.fatec.les.command.DeletarCommand;
import br.com.fatec.les.command.ICommand;
import br.com.fatec.les.command.SalvarCommand;
import br.com.fatec.les.facade.Result;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.viewHelper.IViewHelper;
import br.com.fatec.les.viewHelper.UsuarioVH;

@WebServlet(name = "servletController", urlPatterns = "/usuario")
public class ServletController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Map<String, ICommand> commandMap;
    private static Map<String, IViewHelper> vhMap;
    private static String tarefa = null;
    private static IViewHelper vhCorrespondente;
    
    public ServletController() {
    	vhMap = new HashMap<String, IViewHelper>();
    	
    	vhMap.put("cadastrarUsuario", new UsuarioVH());
    	vhMap.put("consultarUsuario", new UsuarioVH());
    	
    	commandMap = new HashMap<String, ICommand>();
    	
    	commandMap.put("cadastrarUsuario", new SalvarCommand());
    	commandMap.put("atualizarUsuario", new AtualizarCommand());
    	commandMap.put("deletarUsuario", new DeletarCommand());
    	commandMap.put("consultarUsuario", new ConsultarCommand());
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
