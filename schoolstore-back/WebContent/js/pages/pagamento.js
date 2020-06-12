var itensCarrinho;
var estados;

$(document).ready(function () {
  var txtCarrinhoId = $("#txtCarrinhoId").val();
  var txtUsuarioId = $("#txtUsuarioId").val();
  var txtClienteId = parseInt($("#txtClienteId").val());
  $.ajax({
	  url: `http://localhost:8085/schoolstore/app?tarefa=consultarCliente&txtClienteId=${txtClienteId}`,
	  type: "GET",
	  async: false,
	  success: (response) => {
		  console.log(response)
		  response.entidades.forEach(function(data){
			  data.enderecos.forEach(function(endereco) {
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
						  <!--
						  <form action="app" method="post">
						  	<input type="hidden" name="tarefa" value="removerEndereco">
						  	<input type="hidden" name="txtEnderecoId" value="${endereco.id}">
						  	<button type="submit" class="btn btn-link text-danger p-0"><i class="fa fa-times"></i></button>
						  </form>
						  -->
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
				  
				  if(endereco.favorito){
					  $(".card").find(`#endereco${endereco.id}`).find(".enderecoFavorito").prepend(`
								<input type="radio" class="form-check-input enderecoInput" id="enderecoInput${endereco.id }" name="enderecoInput" value="${endereco.id }" checked>

					  `)
				  }else{
					  $(".card").find(`#endereco${endereco.id}`).find(".enderecoFavorito").prepend(`
								<input type="radio" class="form-check-input enderecoInput" id="enderecoInput${endereco.id }" name="enderecoInput" value="${endereco.id }" >

					  `)
				  }
				 
				  
				  
			  })
			  data.cartoesCredito.forEach(function(cartao){
				  $("#accordionCartao").append(`
						  <div class="card">
											<div class="card-header" role="tab" id="cartao${cartao.id}">
												<div class="row">
													<div class="form-check pl-0 mb-2 cartaoFavorito">
														<label class="form-check-label" for="cartaoInput${cartao.id }"></label>
													</div>
													<h5 class="mb-0 flex-grow-1">Cartão - Final ${cartao.numero.substring(12,16)}</h5>
													<div class="d-flex align-items-center">

														<a data-toggle="collapse" class="float-left pr-4" data-parent="#accordionCartao" href="#cartao-${cartao.id}" aria-expanded="false" aria-controls="cartao-${cartao.id}"> <i class="fas fa-angle-down"></i>
														</a>
														<!--<form action="app" method="post">
														  	<input type="hidden" name="tarefa" value="removerCartao">
														  	<input type="hidden" name="txtCartaoCreditoId" value="${cartao.id}">
														  	<button type="submit" class="btn btn-link text-danger p-0"><i class="fa fa-times"></i></button>
														  </form>-->
													</div>
												</div>
											</div>
											<div id="cartao-${cartao.id}" class="collapse" role="tabpanel" aria-labelledby="cartao${cartao.id}" data-parent="#accordionCartao">
												<div class="card-body">
													<ul>
														<li><b>Nome impresso: </b>${cartao.nomeImpresso}</li>
														<li><b>Número: </b>${cartao.numero}</li>
														<li><b>Código: </b>${cartao.codigo}</li>
														<li><b>Favorito: </b>${cartao.favorito == true ? "Sim" : "Não"}</li>
													</ul>
												</div>
											</div>

											<input type="hidden" name="txtCartaoId" value="${cartao.id}">
										</div>
				  `)
				  
				  if(cartao.favorito){
					  $(".card").find(`#cartao${cartao.id}`).find(".cartaoFavorito").prepend(`
						<input type="checkbox" class="form-check-input cartaoInput" id="cartaoInput${cartao.id }" name="cartaoInput" value="${cartao.id }" checked>

					  `)
				  }else{
					  $(".card").find(`#cartao${cartao.id}`).find(".cartaoFavorito").prepend(`
							  <input type="checkbox" class="form-check-input cartaoInput" id="cartaoInput${cartao.id }" name="cartaoInput" value="${cartao.id }">
					  `)
				  }
			  })
			  
			  if(data.usuario.cupons.length === 0){
				  $("#cupons").append("Nenhum cupom vinculado a conta")
			  }else{
				  data.usuario.cupons.forEach(function(item){
					  $("#cupons").append(`
							  <div class="accordion md-accordion" id="accordionCupomTroca" role="tablist" aria-multiselectable="true">
							  	<div class="card">
							  		<div class="card-header" role="tab" id="cupom${item.id}">
							 			<div class="row">
							  				<div class="form-check pl-0 mb-2">
							  					<input type="hidden" class="cuponsValor" id="txtCupomValor" value="${item.valor}">
							  					<input type="checkbox" class="form-check-input cupomInput" id="cupomInput${item.id }" name="cupomInput" value="${item.id }">
							  					<label class="form-check-label" for="cupomInput${item.id }"></label>
							  				</div>
							  				<h5 class="mb-0 flex-grow-1">Cupom - R$ ${item.valor}</h5>
							  			</div>
							  		</div>
							  	</div>
							  </div>
					  `)
					  
				  })
			  }
			  
			  
			  
		  })
	  }
  });
	
  $.ajax({
    url: `http://localhost:8085/schoolstore/app?tarefa=listarItensCarrinho&txtCarrinhoId=${txtCarrinhoId}`,
    type: "GET",
    async: false,
    success: (response) => {
    	console.log(response)
      $("#confirmarCompra").append(`
		  
				  <form action="app" method="post" id="formularioCompra">
					  <ul class="mt-4 pl-0" id="listaItens">

					  </ul>
					  <hr>
				  		<div class="d-flex justify-content-between">
					  		<p class="font-weight-bold">Valor final</p>
					  		<p class="font-weight-bold" id="valorFinal">R$ ${response.subTotal}</p>
					  		<input type="hidden" name="txtSubTotal" id="txtSubTotal" value="${response.subTotal}">
					  	</div>
				  	  <input type="hidden" name="txtClienteId" value="${txtClienteId}">
				  	  <input type="hidden" name="txtUsuarioId" value="${txtUsuarioId}">
				  	  <input type="hidden" name="txtEnderecoId" value="">
				  	  <input type="hidden" name="txtValorTotalCupom" id="txtValorTotalCupom" value="0">
				  	  <input type="hidden" name="txtValorTotalPago" id="txtValorTotalPago" value="0">
				  	  <input type="hidden" name="tarefa" value="efetuarPedido">
					  <button type="submit" id="botaoSubmit" class="btn btn-success float-right">Efetuar Compra</a>
				  </form>
				  
		  `);
      response.itensCarrinho.forEach(function (item) {
        let preco = parseFloat(item.produto.preco);
        let precificacao = parseFloat(item.produto.precificacao.percentual)
        let qtdd = parseInt(item.quantidade)
        var valorTotal = ((preco*precificacao) + preco) * qtdd
        preco = parseFloat(valorTotal).toFixed(2);
        $("#listaItens").prepend(`
					  	<li class="d-flex justify-content-between">
					  		<p>${item.quantidade}x ${item.produto.nome}</p>
					  		<p id="valorPreco-${item.id}">R$ ${preco}</p>
					  		<input type="hidden" name="txtProdutoId" value="${item.produto.id}">
					  		<input type="hidden" name="txtQuantidadeProduto" value="${item.quantidade}">

					  	</li>
			  `);
      });
      let txtEstadoSigla = $(".enderecoInput[checked]")
        .parent()
        .parent()
        .parent()
        .parent()
        .find("input[name=txtEstSigla]")
        .val();
      if (txtEstadoSigla === "SP") {
        $("#listaItens").append(`
					  	<li class="d-flex justify-content-between" id="textoFrete">
					  		<p>+ frete</p>
					  		<p>R$ 10.00</p>
					  		<input type="hidden" name="txtFreteValor" value="10">
					  		<input type="hidden" name="txtFretePrevisao" value="5">
					  	</li>
			  `);
      } else {
        $("#listaItens").append(`
					  	<li class="d-flex justify-content-between" id="textoFrete">
					  		<p>+ frete</p>
					  		<p>R$ 20.00</p>
					  		<input type="hidden" name="txtFreteValor" value="20">
					  		<input type="hidden" name="txtFretePrevisao" value="8">
					  	</li>
			  `);
      }
      if (txtEstadoSigla === "SP") {
        var valorFrete = 10;
      } else {
        var valorFrete = 20;
      }
      let valorTotal = $("#txtSubTotal").val();

      valorFrete = parseFloat(valorFrete);
      valorTotal = parseFloat(valorTotal);
      valorTotal += valorFrete;
      $("#txtSubTotal").val(valorTotal);
      $("#valorFinal").text(`R$ ${valorTotal}`);

      let endereco = $(".enderecoInput[checked]")
        .parent()
        .parent()
        .parent()
        .parent()
        .find("input[name=txtEndId]")
        .val();
      $("#formularioCompra").find("input[name=txtEnderecoId]").val(endereco);

      let cartoes = $(".cartaoInput[checked]");
      for (let i = 0; i < cartoes.length; i++) {
        $("#formularioCompra").append(
          `<input type="hidden" name="txtCartaoCreditoId" value="${cartoes[i].value}">`
        );
        $("#txtValorTotalPago").val($("#txtSubTotal").val())
      }
    },
  });
  
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
  $(".enderecoInput").change(function () {
	  let endereco = $(this)
	    .parent()
	    .parent()
	    .parent()
	    .parent()
	    .find("input[name=txtEndId]")
	    .val();
	  let sigla = $(this)
	    .parent()
	    .parent()
	    .parent()
	    .parent()
	    .find("input[name=txtEstSigla]")
	    .val();
	  if (sigla === "SP") {
		 let valorFreteAtual = parseFloat($("#listaItens").find("#textoFrete").find("input[name=txtFreteValor]").val());
		 let valorTotal = parseFloat($("#txtSubTotal").val())
		 
		 valorTotal -= valorFreteAtual
		 
		 $("#txtSubTotal").val(valorTotal)
		 
	    $("#listaItens").find("#textoFrete").children().eq(1).text("R$ 10.00");
	    $("#listaItens")
	      .find("#textoFrete")
	      .find("input[name=txtFreteValor]")
	      .val(10);
	    $("#listaItens")
	      .find("#textoFrete")
	      .find("input[name=txtFretePrevisao]")
	      .val(5);
	    
	     valorFreteAtual = parseFloat($("#listaItens").find("#textoFrete").find("input[name=txtFreteValor]").val());
		 valorTotal = parseFloat($("#txtSubTotal").val())
		 
		 valorTotal += valorFreteAtual
		 
		 $("#txtSubTotal").val(valorTotal)
		 $("#valorFinal").text(`R$ ${valorTotal}`)
	    
	    
	  } else {
			 let valorFreteAtual = parseFloat($("#listaItens").find("#textoFrete").find("input[name=txtFreteValor]").val());
			 let valorTotal = parseFloat($("#txtSubTotal").val())
			 
			 valorTotal -= valorFreteAtual
			 
			 $("#txtSubTotal").val(valorTotal)
		  
	    $("#listaItens").find("#textoFrete").children().eq(1).text("R$ 20.00");
	    $("#listaItens")
	      .find("#textoFrete")
	      .find("input[name=txtFreteValor]")
	      .val(20);
	    $("#listaItens")
	      .find("#textoFrete")
	      .find("input[name=txtFretePrevisao]")
	      .val(8);
	    
	     valorFreteAtual = parseFloat($("#listaItens").find("#textoFrete").find("input[name=txtFreteValor]").val());
		 valorTotal = parseFloat($("#txtSubTotal").val())
		 
		 valorTotal += valorFreteAtual
		 
		 $("#txtSubTotal").val(valorTotal)
		 $("#valorFinal").text(`R$ ${valorTotal}`)
	  }

	  $("#formularioCompra").find("input[name=txtEnderecoId]").val(endereco);
	});

	$(".cartaoInput").click(function () {
	  if ($(this).prop("checked")) {
			let valorTotal = $("#txtSubTotal").val()
			valorTotal = parseFloat(valorTotal)
			
			let valorTotalPago = $("#txtValorTotalPago").val()
			valorTotalPago = parseFloat(valorTotalPago)
			
			if(valorTotalPago == 0){
				$("#txtValorTotalPago").val(valorTotal)
			}
			
			let valorTotalCupom = $("#txtValorTotalCupom").val()
			valorTotalCupom = parseFloat(valorTotalCupom)
			
			if(valorTotalCupom >= valorTotal){
				alert("Valor de pagamento já atingiu o valor do pedido! Caso insista em usar um cartão, remova algum cupom.")
				return false
			}
		  
		    $(this).prop("checked", "checked");
		    let val = $(this).val();
		    $("#formularioCompra").append(
		      `<input type="hidden" name="txtCartaoCreditoId" value="${val}">`
		    );
	  } else {
		  let cartoes = $(".cartaoInput")
		  var flag = false
		  for(cartao of cartoes){
			  if($(cartao).prop("checked")){
				  continue
			  }else{
				  flag = true
			  }	  
		  }
		  if(flag){
				let valorTotalPago = $("#txtValorTotalPago").val()
				valorTotalPago = parseFloat(valorTotalPago)
				
				let valorCompra = $("#txtSubTotal").val()
				valorCompra = parseFloat(valorCompra)
				
				valorTotalPago -= valorCompra
				
				$("#txtValorTotalPago").val(valorTotalPago)
		  }
		  
	    $(this).removeAttr("checked");
	    let val = $(this).val();
	    $("#formularioCompra")
	      .find(`input[value=${val}][name="txtCartaoCreditoId"]`)
	      .remove();
	  }
	});

	$(".cupomInput").click(function () {
		if($(this).prop("checked")){
			var valorTotal = $("#txtSubTotal").val()
			valorTotal = parseFloat(valorTotal)
			
			var cartoes = $(".cartaoInput[checked]");
			
			var valorTotalCupom = $("#txtValorTotalCupom").val()
			valorTotalCupom = parseFloat(valorTotalCupom)
			
			var valorCupom = $(this).parent().find("#txtCupomValor").val()
			valorCupom = parseFloat(valorCupom)

			valorTotalCupom += valorCupom
			
			if((valorTotalCupom > valorTotal) && ((valorTotalCupom - valorTotal) > valorCupom)){
				alert("Utilização de cupom excedida!")
				return false
			}else if(valorTotalCupom > valorTotal){
				let cartoes = $(".cartaoInput")
				for(cartao of cartoes){
					if($(cartao).prop("checked")){
						alert("Utilização de cupom excedida! Cartão de crédito em uso")
						return false
					}
				}
				
				var cupons = $(".cupomInput")
				for(cupom of cupons){
					if($(cupom).prop("checked")){
						let valor = $(cupom).prev().val()
						valor = parseFloat(valor)
						if(valor > valorTotal){
							for(cp of cupons){
								if($(cupom).val() == $(cp).val()){
									continue
								}else if($(cp).prop("checked")){
									let valorTotalCupom1 = $("#txtValorTotalCupom").val()
									valorTotalCupom1 = parseFloat(valorTotalCupom1)
									
									let valorCupom1 = $(cp).prev().val()
									valorCupom1 = parseFloat(valorCupom1)

									valorTotalCupom1 -= valorCupom1
									
									valorTotalCupom -= valorCupom1

									$("#txtValorTotalCupom").val(valorTotalCupom1)

								    if(cartoes.length == 0){
										let valorTotalPago1 = $("#txtValorTotalPago").val()
										valorTotalPago1 = parseFloat(valorTotalPago1)
										
										valorTotalPago1 -= valorCupom1
										
										$("#txtValorTotalPago").val(valorTotalPago1)
								    }
									
									$(cp).prop("checked",false);
								    let val = $(cp).val();
								    $("#formularioCompra")
								      .find(`input[value=${val}][name="txtCupomId"]`)
								      .remove();
								    
								    $("#formularioCompra")
								      .find(`input[value=${valorCupom1}][name="txtValorCupom"]`)
								      .remove();

								}
							}
							
						}
					}
				}
			}
				
			$("#txtValorTotalCupom").val(valorTotalCupom)
			
			if(cartoes.length == 0){
				let valorTotalPago = $("#txtValorTotalPago").val()
				valorTotalPago = parseFloat(valorTotalPago)
				
				valorTotalPago += valorCupom
				
				$("#txtValorTotalPago").val(valorTotalPago)
			}
			
			$(this).prop("checked", "checked");
		    let val = $(this).val();
		    $("#formularioCompra").append(
		      `<input type="hidden" name="txtCupomId" value="${val}">
		       <input type="hidden" name="txtValorCupom" value="${valorCupom}">
		      `
		    );
			

		}else{
			var cartoes = $(".cartaoInput[checked]");
			let valorTotalCupom = $("#txtValorTotalCupom").val()
			valorTotalCupom = parseFloat(valorTotalCupom)
			
			let valorCupom = $(this).parent().find("#txtCupomValor").val()
			valorCupom = parseFloat(valorCupom)

			valorTotalCupom -= valorCupom
			
			$("#txtValorTotalCupom").val(valorTotalCupom)
			
			if(cartoes.length == 0){
				let valorTotalPago = $("#txtValorTotalPago").val()
				valorTotalPago = parseFloat(valorTotalPago)
				
				valorTotalPago -= valorCupom
				$("#txtValorTotalPago").val(valorTotalPago)
			}

			$(this).removeAttr("checked");
		    let val = $(this).val();
		    $("#formularioCompra")
		      .find(`input[value=${val}][name="txtCupomId"]`)
		      .remove();
		    
		    $("#formularioCompra")
		      .find(`input[value=${valorCupom}][name="txtValorCupom"]`)
		      .remove();
		    
		}

	})
});

