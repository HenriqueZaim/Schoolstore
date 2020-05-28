package br.com.fatec.les.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fatec.les.DAO.CarrinhoDao;
import br.com.fatec.les.DAO.ClienteDao;
import br.com.fatec.les.DAO.CupomDao;
import br.com.fatec.les.DAO.EstadoDao;
import br.com.fatec.les.DAO.IDao;
import br.com.fatec.les.DAO.PedidoDao;
import br.com.fatec.les.DAO.ProdutoDao;
import br.com.fatec.les.DAO.TrocaDao;
import br.com.fatec.les.DAO.UsuarioDao;
import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.endereco.Estado;
import br.com.fatec.les.model.pagamento.cupom.Cupom;
import br.com.fatec.les.model.pedido.Pedido;
import br.com.fatec.les.model.produto.Produto;
import br.com.fatec.les.model.troca.Troca;
import br.com.fatec.les.model.usuario.Carrinho;
import br.com.fatec.les.model.usuario.Cliente;
import br.com.fatec.les.model.usuario.Usuario;
import br.com.fatec.les.strategy.ClienteDocumentoStrategy;
import br.com.fatec.les.strategy.ClienteNomeStrategy;
import br.com.fatec.les.strategy.ClienteTelefoneStrategy;
import br.com.fatec.les.strategy.IStrategy;
import br.com.fatec.les.strategy.UsuarioEmailStrategy;
import br.com.fatec.les.strategy.UsuarioSenhaStrategy;


public class Facade implements IFacade{
	
	private Map<String, IDao> daoMap;
	private Map<String, ArrayList<IStrategy>> strategyMap;
	private Resultado resultado;
	private Mensagem mensagem;
	ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();

	
	public Facade() {
		daoMap = new HashMap<String, IDao>();
		strategyMap = new HashMap<String, ArrayList<IStrategy>>();
		
		ClienteDao clienteDao = new ClienteDao();
		EstadoDao estadoDao = new EstadoDao();
		UsuarioDao usuarioDao = new UsuarioDao();
		CarrinhoDao carrinhoDao = new CarrinhoDao();
		ProdutoDao produtoDao = new ProdutoDao();
		PedidoDao pedidoDao = new PedidoDao();
		CupomDao cupomDao = new CupomDao();
		TrocaDao trocaDao = new TrocaDao();
		
		
		daoMap.put(Cliente.class.getName(), clienteDao);
		daoMap.put(Estado.class.getName(), estadoDao);
		daoMap.put(Usuario.class.getName(), usuarioDao);
		daoMap.put(Carrinho.class.getName(), carrinhoDao);
		daoMap.put(Produto.class.getName(), produtoDao);
		daoMap.put(Pedido.class.getName(), pedidoDao);
		daoMap.put(Cupom.class.getName(), cupomDao);
		daoMap.put(Troca.class.getName(), trocaDao);


		IStrategy clienteDocumentoStrategy = new ClienteDocumentoStrategy();
		IStrategy clienteNomeStrategy = new ClienteNomeStrategy();
		IStrategy clienteTelefoneStrategy = new ClienteTelefoneStrategy();
		
		IStrategy usuarioEmailStrategy = new UsuarioEmailStrategy();
		IStrategy usuarioSenhaStrategy = new UsuarioSenhaStrategy();
		
		ArrayList<IStrategy> usuarioStrategies = new ArrayList<IStrategy>();
		usuarioStrategies.add(usuarioSenhaStrategy);
		usuarioStrategies.add(usuarioEmailStrategy);
		
		ArrayList<IStrategy> clienteStrategies = new ArrayList<IStrategy>();
		clienteStrategies.add(clienteDocumentoStrategy);
		clienteStrategies.add(clienteNomeStrategy);
		clienteStrategies.add(clienteTelefoneStrategy);
		
		clienteStrategies.addAll(usuarioStrategies);

		strategyMap.put(Cliente.class.getName(), clienteStrategies);

	}

	@Override
	public Resultado salvar(EntidadeDominio entidadeDominio) {
		resultado = new Resultado();
		mensagem = new Mensagem();
		mensagens = new ArrayList<Mensagem>();
		
		ArrayList<IStrategy> strategies = strategyMap.get(entidadeDominio.getClass().getName());
		
		if(strategies != null) {
			for(IStrategy s : strategies) {
				mensagem = s.execute(entidadeDominio);
				if(mensagem.getMensagemStatus() == MensagemStatus.ERRO) {
					mensagens.add(mensagem);
				}else
					continue;
			}
		}
		
		if(!mensagens.isEmpty()) {
			resultado.setMensagens(mensagens);;
			return resultado;
		}
		
		IDao daoEntidade = daoMap.get(entidadeDominio.getClass().getName());
		
		try {
			mensagem = daoEntidade.salvar(entidadeDominio);
			mensagens.add(mensagem);
			resultado.setMensagens(mensagens);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public Resultado atualizar(EntidadeDominio entidadeDominio) {
		resultado = new Resultado();
		mensagem = new Mensagem();
		mensagens = new ArrayList<Mensagem>();
		
	    IDao daoEntidade = daoMap.get(entidadeDominio.getClass().getName());

		try {
			mensagem = daoEntidade.atualizar(entidadeDominio);
			mensagens.add(mensagem);
	        resultado.setMensagens(mensagens);
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	        
		return resultado;
	}

	@Override
	public Resultado deletar(EntidadeDominio entidadeDominio) {
		resultado = new Resultado();
		mensagem = new Mensagem();
		mensagens = new ArrayList<Mensagem>();
		
	    IDao daoEntidade = daoMap.get(entidadeDominio.getClass().getName());

		try {
			mensagem = daoEntidade.deletar(entidadeDominio);
			mensagens.add(mensagem);
	        resultado.setMensagens(mensagens);
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	        
		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidadeDominio) {
		resultado = new Resultado();
		mensagem = new Mensagem();
		mensagens = new ArrayList<Mensagem>();
		
	    IDao daoEntidade = daoMap.get(entidadeDominio.getClass().getName());
	    ADominio aDominio = (ADominio) entidadeDominio;
		
	    try {
			List<ADominio> listaEntidades = daoEntidade.consultar(aDominio);			
	        resultado.setEntidades(listaEntidades);
	    } catch(SQLException e) {
	       	e.printStackTrace();
	    }
		return resultado;
	}
	
}
