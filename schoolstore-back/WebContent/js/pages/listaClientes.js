$(document).ready(function () {
  $.ajax({
	  url: "http://localhost:8085/schoolstore/app?tarefa=consultarCliente",
	  type: "GET",
	  success: response => {
		  console.log(response)
		  response.entidades.forEach(function(data){
			  $("#tableClientes").append(`
			  			<tr class="text-center">
	                      <th scope="row">${data.id}</th>
	                      <td> ${data.nome} </td>
	                      <td> ${data.numeroTelefone} </td>
	                      <td id="enderecos-${data.id}">
	                     	
		                      
	                      </td>
	                      <td>
	                        <form action="app" method="POST">
	                          <input type="hidden" name="txtUsuarioId" value="${data.usuario.id}">
	                          <input type="hidden" name="txtClienteId" value="${data.id}">
	                          <input type="hidden" name="txtImagemId" value="${data.usuario.imagem.id}">
	    
	                          <input type="hidden" name="tarefa" value="editaCliente">
	                          <button type="submit"
	                            class="btn btn-sm btn-link p-0 text-warning font-weight-bold">Editar</button>
	                        </form>
	                      </td>
	                      <td>
	                        <form action="app" method="POST" class="text-center">
	                          <input type="hidden" name="txtUsuarioId" value="${data.usuario.id}">
	                          <input type="hidden" name="txtClienteId" value="${data.id}">
	                          <input type="hidden" name="txtImagemId" value="${data.usuario.imagem.id}">
	    
	                          <input type="hidden" name="tarefa" value="deletarCliente">
	                          <button type="submit"
	                            class="btn btn-sm btn-link p-0 text-danger font-weight-bold">Excluir</button>
	                        </form>
	                      </td>
	                    </tr>
			  `)
			  
			  data.enderecos.forEach(function(endereco){
				  $(`#enderecos-${data.id}`).append(
					`
						  <div>
							<input type="hidden" name="txtCep" value="${endereco.cep}">
							<input type="hidden" name="txtEstado" value="${endereco.cidade.estado.nome}">
							<input type="hidden" name="txtCidade" value="${endereco.cidade.nome}">
							<input type="hidden" name="txtLogradouro" value="${endereco.logradouro}">
							<input type="hidden" name="txtNumero" value="${endereco.numero}">
							<input type="hidden" name="txtReferencia" value="${endereco.referencia}">
							<input type="hidden" name="txtComplemento" value="${endereco.complemento}">
							<input type="hidden" name="txtNomeEndereco" value="${endereco.nome}">
							<input type="hidden" name="txtFavorito" value="${endereco.favorito}">
							<input type="hidden" name="txtBairro" value="${endereco.bairro}">
							<input type="hidden" name="txtNome" value="${data.nome}">
							<button type="button" class="btn btn-link teal-text visualizarEndereco p-0 font-weight-bold" >${endereco.nome}</button>
						</div>
					`	
				  )
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
			})
		})					  
	  }
  })
});

$('#modalEnderecos').on('show.bs.modal', function() {
	$(this).find(".modal-body").remove()

})