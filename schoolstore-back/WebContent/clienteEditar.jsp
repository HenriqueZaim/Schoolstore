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
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Cliente - Editar</title>
	</head>

	<body>	
		<ul>
			<c:forEach var="li" items="${mensagens}">
				<li><c:out value="${li}" /></li>
			</c:forEach>
		</ul>
		
		<form action="cliente" method="post" style="width: 50%;">
			<input name="txtClienteId" type="hidden" value="${cliente.getId()}">
			<input name="txtEnderecoId" type="hidden" value="${cliente.getEndereco().getId()}">
			<input name="txtUsuarioId" type="hidden" value="${cliente.getUsuario().getId()}">
			
			<input name="txtNome" maxlength="100" type="text" value="${cliente.getNome()}" placeholder="Nome">
			<br>
			<input name="txtNumeroTelefone" maxlength="11" value="${cliente.getNumeroTelefone()}" type="text" placeholder="Telefone">
			<br>
			<input name="txtNumeroDocumento" maxlength="14" value="${cliente.getNumeroDocumento()}" type="text" placeholder="Documento">
			<br>
			<hr>
			<br>
			<input name="txtEmail" maxlength="100" value="${cliente.getUsuario().getEmail()}" type="text" placeholder="E-mail">
			<hr>
			<br>
			<input name="txtLogradouro" maxlength="100" value="${cliente.getEndereco().getLogradouro()}" type="text" placeholder="Rua">
			<br>
			<input name="txtBairro" maxlength="100" value="${cliente.getEndereco().getBairro()}" type="text" placeholder="Bairro">
			<br>
			<input name="txtNumero" maxlength="4" value="${cliente.getEndereco().getNumero()}" type="text" placeholder="Numero">
			<br>
			<input name="txtComplemento" maxlength="100" value="${cliente.getEndereco().getComplemento()}" type="text" placeholder="Complemento">
			<br>
			<input name="txtReferencia" maxlength="100" value="${cliente.getEndereco().getReferencia()}" type="text" placeholder="Referencia">
			<br>
			<input name="txtCep" maxlength="9" value="${cliente.getEndereco().getCep()}" type="text" placeholder="Cep">
			<br>
			<input type="hidden" name="txtCidadeId" value="8">
			<input type="radio" name="txtFavorito" value="${cliente.getEndereco().isFavorito()}">Favorito - sim
			<br>
			<input type="radio" name="txtFavorito" >Não  
			
			<input type="hidden" name="tarefa" value="atualizarCliente"/>
	        <input type="submit" value="EDITAR">
		</form>
	
	</body>
</html>