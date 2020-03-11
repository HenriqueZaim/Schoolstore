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
		<title>Usuário - Editar</title>
	</head>

	<body>	
		<ul>
			<c:forEach var="li" items="${mensagens}">
				<li><c:out value="${li}" /></li>
			</c:forEach>
		</ul>
	
		<form action="usuario" method="post">
			<input name="txtId" type="hidden" value="${usuario.getId()}">
			<input name="txtNome" type="text" placeholder="Nome" value="${usuario.getNome() }">
			<br>
			<input name="txtEmail" type="text" placeholder="E-mail" value="${usuario.getEmail() }">
			<br>
			<input name="txtTelefone" type="text" placeholder="Telefone" value="${usuario.getNumeroTelefone() }">
			<br>
			<input name="txtDocumento" type="text" placeholder="Documento" value="${usuario.getNumeroDocumento() }">
			<br>
			
			<input type="hidden" name="tarefa" value="atualizarUsuario"/>
	        <input type="submit" value="EDITAR">
		</form>
	
	</body>
</html>