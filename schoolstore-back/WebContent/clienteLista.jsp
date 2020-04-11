<%@page import="java.util.List"%>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="br.com.fatec.les.model.Usuario"%>
<%@page import="br.com.fatec.les.model.EntidadeDominio"%>
<%@page import="br.com.fatec.les.facade.Result"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="pt">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>Material Design for Bootstrap</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
  <link rel="stylesheet" href="./css/bootstrap.min.css">
  <link rel="stylesheet" href="./css/mdb.min.css">

  <link rel="stylesheet" href="css/style.css">

  <style>
    .sidebar-fixed {
      height: 100vh;
      width: 270px;
      -webkit-box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .16), 0 2px 10px 0 rgba(0, 0, 0, .12);
      box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .16), 0 2px 10px 0 rgba(0, 0, 0, .12);
      z-index: 1050;
      background-color: #fff;
      padding: 0 1.5rem 1.5rem
    }

    .sidebar-fixed .list-group .active {
      -webkit-box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .16), 0 2px 10px 0 rgba(0, 0, 0, .12);
      box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .16), 0 2px 10px 0 rgba(0, 0, 0, .12);
      -webkit-border-radius: 5px;
      border-radius: 5px
    }

    .sidebar-fixed .logo-wrapper {
      padding: 2.5rem
    }

    .sidebar-fixed .logo-wrapper img {
      max-height: 50px
    }

    @media (min-width:1200px) {

      .navbar,
      .page-footer,
      main {
        padding-left: 270px
      }
    }

    @media (max-width:1199.98px) {
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
  <div class="container-for-admin">
    <header>
      <nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
        <div class="container-fluid">

          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item">
                <a class="nav-link waves-effect" href="index.html">In�cio
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link waves-effect" href="about.html">
                  Sobre</a>
              </li>
              <li class="nav-item">
                <a class="nav-link waves-effect" href="products.html">Produtos</a>
              </li>
            </ul>

            <ul
              class="navbar-nav ml-auto nav-flex-icons align-items-center flex-row-reverse flex-md-row justify-content-between">

              <li class="nav-item avatar dropdown">
                <a class="nav-link dropdown-toggle waves-effect waves-light" id="navbarDropdownMenuLink-5"
                  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <img src="https://mdbootstrap.com/img/Photos/Avatars/avatar-2.jpg" class="rounded-circle z-depth-0"
                    height="35px" alt="avatar image">
                </a>
                <div class="dropdown-menu dropdown-menu-lg-right py-0 dropdown-secondary"
                  aria-labelledby="navbarDropdownMenuLink-5">
                  <a class="dropdown-item waves-effect waves-light" href="#">Sair</a>
                </div>
              </li>
            </ul>

          </div>

        </div>
      </nav>
      <div class="sidebar-fixed position-fixed">

        <a class="navbar-brand waves-effect" href="index.html">
          <strong class="blue-text h1 m-2">SchoolStore</strong>
        </a>

        <div class="list-group list-group-flush mt-5">
          <a href="cart.html" class="list-group-item  list-group-item-action waves-effect">

            <i class="fas fa-cart-arrow-down mr-3"></i>Meu Carrinho</a>
          <a href="request.html" class="list-group-item  list-group-item-action waves-effect">
            <i class="fa fa-user mr-3"></i>Meu Pedidos</a>
          <a href="profile.html" class="list-group-item list-group-item-action waves-effect">
            <i class="fa fa-table mr-3"></i>Meu Perfil</a>
        </div>
        <div class="list-group list-group-flush mt-5 pt-5">
          <a href="productnew.html" class="list-group-item list-group-item-action waves-effect">
            <i class="fas fa-cart-arrow-down mr-3"></i>Cadastrar Produto</a>
          <a href="productslist.html" class="list-group-item list-group-item-action waves-effect">
            <i class="fa fa-user mr-3"></i>Lista de Produtos</a>
          <a href="productslist.html" class="list-group-item active list-group-item-action waves-effect">
            <i class="fa fa-user mr-3"></i>Lista de Clientes</a>
          <a href="requestlist.html" class="list-group-item  list-group-item-action waves-effect">
            <i class="fa fa-user mr-3"></i>Lista de Pedidos</a>
          <a href="relatorios.html" class="list-group-item list-group-item-action waves-effect">
            <i class="fa fa-table mr-3"></i>Relat�rios</a>
        </div>


      </div>
    </header>

    <main class="pt-5 mx-lg-5">
      <div class="container-fluid mt-5">
        <div class="card mb-4 wow fadeIn">
          <div class="card-body d-sm-flex justify-content-between">
            <h1 class="mb-2 mb-sm-0 pt-1">
              Lista de Clientes
            </h1>
          </div>
        </div>

        <div class="row">
          <div class="col-lg-8">
            <div class="card mb-4 wow fadeIn mb-5">
              <section class="dark-grey-text  ">
                <table class="table table-striped table-responsive-md btn-table">
    
                  <thead>
                    <tr class="font-weight-bold text-center">
                      <th >#Id</th>
                      <th >Nome</th>
                      <th >Telefone</th>
                      <th >Endere�o</th>
                      <th colspan="2">A��o</th>
                    </tr>
                  </thead>
    
                  <tbody>
                  	<c:forEach var="cliente" items="${clientes}">
                  
	                    <tr class="text-center">
	                      <th scope="row">${cliente.getId()}</th>
	                      <td> ${cliente.getNome()} </td>
	                      <td> ${cliente.getNumeroTelefone()} </td>
	                      <td>
	                     	<c:forEach var="endereco" items="${cliente.getEnderecos()}">
		                        <div>
			                        <input type="hidden" name="txtCep" value="${endereco.getCep()}">
			                        <input type="hidden" name="txtEstado" value="${endereco.getCidade().getEstado().getNome()}">
			                        <input type="hidden" name="txtCidade" value="${endereco.getCidade().getNome()}">
			                        <input type="hidden" name="txtLogradouro" value="${endereco.getLogradouro()}">
			                        <input type="hidden" name="txtNumero" value="${endereco.getNumero()}">
			                        <input type="hidden" name="txtComplemento" value="${endereco.getComplemento() }">
			                        <input type="hidden" name="txtBairro" value="${endereco.getBairro() }">
			                        <input type="hidden" name="txtReferencia" value="${endereco.getReferencia() }">
			                        <input type="hidden" name="txtFavorito" value="${endereco.isFavorito() }">
			                        <input type="hidden" name="txtNome" value="${cliente.getNome() }">
			                        <input type="hidden" name="txtNomeEndereco" value="${endereco.getNome()}">
			                        <button type="button" class="btn btn-link btn-sm text-info p-0 font-weight-bold visualizarEndereco" >
			                           ${endereco.getNome()}
			                           <c:if test="${endereco.isFavorito() }">
											<i class="fa fa-star text-yellow" aria-hidden="true"></i>
			                           </c:if>
		                            </button>
		                        </div>
		                      </c:forEach>
	                      </td>
	                      <td>
	                        <form action="cliente" method="POST">
	                          <input type="hidden" name="txtUsuarioId" value="${cliente.getUsuario().getId()}">
	                          <input type="hidden" name="txtClienteId" value="${cliente.getId()}">
	                          <input type="hidden" name="txtImagemId" value="${cliente.getUsuario().getImagem().getId()}">
	    
	                          <input type="hidden" name="tarefa" value="editaCliente">
	                          <button type="submit"
	                            class="btn btn-sm btn-link p-0 text-warning font-weight-bold">Editar</button>
	                        </form>
	                      </td>
	                      <td>
	                        <form action="cliente" method="POST" class="text-center">
	                          <input type="hidden" name="txtUsuarioId" value="${cliente.getUsuario().getId()}">
	                          <input type="hidden" name="txtClienteId" value="${cliente.getId()}">
	                          <input type="hidden" name="txtImagemId" value="${cliente.getUsuario().getImagem().getId()}">
	    
	                          <input type="hidden" name="tarefa" value="deletarCliente">
	                          <button type="submit"
	                            class="btn btn-sm btn-link p-0 text-danger font-weight-bold">Excluir</button>
	                        </form>
	                      </td>
	                    </tr>
                  
                  	</c:forEach>
                  </tbody>
    
                </table>
            </div>
          </div>
          <div class="col-lg-4">
            <div class="card mb-4 wow fadeIn px-2 py-3">
              <h3>Endere�o</h3>
              <section class="dark-grey-text px-3 py-2" id="painelEndereco">
                
              </section>
            </div>

          </div>
        </div>
        </section>
      </div>
    </main>

    <footer class="page-footer text-center font-small primary-color-dark darken-2 mt-4 wow fadeIn">
      <div class="footer-copyright py-3">
        � 2020 Copyright:
        <a href="" target="_blank"> foxdevlabs.com </a>
      </div>
    </footer>
  </div>

  <script type="text/javascript" src="./js/jquery.min.js"></script>
  <script type="text/javascript" src="./js/popper.min.js"></script>
  <script type="text/javascript" src="./js/bootstrap.min.js"></script>
  <script type="text/javascript" src="./js/mdb.min.js"></script>
  <script type="text/javascript" src="./js/pages/listaClientes.js"></script>

</body>

</html>