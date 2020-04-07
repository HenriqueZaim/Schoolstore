package br.com.fatec.les.viewHelper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import br.com.fatec.les.model.IDominio;
import br.com.fatec.les.model.Imagem;

public class ImagemVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Imagem imagem = new Imagem();
		String tarefa = request.getParameter("tarefa");
		
		if(tarefa.equals("atualizarCliente") ||
				tarefa.equals("deletarCliente") || 
				tarefa.equals("editaCliente")) {
			imagem.setId(Long.parseLong(request.getParameter("txtImagemId")));
		}else {
			String base64String = request.getParameter("txtFile");
			String[] strings = base64String.split(",");
			String extension;
			switch (strings[0]) {//check image's extension
				case "data:image/jpeg;base64":
	              extension = "jpeg";
	              break;
				case "data:image/png;base64":
	              extension = "png";
	              break;
				default://should write cases for more images types
	              extension = "jpg";
	              break;
			}
			String nomeImagem = request.getParameter("txtNome") + "." + extension;
			byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
			String path = request.getServletContext().getRealPath("img")+File.separator+nomeImagem;
			File file = new File(path);
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
				outputStream.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			imagem.setFoto(nomeImagem);
			imagem.setDescricao(nomeImagem);
		}
		
		return imagem;
		
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
