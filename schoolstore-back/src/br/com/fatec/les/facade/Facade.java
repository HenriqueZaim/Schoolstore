package br.com.fatec.les.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fatec.les.DAO.ClienteDao;
import br.com.fatec.les.DAO.EnderecoDao;
import br.com.fatec.les.DAO.IDao;
import br.com.fatec.les.DAO.UsuarioDao;
import br.com.fatec.les.model.Cliente;
import br.com.fatec.les.model.Endereco;
import br.com.fatec.les.model.EntidadeDominio;
import br.com.fatec.les.model.Usuario;
import br.com.fatec.les.strategy.ClienteDocumentoStrategy;
import br.com.fatec.les.strategy.ClienteNomeStrategy;
import br.com.fatec.les.strategy.ClienteTelefoneStrategy;
import br.com.fatec.les.strategy.EnderecoBairroStrategy;
import br.com.fatec.les.strategy.EnderecoCepStrategy;
import br.com.fatec.les.strategy.EnderecoComplementoStrategy;
import br.com.fatec.les.strategy.EnderecoLogradouroStrategy;
import br.com.fatec.les.strategy.EnderecoNumeroStrategy;
import br.com.fatec.les.strategy.EnderecoReferenciaStrategy;
import br.com.fatec.les.strategy.IStrategy;
import br.com.fatec.les.strategy.UsuarioEmailStrategy;
import br.com.fatec.les.strategy.UsuarioSenhaStrategy;


public class Facade implements IFacade{
	
	private Map<String, IDao> daoMap;
	private Map<String, ArrayList<IStrategy>> strategyMap;
	private Result result;
	private String mensagem;
	
	public Facade() {
		daoMap = new HashMap<String, IDao>();
		strategyMap = new HashMap<String, ArrayList<IStrategy>>();
		
		ClienteDao clienteDao = new ClienteDao();
		
		daoMap.put(Cliente.class.getName(), clienteDao);
		
//		IStrategy clienteDataNascimentoStrategy = new ClienteDataNascimentoStrategy();
		IStrategy clienteDocumentoStrategy = new ClienteDocumentoStrategy();
		IStrategy clienteNomeStrategy = new ClienteNomeStrategy();
		IStrategy clienteTelefoneStrategy = new ClienteTelefoneStrategy();
		
		IStrategy enderecoBairroStrategy = new EnderecoBairroStrategy();
		IStrategy enderecoCepStrategy = new EnderecoCepStrategy();
		IStrategy enderecoComplementoStragegy = new EnderecoComplementoStrategy();
		IStrategy enderecoLogradouroStrategy = new EnderecoLogradouroStrategy();
		IStrategy enderecoNumeroStrategy = new EnderecoNumeroStrategy();
		IStrategy enderecoReferenciaStrategy = new EnderecoReferenciaStrategy();
		
		IStrategy usuarioEmailStrategy = new UsuarioEmailStrategy();
		IStrategy usuarioSenhaStrategy = new UsuarioSenhaStrategy();
		
		ArrayList<IStrategy> usuarioStrategies = new ArrayList<IStrategy>();
		usuarioStrategies.add(usuarioSenhaStrategy);
		usuarioStrategies.add(usuarioEmailStrategy);
		
		ArrayList<IStrategy> clienteStrategies = new ArrayList<IStrategy>();
//		clienteStrategies.add(clienteDataNascimentoStrategy);
		clienteStrategies.add(clienteDocumentoStrategy);
		clienteStrategies.add(clienteNomeStrategy);
		clienteStrategies.add(clienteTelefoneStrategy);
		
		ArrayList<IStrategy> enderecoStrategies = new ArrayList<IStrategy>();
		enderecoStrategies.add(enderecoBairroStrategy);
		enderecoStrategies.add(enderecoCepStrategy);
		enderecoStrategies.add(enderecoComplementoStragegy);
		enderecoStrategies.add(enderecoLogradouroStrategy);
		enderecoStrategies.add(enderecoNumeroStrategy);
		enderecoStrategies.add(enderecoReferenciaStrategy);
		
		clienteStrategies.addAll(enderecoStrategies);
		clienteStrategies.addAll(usuarioStrategies);

		strategyMap.put(Cliente.class.getName(), clienteStrategies);

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
