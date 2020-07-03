package br.com.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.config.ADominio;
import br.com.fatec.les.model.endereco.Endereco;
import br.com.fatec.les.model.pedido.Frete;

public class FreteVH implements IViewHelper{

	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		String tarefa = request.getParameter("tarefa");
		Frete frete = new Frete();
		EnderecoVH enderecoVH = new EnderecoVH();
		
		if(tarefa.equals("efetuarPedido")) {
			frete.setPrevisaoEmDias(Integer.parseInt(request.getParameter("txtFretePrevisao")));
			frete.setValor(Float.parseFloat(request.getParameter("txtFreteValor")));
			frete.setEndereco((Endereco)enderecoVH.getEntidade(request));
		}
		return frete;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}