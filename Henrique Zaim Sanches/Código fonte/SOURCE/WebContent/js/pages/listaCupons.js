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
		  	console.log(response)
			  response.entidades.forEach(function(cupom){
				  if(admin === "true"){
					  $("#tableCupons").append(`
							  <tr class="text-center">
							  	<td>${cupom.id}</td>
							  	<td>R$ ${cupom.valor}</td>
							  	<td>${cupom.dataHoraVencimento.date.day}/${cupom.dataHoraVencimento.date.month}/${cupom.dataHoraVencimento.date.year} - ${cupom.dataHoraVencimento.time.hour}:${cupom.dataHoraVencimento.time.minute}</td>
							  	<td class="text-danger">
							  		<form action="app" method="post">
							  			<input type="hidden" name="tarefa" value="inativarCupom">
							  			<input type="hidden" name="txtCupomId" value="${cupom.id}">
							  			<button type="submit" class="btn btn-sm btn-link p-0 text-danger">Excluir</button>
							  		</form>
							  	</td>
							  </tr>
					  `)
				  }else{
					  $("#tableCupons").append(`
							  <tr class="text-center">
							  	<td>${cupom.id}</td>
							  	<td>R$ ${cupom.valor}</td>
							  </tr>
					  `)
				  }
				  
			  })
		  
	  }
  })
});