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
    <title>Visualização de produto</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/mdb.min.css">

    <link rel="stylesheet" href="css/style.css">
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
        <section class="py-5 px-2">
            <div class="container dark-grey-text">
                <section class="text-center">
                    <div class="row">
                        <div class="col-lg-6">
                            <img src="${produto.getImagem().getCaminho() }" alt="First slide" class="img-fluid">
                        </div>

                        <div class="col-lg-5 text-center text-md-left">
                            
                            <c:forEach var="categoria" items="${produto.getCategorias()}">
								<span class="badge badge-danger p-1 rounded-pill product ml-xl-0 ml-4">${categoria.getNome() }</span>
                            </c:forEach>
                            <h2 class="h2-responsive text-center text-md-left product-name font-weight-bold dark-grey-text mb-1 ml-xl-0 ml-4">
                                <strong>${produto.getNome() }</strong>
                            </h2>

                            <h3 class="h3-responsive text-center text-md-left my-5 ml-xl-0 ml-4 teal-text font-weight-bold">
                                <strong>
                                R$${Math.round(produto.getPreco() * produto.getPrecificacao().getPercentual() + produto.getPreco()) * 100 / 100}</strong>
                            </h3>

                            <p class="lead"><strong>Descrição: </strong>${produto.getDescricao() }
                            </p>
                            <p class="lead"><strong>Fornecedor: </strong>${produto.getEstoque().getItensEstoque().get(0).getFornecedor().getNome() }
                            </p>
                            <div class="mt-5">
                                <div class="row mt-3">
                                    <div class="col-md-8 text-right">
                                    	<c:if test="${cliente != null}">
	                                        <form action="app" method="post">
												<input type="hidden" name="txtCarrinhoId" value="${cliente.getCarrinho().getId()}">
												<input type="hidden" name="txtSubTotal" value="${Math.round(produto.getPreco() * produto.getPrecificacao().getPercentual() + produto.getPreco()) * 100 / 100}">
						                		<input type="hidden" name="txtProdutoId" value="${produto.getId()}">
						                		<input type="hidden" name="tarefa" value="adicionarProduto">
						                		<button type="submit" class="btn btn-primary btn-md">Adicionar ao carrinho</button>
						                	</form>
					                	</c:if>
					                	<c:if test="${cliente == null}">
					                		<span>Faça login antes para adicionar o produto ao seu carrinho de compras</span>
					                		<button type="button" class="btn btn-primary btn-md disabled">Adicionar ao carrinho</button>
					                	</c:if>
                                    </div>
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

</body>

</html>