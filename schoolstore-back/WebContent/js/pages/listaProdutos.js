var responseProd;
$(document).ready(function () {
	$.ajax({
		  url: "http://localhost:8085/schoolstore/app?tarefa=consultarProdutos",
		  type: "GET",
		  success: response => {
			  responseProd = response
			  response.forEach(function(data){
				  let preco = (data.preco*data.precificacao.percentual) + data.preco
				  preco = (Math.round(preco * 100) / 100).toFixed(2)
				  let carrinho = $("#carrinhoId").val()
				  if(carrinho === undefined){
					  $("#itens").append(`
							  <div class="col-lg-4 col-md-12">
							  		<div class="card hoverable mb-4">
									    <a href="#">
									        <div class="card-body">
									            <div class="row align-items-center">
									                <div class="col-3 px-0">
									                    <img src="${data.imagem.caminho}" class="img-fluid">
									                </div>
									                <div class="col-9" >
									                    <strong class="teal-text">${data.nome}</strong>
									                    
									                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
									                        <strong>R$ ${preco}</strong>
									                    </h6>
									                </div>
									                <div class="col-12 text-right">

									                	<button type="submit" class="btn btn-link btn-sm p-0 pb-2 disabled">+ Carrinho</button>
									                	<form action="app" method="post">
									                		<input type="hidden" name="txtProdutoId" value="${data.id}">
									                		<input type="hidden" name="tarefa" value="consultarProduto">
									                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
									                	</form>
									                </div>
									            </div>
									        </div>
									    </a>
									</div>
								</div>
					  `)
				  }else{
					  $("#itens").append(`
							  <div class="col-lg-4 col-md-12">
							  		<div class="card hoverable mb-4">
									    <a href="#">
									        <div class="card-body">
									            <div class="row align-items-center">
									                <div class="col-3 px-0">
									                    <img src="${data.imagem.caminho}" class="img-fluid">
									                </div>
									                <div class="col-9" >
									                    <strong class="teal-text">${data.nome}</strong>
									                    
									                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
									                        <strong>R$ ${preco}</strong>
									                    </h6>
									                </div>
									                <div class="col-12 text-right">
									                	<form action="app" method="post">
																			<input type="hidden" name="txtCarrinhoId" value="${carrinho}">
																			<input type="hidden" name="txtSubTotal" value="${preco}">
									                		<input type="hidden" name="txtProdutoId" value="${data.id}">
									                		<input type="hidden" name="tarefa" value="adicionarProduto">
									                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">+ Carrinho</button>
									                	</form>
									                	<form action="app" method="post">
									                		<input type="hidden" name="txtProdutoId" value="${data.id}">
									                		<input type="hidden" name="tarefa" value="consultarProduto">
									                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
									                	</form>
									                </div>
									            </div>
									        </div>
									    </a>
									</div>
								</div>
					  `)
				  }
				  
				  
				 
			  })
		  }
	})
});

