<%@page import="java.util.List"%>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="br.com.fatec.les.model.usuario.Usuario"%>
<%@page import="br.com.fatec.les.model.assets.EntidadeDominio"%>
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
							</div>
							<div class="col-lg-12 mt-5">
								<h2>2. Selecione um método de pagamento</h2>
								<div class="pt-3">
									<h3>- Cartão de crédito</h3>
									<div class="accordion md-accordion" id="accordionCartao" role="tablist" aria-multiselectable="true">

									</div>
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

	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<script type="text/javascript" src="./js/popper.min.js"></script>
	<script type="text/javascript" src="./js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./js/mdb.min.js"></script>
	<script type="text/javascript" src="./js/pages/pagamento.js"></script>

</body>

</html>