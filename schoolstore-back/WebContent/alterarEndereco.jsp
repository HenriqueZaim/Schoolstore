<%@page import="java.util.List"%>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="br.com.fatec.les.model.usuario.Usuario"%>
<%@page import="br.com.fatec.les.model.config.EntidadeDominio"%>
<%@page import="br.com.fatec.les.facade.Resultado"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="pt-br" xml:lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Lista de endereços</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/mdb.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
<link rel="stylesheet" href="css/style.css">

<style>
.sidebar-fixed {
	height: 100vh;
	width: 270px;
	-webkit-box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .16), 0 2px 10px 0
		rgba(0, 0, 0, .12);
	box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .16), 0 2px 10px 0
		rgba(0, 0, 0, .12);
	z-index: 1050;
	background-color: #fff;
	padding: 0 1.5rem 1.5rem
}

.sidebar-fixed .list-group .active {
	-webkit-box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .16), 0 2px 10px 0
		rgba(0, 0, 0, .12);
	box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .16), 0 2px 10px 0
		rgba(0, 0, 0, .12);
	-webkit-border-radius: 5px;
	border-radius: 5px
}

.sidebar-fixed .logo-wrapper {
	padding: 2.5rem
}

.sidebar-fixed .logo-wrapper img {
	max-height: 50px
}

@media ( min-width :1200px) {
	.navbar, .page-footer, main {
		padding-left: 270px
	}
}

@media ( max-width :1199.98px) {
	.sidebar-fixed {
		display: none
	}
}

.container-for-admin {
	background-color: #eee !important;
}

.map-container {
	overflow: hidden;
	padding-bottom: 56.25%;
	position: relative;
	height: 0;
}

.map-container iframe {
	left: 0;
	top: 0;
	height: 100%;
	width: 100%;
	position: absolute;
}
</style>
</head>

