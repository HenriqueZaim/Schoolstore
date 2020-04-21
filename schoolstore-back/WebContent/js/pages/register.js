var estados;

$(document).ready(function () {
  $('.mdb-select').materialSelect();
  colocarMascaras(true)
  
  $.ajax({
	  url: "http://localhost:8085/schoolstore/app?tarefa=cadastroCliente",
	  type: "GET",
	  success: response => {
		  response.forEach(function(data){
			  $("#txtEstadoModal").append(
				`<option value="${data.id}">${data.nome}</option>`
			  )
		  })
		  estados = response
	  }
  })
});

$("#clienteFormulario").submit(function(event){
	event.preventDefault()
	colocarMascaras(false)
	$(this).unbind('submit').submit()
})

function colocarMascaras(flag){
	var SPMaskBehavior = function (val) {
		return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
	},
	spOptions = {
			onKeyPress: function(val, e, field, options) {
				field.mask(SPMaskBehavior.apply({}, arguments), options);
			}
	};
	if(flag){
		  $("#txtNumeroModal").mask("0000")
		  $("#txtCepModal").mask("00000-000")
		  $("#txtNumeroTelefone").mask(SPMaskBehavior, spOptions);
		  $("#txtNumeroDocumento").keydown(function(){
		    try {
		        $("#txtNumeroDocumento").unmask();
		    } catch (e) {}

		    var tamanho = $("#txtNumeroDocumento").val().length;

		    if(tamanho < 11){
		        $("#txtNumeroDocumento").mask("999.999.999-99");
		    } else {
		        $("#txtNumeroDocumento").mask("99.999.999/9999-99");
		    }
		    var elem = this;
		    setTimeout(function(){
		        elem.selectionStart = elem.selectionEnd = 10000;
		    }, 0);
		    var currentValue = $(this).val();
		    $(this).val('');
		    $(this).val(currentValue);
		  })
		
	}else{
		$("#txtNumeroModal").unmask()
		$("#txtCepModal").unmask()
		$("#txtNumeroTelefone").unmask()
		$("#txtNumeroDocumento").unmask()
	}
}

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

$("#file").change(() => {
  var files = document.getElementById('file').files;
  if (files.length > 0) {
      getBase64(files[0]);
  }
})

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

$("#btnSalvarEndereco").click(function () {
  let cep = $("#txtCepModal").val()
  let logradouro = $("#txtLogradouroModal").val()
  let numero = $("#txtNumeroModal").val()
  let cidade = $("#txtCidadeModal").val()
  let referencia = $("#txtReferenciaModal").val()
  let complemento = $("#txtComplementoModal").val()
  let favorito = $("#txtFavoritoModal").val()
  let bairro = $("#txtBairroModal").val()
  let estado = $("#txtEstadoModal").val()
  let nome = $("#txtNomeEnderecoModal").val()
  
  let cidadeTexto = $("#txtCidadeModal").find(`option[value=${cidade}]`).text()
  let estadoTexto = $("#txtEstadoModal").find(`option[value=${estado}]`).text()

  let idHeading = `${logradouro}-${numero}`
  let idCollapse = `collapse-${numero}`

  let enderecos = $("#accordionEndereco");

  enderecos.append(`
      <div class="card">
          <div class="card-header" role="tab" id="${idHeading}">
              <h5 class="mb-0">
                  ${nome}
                  <a data-toggle="collapse" data-parent="#accordionEndereco" href="#${idCollapse}" aria-expanded="false" aria-controls="${idCollapse}">
                      <i class="fas fa-angle-down rotate-icon"></i>
                  </a>
                  <button onclick="$(this).parent().parent().parent().remove()" class="btn btn-link p-0 m-0 float-right mr-5">
                      <i class="fas fa-times text-danger"></i>
                  </button>
              </h5>
          </div>
          <div id="${idCollapse}" class="collapse" role="tabpanel" aria-labelledby="${idHeading}" data-parent="#accordionEndereco">
              <div class="card-body">
                  <ul>
                    <li><b>CEP: </b>${cep}</li>
                    <li><b>Estado: </b>${estadoTexto}</li>
                    <li><b>Cidade: </b>${cidadeTexto}</li>
                    <li><b>Logradouro: </b>${logradouro}</li>
                    <li><b>Número: </b>${numero}</li>
                    <li><b>Bairro: </b>${ bairro = bairro != "" ? bairro : "Nenhum" }</li>
                    <li><b>Complemento: </b>${complemento}</li>
                    <li><b>Referência: </b>${referencia}</li>
                    <li><b>Favorito: </b>${favorito}</li>
                  </ul>
              </div>
          </div>

		  <input type="hidden" name="txtEnderecoId" value="">
		  <input type="hidden" name="txtNomeEndereco" value="${nome}">
          <input type="hidden" name="txtCep" value="${cep}">
          <input type="hidden" name="txtBairro" value="${bairro}">
          <input type="hidden" name="txtLogradouro" value="${logradouro}">
          <input type="hidden" name="txtCidadeId" value="${cidade}">
          <input type="hidden" name="txtReferencia" value="${referencia}">
          <input type="hidden" name="txtComplemento" value="${complemento}">
          <input type="hidden" name="txtNumero" value="${numero}">
          <input type="hidden" name="txtFavoritoEndereco" value="${favorito}">
          <input type="hidden" name="txtEndereco" value="true">
      </div>
  `)

  $("#modalEndereco").modal('toggle');
});

