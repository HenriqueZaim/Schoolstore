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
	                      <td id="cartoes-${data.id}">
	                      
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

							<button type="button" class="btn btn-sm btn-link text-danger p-0 font-weight-bold" data-toggle="modal" data-target="#modal-${data.id}">
							  Excluir
							</button>
							
							<div class="modal fade" id="modal-${data.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
							  aria-hidden="true">
							  <div class="modal-dialog" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel">Confirmação de exclusão</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          <span aria-hidden="true">&times;</span>
							        </button>
							      </div>
							      <div class="modal-body">
							        <p>Tem certeza que deseja inativar o cliente: <b>${data.nome}</b> ?</p>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-link text-left" data-dismiss="modal">Cancelar</button>
							        <form action="app" method="POST" class="text-center">
			                          <input type="hidden" name="txtUsuarioId" value="${data.usuario.id}">
			                          <input type="hidden" name="txtClienteId" value="${data.id}">
			                          <input type="hidden" name="txtImagemId" value="${data.usuario.imagem.id}">
			                          <input type="hidden" name="txtCarrinhoId" value="${data.carrinho.id}">
			    
			                          <input type="hidden" name="tarefa" value="deletarCliente">
			                          <button type="submit"
			                            class="btn btn-danger font-weight-bold">Excluir</button>
			                        </form>
							      </div>
							    </div>
							  </div>
							</div>
						</div>
	                      
	                      
	                        
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
							<button type="button" style="white-space: nowrap;" class="btn btn-link teal-text visualizarEndereco p-0 font-weight-bold" >${endereco.nome}
								${endereco.favorito === true ? '<i class="fas fa-star"></i>' : ""}
							</button>

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
					
					  complemento = complemento != "undefined" ? complemento : "Nenhum";
					  referencia = referencia != "undefined" ? referencia : "Nenhum";
					  favorito = favorito === "true" ? "Sim" : "Não"
					
					  let painelEndereco = $("#painelEndereco")
					  painelEndereco.children().eq(0).remove()
					
					  painelEndereco.append(
					    `
					      <div>
					        <h4 class="font-weight-bold text-center">${nomeEndereco}</h4>
					        <p class="text-center">${nomeCliente}</p>
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
			
			
			data.cartoesCredito.forEach(function(cartao){
				  $(`#cartoes-${data.id}`).append(
					`
						  <div>
							<input type="hidden" name="txtCodigoCartao" value="${cartao.codigo}">
							<input type="hidden" name="txtNomeImpressoCartao" value="${cartao.nomeImpresso}">
							<input type="hidden" name="txtNumeroCartao" value="${cartao.numero}">
							<input type="hidden" name="txtFavoritoCartao" value="${cartao.favorito}">
						  	<input type="hidden" name="txtNome" value="${data.nome}">
							<button type="button" style="white-space: nowrap;" class="btn btn-link teal-text visualizarCartao text-lowercase p-0 font-weight-bold" >*.${cartao.numero.substr(12,16)}
								${cartao.favorito === true ? '<i class="fas fa-star"></i>' : ""}
							</button>
						</div>
					`	
				  )
				  $(".visualizarCartao").click(function(){
					  let codigoCartao = $(this).parent().find("input[name='txtCodigoCartao']").val();
					  let nomeImpressoCartao = $(this).parent().find("input[name='txtNomeImpressoCartao']").val();
					  let numeroCartao = $(this).parent().find("input[name='txtNumeroCartao']").val();
					  let favorito = $(this).parent().find("input[name='txtFavoritoCartao']").val();
					  let nomeCliente = $(this).parent().find("input[name='txtNome']").val();
					
					  favorito = favorito === "true" ? "Sim" : "Não"
					
					  let painelCartao = $("#painelCartao")
					  painelCartao.children().eq(0).remove()
					
					  painelCartao.append(
					    `
					      <div>
					        <h4 class="font-weight-bold text-center">Final ${numeroCartao.substr(12,16)}</h4>
					        <p class="text-center">${nomeCliente}</p>
					        <ul>
					          <li><b>Código: </b>${codigoCartao}</li>
					          <li><b>Número: </b>${numeroCartao}</li>
					          <li><b>Nome Impresso: </b>${nomeImpressoCartao}</li>
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