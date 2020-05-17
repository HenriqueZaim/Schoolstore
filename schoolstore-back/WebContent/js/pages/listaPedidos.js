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
		  response.entidades.forEach(function(data){
			  
			  if(cliente === "" || cliente === null || cliente === undefined){
				  $("#tablePedidos").append(`
				  			<tr class="text-center">
		                      <th scope="row">${data.id}</th>
		                      <td id="itens-${data.id}"></td>
		                      <td>
						  		<button type="button" class="btn btn-sm btn-link text-info p-0 font-weight-bold" data-toggle="modal" data-target="#modal-cliente-${data.id}">
								  ${data.cliente.nome}
								</button>
								
								<div class="modal fade" id="modal-cliente-${data.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
								  aria-hidden="true">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <h5 class="modal-title" id="exampleModalLabel">Perfil de cliente: ${data.cliente.nome}</h5>
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
									          <span aria-hidden="true">&times;</span>
									        </button>
									      </div>
									      <div class="modal-body">
									        <ul>
									        	<li><b>Documento: </b>${data.cliente.numeroDocumento}</li>
									        	<li><b>Telefone: </b>${data.cliente.numeroTelefone}</li>
									        	<li><b>E-mail: </b>${data.cliente.usuario.email}</li>
									        </ul>
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-link text-right" data-dismiss="modal">Sair</button>
									      </div>
									    </div>
									  </div>
									</div>
								</div>
						  	  </td>  
		                      <td id="status-${data.id}"></td>
		                      <td>${data.valor}</td>
						  	  <td>${data.formaPagamento.valorTotal}</td>
						  	  <td>formasPagamento</td>
						  	  <td>
						  	  	<button type="button" class="btn btn-sm btn-link text-info p-0 font-weight-bold" data-toggle="modal" data-target="#modal-endereco-${data.id}">
								  ${data.frete.endereco.nome}
								</button>
								
								<div class="modal fade" id="modal-endereco-${data.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
								  aria-hidden="true">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <h5 class="modal-title" id="exampleModalLabel">Endereço solicitado: ${data.frete.endereco.nome}</h5>
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
									          <span aria-hidden="true">&times;</span>
									        </button>
									      </div>
									      <div class="modal-body">
									        <ul>
									        	<li><b>CEP: </b>${data.frete.endereco.cep}</li>
									        	<li><b>Estado: </b>${data.frete.endereco.cidade.estado.nome}</li>
									        	<li><b>Cidade: </b>${data.frete.endereco.cidade.nome}</li>
									        	<li><b>Bairro: </b>${data.frete.endereco.bairro}</li>
									        	<li><b>Logradouro: </b>${data.frete.endereco.logradouro}</li>
									        	<li><b>Número: </b>${data.frete.endereco.numero}</li>
									        	<li><b>Complemento: </b>${data.frete.endereco.complemento}</li>
									        	<li><b>Referência: </b>${data.frete.endereco.referencia}</li>
									        </ul>
									        <hr>
									        <ul>
									        	<li><b>Valor do frete: </b>R$ ${data.frete.valor}</li>
									        	<li><b>Previsão de chegada: </b>${data.frete.previsaoEmDias} dias</li>
									        </ul>
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-link text-right" data-dismiss="modal">Sair</button>
									      </div>
									    </div>
									  </div>
									</div>
								</div>
						  	  
						  	  </td>
						  	  <td>
						  	  	<button type="button" class="btn btn-sm btn-link text-warning p-0 font-weight-bold" data-toggle="modal" data-target="#modal-status-${data.id}">
								  Alterar Status
								</button>
								
								<div class="modal fade" id="modal-status-${data.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
								  aria-hidden="true">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									    	<form action="app" method="POST">
										      <div class="modal-header">
										        <h5 class="modal-title" id="exampleModalLabel">Selecione o novo status do pedido</h5>
										        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
										          <span aria-hidden="true">&times;</span>
										        </button>
										      </div>
										      <div class="modal-body">
										        <select name="txtStatusPedido">
										        	<option selected value="">${data.statusPedido}</option>
										        	<option value="EMPROCESSAMENTO">Em processamento</option>
										        	<option value="REPROVADO">Reprovado</option>
										        	<option value="EMTRANSITO">Em trânsito</option>
										        	<option value="APROVADO">Aprovado</option>
										        	<option value="ENTREGUE">Entregue</option>
										        </select>
										        <input type="hidden" name="tarefa" value="alterarStatusPedido">
										        <input type="hidden" name="txtPedidoId" value="${data.id}">
										      </div>
										      <div class="modal-footer">
										       <button type="submit" class="btn btn-success text-right">Mudar status</button>
										        <button type="button" class="btn btn-link text-left" data-dismiss="modal">Cancelar</button>
										      </div>
									      	</form>
									    </div>
									  </div>
									</div>
								</div>
						  	  
						  	  </td>
		                    </tr>
				  `)
				  
				  
			  }
			  else{
				  $("#tablePedidos").append(`
				  			<tr class="text-center">
		                      <th scope="row">${data.id}</th>
		                      <td id="itens-${data.id}"></td>
		                      <td id="status-${data.id}"></td>
		                      <td>${data.valor}</td>
						  	  <td>${data.formaPagamento.valorTotal}</td>
						  	  <td>${data.frete.previsaoEmDias} dias</td>
						  	  <td id="troca-${data.id}"></td>
		                    </tr>
				  `)
			  }
			  
			  if(data.statusPedido === "REPROVADO"){
				  $(`#status-${data.id}`).append(`
						  <span class="text-danger">Reprovado</span>
				  `)
				  $(`#troca-${data.id}`).append(`
						  <form action="app" method="post">
						  	  <input type="hidden" name="tarefa" value="excluirPedido">
						  	  <input type="hidden" name="txtPedidoId" value="${data.id}">
							  <button type="submit" class="btn btn-link text-red btn-sm p-0 pb-2">Excluir</button>
						  </form>
				  `)
				  
			  }else if(data.statusPedido === "ENTREGUE"){
				  $(`#status-${data.id}`).append(`
						  <span class="text-success">Entregue</span>
				  `)
				  $(`#troca-${data.id}`).append(`
						  <form action="app" method="post">
						  	  <input type="hidden" name="tarefa" value="trocarPedido">
						  	  <input type="hidden" name="txtPedidoId" value="${data.id}">
							  <button type="submit" class="btn btn-link btn-sm p-0 pb-2">Efetuar Troca</button>
						  </form>
				  `)
				  
			  }else if(data.statusPedido === "EMTRANSITO"){
				  $(`#status-${data.id}`).append(`
						  <span class="text-warning">Em trânsito</span>
				  `)
				  $(`#troca-${data.id}`).append(`
						  <button type="submit" class="btn btn-link disabled btn-sm p-0 pb-2">Efetuar Troca</button>

				  `)
				  
			  }else if(data.statusPedido === "APROVADO"){
				  $(`#status-${data.id}`).append(`
						  <span class="text-info">Aprovado</span>
				  `)
				  $(`#troca-${data.id}`).append(`
						  <button type="submit" class="btn btn-link disabled btn-sm p-0 pb-2">Efetuar Troca</button>

				  `)
				  
			  }else{
				  $(`#status-${data.id}`).append(`
						  <span class="text-warning">Em processamento</span>
				  `)
				  $(`#troca-${data.id}`).append(`
						  <button type="submit" class="btn btn-link disabled btn-sm p-0 pb-2">Efetuar Troca</button>

				  `)
			  }
			  
			  
			  data.itensPedido.forEach(function(item){
				  $(`#itens-${data.id}`).append(
					`
						  <span>${item.quantidade}x
						  <form action="app" method="get">
						  	<input type="hidden" name="txtProdutoId" value="${item.produto.id}">
						  	<input type="hidden" name="tarefa" value="consultarProduto">
						  	<button type="submit" class="btn btn-link btn-sm p-0 pb-2">${item.produto.nome}</button>
						  </form>
						  </span>

					`	
				  )
			})
			
		})	
	  }
  })
});
