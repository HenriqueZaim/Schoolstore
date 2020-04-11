$(document).ready(function () {
  $('.mdb-select').materialSelect();
});

(function () {
  'use strict';
  window.addEventListener('load', function () {
      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      var forms = document.getElementsByClassName('needs-validation');
      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function (form) {
          form.addEventListener('submit', function (event) {
              if (form.checkValidity() === false) {
                  event.preventDefault();
                  event.stopPropagation();
              }
              form.classList.add('was-validated');
          }, false);
      });
  }, false);
})();

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

$("#txtEstadoModal").change(function(){
	$("#txtCidadeModal").find(`option`).css("display","none")
	let id = $("#txtEstadoModal").val()
	$("#txtCidadeModal").find(`option.${id}`).css("display", "block")
})

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