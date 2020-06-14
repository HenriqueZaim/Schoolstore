$(document).ready(function () {
	var cliente = $("#txtClienteId").val()
	var url

	if(cliente === "" || cliente === null || cliente === undefined)
		url = "http://localhost:8085/schoolstore/app?tarefa=consultarTrocas"
			else
				url = `http://localhost:8085/schoolstore/app?tarefa=consultarTrocas&txtClienteId=${cliente}`

					$.ajax({
						url: url,
						type: "GET",
						success: response => {
							console.log(response)
							response.entidades.forEach(function(data){
								if(cliente === "" || cliente === null || cliente === undefined){
									$("#tableTrocas").append(`
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

											<td>
											<button type="button" id="alterarStatusBotao-${data.id}" class="btn btn-sm btn-link text-warning p-0 font-weight-bold" data-toggle="modal" data-target="#modal-status-${data.id}">
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
											<select name="txtStatusTroca" id="modalSelect-${data.id}">

											</select>
											<input type="hidden" name="tarefa" value="alterarStatusTroca">
											<input type="hidden" name="txtTrocaId" value="${data.id}">
											<input type="hidden" name="txtValorTotal" value="${data.pedido.valor}">
											<input type="hidden" name="txtPedidoId" value="${data.pedido.id}">
											<input type="hidden" name="txtUsuarioId" value="${data.cliente.usuario.id}"
											</div>
											<div class="modal-footer">
											<button type="submit" class="btn btn-success text-right">Mudar status</button>
											<button type="button" class="btn btn-link text-left" data-dismiss="modal">Cancelar</button>
											</div>
											</form>
											</div>
											</div>
											</div>

											</td>
											</tr>
									`)

									if(data.statusTroca === "TROCAREPROVADA"){
										$(`#modalSelect-${data.id}`).parent().prepend(`<span class="alert alert-warning">Não será possível realizar esta operação!</span>`)
										$(`#alterarStatusBotao-${data.id}`).prop("disabled", true)
										$(`#alterarStatusBotao-${data.id}`).removeClass("text-warning")
									}else if(data.statusTroca === "TROCADO"){
										$(`#modalSelect-${data.id}`).parent().prepend(`<span class="alert alert-warning">Não será possível realizar esta operação!</span>`)
										$(`#alterarStatusBotao-${data.id}`).prop("disabled", true)
										$(`#alterarStatusBotao-${data.id}`).removeClass("text-warning")
									}else if(data.statusTroca === "EMTROCA"){
										$(`#modalSelect-${data.id}`).append(`
												  <option class="text-capitalize" selected value="${data.statusPedido}" disabled>Em troca</option>
												  <option value="TROCADO">Trocado</option>
										  `)
									}else if(data.statusTroca === "TROCAAUTORIZADA"){
										$(`#modalSelect-${data.id}`).append(`
												  <option class="text-capitalize" selected value="${data.statusPedido}" disabled>Autorizado</option>
												  <option value="EMTROCA">Em troca</option>
										  `)
									}else if(data.statusTroca === "CANCELADA"){
										$(`#modalSelect-${data.id}`).parent().prepend(`<span class="alert alert-warning">Não será possível realizar esta operação!</span>`)
										$(`#alterarStatusBotao-${data.id}`).prop("disabled", true)
										$(`#alterarStatusBotao-${data.id}`).removeClass("text-warning")
									}else{
										$(`#modalSelect-${data.id}`).append(`
												  <option class="text-capitalize" selected value="${data.statusPedido}" >Em processamento</option>
												  <option value="TROCAAUTORIZADA">Autorizada</option>
												  <option value="TROCAREPROVADA">Reprovada</option>
										  `)
									}

								}
								else{
									$("#tableTrocas").append(`
											<tr class="text-center">
											<th scope="row">${data.id}</th>
											<td id="itens-${data.id}"></td>
											<td id="status-${data.id}"></td>
											<td id="troca-${data.id}"></td>
											</tr>
									`)
								}

								data.itensTroca.forEach(function(item){
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
									$(`#modalSelect-${data.id}`).parent().append(`
											<input type="hidden" name="txtProdutoId" value="${item.produto.id}">
											<input type="hidden" name="txtQuantidadeProduto" value="${item.quantidade}">
									`)
								})
								if(data.statusTroca === "TROCAREPROVADA"){
									$(`#status-${data.id}`).append(`
											<span class="text-danger">Reprovada</span>
									`)
									$(`#troca-${data.id}`).append(`
											<form action="app" method="POST">
											<input type="hidden" name="txtTrocaId" value="${data.id}">
											<input type="hidden" name="tarefa" value="excluirTroca">

											<button type="submit" class="btn btn-link text-danger">Excluir</button>
											</form>
									`)

								}else if(data.statusTroca === "TROCADO"){
									$(`#status-${data.id}`).append(`
											<span class="text-success">Trocado</span>
									`)
									$(`#troca-${data.id}`).append(`
											<button type="button" class="btn btn-link disabled">Cancelar</button>
									`)

								}else if(data.statusTroca === "EMTROCA"){
									$(`#status-${data.id}`).append(`
											<span class="text-warning">Em troca</span>
									`)
									$(`#troca-${data.id}`).append(`
											<form action="app" method="POST">
											<input type="hidden" name="txtTrocaId" value="${data.id}">
											<input type="hidden" name="txtStatusTroca" value="CANCELADA">
											<input type="hidden" name="tarefa" value="alterarStatusTroca">

											<button type="submit" class="btn btn-link text-danger">Cancelar</button>
											</form>
									`)

								}else if(data.statusTroca === "TROCAAUTORIZADA"){
									$(`#status-${data.id}`).append(`
											<span class="text-info">Autorizado</span>
									`)
									$(`#troca-${data.id}`).append(`
											<form action="app" method="POST">
											<input type="hidden" name="txtTrocaId" value="${data.id}">
											<input type="hidden" name="txtStatusTroca" value="CANCELADA">
											<input type="hidden" name="tarefa" value="alterarStatusTroca">

											<button type="submit" class="btn btn-link text-danger">Cancelar</button>
											</form>
									`)

								}else if(data.statusTroca === "CANCELADA"){
									$(`#status-${data.id}`).append(`
											<span class="text-danger">Cancelado</span>
									`)
									$(`#troca-${data.id}`).append(`
											<form action="app" method="POST">
											<input type="hidden" name="txtTrocaId" value="${data.id}">
											<input type="hidden" name="tarefa" value="excluirTroca">

											<button type="submit" class="btn btn-link text-danger">Excluir</button>
											</form>
									`)

								}else{
									$(`#status-${data.id}`).append(`
											<span class="text-warning">Em processamento</span>
									`)
									$(`#troca-${data.id}`).append(`
											<form action="app" method="POST">
											<input type="hidden" name="txtTrocaId" value="${data.id}">
											<input type="hidden" name="txtStatusTroca" value="CANCELADA">
											<input type="hidden" name="tarefa" value="alterarStatusTroca">

											<button type="submit" class="btn btn-link text-danger">Cancelar</button>
											</form>
									`)
								}

							})
						}
					})
});

