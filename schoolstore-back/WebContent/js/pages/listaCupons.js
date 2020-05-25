$(document).ready(function () {
	var usuario = $("#txtUsuarioId").val()
	var admin = $("#txtIsAdmin").val()
	var url
	
	if(admin === "true")
		url = "http://localhost:8085/schoolstore/app?tarefa=consultarCupons"
	else
		url = `http://localhost:8085/schoolstore/app?tarefa=consultarCupons&txtUsuarioId=${usuario}`

  $.ajax({
	  url: url,
	  type: "GET",
	  success: response => {

			  response.entidades.forEach(function(cupom){
				  $("#tableCupons").append(`
						  <tr class="text-center">
						  	<td>${cupom.id}</td>
						  	<td>R$ ${cupom.valor}</td>
						  	<td class="text-danger">Excluir</td>
						  </tr>
				  `)
			  })

		  
	  }
  })
});