var itensCarrinho;

$(document).ready(function () {
  let txtCarrinhoId = $("#txtCarrinhoId").val();
  let txtClienteId = $("#txtClienteId").val();
  $.ajax({
    url: `http://localhost:8085/schoolstore/app?tarefa=listarItensCarrinho&txtCarrinhoId=${txtCarrinhoId}`,
    type: "GET",
    success: (response) => {
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
				  	  <input type="hidden" name="txtEnderecoId" value="">
				  	  <input type="hidden" name="tarefa" value="efetuarPedido">
					  <button type="submit" class="btn btn-success float-right">Efetuar Compra</a>
				  </form>
				  
		  `);
      response.itensCarrinho.forEach(function (item) {
        let preco =
          item.produto.preco * item.produto.precificacao.percentual +
          item.produto.preco;
        preco = parseFloat(preco).toFixed(2);
        $("#listaItens").prepend(`
					  	<li class="d-flex justify-content-between">
					  		<p>${item.quantidade}x ${item.produto.nome}</p>
					  		<p>R$ ${preco}</p>
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
      $("#txtFretePreco").val(valorTotal);
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
      }
    },
  });
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
    $(this).prop("checked", "checked");
    let val = $(this).val();
    $("#formularioCompra").append(
      `<input type="hidden" name="txtCartaoCreditoId" value="${val}">`
    );
  } else {
    $(this).removeAttr("checked");
    let val = $(this).val();
    $("#formularioCompra")
      .find(`input[value=${val}][name="txtCartaoCreditoId"]`)
      .remove();
  }
});
