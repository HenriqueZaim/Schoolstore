<%@page import="java.util.List"%>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="br.com.fatec.les.model.usuario.Usuario"%>
<%@page import="br.com.fatec.les.model.assets.EntidadeDominio"%>
<%@page import="br.com.fatec.les.facade.Resultado"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="pt-br" xml:lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Lista de produtos</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/mdb.min.css">
    <link rel="stylesheet" href="css/style.css">

    <style>
        .link-black a {
            color: black;
        }

        .link-black a:hover {
            color: #0056b3;
        }

        .link-black .active {
            color: #0056b3;
        }

        .divider-small {
            width: 30px;
            background-color: rgba(0, 0, 0, .1);
            height: 3px;
        }
    </style>
</head>

<body>

    <header>
        <nav class="navbar navbar-expand-lg navbar-dark stylish-color scrolling-navbar">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent-5"
                aria-controls="navbarSupportedContent-5" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent-5">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item ">
                        <a class="nav-link waves-effect waves-light" href="index.jsp">Inicial
                        </a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link waves-effect waves-light" href="produtosLista.jsp">Produtos</a>
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
										<input type="hidden" id="carrinhoId" value="${cliente.getCarrinho().getId() }">
										<form action="app" method="POST">
											<input type="hidden" name="txtUsuarioId" value="${usuario.getId()}"> 
											<input type="hidden" name="txtClienteId" value="${cliente.getId()}"> 
											<input type="hidden" name="txtImagemId" value="${usuario.getImagem().getId()}"> 
											<input type="hidden" name="tarefa" value="editaCliente">
											
											<button type="submit" class="dropdown-item waves-effect waves-light">Meu Perfil</button>

										</form>
                                    </c:if>
                                    <a href="clienteMenu.jsp" class="dropdown-item waves-effect waves-light">Menu</a>
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

    <main>
        <section class="jumbotron jumbotron-fluid mb-0 white-text rounded-0 shadow-none"
            style="text-shadow: 1px 1px 4px black;background-image: linear-gradient(to right, #4b515cc4, #4b515c3d),url(../img/about.jpg);background-size: cover;background-position: center;">
            <div class="container">
                <h1 class="display-4 font-weight-bold">Produtos</h1>
                <p class="lead font-weight-bold">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Animi, nihil.
                </p>
            </div>
        </section>

        <section class="px-2 mt-4">
            <div class="container">
                <div class="row">
                    <div class="col-3 mx-3">
                        <div class="row flex-grow-1">
                            <div class="col-lg-12 border p-4">
                                <div class="mb-5 mx-3">
                                    <h5 class="font-weight-bold mb-3">Filtrar por categoria</h5>

                                    <div class="divider-small mb-3"></div>
                                    <input type="hidden" id="filtroInput" value="todos">

                                    <div class="form-check pl-0 mb-2">
                                        <input type="radio" class="form-check-input filtro" id="categoria1"
                                            name="categoria" value="todos" checked>
                                        <label class="form-check-label" for="categoria1">Todos</label>
                                    </div>
                                    <div class="form-check pl-0 mb-2">
                                        <input type="radio" class="form-check-input filtro" id="categoria2"
                                            name="categoria" value="cadeira">
                                        <label class="form-check-label" for="categoria2">Cadeiras</label>
                                    </div>
                                    <div class="form-check pl-0 mb-2">
                                        <input type="radio" class="form-check-input filtro" id="categoria3"
                                            name="categoria" value="mesa">
                                        <label class="form-check-label" for="categoria3">Mesas</label>
                                    </div>
                                    <div class="form-check pl-0 mb-2">
                                        <input type="radio" class="form-check-input filtro" id="categoria4"
                                            name="categoria" value="acessorio">
                                        <label class="form-check-label" for="categoria4">Acessórios</label>
                                    </div>
                                </div>
                                <div class="mb-2 mx-3">
                                    <h5 class="font-weight-bold mb-3">Filtrar por tipo</h5>
                                    <div class="divider-small mb-3"></div>
                                    <input type="hidden" id="filtro2input" value="todos">
                                    <div class="form-check pl-0 mb-2">
                                        <input type="radio" checked value="todos" class="form-check-input filtro2" id="tipo1"
                                            name="tipo">
                                        <label class="form-check-label" for="tipo1">Todos</label>
                                    </div>
                                    <div class="form-check pl-0 mb-2">
                                        <input type="radio" class="form-check-input filtro2" value="escritorio" id="tipo2"
                                            name="tipo" >
                                        <label class="form-check-label" for="tipo2">Escritório</label>
                                    </div>
                                    <div class="form-check pl-0 mb-2">
                                        <input type="radio" class="form-check-input filtro2" value="escolar" id="tipo3"
                                            name="tipo">
                                        <label class="form-check-label" for="tipo3">Escolar</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-8">
                        <div class="container-fluid my-5 mx-3">
                            <div class="row" id="itens">
                                
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <footer class="page-footer stylish-color font-small">
        <div class="footer-copyright text-center text-white stylish-color-dark py-3">© 2020 Todos os direitos
            reservados:
            <a class="text-light font-weight-bold" href="https://mdbootstrap.com/education/bootstrap/">
                foxdevlabs.com</a>
        </div>
    </footer>

    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript" src="./js/popper.min.js"></script>
    <script type="text/javascript" src="./js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./js/mdb.min.js"></script>
    <script type="text/javascript" src="./js/pages/listaProdutos.js"></script>

</body>

</html>