<body>
	<%
    String login = "";
    login = (String) session.getAttribute("status");
    Usuario usuario = new Usuario();
    usuario = (Usuario) session.getAttribute("usuario");
    
    if(login == null || login == "off"){
        response.sendRedirect("usuarioLogin.jsp");
    }else if (usuario.isAdmin()){
        response.sendRedirect("index.jsp");
    }
    %>
	<div class="container-for-admin">
		<header>
			<nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
				<div class="container-fluid">

					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item"><a class="nav-link waves-effect" href="index.jsp">Início </a></li>
						</ul>

						<ul class="navbar-nav ml-auto nav-flex-icons align-items-center flex-row-reverse flex-md-row justify-content-between">

							<li class="nav-item avatar dropdown"><a class="nav-link dropdown-toggle waves-effect waves-light" id="navbarDropdownMenuLink-5" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <img src="${usuario.getImagem().getCaminho() }" class="rounded-circle z-depth-0" height="35px" alt="avatar image">
							</a>
								<div class="dropdown-menu dropdown-menu-lg-right py-0 dropdown-default" aria-labelledby="navbarDropdownMenuLink-5">

									<form action="app" method="POST">
										<input type="hidden" name="txtUsuarioId" value="${usuario.getId()}"> <input type="hidden" name="txtClienteId" value="${cliente.getId()}"> <input type="hidden" name="txtImagemId" value="${usuario.getImagem().getId()}"> <input type="hidden" name="tarefa" value="editaCliente">

										<button type="submit" class="dropdown-item waves-effect waves-light">Meu Perfil</button>

									</form>
									
									<a href="clienteMenu.jsp" class="dropdown-item waves-effect waves-light">Menu</a>

									<form action="logout" method="POST">
										<button type="submit" class="dropdown-item waves-effect waves-light">Sair</button>
									</form>
								</div></li>
						</ul>

					</div>

				</div>
			</nav>
			<div class="sidebar-fixed position-fixed">

				<a class="navbar-brand waves-effect" href="index.jsp"> <strong class="blue-text h1 m-2">SchoolStore</strong>
				</a>

				<div class="list-group list-group-flush mt-5">
					<a href="clienteMenu.jsp" class="list-group-item list-group-item-action waves-effect"><i class="fas fa-th-list mr-3"></i> Menu Principal </a>

					<form action="app" method="POST">
						<input type="hidden" name="tarefa" value="consultarCarrinho"> <input type="hidden" name="txtCarrinhoId" value="${cliente.getCarrinho().getId()}">
						<button type="submit" class="list-group-item list-group-item-action waves-effect">
							<i class="fas fa-cart-arrow-down mr-3"></i>Meu Carrinho
						</button>
					</form>
					<a href="pedidoLista.jsp" class="list-group-item list-group-item-action waves-effect"><i class="fas fa-box-open mr-3"></i> Meu Pedidos </a>
					<a href="trocaLista.jsp" class="list-group-item list-group-item-action waves-effect"> <i class="fas fa-exchange-alt mr-3"></i>Minhas Trocas
						</a>
						<a href="cuponsLista.jsp" class="list-group-item list-group-item-action waves-effect"> <i class="fas fa-ticket-alt mr-3"></i>Meus Cupons
						</a>
						<a href="alterarSenha.jsp" class="list-group-item list-group-item-action waves-effect"> <i class="fas fa-tools mr-3"></i>Alterar Senha
						</a>
						<a href="alterarEndereco.jsp" class="list-group-item active list-group-item-action waves-effect"> <i class="fas fa-map-marked mr-3"></i>Alterar endereço
						</a>
					<form action="app" method="POST">
						<input type="hidden" name="txtUsuarioId" value="${usuario.getId()}"> <input type="hidden" id="txtClienteId" name="txtClienteId" value="${cliente.getId()}"> <input type="hidden" name="txtImagemId" value="${usuario.getImagem().getId()}"> <input type="hidden" name="tarefa" value="editaCliente">
						<button type="submit" class="list-group-item list-group-item-action waves-effect">
							<i class="fa fa-user mr-3"></i> Meu Perfil
						</button>
					</form>
				</div>

			</div>
		</header>

		<main class="pt-5 mx-lg-5">
		<div class="container-fluid mt-5">
			<div class="card mb-4 wow fadeIn">
				<div class="card-body d-sm-flex justify-content-between">
					<h1 class="mb-2 mb-sm-0 pt-1">Lista de Endereços</h1>
				</div>
			</div>
			<c:forEach var="mensagem" items="${resultado.getMensagens()}">
				<c:choose>
					<c:when test="${mensagem.getMensagemStatus() == 'ERRO'}">
						<div class="alert alert-danger p-0 border-0" role="alert">
							<span class="d-block px-3 py-2"><i class="fas fa-exclamation-circle p-0 px-2"></i>${mensagem.getMensagem()}</span>
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-success p-0 border-0" role="alert">
							<span class="d-block px-3 py-2"><i class="fas fa-exclamation-circle p-0 px-2"></i>${mensagem.getMensagem()}</span>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>


		</div>
		<div class="container-fluid mt-5">
			<div class="card mb-4 wow fadeIn">
				<section class="dark-grey-text p-4">
					<div class="accordion md-accordion" id="accordionEndereco" role="tablist" aria-multiselectable="true">
									
								</div>
					<a href="#" class="btn btn-link text-info p-2 mb-2" data-toggle="modal" id="modalEnderecoButton" data-target="#modalEndereco"> <i class="fas fa-plus pr-2"> Novo endereço</i>
					</a>
				</section>
			</div>
		</div>
		</main>

		<footer class="page-footer text-center font-small primary-color-dark darken-2 mt-4 wow fadeIn">
			<div class="footer-copyright py-3">
				© 2020 Copyright: <a href="https://mdbootstrap.com/education/bootstrap/" target="_blank"> foxdevlabs.com </a>
			</div>
		</footer>
	</div>
	
	<div class="modal fade" id="modalEndereco" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<form action="app" method="POST"> 
				<input type="hidden" name="tarefa" value="adicionarEnderecoLista">
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
								<label for="txtComplementoModal">Complemento</label> <input type="text" maxlength="100" class="form-control" id="txtComplementoModal" name="txtComplemento" >
							</div>
						</div>
						<div class="form-row">
							<div class="col mb-3 md-form">
								<label for="txtReferenciaModal">Referência</label> <input type="text" class="form-control" id="txtReferenciaModal" name="txtReferencia"  maxlength="100">
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


	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<script type="text/javascript" src="./js/popper.min.js"></script>
	<script type="text/javascript" src="./js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./js/mdb.min.js"></script>
	<script type="text/javascript" src="./js/pages/alterarEndereco.js"></script>

</body>

</html>