$("#btnSalvarCartao").click(function () {

  let nome = $("#txtNomeImpressoModal").val()
  let codigo = $("#txtCodigoModal").val()
  let numero = $("#txtNumeroCartaoModal").val()
  let favorito = $("#txtFavoritoCartaoModal").val()
  let digitos = numero.substr(12,15)

  let idHeading = `${nome}-${codigo}`
  let idCollapse = `collapse-${codigo}`

  let cartoes = $("#accordionCartao");

  cartoes.append(`
      <div class="card">
          <div class="card-header" role="tab" id="${idHeading}">
              <h5 class="mb-0">
                  ${nome} - Final ${digitos} 
                  <a data-toggle="collapse" data-parent="#accordionCartao" href="#${idCollapse}" aria-expanded="false" aria-controls="${idCollapse}">
                      <i class="fas fa-angle-down rotate-icon"></i>
                  </a>
                  <button onclick="$(this).parent().parent().parent().remove()" class="btn btn-link p-0 m-0 float-right mr-5">
                      <i class="fas fa-times text-danger"></i>
                  </button>
              </h5>
          </div>
          <div id="${idCollapse}" class="collapse" role="tabpanel" aria-labelledby="${idHeading}" data-parent="#accordionCartao">
              <div class="card-body">
                  <ul>
                    <li><b>Nome impresso: </b>${nome}</li>
                    <li><b>Número: </b>${numero}</li>
                    <li><b>Código de segurança: </b>${codigo}</li>
                    <li><b>Favorito: </b>${favorito}</li>
                  </ul>
              </div>
          </div>

          <input type="hidden" name="txtNomeCartao" value="${nome}">
          <input type="hidden" name="txtCodigoCartao" value="${codigo}">
          <input type="hidden" name="txtNumeroCartao" value="${numero}">
          <input type="hidden" name="txtFavoritoCartao" value="${favorito}">
      </div>
  `)

  $("#modalCartao").modal('toggle');
});

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

// $('#modalEndereco').on('show.bs.modal', function() {
// 	$(this)	
// 	.find("input,textarea,select")
// 	.val('')
// 	.end()
// })

// $('#modalCartao').on('show.bs.modal', function() {
// 	$(this)	
// 	.find("input,textarea,select")
// 	.val('')
// 	.end()
// })