$('#modalEnderecos').on('show.bs.modal', function() {
	$(this).find(".modal-body").remove()

})

$(".visualizarEndereco").click(function(){
  let cep = $(this).parent().find("input[name='txtCep']").val();
  let estado = $(this).parent().find("input[name='txtEstado']").val();
  let cidade = $(this).parent().find("input[name='txtCidade']").val();
  let logradouro = $(this).parent().find("input[name='txtLogradouro']").val();
  let numero = $(this).parent().find("input[name='txtNumero']").val();
  let bairro = $(this).parent().find("input[name='txtBairro']").val();
  let complemento = $(this).parent().find("input[name='txtComplemento']").val();
  let referencia = $(this).parent().find("input[name='txtReferencia']").val();
  let favorito = $(this).parent().find("input[name='txtFavorito']").val();
  let nomeEndereco = $(this).parent().find("input[name='txtNomeEndereco']").val();
  let nomeCliente = $(this).parent().find("input[name='txtNome']").val();

  complemento = complemento != "" ? complemento : "Nenhum";
  referencia = referencia != "" ? referencia : "Nenhum";
  favorito = favorito === "true" ? "Sim" : "Não"

  let painelEndereco = $("#painelEndereco")
  painelEndereco.children().eq(0).remove()

  painelEndereco.append(
    `
      <div>
        <h4>${nomeEndereco}-${nomeCliente}</h4>
        <ul>
          <li><b>CEP: </b>${cep}</li>
          <li><b>Estado: </b>${estado}</li>
          <li><b>Cidade: </b>${cidade}</li>
          <li><b>Logradouro: </b>${logradouro}</li>
          <li><b>Número: </b>${numero}</li>
          <li><b>Bairro: </b>${bairro}</li>
          <li><b>Complemento: </b>${complemento}</li>
          <li><b>Referencia: </b>${referencia}</li>
          <li><b>Favorito: </b>${favorito}</li>
        </ul>
      </div>
    `
  )
})