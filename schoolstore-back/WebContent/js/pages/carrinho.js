$(".excluir").click(function(){
	$(this).parent().parent().remove()
	
	let v = $(".valorSomado");
	let subTotal = 0;
	subTotal = parseFloat(subTotal)
	for(let i = 0 ; i < v.length ; i++){
		subTotal += parseFloat(v[i].value)
	}

	subTotal = parseFloat(subTotal)
	subTotal = (Math.round(subTotal * 100) / 100).toFixed(2)
	$("#subTotal").val(subTotal)
	$("#valorTotalCompra").html(`R$ ${subTotal}`)
})

$(".quantidade").change(function(){
	let qtdd = $(this).val()
	qtdd = parseInt(qtdd, 10)
	let precoProduto = $(this).parent().parent().find(".valor").val()
	let precificacao = $(this).parent().parent().find(".precificacao").val()
	precoProduto = parseFloat(precoProduto)
	precificacao = parseFloat(precificacao)
	
	precificacao = (Math.round(((precoProduto * precificacao) + Number.EPSILON) * 100) / 100) 
	let valorTotal = (precificacao + precoProduto) * qtdd
	valorTotal = (Math.round(valorTotal * 100) / 100).toFixed(2)
	$(this).parent().parent().find(".valorSomado").val(valorTotal)
	
	$(this).parent().parent().find(".preco").html(`R$ ${valorTotal}`)
	
	
	let v = $(".valorSomado");
	let subTotal = 0;
	subTotal = parseFloat(subTotal)
	for(let i = 0 ; i < v.length ; i++){
		subTotal += parseFloat(v[i].value)
	}

	subTotal = parseFloat(subTotal)
	subTotal = (Math.round(subTotal * 100) / 100).toFixed(2)
	$("#subTotal").val(subTotal)
	$("#valorTotalCompra").html(`R$ ${subTotal}`)
})