package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import br.com.fatec.les.model.Cidade;
import br.com.fatec.les.model.Estado;
import br.com.fatec.les.model.IDominio;

public class CidadeVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Cidade cidade = new Cidade();
		
		if(request.getParameter("txtCidadeId") == null || request.getParameter("txtCidadeId") == "") {
			cidade.setId(null);
		}else {
			cidade.setId(Long.parseLong(request.getParameter("txtCidadeId")));
		}
		
//		cidade.setId(request.getParameter("txtCidadeId") == "" ? 0 : Long.parseLong(request.getParameter("txtCidadeId")));
		
		return cidade;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
