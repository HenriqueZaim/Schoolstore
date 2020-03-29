package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import br.com.fatec.les.model.Cidade;
import br.com.fatec.les.model.Endereco;
import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Usuario;

public class EnderecoVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		CidadeVH cidadeVH = new CidadeVH();
		Endereco endereco = new Endereco();
		String tarefa = request.getParameter("tarefa");
		
//		if(!tarefa.equals("cadastrarCliente")) {
//			endereco.setId(Long.parseLong(request.getParameter("txtEnderecoId")));
//		}
		
		if(request.getParameter("tarefa").equals("atualizarCliente") ||
				request.getParameter("tarefa").equals("deletarCliente") || 
				request.getParameter("tarefa").equals("editaCliente")) {
			endereco.setId(Long.parseLong(request.getParameter("txtEnderecoId")));
		}
		
		endereco.setBairro(request.getParameter("txtBairro"));
		endereco.setCep(request.getParameter("txtCep"));
		endereco.setComplemento(request.getParameter("txtComplemento"));
		if (request.getParameter("txtNumero") == null || request.getParameter("txtNumero") == "") {
			endereco.setNumero(0);
		}else {
			endereco.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
		}
		
		if(request.getParameter("txtFavorito") == null || request.getParameter("txtFavorito") == "") {
			endereco.setFavorito(false);
		}else {
			endereco.setFavorito(Boolean.parseBoolean(request.getParameter("txtFavorito")));
		}
		endereco.setReferencia(request.getParameter("txtReferencia"));
		endereco.setLogradouro(request.getParameter("txtLogradouro"));
		endereco.setCidade((Cidade)cidadeVH.getEntidade(request));

		return endereco;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