$(".enderecoInput").change(function () {
  let endereco = $(this)
    .parent()
    .parent()
    .parent()
    .parent()
    .find("input[name=txtEndId]")
    .val();
  let sigla = $(this)
    .parent()
    .parent()
    .parent()
    .parent()
    .find("input[name=txtEstSigla]")
    .val();
  if (sigla === "SP") {
    $("#listaItens").find("#textoFrete").children().eq(1).text("R$ 10.00");
    $("#listaItens")
      .find("#textoFrete")
      .find("input[name=txtFreteValor]")
      .val(10);
    $("#listaItens")
      .find("#textoFrete")
      .find("input[name=txtFretePrevisao]")
      .val(5);
  } else {
    $("#listaItens").find("#textoFrete").children().eq(1).text("R$ 20.00");
    $("#listaItens")
      .find("#textoFrete")
      .find("input[name=txtFreteValor]")
      .val(20);
    $("#listaItens")
      .find("#textoFrete")
      .find("input[name=txtFretePrevisao]")
      .val(8);
  }

  $("#formularioCompra").find("input[name=txtEnderecoId]").val(endereco);
});

$(".cartaoInput").click(function () {
  if ($(this).prop("checked")) {
		let valorTotal = $("#txtSubTotal").val()
		valorTotal = parseFloat(valorTotal)
		
		let valorTotalPago = $("#txtValorTotalPago").val()
		valorTotalPago = parseFloat(valorTotalPago)
		
		if(valorTotalPago == 0){
			$("#txtValorTotalPago").val(valorTotal)
		}
		
		let valorTotalCupom = $("#txtValorTotalCupom").val()
		valorTotalCupom = parseFloat(valorTotalCupom)
		
		if(valorTotalCupom >= valorTotal){
			alert("Valor de pagamento já atingiu o valor do pedido! Caso insista em usar um cartão, remova algum cupom.")
			return false
		}
	  
	    $(this).prop("checked", "checked");
	    let val = $(this).val();
	    $("#formularioCompra").append(
	      `<input type="hidden" name="txtCartaoCreditoId" value="${val}">`
	    );
  } else {
	  let cartoes = $(".cartaoInput")
	  var flag = false
	  for(cartao of cartoes){
		  if($(cartao).prop("checked")){
			  continue
		  }else{
			  flag = true
		  }	  
	  }
	  if(flag){
			let valorTotalPago = $("#txtValorTotalPago").val()
			valorTotalPago = parseFloat(valorTotalPago)
			
			let valorCompra = $("#txtSubTotal").val()
			valorCompra = parseFloat(valorCompra)
			
			valorTotalPago -= valorCompra
			
			$("#txtValorTotalPago").val(valorTotalPago)
	  }
	  
    $(this).removeAttr("checked");
    let val = $(this).val();
    $("#formularioCompra")
      .find(`input[value=${val}][name="txtCartaoCreditoId"]`)
      .remove();
  }
});

