$(document).ready(function () {
	var cliente = $("#txtClienteId").val()
	var url
	
	if(cliente === "" || cliente === null || cliente === undefined)
		url = "http://localhost:8085/schoolstore/app?tarefa=consultarPedidos"
	else
		url = `http://localhost:8085/schoolstore/app?tarefa=consultarPedidos&txtClienteId=${cliente}`

  $.ajax({
	  url: url,
	  type: "GET",
	  success: response => {
		  console.log(response)
//		  response.entidades.forEach(function(data){
//			  console.log(data)
//		  })					  
	  }
  })
});
