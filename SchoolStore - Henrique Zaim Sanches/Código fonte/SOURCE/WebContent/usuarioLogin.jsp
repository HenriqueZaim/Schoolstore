<%@page import="java.util.List"%>
<%@page
	import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="br.com.fatec.les.model.usuario.Usuario"%>
<%@page import="br.com.fatec.les.model.config.EntidadeDominio"%>
<%@page import="br.com.fatec.les.facade.Resultado"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="pt-br" xml:lang="pt-br">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

  <title>Login</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
  <link rel="stylesheet" href="./css/bootstrap.min.css">
  <link rel="stylesheet" href="./css/mdb.min.css">

  <style>
    body {
      background: linear-gradient(to bottom, #74747478, #000000),
        url(./img/row-of-books-in-shelf-256541.jpg);
      background-position: center;
      background-size: cover;
      background-attachment: fixed;
      background-repeat: no-repeat;
    }
  </style>
</head>

<body>

  <%
        String login = "";
        login = (String) session.getAttribute("status");
        
        if(login == "on"){
            response.sendRedirect("clienteMenu.jsp");
        }
    %>

  <header style="background: rgba(0, 0, 0, 0.6);">
    <nav class="navbar navbar-expand-lg navbar-dark bg-transparent shadow-none scrolling-navbar">
      <div class="container">
        <a class="navbar-brand mx-auto font-weight-bold" href="index.html">SCHOOLSTORE</a>
      </div>
    </nav>
  </header>

  <main>
    <section>
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-6 my-5 py-2">
            <div class="card">
              <div
                class="green darken-4 white-text px-2 px-lg-5 card-header d-flex align-items-center justify-content-between">
                <h1 class="h2 mb-0">LOGIN</h1>
                <p class="mb-0 small">
                  Não está cadastrado? <a href="clienteCadastro.jsp" class="cyan-text">Cadastre-se
                    já</a>
                </p>
              </div>
              <div class="card-body px-2 px-lg-5 pt-3 text-center ">
                <c:forEach var="mensagem" items="${resultado.getMensagens()}">
                  <c:choose>
                    <c:when test="${mensagem.getMensagemStatus() == 'ERRO'}">
                      <div class="alert alert-danger p-0 border-0" role="alert">
                        <span class="d-block px-3 py-2"><i
                            class="fas fa-exclamation-circle p-0 px-2"></i>${mensagem.getMensagem()}</span>
                      </div>
                    </c:when>
                    <c:otherwise>
                      <div class="alert alert-success p-0 border-0" role="alert">
                        <span class="d-block px-3 py-2"><i
                            class="fas fa-exclamation-circle p-0 px-2"></i>${mensagem.getMensagem()}</span>
                      </div>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>

                <form class="text-center" style="color: #757575;" method="POST" action="app">
                  <div class="md-form">
                    <input type="email" id="txtEmail" name="txtEmail" class="form-control"> <label
                      for="txtEmail">E-mail</label>
                  </div>
                  <div class="md-form">
                    <input type="password" id="txtSenha" name="txtSenha" class="form-control"> <label
                      for="txtSenha">Senha</label>
                  </div>
                  <input type="hidden" name="tarefa" value="loginUsuario" />

                  <button
                    class="btn btn-outline-success rounded-pill w-50 mx-auto btn-rounded btn-block mt-5 mb-3 waves-effect z-depth-0"
                    type="submit">Entrar</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>

  <script type="text/javascript" src="./js/jquery.min.js"></script>
  <script type="text/javascript" src="./js/popper.min.js"></script>
  <script type="text/javascript" src="./js/bootstrap.min.js"></script>
  <script type="text/javascript" src="./js/mdb.min.js"></script>

</body>

</html>