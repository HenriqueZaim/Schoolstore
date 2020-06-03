package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.ADominio;
import br.com.fatec.les.model.pagamento.cartao.CartaoCredito;
import br.com.fatec.les.model.usuario.Cliente;

public class CartaoCreditoVH implements IViewHelper{

	public ArrayList<CartaoCredito> getEntidades(HttpServletRequest request){
		ArrayList<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();
		CartaoCredito cartaoCredito = new CartaoCredito();
		String tarefa = request.getParameter("tarefa");

		if(request.getParameterValues("txtCartaoCredito") == null) {
			return cartoes;
		}else {
			String[] cartoesForm = request.getParameterValues("txtCartaoCredito");
			for(int i = 0 ; i < cartoesForm.length ; i++) {
				cartaoCredito = new CartaoCredito();

				if(tarefa.equals("atualizarCliente")) {
					cartaoCredito.setId(request.getParameterValues("txtCartaoCreditoId")[i] != "" ? Long.parseLong(request.getParameterValues("txtCartaoCreditoId")[i]) : null);
				}

				cartaoCredito.setCodigo(request.getParameterValues("txtCodigoCartao")[i]);
				cartaoCredito.setNomeImpresso(request.getParameterValues("txtNomeImpressoCartao")[i]);
				cartaoCredito.setNumero(request.getParameterValues("txtNumeroCartao")[i]);
				cartaoCredito.setFavorito(Boolean.parseBoolean(request.getParameterValues("txtFavoritoCartao")[i]));

				cartoes.add(cartaoCredito);
			}
		}



		return cartoes;
	}

	@Override
	public ADominio getEntidade(HttpServletRequest request) {
		CartaoCredito cartaoCredito = new CartaoCredito();
		ClienteVH clienteVH = new ClienteVH();
		String tarefa = request.getParameter("tarefa");
		
		if(tarefa.equals("adicionarCartao")) {
			cartaoCredito.setCodigo(request.getParameter("txtCodigoCartao"));
			cartaoCredito.setNomeImpresso(request.getParameter("txtNomeImpressoCartao"));
			cartaoCredito.setNumero(request.getParameter("txtNumeroCartao"));
			cartaoCredito.setFavorito(Boolean.parseBoolean(request.getParameter("txtFavoritoCartao")));
			cartaoCredito.setCliente((Cliente)clienteVH.getEntidade(request));
		}else {
			cartaoCredito.setId(Long.parseLong(request.getParameter("txtCartaoCreditoId")));
		}

		return cartaoCredito;
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("pagamento.jsp");
	}

}