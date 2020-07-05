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
<title>Pedidos</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/mdb.min.css">

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
        }else if (!usuario.isAdmin()){
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
									<c:if test="${cliente != null}">
										<form action="app" method="POST">
											<input type="hidden" name="txtUsuarioId" value="${usuario.getId()}"> <input type="hidden" name="txtClienteId" value="${cliente.getId()}"> <input type="hidden" name="txtImagemId" value="${usuario.getImagem().getId()}"> <input type="hidden" name="tarefa" value="editaCliente">

											<button type="submit" class="dropdown-item waves-effect waves-light">Meu Perfil</button>

										</form>
									</c:if>
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
						<a href="clienteMenu.jsp" class="list-group-item  list-group-item-action waves-effect"> <i class="fas fa-th-list mr-3"></i>Menu Principal
					</a> <a href="clienteLista.jsp" class="list-group-item  list-group-item-action waves-effect"> <i class="fas fa-users mr-3"></i>Lista de Clientes
					</a> <a href="adminProdutoLista.jsp" class="list-group-item list-group-item-action waves-effect"> <i class="fas fa-box-open mr-3"></i>Lista de Produtos
					</a> <a href="pedidoLista.jsp" class="list-group-item list-group-item-action waves-effect"> <i class="fas fa-exchange-alt mr-3"></i>Lista de Pedidos
					</a> <a href="trocaLista.jsp" class="list-group-item list-group-item-action waves-effect"> <i class="fas fa-exchange-alt mr-3"></i>Lista de Trocas
					</a> <a href="cuponsLista.jsp" class="list-group-item list-group-item-action waves-effect"> <i class="fas fa-ticket-alt mr-3"></i>Lista de Cupons
					</a><a href="alterarSenha.jsp" class="list-group-item list-group-item-action waves-effect"> <i class="fas fa-tools mr-3"></i>Alterar Senha
						</a> 
					<a href="relatorio.jsp" class="list-group-item list-group-item-action waves-effect"> <i class="fas fa-chart-line mr-3"></i>Relatórios
					</a>
					</div>
			</div>
		</header>

		<main class="pt-5 mx-lg-5">
		<div class="container-fluid mt-5">
			<div class="card mb-4 wow fadeIn">
				<div class="card-body d-sm-flex justify-content-between">
					<h1 class="mb-2 mb-sm-0 pt-1">Lista de pedidos do cliente</h1>
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

			<div class="row">
				<div class="col-lg-12">
					<div class="card mb-4 wow fadeIn mb-5">
						<section class="dark-grey-text">
							<table class="table table-striped table-responsive-md btn-table">
								<thead>
									<tr class="font-weight-bold text-center">
										<th>#Id</th>
										<th>Itens</th>
										<th>Status</th>
										<th>Valor da compra</th>
										<th>Valor Pago</th>
										<th>Meios de pagamento</th>
										<th>Endereço</th>
										<th>Ação</th>
									</tr>
								</thead>


								<tbody>
									<c:forEach var="pedido" items="${resultado.getEntidades()}">
										<tr>
											<td>${pedido.getId()}</td>
											<td>
												<c:forEach var="item" items="${pedido.getItensPedido()}">
													<span>${item.quantidade}x
														  <form action="app" method="get">
														  	<input type="hidden" name="txtProdutoId" value="${item.getProduto().getId()}">
														  	<input type="hidden" name="tarefa" value="consultarProduto">
														  	<button type="submit" class="btn btn-link btn-sm p-0 pb-2">${item.getProduto().getNome()}</button>
														  </form>
												  	</span>
												</c:forEach>
											</td>
											<td>
												<c:choose>
													<c:when test="${pedido.getStatusPedido() == 'REPROVADO'}">
														<span class="text-danger">Reprovado</span>
													</c:when>
													<c:when test="${pedido.getStatusPedido() == 'ENTREGUE'}">
														<span class="text-success">Entregue</span>
													</c:when>
													<c:when test="${pedido.getStatusPedido() == 'EMTRANSITO'}">
														<span class="text-warning">Em trânsito</span>
													</c:when>
													<c:when test="${pedido.getStatusPedido() == 'APROVADO'}">
														<span class="text-info">Aprovado</span>
													</c:when>
													<c:when test="${pedido.getStatusPedido() == 'ATUALIZADO'}">
														 <span class="text-info">Atualizado</span>
													</c:when>
													<c:when test="${pedido.getStatusPedido() == 'TROCADO'}">
														 <span class="text-success">Trocado</span>
													</c:when>
													<c:otherwise>
														<span class="text-warning">Em processamento</span>
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												R$${pedido.getValor()}
											</td>
											<td>
												R$${pedido.getFormaPagamento().getValorTotal() }
											</td>
											<td>
												<c:forEach var="cartao" items="${pedido.getFormaPagamento().getPagamentosCartao()}">
													<span>Cartão: R$ ${cartao.getValorTotalCartao()}</span><br>
												</c:forEach>
												<c:forEach var="cupom" items="${pedido.getFormaPagamento().getPagamentosCupom()}">
													<span>Cupom: R$ ${cupom.getValorTotalCupom()}</span><br>
												</c:forEach>
											</td>
											<td>
												<c:if test="${pedido.getFrete().getEndereco().isAtivo() }">
													<button type="button" class="btn btn-sm btn-link text-info p-0 font-weight-bold" data-toggle="modal" data-target="#modal-endereco-${pedido.getId()}">${pedido.getFrete().getEndereco().getNome()}</button>
												</c:if>
												<c:if test="${!pedido.getFrete().getEndereco().isAtivo() }">
													<button type="button" class="btn btn-sm btn-link disabled p-0 font-weight-bold" data-toggle="modal" data-target="#modal-endereco-${pedido.getId()}">${pedido.getFrete().getEndereco().getNome()}</button>
												</c:if>

												<div class="modal fade" id="modal-endereco-${pedido.getId()}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title" id="exampleModalLabel">Endereço solicitado: ${pedido.getFrete().getEndereco().getNome()}</h5>
																<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																<ul>
																	<li><b>CEP: </b>${pedido.getFrete().endereco.cep}</li>
																	<li><b>Estado: </b>${pedido.getFrete().getEndereco().getCidade().getEstado().getNome()}</li>
																	<li><b>Cidade: </b>${pedido.getFrete().getEndereco().getCidade().getNome()}</li>
																	<li><b>Bairro: </b>${pedido.getFrete().getEndereco().getBairro()}</li>
																	<li><b>Logradouro: </b>${pedido.getFrete().getEndereco().getLogradouro()}</li>
																	<li><b>Número: </b>${pedido.getFrete().getEndereco().getNumero()}</li>
																	<li><b>Complemento: </b>${pedido.getFrete().getEndereco().getComplemento()}</li>
																	<li><b>Referência: </b>${pedido.getFrete().getEndereco().getReferencia()}</li>
																</ul>
																<hr>
																<ul>
																	<li><b>Valor do frete: </b>R$ ${pedido.getFrete().getValor()}</li>
																	<li><b>Previsão de chegada: </b>${pedido.getFrete().getPrevisaoEmDias()} dias</li>
																</ul>
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-link text-right" data-dismiss="modal">Sair</button>
															</div>
														</div>
													</div>
												</div>
											</td>
											<td>
												<c:choose>
													<c:when test="${pedido.getStatusPedido() == 'ENTREGUE'}">
														<button type="button" id="alterarBotaoStatus-${pedido.getId()}" class="btn btn-sm btn-link disabled p-0 font-weight-bold" data-toggle="modal" data-target="#modal-status-${pedido.getId()}">Alterar Status</button>
													</c:when>
													<c:when test="${pedido.getStatusPedido() == 'REPROVADO'}">
														<button type="button" id="alterarBotaoStatus-${pedido.getId()}" class="btn btn-sm btn-link disabled p-0 font-weight-bold" data-toggle="modal" data-target="#modal-status-${pedido.getId()}">Alterar Status</button>
													</c:when>
													<c:otherwise>
														<button type="button" id="alterarBotaoStatus-${pedido.getId()}" class="btn btn-sm btn-link text-warning p-0 font-weight-bold" data-toggle="modal" data-target="#modal-status-${pedido.getId()}">Alterar Status</button>
													</c:otherwise>
												</c:choose>
											

												<div class="modal fade" id="modal-status-${pedido.getId()}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<form action="app" method="POST">
																<div class="modal-header">
																	<h5 class="modal-title" id="exampleModalLabel">Selecione o novo status do pedido</h5>
																	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body" id="statusEstoque-${pedido.getId()}">
																	<select name="txtStatusPedido" id="modalSelect-${pedido.getId()}">
																		<c:choose>
																			<c:when test="${pedido.getStatusPedido() == 'EMTRANSITO'}">
																				<option class="text-capitalize" selected value="${pedido.getStatusPedido()}" disabled>Em trânsito</option>
							  													<option value="ENTREGUE">Entregue</option>
																			</c:when>
																			<c:when test="${pedido.getStatusPedido() == 'APROVADO'}">
																				<option class="text-capitalize" selected value="${pedido.getStatusPedido()}">Aprovado</option>
			                  													<option value="EMTRANSITO">Em trânsito</option>
																			</c:when>
																			<c:when test="${pedido.getStatusPedido() == 'ATUALIZADO'}">
																				  <option class="text-capitalize" selected value="${pedido.getStatusPedido()}">Atualizado</option>
																				  <option value="REPROVADO">Reprovado</option>
																				  <option value="APROVADO">Aprovado</option>
																			</c:when>
																			<c:otherwise>
																				<option class="text-capitalize" selected value="${pedido.getStatusPedido()}" >Em processamento</option>
																				<option value="REPROVADO">Reprovado</option>
																				<option value="APROVADO">Aprovado</option>
																			</c:otherwise>
																		</c:choose>
																	</select>
																	<c:forEach var="item" items="${pedido.getItensPedido()}">
																		<input type="hidden" name="txtItemPedidoId" value="${item.getId()}">
																		<input type="hidden" name="txtProdutoId" value="${item.getProduto().getId()}">
																	    <input type="hidden" name="txtQuantidadeProduto" value="${item.getQuantidade()}">
																	 </c:forEach>
																	<input type="hidden" name="tarefa" value="alterarStatusPedido"> 
																	<input type="hidden" name="txtPedidoId" value="${pedido.getId()}"> 
																	<input type="hidden" name="txtValor" value="${pedido.getValor()}"> 
																	<input type="hidden" name="txtUsuarioId" value="${pedido.getCliente().getUsuario().getId()}">
																</div>
																<div class="modal-footer">
																	<button type="submit" id="alterarStatusBotao-${pedido.getId()}" class="btn btn-success text-right">Mudar status</button>
																	<button type="button" class="btn btn-link text-left" data-dismiss="modal">Cancelar</button>
																</div>
															</form>
														</div>
													</div>
												</div>
											</td>
										<tr>
									</c:forEach>
								</tbody>

							</table>
						</section>
					</div>
				</div>
				
			</div>
		</div>
		</main>

		<footer class="page-footer text-center font-small primary-color-dark darken-2 mt-4 wow fadeIn">
			<div class="footer-copyright py-3">
				© 2020 Copyright: <a href="" target="_blank"> foxdevlabs.com </a>
			</div>
		</footer>
	</div>

	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<script type="text/javascript" src="./js/popper.min.js"></script>
	<script type="text/javascript" src="./js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./js/mdb.min.js"></script>

</body>

</html>