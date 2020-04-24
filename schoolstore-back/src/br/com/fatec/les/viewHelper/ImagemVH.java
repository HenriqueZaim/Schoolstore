package br.com.fatec.les.viewHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.assets.Imagem;

public class ImagemVH implements IViewHelper{

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
		Imagem imagem = new Imagem();
		String tarefa = request.getParameter("tarefa");
		String base64String = request.getParameter("txtFile");
		
		if(tarefa.equals("atualizarCliente") ||
				tarefa.equals("deletarCliente") || 
				tarefa.equals("editaCliente")) {
			imagem.setId(Long.parseLong(request.getParameter("txtImagemId")));
		}
		
		if((tarefa.equals("cadastrarCliente") || tarefa.equals("atualizarCliente")) && base64String != "") {
			String[] strings = base64String.split(",");
			String extension;
			switch (strings[0]) {
				case "data:image/jpeg;base64":
	              extension = "jpeg";
	              break;
				case "data:image/png;base64":
	              extension = "png";
	              break;
				case "data:image/svg;base64":
					extension = "svg";
		            break;
				default:
	              extension = "jpg";
	              break;
			}
			// TODO: rever quando chegar em produto
			String nomeImagem = request.getParameter("txtNumeroDocumento") + "." + extension;
			byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
			String path = "/home/henrique/Documentos/git/les-project/schoolstore-back/WebContent/img/" + nomeImagem;
			File file = new File(path);
			try {
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			imagem.setFoto(nomeImagem);
			imagem.setDescricao("Imagem " + nomeImagem);
			imagem.setCaminho("./img/"+nomeImagem);
		}
		
		return imagem;
		
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
