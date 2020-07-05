<%@page import="java.util.List"%>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="br.com.fatec.les.model.usuario.Usuario"%>
<%@page import="br.com.fatec.les.model.config.EntidadeDominio"%>
<%@page import="br.com.fatec.les.facade.Resultado"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="pt-br" xml:lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Pagamento</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/mdb.min.css">

<link rel="stylesheet" href="css/style.css">

<style>
.dropzone {
	background-color: #cecece;
	border: 2px dotted #aeaeae;
	height: 100%;
}
</style>
</head>

<body>
	<%
		String login = "";
		login = (String) session.getAttribute("status");

		if (login == null || login == "off") {
			response.sendRedirect("usuarioLogin.jsp");
		}
	%>
	<header style="background: rgba(0, 0, 0, 0.8);">
		<nav class="navbar navbar-expand-lg navbar-dark bg-transparent shadow-none scrolling-navbar">
			<div class="container">
				<a class="navbar-brand mx-auto font-weight-bold" href="index.html">SCHOOLSTORE</a>
			</div>
		</nav>
	</header>

	<main> <input type="hidden" id="txtCarrinhoId" value="${cliente.getCarrinho().getId() }"> 
	<input type="hidden" id="txtClienteId" value="${cliente.getId() }">
	<input type="hidden" id="txtUsuarioId" value="${usuario.getId() }">
	<section class="py-5 px-2">
		<div class="container mt-5 dark-grey-text">
			<h1 class="text-center">DADOS DE PAGAMENTO</h1>
			<hr>
			<section>
				<div class="row justify-content-between mt-5">
					<div class="col-lg-7">
						<div class="row">
							<div class="col-lg-12">
								<h2>1. Selecione o endereço de entrega</h2>
								<div class="accordion md-accordion" id="accordionEndereco" role="tablist" aria-multiselectable="true">
									
								</div>
								<a href="#" class="btn btn-link text-info p-2 mb-2" data-toggle="modal" id="modalEnderecoButton"
			                    data-target="#modalEndereco"> <i class="fas fa-plus pr-2"> Novo endereço</i>
			                  </a>
							</div>
							<div class="col-lg-12 mt-5">
								<h2>2. Selecione um método de pagamento</h2>
								<div class="pt-3">
									<h3>- Cartão de crédito</h3>
									<div class="accordion md-accordion" id="accordionCartao" role="tablist" aria-multiselectable="true">

									</div>
									<a href="#" class="btn btn-link text-info p-2 mb-2" data-toggle="modal" id="modalCartaoButton"
			                   		 data-target="#modalCartao"> <i class="fas fa-plus pr-2"> Novo Cartão</i></a>
								</div>
								<div class="pt-3" id="cupons">
									<h3>- Cupons</h3>
									
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="row">
							<div class="col-lg-12 mt-5" id="confirmarCompra">
								<h2>3. Confirme sua compra</h2>

							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</section>
	</main>

	<div class="modal fade" id="modalEndereco" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<form action="app" method="POST"> 
				<input type="hidden" name="tarefa" value="adicionarEndereco">
				<input type="hidden" name="txtClienteId" value="${cliente.getId() }">
				<div class="modal-content">
					<div class="modal-header text-center">
						<h4 class="modal-title w-100 font-weight-bold">Novo Endereço</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body mx-3">
						<div class="form-row">
							<div class="col-md-4 mb-3 md-form">
								<label for="txtCepModal">CEP*</label> 
								<input type="text" class="form-control" id="txtCepModal" name="txtCep" required maxlength="8">
							</div>
							<div class="col-md-8 mb-3 md-form">
								<label for="txtLogradouroModal">Logradouro*</label> 
								<input type="text" class="form-control" id="txtLogradouroModal" name="txtLogradouro" required maxlength="100">
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6 mb-3 md-form">
								<select style="width: 100%;" id="txtEstadoModal" required searchable="Selecione..">
									<option value="" disabled selected>Selecione seu estado*</option>

								</select>
							</div>
							<div class="col-md-6 mb-3 md-form">
								<select style="width: 100%;" id="txtCidadeModal" name="txtCidadeId" required placeholder="Selecione..">
									<option value="" disabled selected>Selecione sua cidade*</option>
								</select>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-4 mb-3 md-form">
								<label for="txtNumeroModal">Número*</label> <input type="number" min="1" max="9999" maxlength="4" name="txtNumero" class="form-control" id="txtNumeroModal" required>
							</div>
							<div class="col-md-4 mb-3 md-form">
								<label for="txtBairroModal">Bairro*</label> <input type="text" class="form-control" id="txtBairroModal" name="txtBairro" required maxlength="100">
							</div>
							<div class="col-md-4 mb-3 md-form">
								<label for="txtComplementoModal">Complemento</label> <input type="text" maxlength="100" class="form-control" id="txtComplementoModal" name="txtComplemento">
							</div>
						</div>
						<div class="form-row">
							<div class="col mb-3 md-form">
								<label for="txtReferenciaModal">Referência</label> <input type="text" class="form-control" id="txtReferenciaModal" name="txtReferencia" maxlength="100">
							</div>
						</div>
						<div class="form-row">
							<div class="col mb-3 md-form">
								<label for="txtNomeEnderecoModal">Identificação de Endereço*</label> <input type="text" class="form-control" id="txtNomeEnderecoModal" name="txtNomeEndereco" required maxlength="100">
							</div>
						</div>
						<div class="form-row">
							<div class="col mb-3 md-form">
								<input class="form-check-input" type="checkbox" value="false" name="txtFavoritoEndereco" id="txtFavoritoModal"> <label class="form-check-label" for="txtFavoritoModal"> Marcar como favorito </label>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-center">
						<button class="btn btn-unique" type="submit" id="btnSalvarEndereco">
							Adicionar <i class="fas fa-paper-plane-o ml-1"></i>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="modal fade" id="modalCartao" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<form action="app" method="POST">
				<input type="hidden" name="tarefa" value="adicionarCartao"> 
				<input type="hidden" name="txtClienteId" value="${cliente.getId() }">
				<div class="modal-content">
					<div class="modal-header text-center">
						<h4 class="modal-title w-100 font-weight-bold">Novo cartão de crédito</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body mx-3">
						<div class="form-row">
							<div class="col mb-3 md-form">
								<label for="txtNomeImpressoModal">Nome Impresso*</label> 
								<input type="text" class="form-control" id="txtNomeImpressoModal" name="txtNomeImpressoCartao" required maxlength="100">
							</div>
							<div class="col mb-3">
				              <label for="txtBandeiraModal">Bandeira*</label> 
				              <select id="txtBandeiraModal" name="txtBandeiraCartao" required>
				              	<option value="VISA" >Visa</option>
								<option value="ELO" >Elo</option>
								<option value="HIPERCARD" >Hipercard</option>
				              	<option value="CABAL" >Cabal</option>              	             	              
				              	<option value="MASTERCARD">Mastercard</option>
				              </select>
				            </div>
						</div>
						<div class="form-row">
							<div class="col-md-8 mb-3 md-form">
								<label for="txtNumeroCartaoModal">Número*</label> 
								<input type="text" maxlength="16" class="form-control" name="txtNumeroCartao" id="txtNumeroCartaoModal" required>
							</div>
							<div class="col-md-4 mb-3 md-form">
								<label for="txtCodigoModal">Código*</label> 
								<input type="text" class="form-control" id="txtCodigoModal" name="txtCodigoCartao" required maxlength="3">
							</div>
						</div>
						<div class="form-row">
							<div class="col mb-3 md-form">
								<input class="form-check-input" type="checkbox" value="false" name="txtFavoritoCartao" id="txtFavoritoCartaoModal"> 
								<label class="form-check-label" for="txtFavoritoCartaoModal"> Marcar como favorito </label>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-center">
						<button class="btn btn-unique" id="btnSalvarCartao" type="submit">
							Adicionar <i class="fas fa-paper-plane-o ml-1"></i>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>


	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<script type="text/javascript" src="./js/popper.min.js"></script>
	<script type="text/javascript" src="./js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./js/mdb.min.js"></script>
	<script type="text/javascript" src="./js/pages/pagamento.js"></script>

</body>

</html>