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
	</head>
	

	<body>	
	
		<%
			System.out.print(request.getAttribute("usuarios"));
		
		%>


		<table>
			<tr>
				<th> Nome </th>

			</tr> 
			<c:forEach var="li" items="${usuarios}">
		        <tr>
		            <td><c:out value="${li.getNome()}" /></td>
			     </tr>
			</c:forEach>
			
		</table>		
	</body>
</html>