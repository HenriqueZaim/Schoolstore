package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.endereco.Cidade;
import br.com.fatec.les.model.endereco.Endereco;
import br.com.fatec.les.model.usuario.Cliente;

public class EnderecoVH implements IViewHelper{
	
	public ArrayList<Endereco> getEntidades(HttpServletRequest request){
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		CidadeVH cidadeVH = new CidadeVH();
		Endereco endereco = new Endereco();
		String tarefa = request.getParameter("tarefa");
		if(request.getParameterValues("txtEndereco") == null) {
			return enderecos;
		}else {
			String[] enderecosForm = request.getParameterValues("txtEndereco");
			for(int i = 0 ; i < enderecosForm.length ; i++) {
				endereco = new Endereco();
				
				if(tarefa.equals("atualizarCliente")) {
					endereco.setId(request.getParameterValues("txtEnderecoId")[i] != "" ? Long.parseLong(request.getParameterValues("txtEnderecoId")[i]) : null);
				}
				
				endereco.setNome(request.getParameterValues("txtNomeEndereco")[i]);
				endereco.setBairro(request.getParameterValues("txtBairro")[i]);
				endereco.setCep(request.getParameterValues("txtCep")[i]);
				endereco.setComplemento(request.getParameterValues("txtComplemento")[i] != "" ? request.getParameterValues("txtComplemento")[i] : null);
				endereco.setNumero(Integer.parseInt(request.getParameterValues("txtNumero")[i]));
				endereco.setReferencia(request.getParameterValues("txtReferencia")[i] != "" ? request.getParameterValues("txtReferencia")[i] : null);
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
	public ADominio getEntidade(HttpServletRequest request) {
		Endereco endereco = new Endereco();
		String tarefa = request.getParameter("tarefa");
		if(tarefa.equals("adicionarEndereco")) {
			CidadeVH cidadeVH = new CidadeVH();
			ClienteVH clienteVH = new ClienteVH();
			endereco.setNome(request.getParameter("txtNomeEndereco"));
			endereco.setBairro(request.getParameter("txtBairro"));
			endereco.setCep(request.getParameter("txtCep"));
			endereco.setComplemento(request.getParameter("txtComplemento") != "" ? request.getParameter("txtComplemento") : null);
			endereco.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
			endereco.setReferencia(request.getParameter("txtReferencia") != "" ? request.getParameter("txtReferencia") : null);
			endereco.setLogradouro(request.getParameter("txtLogradouro"));
			endereco.setFavorito(Boolean.parseBoolean(request.getParameter("txtFavoritoEndereco")));
			endereco.setCliente((Cliente) clienteVH.getEntidade(request));
			
			request.setAttribute("txtCidadeIdAtual", request.getParameter("txtCidadeId"));

			endereco.setCidade((Cidade)cidadeVH.getEntidade(request));
		}else {
			endereco.setId(Long.parseLong(request.getParameter("txtEnderecoId")));
		}

		return endereco;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("pagamento.jsp");
		
	}

}