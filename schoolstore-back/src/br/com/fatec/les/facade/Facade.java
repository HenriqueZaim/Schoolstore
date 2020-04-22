package br.com.fatec.les.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fatec.les.DAO.ClienteDao;
import br.com.fatec.les.DAO.EstadoDao;
import br.com.fatec.les.DAO.IDao;
import br.com.fatec.les.model.assets.EntidadeDominio;
import br.com.fatec.les.model.endereco.Estado;
import br.com.fatec.les.model.usuario.Cliente;
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
		
		daoMap.put(Cliente.class.getName(), clienteDao);
		daoMap.put(Estado.class.getName(), estadoDao);

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
		
		for(IStrategy s : strategies) {
			mensagem = s.execute(entidadeDominio);
			if(mensagem.getMensagemStatus() == MensagemStatus.ERRO) {
				mensagens.add(mensagem);
			}else
				continue;
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
		
	    try {
			List<EntidadeDominio> listaEntidades = daoEntidade.consultar(entidadeDominio);			
	        resultado.setEntidades(listaEntidades);
	    } catch(SQLException e) {
	       	e.printStackTrace();
	    }
		return resultado;
	}
	
}
