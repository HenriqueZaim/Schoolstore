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
import br.com.fatec.les.facade.Resultado;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.viewHelper.CarrinhoVH;
import br.com.fatec.les.viewHelper.ClienteVH;
import br.com.fatec.les.viewHelper.EstadoVH;
import br.com.fatec.les.viewHelper.IViewHelper;
import br.com.fatec.les.viewHelper.PedidoVH;
import br.com.fatec.les.viewHelper.ProdutoVH;
import br.com.fatec.les.viewHelper.UsuarioVH;

@WebServlet(name = "servletController", urlPatterns = "/app")
public class ServletController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Map<String, ICommand> commandMap;
    private static Map<String, IViewHelper> vhMap;
    private static String tarefa = null;
    private static IViewHelper vhCorrespondente;
    
    public ServletController() {
    	vhMap = new HashMap<String, IViewHelper>();
    	commandMap = new HashMap<String, ICommand>();
    	
    	// Crud cliente
    	vhMap.put("cadastrarCliente", new ClienteVH());
    	vhMap.put("consultarCliente", new ClienteVH());
    	vhMap.put("atualizarCliente", new ClienteVH());
    	vhMap.put("deletarCliente", new ClienteVH());
    	vhMap.put("editaCliente", new ClienteVH());
    	commandMap.put("cadastrarCliente", new SalvarCommand());
    	commandMap.put("editaCliente", new ConsultarCommand());
    	commandMap.put("deletarCliente", new DeletarCommand());
    	commandMap.put("consultarCliente", new ConsultarCommand());
    	commandMap.put("atualizarCliente", new AtualizarCommand());
    	
    	// Get nos estados e cidades
    	vhMap.put("cadastroCliente", new EstadoVH());
    	commandMap.put("cadastroCliente", new ConsultarCommand());
    	
    	// Pedido
    	vhMap.put("efetuarPedido", new PedidoVH());
    	commandMap.put("efetuarPedido", new SalvarCommand());
    	
    	// Login de usuário
    	// Vai consultar com um e-mail e senha, e vai trazer o usuário todo + cliente
    	vhMap.put("loginUsuario", new UsuarioVH());
    	vhMap.put("logoutUsuario", new UsuarioVH());
    	commandMap.put("loginUsuario", new ConsultarCommand());
    	commandMap.put("logoutUsuario", new ConsultarCommand());
    	
    	// Carrinho
    	vhMap.put("consultarCarrinho", new CarrinhoVH());
    	vhMap.put("atualizarCarrinho", new CarrinhoVH());
    	vhMap.put("listarItensCarrinho", new CarrinhoVH());
    	commandMap.put("consultarCarrinho", new ConsultarCommand());
    	commandMap.put("atualizarCarrinho", new AtualizarCommand());
    	commandMap.put("listarItensCarrinho", new ConsultarCommand());
    	
    	// produtos
    	vhMap.put("consultarProdutos", new ProdutoVH());
    	commandMap.put("consultarProdutos", new ConsultarCommand());
    	
    	// Visualziar produto
    	vhMap.put("consultarProduto", new ProdutoVH());
    	commandMap.put("consultarProduto", new ConsultarCommand());
    	
    	// Adicionar produto no carrinho
    	vhMap.put("adicionarProduto", new CarrinhoVH());
    	commandMap.put("adicionarProduto", new AtualizarCommand());
	}
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
        	tarefa = request.getParameter("tarefa");
        	Resultado resultado;           
            vhCorrespondente = vhMap.get(tarefa);
            ADominio entidadeCorrespondente = vhCorrespondente.getEntidade(request);
            ICommand commandCorrespondente = commandMap.get(tarefa);
            resultado = commandCorrespondente.execute(entidadeCorrespondente);
            
            request.setAttribute("resultado", resultado);
            
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
