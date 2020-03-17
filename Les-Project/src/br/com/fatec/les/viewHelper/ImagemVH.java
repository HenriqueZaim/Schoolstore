package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Imagem;

public class ImagemVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Imagem imagem = new Imagem();
		
		imagem.setFoto(request.getParameter("foto"));
		imagem.setDescricao("Foto de perfil");
		
		return imagem;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
