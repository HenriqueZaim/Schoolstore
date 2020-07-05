package br.com.fatec.les.servlet;

import java.io.IOException;
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
import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.viewHelper.CarrinhoVH;
import br.com.fatec.les.viewHelper.CartaoCreditoVH;
import br.com.fatec.les.viewHelper.ClienteVH;
import br.com.fatec.les.viewHelper.CupomVH;
import br.com.fatec.les.viewHelper.EnderecoVH;
import br.com.fatec.les.viewHelper.EstadoVH;
import br.com.fatec.les.viewHelper.IViewHelper;
import br.com.fatec.les.viewHelper.PedidoVH;
import br.com.fatec.les.viewHelper.ProdutoVH;
import br.com.fatec.les.viewHelper.RelatorioVH;
import br.com.fatec.les.viewHelper.TrocaVH;
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
    	
    	// relatorio
    	vhMap.put("relatorio", new RelatorioVH());
    	commandMap.put("relatorio", new ConsultarCommand());
    	
    	// Get nos estados e cidades
    	vhMap.put("cadastroCliente", new EstadoVH()); //TODO: Alterar nome da tarefa
    	commandMap.put("cadastroCliente", new ConsultarCommand());

    	// Pedido
    	vhMap.put("efetuarPedido", new PedidoVH());
    	vhMap.put("consultarPedidos", new PedidoVH());
    	vhMap.put("pesquisaPedidosCliente", new PedidoVH());
    	vhMap.put("alterarStatusPedido", new PedidoVH());
    	vhMap.put("consultarPedido", new PedidoVH());
    	vhMap.put("consultarPedidoCancelamento", new PedidoVH());
    	vhMap.put("efetuarCancelamento", new PedidoVH());
    	commandMap.put("efetuarPedido", new SalvarCommand());
    	commandMap.put("consultarPedidos", new ConsultarCommand());
    	commandMap.put("alterarStatusPedido", new AtualizarCommand());
    	commandMap.put("efetuarCancelamento", new DeletarCommand());
    	commandMap.put("consultarPedido", new ConsultarCommand());
    	commandMap.put("consultarPedidoCancelamento", new ConsultarCommand());
    	commandMap.put("pesquisaPedidosCliente", new ConsultarCommand());

    	
    	// Endereços
    	vhMap.put("adicionarEndereco", new EnderecoVH());
    	vhMap.put("removerEndereco", new EnderecoVH());
    	commandMap.put("adicionarEndereco", new SalvarCommand());
    	commandMap.put("removerEndereco", new DeletarCommand());
    	
    	// Cartão
    	vhMap.put("adicionarCartao", new CartaoCreditoVH());
    	vhMap.put("removerCartao", new CartaoCreditoVH());
    	commandMap.put("adicionarCartao", new SalvarCommand());
    	commandMap.put("removerCartao", new DeletarCommand());
    	
    	// Troca
    	vhMap.put("efetuarTroca", new TrocaVH());
    	vhMap.put("consultarTrocas", new TrocaVH());
    	vhMap.put("alterarStatusTroca", new TrocaVH());
    	vhMap.put("excluirTroca", new TrocaVH());
    	commandMap.put("efetuarTroca", new SalvarCommand());
    	commandMap.put("alterarStatusTroca", new AtualizarCommand());
    	commandMap.put("consultarTrocas", new ConsultarCommand());
    	commandMap.put("excluirTroca", new DeletarCommand());
    	
    	// Login de usuário
    	vhMap.put("loginUsuario", new UsuarioVH());
    	vhMap.put("logoutUsuario", new UsuarioVH());
    	commandMap.put("loginUsuario", new ConsultarCommand());
    	commandMap.put("logoutUsuario", new ConsultarCommand());
    	
    	// Carrinho
    	vhMap.put("consultarCarrinho", new CarrinhoVH());
    	vhMap.put("atualizarCarrinho", new CarrinhoVH());
    	vhMap.put("listarItensCarrinho", new CarrinhoVH());
    	vhMap.put("efetuarPagamento", new CarrinhoVH());
    	commandMap.put("consultarCarrinho", new ConsultarCommand());
    	commandMap.put("atualizarCarrinho", new AtualizarCommand());
    	commandMap.put("efetuarPagamento", new AtualizarCommand());
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
    	
    	// Cupons
    	
    	vhMap.put("consultarCupons", new CupomVH());
    	vhMap.put("inativarCupom", new CupomVH());
    	vhMap.put("consultarCuponsPromocionais", new CupomVH());
    	vhMap.put("adicionarCupomPromocional", new CupomVH());
    	commandMap.put("consultarCupons", new ConsultarCommand());
    	commandMap.put("consultarCuponsPromocionais", new ConsultarCommand());
    	commandMap.put("inativarCupom", new DeletarCommand());
    	commandMap.put("adicionarCupomPromocional", new SalvarCommand());
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
