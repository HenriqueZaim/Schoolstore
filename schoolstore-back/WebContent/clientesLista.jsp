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
		<title>Usuários</title>
		<style>
			td{
				margin: 5px 10px;
			}
		</style>
	</head>
	

	<body>	
			<ul>
			<c:forEach var="li" items="${mensagens}">
				<li><c:out value="${li}" /></li>
			</c:forEach>
		</ul>
		<table>
		<caption>Lista de usuários</caption>
			<theader>
				<tr>
					<th> Id </th>
					<th> Nome </th>
					<th> E-mail </th>
					<th> Telefone </th>
					<th> Documento </th>
					<th> Logradouro </th>
					<th colspan="2"> Ação </th>
				</tr> 
			</theader>
			<tbody>
				<c:forEach var="li" items="${clientes}">
			        <tr>
			            <td><c:out value="${li.getId()}" /></td>
			            <td><c:out value="${li.getNome()}" /></td>
			            <td><c:out value="${li.getUsuario().getEmail()}" /></td>
			            <td><c:out value="${li.getNumeroTelefone()}" /></td>
			            <td><c:out value="${li.getNumeroDocumento()}" /></td>
			            <td><c:out value="${li.getEndereco().getLogradouro()}" /></td>
			            <td>
			            	<form action="cliente" method="POST">
			            		<input type="hidden" name="txtUsuarioId" value="${li.getUsuario().getId()}">
			            		<input type="hidden" name="txtEnderecoId" value="${li.getEndereco().getId()}">
			            		<input type="hidden" name="txtClienteId" value="${li.getId()}">
			            		
			            		<input type="hidden" name="tarefa" value="editaCliente">
			            		<input type="submit" value="EDITAR" >
			            	</form>
			            </td>
			            <td>
			            	<form action="cliente" method="POST">
			            		<input type="hidden" name="txtUsuarioId" value="${li.getUsuario().getId()}">
			            		<input type="hidden" name="txtEnderecoId" value="${li.getEndereco().getId()}">
			            		<input type="hidden" name="txtClienteId" value="${li.getId()}">
			            		
			            		<input type="hidden" name="tarefa" value="deletarCliente">
			            		<input type="submit" value="EXCLUIR" >
			            	</form>
			            </td>
				     </tr>
				</c:forEach>
			</tbody>
		</table>		
	</body>
</html>