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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<c:forEach var="li" items="${mensagens}">
			<li><c:out value="${li}" /></li>
		</c:forEach>
	</ul>
	<form action="cliente" method="post" style="width: 50%;">
		<input name="txtNome" maxlength="100" type="text" placeholder="Nome">
		<br>
		<input name="txtNumeroTelefone" maxlength="11" type="text" placeholder="Telefone">
		<br>
		<input name="txtNumeroDocumento" maxlength="14" type="text" placeholder="Documento">
		<br>
		<input type="file" placeholder="imagem" id="file">
		<input id="button" type="button" value="inserir imagem">
		<br>
		<hr>
		<br>
		<input name="txtEmail" maxlength="100" type="text" placeholder="E-mail">
		<br>
		<input name="txtSenha" maxlength="100" type="password" placeholder="Senha">
		<br>
		<hr>
		<br>
		<input name="txtLogradouro" maxlength="100" type="text" placeholder="Rua">
		<br>
		<input name="txtBairro" maxlength="100" type="text" placeholder="Bairro">
		<br>
		<input name="txtNumero" maxlength="4" type="text" placeholder="Numero">
		<br>
		<input name="txtComplemento" maxlength="100" type="text" placeholder="Complemento">
		<br>
		<input name="txtReferencia" maxlength="100" type="text" placeholder="Referencia">
		<br>
		<input name="txtCep" maxlength="9" type="text" placeholder="Cep">
		<br>
		<input type="hidden" name="txtCidadeId" value="8">
		<input type="hidden" name="txtEnderecoId" value="1">
		<br>
		<hr>
		<br>
		<input name="txtLogradouro" maxlength="100" type="text" placeholder="Rua">
		<br>
		<input name="txtBairro" maxlength="100" type="text" placeholder="Bairro">
		<br>
		<input name="txtNumero" maxlength="4" type="text" placeholder="Numero">
		<br>
		<input name="txtComplemento" maxlength="100" type="text" placeholder="Complemento">
		<br>
		<input name="txtReferencia" maxlength="100" type="text" placeholder="Referencia">
		<br>
		<input name="txtCep" maxlength="9" type="text" placeholder="Cep">
		<br>
		<input type="hidden" name="txtCidadeId" value="8">
		<input type="hidden" name="txtEnderecoId" value="2">
 
				<br>
		<hr>
		<br>
		<input name="txtLogradouro" maxlength="100" type="text" placeholder="Rua">
		<br>
		<input name="txtBairro" maxlength="100" type="text" placeholder="Bairro">
		<br>
		<input name="txtNumero" maxlength="4" type="text" placeholder="Numero">
		<br>
		<input name="txtComplemento" maxlength="100" type="text" placeholder="Complemento">
		<br>
		<input name="txtReferencia" maxlength="100" type="text" placeholder="Referencia">
		<br>
		<input name="txtCep" maxlength="9" type="text" placeholder="Cep">
		<br>
		<input type="hidden" name="txtCidadeId" value="8">
		<input type="hidden" name="txtEnderecoId" value="3">		
		
		<input type="hidden" id="base64" name="file">
		<input type="hidden" name="tarefa" value="cadastrarCliente"/>
        <input type="submit" value="CADASTRAR">
	</form>
</body>

<script>

document.getElementById('button').addEventListener('click', function() {
  var files = document.getElementById('file').files;
  if (files.length > 0) {
	getBase64(files[0]);
  }
});

function getBase64(file) {
   var reader = new FileReader();
   reader.readAsDataURL(file);
   reader.onload = function () {
	   document.getElementById('base64').value = reader.result;
   };
   reader.onerror = function (error) {
     console.log('Error: ', error);
   };
}

</script>
</html>