package br.com.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.les.model.assets.IDominio;
import br.com.fatec.les.model.pagamento.cartao.CartaoCredito;

public class CartaoCreditoVH implements IViewHelper{
	
	public ArrayList<CartaoCredito> getEntidades(HttpServletRequest request){
		ArrayList<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();
		CartaoCredito cartaoCredito = new CartaoCredito();
		String tarefa = request.getParameter("tarefa");
		
		if(tarefa.equals("efetuarPedido")) {
			if(request.getParameterValues("txtCartaoCreditoId") == null) {
				return cartoes;
			}else {
				String[] cartoesForm = request.getParameterValues("txtCartaoCreditoId");
				for(int i = 0 ; i < cartoesForm.length ; i++) {
					cartaoCredito = new CartaoCredito();

					cartaoCredito.setId(Long.parseLong(request.getParameterValues("txtCartaoCreditoId")[i]));
					
					cartoes.add(cartaoCredito);
				}
			}
		}else {
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
		}

		
		return cartoes;
	}

	@Override
	public IDominio getEntidade(HttpServletRequest request) {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public void setEntidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        throw new UnsupportedOperationException("Operação não suportada.");
	}

}