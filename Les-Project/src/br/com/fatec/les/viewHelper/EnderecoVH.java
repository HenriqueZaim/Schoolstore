package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.Cidade;
import br.com.fatec.les.model.Endereco;
import br.com.fatec.les.model.IDominio;

public class EnderecoVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		CidadeVH cidadeVH = new CidadeVH();
		Endereco endereco = new Endereco();
		
		endereco.setBairro(request.getParameter("txtBairro"));
		endereco.setCep(request.getParameter("txtCep"));
		endereco.setComplemento(request.getParameter("txtComplemento"));
		endereco.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
		endereco.setReferencia(request.getParameter("txtReferencia"));
		endereco.setLogradouro(request.getParameter("txtLogradouro"));
		endereco.setCidade((Cidade)cidadeVH.getEntidade(request));
		endereco.setFavorito(Boolean.parseBoolean(request.getParameter("txtFavorito")));
		
		return endereco;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
