
var itensCarrinho;
var estados;

$(document).ready(function () {
  var ativo = "true"
  var txtClienteId = $("#txtClienteId").val();
  $.ajax({
	  url: `http://localhost:8085/schoolstore/app?tarefa=consultarEndereco&txtClienteId=${txtClienteId}&txtAtivo=${ativo}`,
	  type: "GET",
	  async: false,
	  success: (response) => {
		  console.log(response)
		  response.entidades.forEach(function(endereco){
			  $("#accordionEndereco").append(`
					  <div class="card">
					  <div class="card-header" role="tab" id="endereco${endereco.id}">
					  <div class="row">
					  <div class="form-check pl-0 mb-2 enderecoFavorito">
					  <label class="form-check-label" for="enderecoInput${endereco.id }"></label>
					  </div>
					  <h5 class="mb-0 flex-grow-1">${endereco.nome}</h5>
					  <div class="d-flex align-items-center">
					  <a data-toggle="collapse" class="float-left pr-4" data-parent="#accordionEndereco" href="#endereco-${endereco.id}" aria-expanded="false" aria-controls="endereco-${endereco.id}"> <i class="fas fa-angle-down"></i>
					  </a>

					  <form action="app" method="post">
					  	<input type="hidden" name="tarefa" value="removerEnderecoLista">
					  	<input type="hidden" name="txtEnderecoId" value="${endereco.id}">
					  	<button type="submit" id="eapagar${endereco.id}" class="btn btn-link text-danger p-0"><i class="fa fa-times"></i></button>
					  </form>

					  </div>
					  </div>
					  </div>
					  <div id="endereco-${endereco.id}" class="collapse" role="tabpanel" aria-labelledby="endereco${endereco.id}" data-parent="#accordionEndereco">
					  <div class="card-body">
					  <ul>
					  <li><b>CEP: </b>${endereco.cep}</li>
					  <li><b>Estado: </b>${endereco.cidade.estado.nome}</li>
					  <li><b>Cidade: </b>${endereco.cidade.nome}</li>
					  <li><b>Logradouro: </b>${endereco.logradouro}</li>
					  <li><b>Número: </b>${endereco.numero}</li>
					  <li><b>Bairro: </b>${endereco.bairro}</li>
					  <li><b>Complemento: </b>${endereco.complemento == null ? "Nenhum" : endereco.complemento}</li>
					  <li><b>Referência: </b>${endereco.referencia == null ? "Nenhum" : endereco.referencia}</li>
					  <li><b>Favorito: </b>${endereco.favorito == true ? "Sim" : "Não"}</li>
					  </ul>
					  </div>
					  </div>

					  <input type="hidden" name="txtEndId" value="${endereco.id}"> <input type="hidden" name="txtEstSigla" value="${endereco.cidade.estado.sigla }">

					  </div>
			  `)
		  })
	  }
  })
  
    $.ajax({
	  url: "http://localhost:8085/schoolstore/app?tarefa=cadastroCliente",
	  type: "GET",
	  async: false,
	  success: response => {
		  response.forEach(function(data){
			  $("#txtEstadoModal").append(
				`<option value="${data.id}">${data.nome}</option>`
			  )
		  })
		  estados = response
	  }
  })
})

$("#txtEstadoModal").change(function(){
	$("#txtCidadeModal").children(".cidade").remove()
	let id = $("#txtEstadoModal").val()
	estados.forEach(function(data){
		if(id == data.id){
			data.cidades.forEach(function(cidade){
				$("#txtCidadeModal").append(
					`<option value="${cidade.id}" class="cidade">${cidade.nome}</option>`
				)
			})
		}
	})
})

$("#txtFavoritoModal").click(() => {
	  if($("#txtFavoritoModal").val() == "false"){
	    $("#txtFavoritoModal").val("true")
	  }else{
	    $("#txtFavoritoModal").val("false")
	  }
	})

	$("#txtFavoritoCartaoModal").click(() => {
	  if($("#txtFavoritoCartaoModal").val() == "false"){
	    $("#txtFavoritoCartaoModal").val("true")
	  }else{
	    $("#txtFavoritoCartaoModal").val("false")
	  }
	})