$(".cupomInput").click(function () {
	if($(this).prop("checked")){
		var valorTotal = $("#txtSubTotal").val()
		valorTotal = parseFloat(valorTotal)
		
		var valorTotalCupom = $("#txtValorTotalCupom").val()
		valorTotalCupom = parseFloat(valorTotalCupom)
		
		var valorCupom = $(this).parent().find("#txtCupomValor").val()
		valorCupom = parseFloat(valorCupom)

		valorTotalCupom += valorCupom
		
		if((valorTotalCupom > valorTotal) && ((valorTotalCupom - valorTotal) > valorCupom)){
			alert("Utilização de cupom excedida!")
			return false
		}else if(valorTotalCupom > valorTotal){
			let cartoes = $(".cartaoInput")
			for(cartao of cartoes){
				if($(cartao).prop("checked")){
					alert("Utilização de cupom excedida! Cartão de crédito em uso")
					return false
				}
			}
			
			var cupons = $(".cupomInput")
			for(cupom of cupons){
				if($(cupom).prop("checked")){
					let valor = $(cupom).prev().val()
					valor = parseFloat(valor)
					if(valor > valorTotal){
						for(cp of cupons){
							if($(cupom).val() == $(cp).val()){
								continue
							}else if($(cp).prop("checked")){
								let valorTotalCupom1 = $("#txtValorTotalCupom").val()
								valorTotalCupom1 = parseFloat(valorTotalCupom1)
								
								let valorCupom1 = $(cp).prev().val()
								valorCupom1 = parseFloat(valorCupom1)

								valorTotalCupom1 -= valorCupom1
								
								valorTotalCupom -= valorCupom1

								$("#txtValorTotalCupom").val(valorTotalCupom1)
								
								let valorTotalPago1 = $("#txtValorTotalPago").val()
								valorTotalPago1 = parseFloat(valorTotalPago1)
								
								valorTotalPago1 -= valorCupom1
								
								$("#txtValorTotalPago").val(valorTotalPago1)
								
								$(cp).prop("checked",false);
							    let val = $(cp).val();
							    $("#formularioCompra")
							      .find(`input[value=${val}][name="txtCupomId"]`)
							      .remove();
							    
							    $("#formularioCompra")
							      .find(`input[value=${valorCupom1}][name="txtValorCupom"]`)
							      .remove();

							}
						}
						
					}
				}
			}
		}
			
		$("#txtValorTotalCupom").val(valorTotalCupom)
		
		
		let valorTotalPago = $("#txtValorTotalPago").val()
		valorTotalPago = parseFloat(valorTotalPago)
		
		valorTotalPago += valorCupom
		
		$("#txtValorTotalPago").val(valorTotalPago)
		
		$(this).prop("checked", "checked");
	    let val = $(this).val();
	    $("#formularioCompra").append(
	      `<input type="hidden" name="txtCupomId" value="${val}">
	       <input type="hidden" name="txtValorCupom" value="${valorCupom}">
	      `
	    );
		

	}else{

		let valorTotalCupom = $("#txtValorTotalCupom").val()
		valorTotalCupom = parseFloat(valorTotalCupom)
		
		let valorCupom = $(this).parent().find("#txtCupomValor").val()
		valorCupom = parseFloat(valorCupom)

		valorTotalCupom -= valorCupom
		
		$("#txtValorTotalCupom").val(valorTotalCupom)
		
		let valorTotalPago = $("#txtValorTotalPago").val()
		valorTotalPago = parseFloat(valorTotalPago)
		
		valorTotalPago -= valorCupom
		
		$("#txtValorTotalPago").val(valorTotalPago)
		
		$(this).removeAttr("checked");
	    let val = $(this).val();
	    $("#formularioCompra")
	      .find(`input[value=${val}][name="txtCupomId"]`)
	      .remove();
	    
	    $("#formularioCompra")
	      .find(`input[value=${valorCupom}][name="txtValorCupom"]`)
	      .remove();
	    
	}

})

function disable(){

	let valor = $("#txtValorTotalPago").val()
	valor = parseFloat(valor)
	
	var valorTotal = $("#txtSubTotal").val()
	valorTotal = parseFloat(valorTotal)
	
	if(valorTotal > valor){
		$("#botaoSubmit").addClass("disabled")
	}else{
		$("#botaoSubmit").removeClass("disabled")
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



