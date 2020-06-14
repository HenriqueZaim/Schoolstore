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
		                      <td>R$ ${data.valor}</td>
						  	  <td>R$ ${data.formaPagamento.valorTotal}</td>
						  	  <td id="formasPagamento-${data.id}"></td>
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
						  	  	<button type="button" id="alterarBotaoStatus-${data.id}" class="btn btn-sm btn-link text-warning p-0 font-weight-bold" data-toggle="modal" data-target="#modal-status-${data.id}">
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
										      <div class="modal-body" id="statusEstoque-${data.id}">
										        <select name="txtStatusPedido" id="modalSelect-${data.id}">

										        </select>
										        <input type="hidden" name="tarefa" value="alterarStatusPedido">
										        <input type="hidden" name="txtPedidoId" value="${data.id}">
										        <input type="hidden" name="txtValor" value="${data.valor}">
										        <input type="hidden" name="txtUsuarioId" value="${data.cliente.usuario.id}">
										      </div>
										      <div class="modal-footer">
										       <button type="submit" id="alterarStatusBotao-${data.id}" class="btn btn-success text-right">Mudar status</button>
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
				  
				  if(data.statusPedido === "REPROVADO"){
					  $(`#modalSelect-${data.id}`).parent().prepend(`<span class="alert alert-warning">Não será possível realizar esta operação!</span>`)
					  $(`#alterarStatusBotao-${data.id}`).prop("disabled", true)
					  $(`#alterarBotaoStatus-${data.id}`).prop("disabled", true).removeClass("text-warning").add("text-grey")
				  }else if(data.statusPedido === "EMPROCESSAMENTO"){
					  $(`#modalSelect-${data.id}`).append(`
							  <option class="text-capitalize" selected value="${data.statusPedido}" disabled>Em processamento</option>
							  <option value="REPROVADO">Reprovado</option>
							  <option value="APROVADO">Aprovado</option>
					  `)
				  }
				  else if(data.statusPedido === "ATUALIZADO"){
					  $(`#modalSelect-${data.id}`).append(`
							  <option class="text-capitalize" selected value="${data.statusPedido}" disabled>Atualizado</option>
							  <option value="REPROVADO">Reprovado</option>
							  <option value="APROVADO">Aprovado</option>
					  `)
				  }
				  else if(data.statusPedido === "APROVADO"){
					  $(`#modalSelect-${data.id}`).append(`
							  <option class="text-capitalize" selected value="${data.statusPedido}" disabled>Aprovado</option>
			                  <option value="EMTRANSITO">Em trânsito</option>
					  `)
				  }
				  else if(data.statusPedido === "EMTRANSITO"){
					  $(`#modalSelect-${data.id}`).append(`
							  <option class="text-capitalize" selected value="${data.statusPedido}" disabled>Em trânsito</option>
							  <option value="ENTREGUE">Entregue</option>
					  `)
				  }
				  else if(data.statusPedido === "ENTREGUE"){
					  $(`#modalSelect-${data.id}`).parent().prepend(`<span class="alert alert-warning">Não será possível realizar esta operação!</span>`)
					  $(`#alterarStatusBotao-${data.id}`).prop("disabled", true)
					  $(`#alterarBotaoStatus-${data.id}`).prop("disabled", true).removeClass("text-warning").add("text-grey")
				  }else if(data.statusPedido === "TROCADO"){
					  $(`#modalSelect-${data.id}`).parent().prepend(`<span class="alert alert-warning">Não será possível realizar esta operação!</span>`)
					  $(`#alterarStatusBotao-${data.id}`).prop("disabled", true)
					  $(`#alterarBotaoStatus-${data.id}`).prop("disabled", true).removeClass("text-warning").add("text-grey")
				  }
				  
				  
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
				  let clienId = $("#txtClienteId").val()
				  let usuaId = $("#txtUsuarioId").val()
				  $(`#status-${data.id}`).append(`
						  <span class="text-danger">Reprovado</span>
				  `)
				  $(`#troca-${data.id}`).append(`
						  <form action="app" method="post" id="form-${data.id}">
						  	  <input type="hidden" name="tarefa" value="consultarPedidoCancelamento">
						  	  <input type="hidden" name="txtPedidoId" value="${data.id}">
							  <button type="submit" class="btn btn-link text-danger btn-sm p-0 pb-2">Atualizar</button>
						  </form>
				  `)
				  
			  }else if(data.statusPedido === "ENTREGUE"){
				  $(`#status-${data.id}`).append(`
						  <span class="text-success">Entregue</span>
				  `)
				  $(`#troca-${data.id}`).append(`
						  <form action="app" method="post" id="form-${data.id}">
						  	  <input type="hidden" name="tarefa" value="consultarPedido">
						  	  <input type="hidden" name="txtPedidoId" value="${data.id}">
							  <button type="submit" class="btn btn-link text-info btn-sm p-0 pb-2">Efetuar Troca</button>
						  </form>
				  `)
				  
			  }else if(data.statusPedido === "EMTRANSITO"){
				  $(`#status-${data.id}`).append(`
						  <span class="text-warning">Em trânsito</span>
				  `)
				  $(`#troca-${data.id}`).append(`
						  <button type="submit" class="btn btn-link text-info disabled btn-sm p-0 pb-2">Efetuar Troca</button>

				  `)
				  
			  }else if(data.statusPedido === "APROVADO"){
				  let clienId = $("#txtClienteId").val()
				  let usuaId = $("#txtUsuarioId").val()
				  $(`#status-${data.id}`).append(`
						  <span class="text-info">Aprovado</span>
				  `)
				  $(`#troca-${data.id}`).append(`
						  <form action="app" method="post" id="form-${data.id}">
						  	  <input type="hidden" name="tarefa" value="consultarPedidoCancelamento">
						  	  <input type="hidden" name="txtPedidoId" value="${data.id}">
							  <button type="submit" class="btn btn-link text-danger btn-sm p-0 pb-2">Cancelar</button>
						  </form>
				  `)
				  
			  }else if(data.statusPedido === "ATUALIZADO"){
				  let clienId = $("#txtClienteId").val()
				  let usuaId = $("#txtUsuarioId").val()
				  $(`#status-${data.id}`).append(`
						  <span class="text-info">Atualizado</span>
				  `)
				  $(`#troca-${data.id}`).append(`
						  <form action="app" method="post" id="form-${data.id}">
						  	  <input type="hidden" name="tarefa" value="consultarPedidoCancelamento">
						  	  <input type="hidden" name="txtPedidoId" value="${data.id}">
							  <button type="submit" class="btn btn-link text-danger btn-sm p-0 pb-2">Cancelar</button>
						  </form>
				  `)
				  
			  }else if(data.statusPedido === "TROCADO"){
				  $(`#status-${data.id}`).append(`
						  <span class="text-success">Trocado</span>
				  `)
				  $(`#troca-${data.id}`).append(`
						  
					  <button type="submit" class="btn btn-link disabled btn-sm p-0 pb-2">Efetuar Troca</button>
						  
				  `)
			  }else{
				  let clienId = $("#txtClienteId").val()
				  let usuaId = $("#txtUsuarioId").val()
				  $(`#status-${data.id}`).append(`
						  <span class="text-warning">Em processamento</span>
				  `)
				  $(`#troca-${data.id}`).append(`
						  <form action="app" method="post" id="form-${data.id}">
						  	  <input type="hidden" name="tarefa" value="consultarPedidoCancelamento">
						  	  <input type="hidden" name="txtPedidoId" value="${data.id}">
							  <button type="submit" class="btn btn-link text-danger btn-sm p-0 pb-2">Cancelar</button>
						  </form>
				  `)
			  }
			  
			  if(data.formaPagamento.pagamentosCartao.length !== 0){
				  var valor = 0
				  data.formaPagamento.pagamentosCartao.forEach(function(cartao){
					  $(`#formasPagamento-${data.id}`).append(`
					     <span>Cartão: R$${cartao.valorTotalCartao}</span><br>
					  `)
					  valor += cartao.valorTotalCartao
				  })
			  }
			  
			  if(data.formaPagamento.pagamentosCupom.length !== 0){
				  data.formaPagamento.pagamentosCupom.forEach(function(cupom){
					  $(`#formasPagamento-${data.id}`).append(`
					     <span>Cupom: R$${cupom.valorTotalCupom}</span><br>
					  `)
				  })
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
				  $(`#form-${data.id}`).prepend(`
						  <input type="hidden" name="txtItemPedidoId" value="${item.id}">
						  <input type="hidden" name="txtProdutoId" value="${item.produto.id}">
						  <input type="hidden" name="txtQuantidadeProduto" value="${item.quantidade}">
				  `)
				  
				  $(`#statusEstoque-${data.id}`).prepend(`
						  <input type="hidden" name="txtItemPedidoId" value="${item.id}">
						  <input type="hidden" name="txtProdutoId" value="${item.produto.id}">
						  <input type="hidden" name="txtQuantidadeProduto" value="${item.quantidade}">
				  `)
			})
			
			data.formaPagamento.pagamentosCupom.forEach(function(item){
				$(`#form-${data.id}`).prepend(`
						  <input type="hidden" name="txtCupomId" value="${item.cupom.id}">
				  `)
			})
			
		})	
	  }
  })
});
