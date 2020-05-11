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
  <title>Material Design for Bootstrap</title>
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
  <header style="background: rgba(0,0,0,0.8);">
    <nav class="navbar navbar-expand-lg navbar-dark bg-transparent shadow-none scrolling-navbar">
      <div class="container">
        <a class="navbar-brand mx-auto font-weight-bold" href="index.html">SCHOOLSTORE</a>
      </div>
    </nav>
  </header>

  <main>
    <input type="hidden" id="txtCarrinhoId" value="${cliente.getCarrinho().getId() }">
    <input type="hidden" id="txtClienteId" value="${cliente.getId() }">
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
                    <c:forEach var="endereco" items="${cliente.getEnderecos()}">
                      <div class="card">
                        <div class="card-header" role="tab" id="endereco${endereco.getId()}">
                          <div class="row">
                            <div class="form-check pl-0 mb-2">
                              <c:if test="${endereco.isFavorito() }">
                                <input type="radio" class="form-check-input enderecoInput"
                                  id="enderecoInput${endereco.getId() }" name="enderecoInput"
                                  value="${endereco.getId() }" checked>
                              </c:if>
                              <c:if test="${!endereco.isFavorito() }">
                                <input type="radio" class="form-check-input enderecoInput"
                                  id="enderecoInput${endereco.getId() }" name="enderecoInput"
                                  value="${endereco.getId() }">
                              </c:if>
                              <label class="form-check-label" for="enderecoInput${endereco.getId() }"></label>
                            </div>
                            <h5 class="mb-0 flex-grow-1">
                              ${endereco.getNome()}
                            </h5>
                            <div>
                              <a data-toggle="collapse" class="float-left" data-parent="#accordionEndereco"
                                href="#endereco-${endereco.getId()}" aria-expanded="false"
                                aria-controls="endereco-${endereco.getId()}">
                                <i class="fas fa-angle-down rotate-icon"></i>
                              </a>
                              <button onclick="$(this).parent().parent().parent().remove()"
                                class="btn btn-link p-0 m-0 float-right ml-5">
                                <i class="fas fa-times text-danger"></i>
                              </button>
                            </div>
                          </div>
                        </div>
                        <div id="endereco-${endereco.getId()}" class="collapse" role="tabpanel"
                          aria-labelledby="endereco${endereco.getId()}" data-parent="#accordionEndereco">
                          <div class="card-body">
                            <ul>
                              <li><b>CEP: </b>${endereco.getCep()}</li>
                              <li><b>Estado: </b>${endereco.getCidade().getEstado().getNome()}</li>
                              <li><b>Cidade: </b>${endereco.getCidade().getNome()}</li>
                              <li><b>Logradouro: </b>${endereco.getLogradouro()}</li>
                              <li><b>Número: </b>${endereco.getNumero()}</li>
                              <li><b>Bairro: </b>${endereco.getBairro()}</li>
                              <li><b>Complemento: </b>${endereco.getComplemento() == null ? "Nenhum" :
                                endereco.getComplemento()}</li>
                              <li><b>Referência: </b>${endereco.getReferencia() == null ? "Nenhum" :
                                endereco.getReferencia()}</li>
                              <li><b>Favorito: </b>${endereco.isFavorito() == true ? "Sim" : "Não"}</li>
                            </ul>
                          </div>
                        </div>

                        <input type="hidden" name="txtEndId" value="${endereco.getId()}">
                        <input type="hidden" name="txtEstSigla" value="${endereco.getCidade().getEstado().getSigla() }">

                      </div>
                    </c:forEach>
                  </div>
                </div>
                <div class="col-lg-12 mt-5">
                  <h2>2. Selecione um método de pagamento</h2>
                  <div class="accordion md-accordion" id="accordionCartao" role="tablist" aria-multiselectable="true">
                    <c:forEach var="cartao" items="${cliente.getCartoesCredito()}">
                      <div class="card">
                        <div class="card-header" role="tab" id="cartao${cartao.getId()}">
                          <div class="row">
                            <div class="form-check pl-0 mb-2">
                              <c:if test="${cartao.isFavorito() }">
                                <input type="checkbox" class="form-check-input cartaoInput"
                                  id="cartaoInput${cartao.getId() }" name="cartaoInput" value="${cartao.getId() }"
                                  checked>
                              </c:if>
                              <c:if test="${!cartao.isFavorito() }">
                                <input type="checkbox" class="form-check-input cartaoInput"
                                  id="cartaoInput${cartao.getId() }" name="cartaoInput" value="${cartao.getId() }">
                              </c:if>
                              <label class="form-check-label" for="cartaoInput${cartao.getId() }"></label>
                            </div>
                            <h5 class="mb-0 flex-grow-1">
                              Final ${cartao.getNumero().substring(12,16)}
                            </h5>
                            <div>

                              <a data-toggle="collapse" class="float-left" data-parent="#accordionCartao"
                                href="#cartao-${cartao.getId()}" aria-expanded="false"
                                aria-controls="cartao-${cartao.getId()}">
                                <i class="fas fa-angle-down rotate-icon"></i>
                              </a>
                              <button onclick="$(this).parent().parent().parent().remove()"
                                class="btn btn-link p-0 m-0 float-right ml-5">
                                <i class="fas fa-times text-danger"></i>
                              </button>

                            </div>
                          </div>
                        </div>
                        <div id="cartao-${cartao.getId()}" class="collapse" role="tabpanel"
                          aria-labelledby="cartao${cartao.getId()}" data-parent="#accordionCartao">
                          <div class="card-body">
                            <ul>
                              <li><b>Nome impresso: </b>${cartao.getNomeImpresso()}</li>
                              <li><b>Número: </b>${cartao.getNumero()}</li>
                              <li><b>Código: </b>${cartao.getCodigo()}</li>
                              <li><b>Favorito: </b>${cartao.isFavorito() == true ? "Sim" : "Não"}</li>
                            </ul>
                          </div>
                        </div>

                        <input type="hidden" name="txtCartaoId" value="${endereco.getId()}">
                      </div>
                    </c:forEach>
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