<%@page import="java.util.List"%>
<%@page
	import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="br.com.fatec.les.model.usuario.Usuario"%>
<%@page import="br.com.fatec.les.model.assets.EntidadeDominio"%>
<%@page import="br.com.fatec.les.facade.Resultado"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="pt-br" xml:lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Material Design for Bootstrap</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/mdb.min.css">

</head>

<body>

	<header>
		<nav
			class="navbar navbar-expand-lg navbar-dark stylish-color scrolling-navbar">
			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent-5"
				aria-controls="navbarSupportedContent-5" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent-5">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a
						class="nav-link waves-effect waves-light" href="index.jsp">Inicial </a></li>
					<li class="nav-item"><a
						class="nav-link waves-effect waves-light" href="produtoLista.jsp">Produtos</a>
					</li>
				</ul>

				<c:choose>
					<c:when test="${usuario == null}">
						<ul
							class="navbar-nav ml-auto nav-flex-icons align-items-center flex-row-reverse flex-md-row justify-content-between">
							<li class="nav-item pr-0 pr-lg-3"><a href="usuarioLogin.jsp"
								class="m-0 nav-link btn btn-link text-white waves-effect font-weight-bold">
									LOGIN </a></li>
							<li class="nav-item avatar dropdown"><a
								href="clienteCadastro.jsp"
								class="m-0 nav-link btn  btn-outline-success waves-effect font-weight-bold">
									Cadastro </a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul
							class="navbar-nav ml-auto nav-flex-icons align-items-center flex-row-reverse flex-md-row justify-content-between">
							<li class="nav-item avatar dropdown"><a
								class="nav-link dropdown-toggle waves-effect waves-light"
								id="navbarDropdownMenuLink-5" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> <img
									src="${usuario.getImagem().getCaminho() }"
									class="rounded-circle z-depth-0" height="35px"
									alt="avatar image">
							</a>
								<div
									class="dropdown-menu dropdown-menu-lg-right py-0 dropdown-default"
									aria-labelledby="navbarDropdownMenuLink-5">
									<c:if test="${cliente != null}">
										<form action="app" method="POST">
											<input type="hidden" name="txtUsuarioId" value="${usuario.getId()}"> 
											<input type="hidden" name="txtClienteId" value="${cliente.getId()}"> 
											<input type="hidden" name="txtImagemId" value="${usuario.getImagem().getId()}"> 
											<input type="hidden" name="tarefa" value="editaCliente">
											
											<button type="submit" class="dropdown-item waves-effect waves-light">Meu Perfil</button>

										</form>
                                    </c:if>
									<form action="logout" method="POST">
										<button type="submit"
											class="dropdown-item waves-effect waves-light">Sair</button>
									</form>
								</div></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</nav>
	</header>

	<footer class="page-footer stylish-color font-small lighten-5">
		<div style="background-color: #16a270;">
			<div class="container">
				<div class="row py-4 d-flex align-items-center">
					<div
						class="col-md-6 col-lg-5 text-center text-md-left mb-4 mb-md-0">
						<h6 class="mb-0 font-weight-bold">Conheça nossas redes
							sociais!</h6>
					</div>
					<div class="col-md-6 col-lg-7 text-center text-md-right">
						<a class="fb-ic"> <i class="fab fa-facebook-f white-text mr-4">
						</i>
						</a> <a class="li-ic"> <i
							class="fab fa-linkedin-in white-text mr-4"> </i>
						</a> <a class="ins-ic"> <i class="fab fa-instagram white-text">
						</i>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="container  text-center text-md-left mt-5">
			<div class="row mt-3 text-white">
				<div class="col-md-3 col-lg-4 col-xl-3 mb-4">
					<h6 class="text-uppercase font-weight-bold">SchoolStore</h6>
					<hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto"
						style="width: 60px;">
					<p class="font-weight-bold">Here you can use rows and columns
						to organize your footer content. Lorem ipsum dolor sit amet,
						consectetur adipisicing elit.</p>
				</div>
				<div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
					<h6 class="text-uppercase font-weight-bold">Produtos</h6>
					<hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto"
						style="width: 60px;">
					<p>
						<a class="text-light font-weight-bold" href="#!">Conjuntos</a>
					</p>
					<p>
						<a class="text-light font-weight-bold" href="#!">Personalizados</a>
					</p>
					<p>
						<a class="text-light font-weight-bold" href="#!">Avulsos</a>
					</p>
				</div>
				<div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
					<h6 class="text-uppercase font-weight-bold">Links Úteis</h6>
					<hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto"
						style="width: 60px;">
					<p>
						<a class="text-light font-weight-bold" href="#!">Seu Perfil</a>
					</p>
					<p>
						<a class="text-light font-weight-bold" href="#!">Seja um
							Fornecedor</a>
					</p>
					<p>
						<a class="text-light font-weight-bold" href="#!">Sobre</a>
					</p>
				</div>
				<div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
					<h6 class="text-uppercase font-weight-bold">Contact</h6>
					<hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto"
						style="width: 60px;">
					<p class="font-weight-bold">
						<i class="fas fa-home mr-3"></i> Mogi das Cruzes, São Paulo, BR
					</p>
					<p class="font-weight-bold">
						<i class="fas fa-envelope mr-3"></i> schoolstore@gmail.com
					</p>
					<p class="font-weight-bold">
						<i class="fas fa-phone mr-3"></i> + 55 11 4002 8922
					</p>
				</div>
			</div>
		</div>
		<div
			class="footer-copyright text-center text-white stylish-color-dark py-3">
			© 2020 Todos os direitos reservados: <a
				class="text-light font-weight-bold"
				href="https://mdbootstrap.com/education/bootstrap/">
				foxdevlabs.com</a>
		</div>
	</footer>

	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<script type="text/javascript" src="./js/popper.min.js"></script>
	<script type="text/javascript" src="./js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./js/mdb.min.js"></script>

</body>

</html>