$(".filtro").change(function(){
	$("#filtroInput").val($(this).val())
	if($("#filtroInput").val() == "mesa"){
		if($("#filtro2input").val() == "escritorio"){
			$("#itens").children().remove()
				  responseProd.forEach(function(data){
					  let filtro1 = false
					  let filtro2 = false
					  data.categorias.forEach(function(cat){
						  if(cat.nome == "Mesa")
							  filtro1 = true
							 
						  if(cat.nome == "Escritório")
							  filtro2 = true
					  })
					  if(filtro1 && filtro2){
						  let preco = (data.preco*data.precificacao.percentual) + data.preco
						  preco = (Math.round(preco * 100) / 100).toFixed(2)
						  let carrinho = $("#carrinhoId").val()
						  if(carrinho === undefined){
							  $("#itens").append(`
									  <div class="col-lg-4 col-md-12">
									  		<div class="card hoverable mb-4">
											    <a href="#">
											        <div class="card-body">
											            <div class="row align-items-center">
											                <div class="col-3 px-0">
											                    <img src="${data.imagem.caminho}" class="img-fluid">
											                </div>
											                <div class="col-9" >
											                    <strong class="teal-text">${data.nome}</strong>
											                    
											                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
											                        <strong>R$ ${preco}</strong>
											                    </h6>
											                </div>
											                <div class="col-12 text-right">

											                	<button type="submit" class="btn btn-link btn-sm p-0 pb-2 disabled">+ Carrinho</button>
											                	<form action="app" method="post">
											                		<input type="hidden" name="txtProdutoId" value="${data.id}">
											                		<input type="hidden" name="tarefa" value="consultarProduto">
											                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
											                	</form>
											                </div>
											            </div>
											        </div>
											    </a>
											</div>
										</div>
							  `)
						  }else{
							  $("#itens").append(`
									  <div class="col-lg-4 col-md-12">
									  		<div class="card hoverable mb-4">
											    <a href="#">
											        <div class="card-body">
											            <div class="row align-items-center">
											                <div class="col-3 px-0">
											                    <img src="${data.imagem.caminho}" class="img-fluid">
											                </div>
											                <div class="col-9" >
											                    <strong class="teal-text">${data.nome}</strong>
											                    
											                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
											                        <strong>R$ ${preco}</strong>
											                    </h6>
											                </div>
											                <div class="col-12 text-right">
											                	<form action="app" method="post">
																					<input type="hidden" name="txtCarrinhoId" value="${carrinho}">
																					<input type="hidden" name="txtSubTotal" value="${preco}">
											                		<input type="hidden" name="txtProdutoId" value="${data.id}">
											                		<input type="hidden" name="tarefa" value="adicionarProduto">
											                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">+ Carrinho</button>
											                	</form>
											                	<form action="app" method="post">
											                		<input type="hidden" name="txtProdutoId" value="${data.id}">
											                		<input type="hidden" name="tarefa" value="consultarProduto">
											                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
											                	</form>
											                </div>
											            </div>
											        </div>
											    </a>
											</div>
										</div>
							  `)
						  }
					  }
			  })
		}else if($("#filtro2input").val() == "escolar"){
			$("#itens").children().remove()
			  responseProd.forEach(function(data){
				  let filtro1 = false
				  let filtro2 = false
				  data.categorias.forEach(function(cat){
					  if(cat.nome == "Mesa")
						  filtro1 = true
						 
					  if(cat.nome == "Escolar")
						  filtro2 = true
				  })
				  if(filtro1 && filtro2){
					  let preco = (data.preco*data.precificacao.percentual) + data.preco
					  preco = (Math.round(preco * 100) / 100).toFixed(2)
					  let carrinho = $("#carrinhoId").val()
					  if(carrinho === undefined){
						  $("#itens").append(`
								  <div class="col-lg-4 col-md-12">
								  		<div class="card hoverable mb-4">
										    <a href="#">
										        <div class="card-body">
										            <div class="row align-items-center">
										                <div class="col-3 px-0">
										                    <img src="${data.imagem.caminho}" class="img-fluid">
										                </div>
										                <div class="col-9" >
										                    <strong class="teal-text">${data.nome}</strong>
										                    
										                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
										                        <strong>R$ ${preco}</strong>
										                    </h6>
										                </div>
										                <div class="col-12 text-right">

										                	<button type="submit" class="btn btn-link btn-sm p-0 pb-2 disabled">+ Carrinho</button>
										                	<form action="app" method="post">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="consultarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
										                	</form>
										                </div>
										            </div>
										        </div>
										    </a>
										</div>
									</div>
						  `)
					  }else{
						  $("#itens").append(`
								  <div class="col-lg-4 col-md-12">
								  		<div class="card hoverable mb-4">
										    <a href="#">
										        <div class="card-body">
										            <div class="row align-items-center">
										                <div class="col-3 px-0">
										                    <img src="${data.imagem.caminho}" class="img-fluid">
										                </div>
										                <div class="col-9" >
										                    <strong class="teal-text">${data.nome}</strong>
										                    
										                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
										                        <strong>R$ ${preco}</strong>
										                    </h6>
										                </div>
										                <div class="col-12 text-right">
										                	<form action="app" method="post">
																				<input type="hidden" name="txtCarrinhoId" value="${carrinho}">
																				<input type="hidden" name="txtSubTotal" value="${preco}">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="adicionarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">+ Carrinho</button>
										                	</form>
										                	<form action="app" method="post">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="consultarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
										                	</form>
										                </div>
										            </div>
										        </div>
										    </a>
										</div>
									</div>
						  `)
					  }
				  }
		  })
		}else{
			$("#itens").children().remove()
			  responseProd.forEach(function(data){
				  let filtro1 = false
				  data.categorias.forEach(function(cat){
					  if(cat.nome == "Mesa")
						  filtro1 = true
				  })
				  if(filtro1){
					  let preco = (data.preco*data.precificacao.percentual) + data.preco
					  preco = (Math.round(preco * 100) / 100).toFixed(2)
					  let carrinho = $("#carrinhoId").val()
					  if(carrinho === undefined){
						  $("#itens").append(`
								  <div class="col-lg-4 col-md-12">
								  		<div class="card hoverable mb-4">
										    <a href="#">
										        <div class="card-body">
										            <div class="row align-items-center">
										                <div class="col-3 px-0">
										                    <img src="${data.imagem.caminho}" class="img-fluid">
										                </div>
										                <div class="col-9" >
										                    <strong class="teal-text">${data.nome}</strong>
										                    
										                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
										                        <strong>R$ ${preco}</strong>
										                    </h6>
										                </div>
										                <div class="col-12 text-right">

										                	<button type="submit" class="btn btn-link btn-sm p-0 pb-2 disabled">+ Carrinho</button>
										                	<form action="app" method="post">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="consultarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
										                	</form>
										                </div>
										            </div>
										        </div>
										    </a>
										</div>
									</div>
						  `)
					  }else{
						  $("#itens").append(`
								  <div class="col-lg-4 col-md-12">
								  		<div class="card hoverable mb-4">
										    <a href="#">
										        <div class="card-body">
										            <div class="row align-items-center">
										                <div class="col-3 px-0">
										                    <img src="${data.imagem.caminho}" class="img-fluid">
										                </div>
										                <div class="col-9" >
										                    <strong class="teal-text">${data.nome}</strong>
										                    
										                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
										                        <strong>R$ ${preco}</strong>
										                    </h6>
										                </div>
										                <div class="col-12 text-right">
										                	<form action="app" method="post">
																				<input type="hidden" name="txtCarrinhoId" value="${carrinho}">
																				<input type="hidden" name="txtSubTotal" value="${preco}">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="adicionarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">+ Carrinho</button>
										                	</form>
										                	<form action="app" method="post">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="consultarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
										                	</form>
										                </div>
										            </div>
										        </div>
										    </a>
										</div>
									</div>
						  `)
					  }
				  }
		  })
		}
	}else if($("#filtroInput").val() == "cadeira"){
		if($("#filtro2input").val() == "escritorio"){
			$("#itens").children().remove()
				  responseProd.forEach(function(data){
					  let filtro1 = false
					  let filtro2 = false
					  data.categorias.forEach(function(cat){
						  if(cat.nome == "Cadeira")
							  filtro1 = true
							 
						  if(cat.nome == "Escritório")
							  filtro2 = true
					  })
					  if(filtro1 && filtro2){
						  let preco = (data.preco*data.precificacao.percentual) + data.preco
						  preco = (Math.round(preco * 100) / 100).toFixed(2)
						  let carrinho = $("#carrinhoId").val()
						  if(carrinho === undefined){
							  $("#itens").append(`
									  <div class="col-lg-4 col-md-12">
									  		<div class="card hoverable mb-4">
											    <a href="#">
											        <div class="card-body">
											            <div class="row align-items-center">
											                <div class="col-3 px-0">
											                    <img src="${data.imagem.caminho}" class="img-fluid">
											                </div>
											                <div class="col-9" >
											                    <strong class="teal-text">${data.nome}</strong>
											                    
											                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
											                        <strong>R$ ${preco}</strong>
											                    </h6>
											                </div>
											                <div class="col-12 text-right">

											                	<button type="submit" class="btn btn-link btn-sm p-0 pb-2 disabled">+ Carrinho</button>
											                	<form action="app" method="post">
											                		<input type="hidden" name="txtProdutoId" value="${data.id}">
											                		<input type="hidden" name="tarefa" value="consultarProduto">
											                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
											                	</form>
											                </div>
											            </div>
											        </div>
											    </a>
											</div>
										</div>
							  `)
						  }else{
							  $("#itens").append(`
									  <div class="col-lg-4 col-md-12">
									  		<div class="card hoverable mb-4">
											    <a href="#">
											        <div class="card-body">
											            <div class="row align-items-center">
											                <div class="col-3 px-0">
											                    <img src="${data.imagem.caminho}" class="img-fluid">
											                </div>
											                <div class="col-9" >
											                    <strong class="teal-text">${data.nome}</strong>
											                    
											                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
											                        <strong>R$ ${preco}</strong>
											                    </h6>
											                </div>
											                <div class="col-12 text-right">
											                	<form action="app" method="post">
																					<input type="hidden" name="txtCarrinhoId" value="${carrinho}">
																					<input type="hidden" name="txtSubTotal" value="${preco}">
											                		<input type="hidden" name="txtProdutoId" value="${data.id}">
											                		<input type="hidden" name="tarefa" value="adicionarProduto">
											                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">+ Carrinho</button>
											                	</form>
											                	<form action="app" method="post">
											                		<input type="hidden" name="txtProdutoId" value="${data.id}">
											                		<input type="hidden" name="tarefa" value="consultarProduto">
											                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
											                	</form>
											                </div>
											            </div>
											        </div>
											    </a>
											</div>
										</div>
							  `)
						  }
					  }
			  })
		}else if($("#filtro2input").val() == "escolar"){
			$("#itens").children().remove()
			  responseProd.forEach(function(data){
				  let filtro1 = false
				  let filtro2 = false
				  data.categorias.forEach(function(cat){
					  if(cat.nome == "Cadeira")
						  filtro1 = true
						 
					  if(cat.nome == "Escolar")
						  filtro2 = true
				  })
				  if(filtro1 && filtro2){
					  let preco = (data.preco*data.precificacao.percentual) + data.preco
					  preco = (Math.round(preco * 100) / 100).toFixed(2)
					  let carrinho = $("#carrinhoId").val()
					  if(carrinho === undefined){
						  $("#itens").append(`
								  <div class="col-lg-4 col-md-12">
								  		<div class="card hoverable mb-4">
										    <a href="#">
										        <div class="card-body">
										            <div class="row align-items-center">
										                <div class="col-3 px-0">
										                    <img src="${data.imagem.caminho}" class="img-fluid">
										                </div>
										                <div class="col-9" >
										                    <strong class="teal-text">${data.nome}</strong>
										                    
										                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
										                        <strong>R$ ${preco}</strong>
										                    </h6>
										                </div>
										                <div class="col-12 text-right">

										                	<button type="submit" class="btn btn-link btn-sm p-0 pb-2 disabled">+ Carrinho</button>
										                	<form action="app" method="post">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="consultarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
										                	</form>
										                </div>
										            </div>
										        </div>
										    </a>
										</div>
									</div>
						  `)
					  }else{
						  $("#itens").append(`
								  <div class="col-lg-4 col-md-12">
								  		<div class="card hoverable mb-4">
										    <a href="#">
										        <div class="card-body">
										            <div class="row align-items-center">
										                <div class="col-3 px-0">
										                    <img src="${data.imagem.caminho}" class="img-fluid">
										                </div>
										                <div class="col-9" >
										                    <strong class="teal-text">${data.nome}</strong>
										                    
										                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
										                        <strong>R$ ${preco}</strong>
										                    </h6>
										                </div>
										                <div class="col-12 text-right">
										                	<form action="app" method="post">
																				<input type="hidden" name="txtCarrinhoId" value="${carrinho}">
																				<input type="hidden" name="txtSubTotal" value="${preco}">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="adicionarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">+ Carrinho</button>
										                	</form>
										                	<form action="app" method="post">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="consultarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
										                	</form>
										                </div>
										            </div>
										        </div>
										    </a>
										</div>
									</div>
						  `)
					  }
				  }
		  })
		}else{
			$("#itens").children().remove()
			  responseProd.forEach(function(data){
				  let filtro1 = false
				  data.categorias.forEach(function(cat){
					  if(cat.nome == "Cadeira")
						  filtro1 = true
				  })
				  if(filtro1){
					  let preco = (data.preco*data.precificacao.percentual) + data.preco
					  preco = (Math.round(preco * 100) / 100).toFixed(2)
					  let carrinho = $("#carrinhoId").val()
					  if(carrinho === undefined){
						  $("#itens").append(`
								  <div class="col-lg-4 col-md-12">
								  		<div class="card hoverable mb-4">
										    <a href="#">
										        <div class="card-body">
										            <div class="row align-items-center">
										                <div class="col-3 px-0">
										                    <img src="${data.imagem.caminho}" class="img-fluid">
										                </div>
										                <div class="col-9" >
										                    <strong class="teal-text">${data.nome}</strong>
										                    
										                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
										                        <strong>R$ ${preco}</strong>
										                    </h6>
										                </div>
										                <div class="col-12 text-right">

										                	<button type="submit" class="btn btn-link btn-sm p-0 pb-2 disabled">+ Carrinho</button>
										                	<form action="app" method="post">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="consultarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
										                	</form>
										                </div>
										            </div>
										        </div>
										    </a>
										</div>
									</div>
						  `)
					  }else{
						  $("#itens").append(`
								  <div class="col-lg-4 col-md-12">
								  		<div class="card hoverable mb-4">
										    <a href="#">
										        <div class="card-body">
										            <div class="row align-items-center">
										                <div class="col-3 px-0">
										                    <img src="${data.imagem.caminho}" class="img-fluid">
										                </div>
										                <div class="col-9" >
										                    <strong class="teal-text">${data.nome}</strong>
										                    
										                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
										                        <strong>R$ ${preco}</strong>
										                    </h6>
										                </div>
										                <div class="col-12 text-right">
										                	<form action="app" method="post">
																				<input type="hidden" name="txtCarrinhoId" value="${carrinho}">
																				<input type="hidden" name="txtSubTotal" value="${preco}">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="adicionarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">+ Carrinho</button>
										                	</form>
										                	<form action="app" method="post">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="consultarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
										                	</form>
										                </div>
										            </div>
										        </div>
										    </a>
										</div>
									</div>
						  `)
					  }
				  }
		  })
		}
	}else if($("#filtroInput").val() == "acessorio"){
		if($("#filtro2input").val() == "escritorio"){
			$("#itens").children().remove()
				  responseProd.forEach(function(data){
					  let filtro1 = false
					  let filtro2 = false
					  data.categorias.forEach(function(cat){
						  if(cat.nome == "Acessório")
							  filtro1 = true
							 
						  if(cat.nome == "Escritório")
							  filtro2 = true
					  })
					  if(filtro1 && filtro2){
						  let preco = (data.preco*data.precificacao.percentual) + data.preco
						  preco = (Math.round(preco * 100) / 100).toFixed(2)
						  let carrinho = $("#carrinhoId").val()
						  if(carrinho === undefined){
							  $("#itens").append(`
									  <div class="col-lg-4 col-md-12">
									  		<div class="card hoverable mb-4">
											    <a href="#">
											        <div class="card-body">
											            <div class="row align-items-center">
											                <div class="col-3 px-0">
											                    <img src="${data.imagem.caminho}" class="img-fluid">
											                </div>
											                <div class="col-9" >
											                    <strong class="teal-text">${data.nome}</strong>
											                    
											                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
											                        <strong>R$ ${preco}</strong>
											                    </h6>
											                </div>
											                <div class="col-12 text-right">

											                	<button type="submit" class="btn btn-link btn-sm p-0 pb-2 disabled">+ Carrinho</button>
											                	<form action="app" method="post">
											                		<input type="hidden" name="txtProdutoId" value="${data.id}">
											                		<input type="hidden" name="tarefa" value="consultarProduto">
											                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
											                	</form>
											                </div>
											            </div>
											        </div>
											    </a>
											</div>
										</div>
							  `)
						  }else{
							  $("#itens").append(`
									  <div class="col-lg-4 col-md-12">
									  		<div class="card hoverable mb-4">
											    <a href="#">
											        <div class="card-body">
											            <div class="row align-items-center">
											                <div class="col-3 px-0">
											                    <img src="${data.imagem.caminho}" class="img-fluid">
											                </div>
											                <div class="col-9" >
											                    <strong class="teal-text">${data.nome}</strong>
											                    
											                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
											                        <strong>R$ ${preco}</strong>
											                    </h6>
											                </div>
											                <div class="col-12 text-right">
											                	<form action="app" method="post">
																					<input type="hidden" name="txtCarrinhoId" value="${carrinho}">
																					<input type="hidden" name="txtSubTotal" value="${preco}">
											                		<input type="hidden" name="txtProdutoId" value="${data.id}">
											                		<input type="hidden" name="tarefa" value="adicionarProduto">
											                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">+ Carrinho</button>
											                	</form>
											                	<form action="app" method="post">
											                		<input type="hidden" name="txtProdutoId" value="${data.id}">
											                		<input type="hidden" name="tarefa" value="consultarProduto">
											                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
											                	</form>
											                </div>
											            </div>
											        </div>
											    </a>
											</div>
										</div>
							  `)
						  }
					  }
			  })
		}else if($("#filtro2input").val() == "escolar"){
			$("#itens").children().remove()
			  responseProd.forEach(function(data){
				  let filtro1 = false
				  let filtro2 = false
				  data.categorias.forEach(function(cat){
					  if(cat.nome == "Acessório")
						  filtro1 = true
						 
					  if(cat.nome == "Escolar")
						  filtro2 = true
				  })
				  if(filtro1 && filtro2){
					  let preco = (data.preco*data.precificacao.percentual) + data.preco
					  preco = (Math.round(preco * 100) / 100).toFixed(2)
					  let carrinho = $("#carrinhoId").val()
					  if(carrinho === undefined){
						  $("#itens").append(`
								  <div class="col-lg-4 col-md-12">
								  		<div class="card hoverable mb-4">
										    <a href="#">
										        <div class="card-body">
										            <div class="row align-items-center">
										                <div class="col-3 px-0">
										                    <img src="${data.imagem.caminho}" class="img-fluid">
										                </div>
										                <div class="col-9" >
										                    <strong class="teal-text">${data.nome}</strong>
										                    
										                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
										                        <strong>R$ ${preco}</strong>
										                    </h6>
										                </div>
										                <div class="col-12 text-right">

										                	<button type="submit" class="btn btn-link btn-sm p-0 pb-2 disabled">+ Carrinho</button>
										                	<form action="app" method="post">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="consultarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
										                	</form>
										                </div>
										            </div>
										        </div>
										    </a>
										</div>
									</div>
						  `)
					  }else{
						  $("#itens").append(`
								  <div class="col-lg-4 col-md-12">
								  		<div class="card hoverable mb-4">
										    <a href="#">
										        <div class="card-body">
										            <div class="row align-items-center">
										                <div class="col-3 px-0">
										                    <img src="${data.imagem.caminho}" class="img-fluid">
										                </div>
										                <div class="col-9" >
										                    <strong class="teal-text">${data.nome}</strong>
										                    
										                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
										                        <strong>R$ ${preco}</strong>
										                    </h6>
										                </div>
										                <div class="col-12 text-right">
										                	<form action="app" method="post">
																				<input type="hidden" name="txtCarrinhoId" value="${carrinho}">
																				<input type="hidden" name="txtSubTotal" value="${preco}">
																				<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="adicionarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">+ Carrinho</button>
										                	</form>
										                	<form action="app" method="post">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="consultarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
										                	</form>
										                </div>
										            </div>
										        </div>
										    </a>
										</div>
									</div>
						  `)
					  }
				  }
		  })
		}else{
			$("#itens").children().remove()
			  responseProd.forEach(function(data){
				  let filtro1 = false
				  data.categorias.forEach(function(cat){
					  if(cat.nome == "Acessório")
						  filtro1 = true
				  })
				  if(filtro1){
					  let preco = (data.preco*data.precificacao.percentual) + data.preco
					  preco = (Math.round(preco * 100) / 100).toFixed(2)
					  let carrinho = $("#carrinhoId").val()
					  if(carrinho === undefined){
						  $("#itens").append(`
								  <div class="col-lg-4 col-md-12">
								  		<div class="card hoverable mb-4">
										    <a href="#">
										        <div class="card-body">
										            <div class="row align-items-center">
										                <div class="col-3 px-0">
										                    <img src="${data.imagem.caminho}" class="img-fluid">
										                </div>
										                <div class="col-9" >
										                    <strong class="teal-text">${data.nome}</strong>
										                    
										                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
										                        <strong>R$ ${preco}</strong>
										                    </h6>
										                </div>
										                <div class="col-12 text-right">

										                	<button type="submit" class="btn btn-link btn-sm p-0 pb-2 disabled">+ Carrinho</button>
										                	<form action="app" method="post">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="consultarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
										                	</form>
										                </div>
										            </div>
										        </div>
										    </a>
										</div>
									</div>
						  `)
					  }else{
						  $("#itens").append(`
								  <div class="col-lg-4 col-md-12">
								  		<div class="card hoverable mb-4">
										    <a href="#">
										        <div class="card-body">
										            <div class="row align-items-center">
										                <div class="col-3 px-0">
										                    <img src="${data.imagem.caminho}" class="img-fluid">
										                </div>
										                <div class="col-9" >
										                    <strong class="teal-text">${data.nome}</strong>
										                    
										                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
										                        <strong>R$ ${preco}</strong>
										                    </h6>
										                </div>
										                <div class="col-12 text-right">
										                	<form action="app" method="post">
																				<input type="hidden" name="txtCarrinhoId" value="${carrinho}">
																				<input type="hidden" name="txtSubTotal" value="${preco}">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="adicionarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">+ Carrinho</button>
										                	</form>
										                	<form action="app" method="post">
										                		<input type="hidden" name="txtProdutoId" value="${data.id}">
										                		<input type="hidden" name="tarefa" value="consultarProduto">
										                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
										                	</form>
										                </div>
										            </div>
										        </div>
										    </a>
										</div>
									</div>
						  `)
					  }
				  }
		  })
		}
	}else{
		$("#itens").children().remove()
				  responseProd.forEach(function(data){
						  let preco = (data.preco*data.precificacao.percentual) + data.preco
						  preco = (Math.round(preco * 100) / 100).toFixed(2)
						  let carrinho = $("#carrinhoId").val()
						  if(carrinho === undefined){
							  $("#itens").append(`
									  <div class="col-lg-4 col-md-12">
									  		<div class="card hoverable mb-4">
											    <a href="#">
											        <div class="card-body">
											            <div class="row align-items-center">
											                <div class="col-3 px-0">
											                    <img src="${data.imagem.caminho}" class="img-fluid">
											                </div>
											                <div class="col-9" >
											                    <strong class="teal-text">${data.nome}</strong>
											                    
											                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
											                        <strong>R$ ${preco}</strong>
											                    </h6>
											                </div>
											                <div class="col-12 text-right">

											                	<button type="submit" class="btn btn-link btn-sm p-0 pb-2 disabled">+ Carrinho</button>
											                	<form action="app" method="post">
											                		<input type="hidden" name="txtProdutoId" value="${data.id}">
											                		<input type="hidden" name="tarefa" value="consultarProduto">
											                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
											                	</form>
											                </div>
											            </div>
											        </div>
											    </a>
											</div>
										</div>
							  `)
						  }else{
							  $("#itens").append(`
									  <div class="col-lg-4 col-md-12">
									  		<div class="card hoverable mb-4">
											    <a href="#">
											        <div class="card-body">
											            <div class="row align-items-center">
											                <div class="col-3 px-0">
											                    <img src="${data.imagem.caminho}" class="img-fluid">
											                </div>
											                <div class="col-9" >
											                    <strong class="teal-text">${data.nome}</strong>
											                    
											                    <h6 class="h6-responsive font-weight-bold dark-grey-text">
											                        <strong>R$ ${preco}</strong>
											                    </h6>
											                </div>
											                <div class="col-12 text-right">
											                	<form action="app" method="post">
																					<input type="hidden" name="txtCarrinhoId" value="${carrinho}">
																					<input type="hidden" name="txtSubTotal" value="${preco}">
											                		<input type="hidden" name="txtProdutoId" value="${data.id}">
											                		<input type="hidden" name="tarefa" value="adicionarProduto">
											                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">+ Carrinho</button>
											                	</form>
											                	<form action="app" method="post">
											                		<input type="hidden" name="txtProdutoId" value="${data.id}">
											                		<input type="hidden" name="tarefa" value="consultarProduto">
											                		<button type="submit" class="btn btn-link btn-sm p-0 pb-2">Ver</button>
											                	</form>
											                </div>
											            </div>
											        </div>
											    </a>
											</div>
										</div>
							  `)
						  }
					  
			  })
	}
})

$(".filtro2").change(function(){
	$("#filtro2input").val($(this).val())
})


