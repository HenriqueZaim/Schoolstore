package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.Cidade;
import br.com.fatec.les.model.Endereco;
import br.com.fatec.les.model.IDominio;

public class EnderecoVH implements IViewHelper{
	
	public ArrayList<Endereco> getEntidades(HttpServletRequest request){
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		CidadeVH cidadeVH = new CidadeVH();
		String tarefa = request.getParameter("tarefa");
			
		Endereco endereco = new Endereco();
		
		if(tarefa.equals("cadastrarCliente")) {
			String[] enderecosForm = request.getParameterValues("txtEndereco");
			
			for(int i = 0 ; i < enderecosForm.length ; i++) {
				endereco = new Endereco();
				
				if(tarefa.equals("editaCliente"))
					endereco.setId(Long.parseLong(request.getParameter("txtEnderecoId")));
				
				endereco.setNome(request.getParameterValues("txtNomeEndereco")[i]);
				endereco.setBairro(request.getParameterValues("txtBairro")[i]);
				endereco.setCep(request.getParameterValues("txtCep")[i]);
				endereco.setComplemento(request.getParameterValues("txtComplemento")[i]);
				endereco.setNumero(Integer.parseInt(request.getParameterValues("txtNumero")[i]));
				endereco.setReferencia(request.getParameterValues("txtReferencia")[i]);
				endereco.setLogradouro(request.getParameterValues("txtLogradouro")[i]);
				endereco.setFavorito(Boolean.parseBoolean(request.getParameterValues("txtFavoritoEndereco")[i]));
				
				request.setAttribute("txtCidadeIdAtual", request.getParameterValues("txtCidadeId")[i]);

				endereco.setCidade((Cidade)cidadeVH.getEntidade(request));
				
				enderecos.add(endereco);
			}
		}

		return enderecos;
	}

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
//		CidadeVH cidadeVH = new CidadeVH();
//		Endereco endereco = new Endereco();
//		String tarefa = request.getParameter("tarefa");
//		
//		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
//		
//		String[] enderecosForm = request.getParameterValues("txtEnderecoId");
//		
//		for(int i = 0 ; i < enderecosForm.length ; i++) {
//			endereco = new Endereco();
//			System.out.println("Iteração: " + i);
//			endereco.setBairro(request.getParameterValues("txtBairro")[i]);
//			endereco.setCep(request.getParameterValues("txtCep")[i]);
//			endereco.setComplemento(request.getParameterValues("txtComplemento")[i]);
//			endereco.setNumero(Integer.parseInt(request.getParameterValues("txtNumero")[i]));
//			endereco.setReferencia(request.getParameterValues("txtReferencia")[i]);
//			endereco.setLogradouro(request.getParameterValues("txtLogradouro")[i]);
//			
//			
//			enderecos.add(endereco);
//		}
		
//		if(tarefa.equals("atualizarCliente") ||
//				tarefa.equals("deletarCliente") || 
//				tarefa.equals("editaCliente")) {
//			endereco.setId(Long.parseLong(request.getParameter("txtEnderecoId")));
//		}
		
//		endereco.setBairro(request.getParameter("txtBairro"));
//		endereco.setCep(request.getParameter("txtCep"));
//		endereco.setComplemento(request.getParameter("txtComplemento"));
//		if (request.getParameter("txtNumero") == null || request.getParameter("txtNumero") == "") {
//			endereco.setNumero(0);
//		}else {
//			endereco.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
//		}
//		
//		if(request.getParameter("txtFavorito") == null || request.getParameter("txtFavorito") == "") {
//			endereco.setFavorito(false);
//		}else {
//			endereco.setFavorito(Boolean.parseBoolean(request.getParameter("txtFavorito")));
//		}
//		endereco.setReferencia(request.getParameter("txtReferencia"));
//		endereco.setLogradouro(request.getParameter("txtLogradouro"));
//		endereco.setCidade((Cidade)cidadeVH.getEntidade(request));
//		System.out.println("Enderecos: " + enderecos);

		return null;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
