package br.com.fatec.les.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fatec.les.DAO.IDao;
import br.com.fatec.les.DAO.UsuarioDao;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.Usuario;
import br.com.fatec.les.strategy.IStrategy;
import br.com.fatec.les.strategy.UsuarioDocumentoStrategy;
import br.com.fatec.les.strategy.UsuarioEmailStrategy;
import br.com.fatec.les.strategy.UsuarioNomeStrategy;
import br.com.fatec.les.strategy.UsuarioSenhaStrategy;
import br.com.fatec.les.strategy.UsuarioTelefoneStrategy;

public class Facade implements IFacade{
	
	private Map<String, IDao> daoMap;
	private Map<String, ArrayList<IStrategy>> strategyMap;
	private Result result;
	private String mensagem;
	
	public Facade() {
		daoMap = new HashMap<String, IDao>();
		strategyMap = new HashMap<String, ArrayList<IStrategy>>();
		
		UsuarioDao usuarioDao = new UsuarioDao();
		daoMap.put(Usuario.class.getName(), usuarioDao);
		
		IStrategy usuarioDocumentoStrategy = new UsuarioDocumentoStrategy();
		IStrategy usuarioEmailStrategy = new UsuarioEmailStrategy();
		IStrategy usuarioNomeStrategy = new UsuarioNomeStrategy();
		IStrategy usuarioSenhaStrategy = new UsuarioSenhaStrategy();
		IStrategy usuarioTelefoneStrategy = new UsuarioTelefoneStrategy();
		
		ArrayList<IStrategy> usuarioStrategies = new ArrayList<IStrategy>();
		usuarioStrategies.add(usuarioDocumentoStrategy);
		usuarioStrategies.add(usuarioNomeStrategy);
		usuarioStrategies.add(usuarioSenhaStrategy);
		usuarioStrategies.add(usuarioEmailStrategy);
		usuarioStrategies.add(usuarioTelefoneStrategy);
		
		strategyMap.put(Usuario.class.getName(), usuarioStrategies);
	}

	@Override
	public Result salvar(EntidadeDominio entidadeDominio) {
		result = new Result();
		
		ArrayList<String> mensagens = new ArrayList<String>();
		ArrayList<IStrategy> strategies = strategyMap.get(entidadeDominio.getClass().getName());
		
		for(IStrategy s : strategies) {
			mensagem = s.execute(entidadeDominio);
			if(!mensagem.equals("")) {
				mensagens.add(mensagem);
			}else
				continue;
		}
		
		if(!mensagens.isEmpty()) {
			result.setMensagem(mensagens);
			return result;
		}
		
		IDao daoEntidade = daoMap.get(entidadeDominio.getClass().getName());
		
		try {
			String resposta = daoEntidade.salvar(entidadeDominio);
			mensagens.add(resposta);
			result.setMensagem(mensagens);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result atualizar(EntidadeDominio entidadeDominio) {
		result = new Result();
		
		ArrayList<String> mensagens = new ArrayList<String>();
		
		String entidadeCorrespondente = entidadeDominio.getClass().getName();
	    IDao daoEntidade = daoMap.get(entidadeCorrespondente);

		try {
			String mensagem = daoEntidade.atualizar(entidadeDominio);
			mensagens.add(mensagem);
	        result.setMensagem(mensagens);
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    	mensagem = "Não foi possível atualizar a entidade no banco de dados";
	    	mensagens.add(mensagem);
			result.setMensagem(mensagens);
	    }
	        
		return result;
	}

	@Override
	public Result deletar(EntidadeDominio entidadeDominio) {
		result = new Result();
		
		ArrayList<String> mensagens = new ArrayList<String>();
		
		String entidadeCorrespondente = entidadeDominio.getClass().getName();
	    IDao daoEntidade = daoMap.get(entidadeCorrespondente);

		try {
			String mensagem = daoEntidade.deletar(entidadeDominio);
			mensagens.add(mensagem);
	        result.setMensagem(mensagens);
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    	mensagem = "Não foi possível deletar esta entidade no banco de dados";
	    	mensagens.add(mensagem);
			result.setMensagem(mensagens);
	    }
	        
		return result;
	}

	@Override
	public Result consultar(EntidadeDominio entidadeDominio) {
		result = new Result();
		
		ArrayList<String> mensagens = new ArrayList<String>();
		String entidadeCorrespondente = entidadeDominio.getClass().getName();
	    IDao daoEntidade = daoMap.get(entidadeCorrespondente);
		
	    try {
			List<EntidadeDominio> listaEntidades = daoEntidade.consultar(entidadeDominio);
	        result.setEntidades(listaEntidades);
	    } catch(SQLException e) {
	       	e.printStackTrace();
	       	mensagem = "Não foi possível listar o pessoal";
	       	mensagens.add(mensagem);
			result.setMensagem(mensagens);
	    }
		return result;
	}
	
}
