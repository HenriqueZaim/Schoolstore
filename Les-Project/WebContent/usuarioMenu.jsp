<%@page import="br.com.fatec.les.model.Usuario"%>
<%@page import="br.com.fatec.les.model.EntidadeDominio"%>
<%@page import="br.com.fatec.les.facade.Result"%>
<%@page import="java.util.ArrayList"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Usuários</title>
	</head>
	

	<body>	
		<h1>Olá usuário</h1>
		<form action="usuario" method="get">
			<input value="consultarUsuario" name="tarefa" type="submit">
		</form>

<!-- 		<table> -->
<!-- 			<tr> -->
<!-- 				<th> Nome </th> -->
<!-- 				<th> Valor </th> -->
<!-- 				<th> Usado </th>  -->
<!-- 			</tr>  -->
<%-- 			<c:forEach var="result" items="${resultado.getEntidades()}"> --%>
<!-- 				<tr> -->
<%-- 					<td>${p.nome} </td> --%>
<%-- 					<td>${p.valor} </td> --%>
<!-- 					<td> -->
<%-- 					<c:otherwise> --%>
					
<%-- 						<c:choose when="${p.usado}"> --%>
<!-- 							Sim -->
<%-- 						</c:choose> --%>
<%-- 						<c:otherwise> --%>
<!-- 							Não -->
<%-- 						</c:otherwise> --%>
<%-- 					</c:otherwise> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>
			
<!-- 		</table>		 -->
	</body>
</html>