
$(document).ready(function () {
	$.ajax({
		  url: "http://localhost:8085/schoolstore/app?tarefa=consultarProdutos",
		  type: "GET",
		  success: response => {
			  console.log(response)
			  response.forEach(function(data){
				  $("#tableProdutos").append(`
				  			<tr class="text-center">
		                      <th scope="row">${data.id}</th>
		                      <td>${data.nome}</td>
		                      <td>${data.preco}</td>
		                      <td>${data.descricao}</td>
		                      <td>${data.nome}</td>
		                      <td>${data.estoque.quantidadeTotal}</td>
		                      <td>${data.estoque.itensEstoque[0].fornecedor.nome}</td>
		                      <td>${data.precificacao.percentual}</td>
		                      <td>Inativar</td>
		                    </tr>
				  `)
			  })
		  }
	})
});