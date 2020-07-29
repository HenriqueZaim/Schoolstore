var itens;

//var entregue = 0;
//var cancelado;

var janeiroE = 0
var fevereiroE = 0
var marcoE = 0
var abrilE = 0
var maioE = 0
var junhoE = 0

var janeiroA = 0
var fevereiroA = 0
var marcoA = 0
var abrilA = 0
var maioA = 0
var junhoA = 0

var entregue = 0
var cancelado = 0

var inicioE = 0
var inicioA = 0

var fimE = 0
var fimA = 0

$(document).ready(function () {
	$.ajax({
		url: "http://localhost:8085/schoolstore/app?tarefa=consultarPedidos",
		type: "GET",
		async: false,
		success: response => {
			console.log(response)
			response.entidades.forEach(function(item){
				if(item.statusPedido === "ENTREGUE"){
					if(item.dataHoraCriacao.date.month === item.dataHoraAtualizacao.date.month){
						switch(item.dataHoraCriacao.date.month){
						case 1: 
							janeiroE++
							break;
						case 2: 
							fevereiroE++
							break;
						case 3: 
							marcoE++
							break;
						case 4:
							abrilE++
							break;
						case 5:
							maioE++
							break;
						case 6: 
							junhoE++
							break;
						default:
							break;
						}
					}else{
						switch(item.dataHoraAtualizacao.date.month){
						case 1: 
							janeiroE++
							break;
						case 2: 
							fevereiroE++
							break;
						case 3: 
							marcoE++
							break;
						case 4:
							abrilE++
							break;
						case 5:
							maioE++
							break;
						case 6: 
							junhoE++
							break;
						default:
							break;
						}
					}
				}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
					if(item.dataHoraCriacao.date.month === item.dataHoraAtualizacao.date.month){
						switch(item.dataHoraCriacao.date.month){
						case 1: 
							janeiroA++
							break;
						case 2: 
							fevereiroA++
							break;
						case 3: 
							marcoA++
							break;
						case 4:
							abrilA++
							break;
						case 5:
							maioA++
							break;
						case 6: 
							junhoA++
							break;
						default:
							break;
						}
					}else{
						switch(item.dataHoraAtualizacao.date.month){
						case 1: 
							janeiroA++
							break;
						case 2: 
							fevereiroA++
							break;
						case 3: 
							marcoA++
							break;
						case 4:
							abrilA++
							break;
						case 5:
							maioA++
							break;
						case 6: 
							junhoA++
							break;
						default:
							break;
						}
					}
				}

			})
						
			var ctx = document.getElementById('myChart').getContext('2d');
			window.myLine = Chart.Line(ctx, {
				data: {
					labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho'],
					datasets: [{
						label: 'Pedidos entregues',
						borderColor: "#51b286",
						backgroundColor: "#51b286",
						fill: false,
						data: [
							janeiroE,
							fevereiroE,
							marcoE,
							abrilE,
							maioE,
							junhoE
							],
							yAxisID: 'y-axis-1',
					}, {
						label: 'Pedidos cancelados + reprovados',
						borderColor: "#d55655",
						backgroundColor: "#d55655",
						fill: false,
						data: [
							janeiroA,
							fevereiroA,
							marcoA,
							abrilA,
							maioA,
							junhoA
							],
							yAxisID: 'y-axis-1'
					}]
				},
				options: {
					responsive: true,
					hoverMode: 'index',
					stacked: false,
					title: {
						display: true,
						text: 'Gráfico - Situação de pedidos'
					},
					scales: {
						yAxes: [{
							type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display: true,
							position: 'left',
							id: 'y-axis-1',
						}],
					}
				}
			});
			itens = response
		}
	});
})

$("#txtDataInicio").change(function(){
	if($("#txtDataInicio").val() === ""){
		if($("#txtDataFim").val() === ""){
			// tudo
			myLine.destroy()
			
			janeiroE = 0
			fevereiroE = 0
			marcoE = 0
			abrilE = 0
			maioE = 0
			junhoE = 0
			
			janeiroA = 0
			fevereiroA = 0
			marcoA = 0
			abrilA = 0
			maioA = 0
			junhoA = 0
			
			itens.entidades.forEach(function(item){
				if(item.statusPedido === "ENTREGUE"){
					if(item.dataHoraCriacao.date.month === item.dataHoraAtualizacao.date.month){
						switch(item.dataHoraCriacao.date.month){
						case 1: 
							janeiroE++
							break;
						case 2: 
							fevereiroE++
							break;
						case 3: 
							marcoE++
							break;
						case 4:
							abrilE++
							break;
						case 5:
							maioE++
							break;
						case 6: 
							junhoE++
							break;
						default:
							break;
						}
					}else{
						switch(item.dataHoraAtualizacao.date.month){
						case 1: 
							janeiroE++
							break;
						case 2: 
							fevereiroE++
							break;
						case 3: 
							marcoE++
							break;
						case 4:
							abrilE++
							break;
						case 5:
							maioE++
							break;
						case 6: 
							junhoE++
							break;
						default:
							break;
						}
					}
				}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
					if(item.dataHoraCriacao.date.month === item.dataHoraAtualizacao.date.month){
						switch(item.dataHoraCriacao.date.month){
						case 1: 
							janeiroA++
							break;
						case 2: 
							fevereiroA++
							break;
						case 3: 
							marcoA++
							break;
						case 4:
							abrilA++
							break;
						case 5:
							maioA++
							break;
						case 6: 
							junhoA++
							break;
						default:
							break;
						}
					}else{
						switch(item.dataHoraAtualizacao.date.month){
						case 1: 
							janeiroA++
							break;
						case 2: 
							fevereiroA++
							break;
						case 3: 
							marcoA++
							break;
						case 4:
							abrilA++
							break;
						case 5:
							maioA++
							break;
						case 6: 
							junhoA++
							break;
						default:
							break;
						}
					}
				}

			})
						
			var ctx = document.getElementById('myChart').getContext('2d');
			window.myLine = Chart.Line(ctx, {
				data: {
					labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho'],
					datasets: [{
						label: 'Pedidos entregues',
						borderColor: "#51b286",
						backgroundColor: "#51b286",
						fill: false,
						data: [
							janeiroE,
							fevereiroE,
							marcoE,
							abrilE,
							maioE,
							junhoE
							],
							yAxisID: 'y-axis-1',
					}, {
						label: 'Pedidos cancelados + reprovados',
						borderColor: "#d55655",
						backgroundColor: "#d55655",
						fill: false,
						data: [
							janeiroA,
							fevereiroA,
							marcoA,
							abrilA,
							maioA,
							junhoA
							],
							yAxisID: 'y-axis-1'
					}]
				},
				options: {
					responsive: true,
					hoverMode: 'index',
					stacked: false,
					title: {
						display: true,
						text: 'Gráfico - Situação de pedidos'
					},
					scales: {
						yAxes: [{
							type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display: true,
							position: 'left',
							id: 'y-axis-1',
						}],
					}
				}
			});

		}else{
			// só até a data fim colocada
			myLine.destroy()
			
			entregue = 0
			cancelado = 0
			
			inicioE = 0
			inicioA = 0
			
			var mes = parseInt($("#txtDataFim").val().substr(5,2))
			var dia = parseInt($("#txtDataFim").val().substr(8,2))
			
			itens.entidades.forEach(function(item){
				
				if(item.dataHoraCriacao.date.month === item.dataHoraAtualizacao.date.month){
					if(item.dataHoraCriacao.date.month <= mes){
						if(item.dataHoraCriacao.date.month == mes){
							if(item.dataHoraCriacao.date.day < dia){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}
						}else{
							if(item.statusPedido === "ENTREGUE"){
								entregue++
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								cancelado++
							}
						}
					}
				}else{
					if(item.dataHoraAtualizacao.date.month <= mes){
						if(item.dataHoraAtualizacao.date.month == mes){
							if(item.dataHoraAtualizacao.date.day <= dia){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}
						}else{
							if(item.statusPedido === "ENTREGUE"){
								entregue++
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								cancelado++
							}
						}
					}
				}
				
			})
						
			var textodata = $("#txtDataFim").val().substr(8,2) + "/" + $("#txtDataFim").val().substr(5,2) + "/" + $("#txtDataFim").val().substr(0,4)
			var ctx = document.getElementById('myChart').getContext('2d');
			window.myLine = Chart.Line(ctx, {
				data: {
					labels: ['Início', textodata],
					datasets: [{
						label: 'Pedidos entregues',
						borderColor: "#51b286",
						backgroundColor: "#51b286",
						fill: false,
						data: [
							0,
							entregue
						],
							yAxisID: 'y-axis-1',
					}, {
						label: 'Pedidos cancelados + reprovados',
						borderColor: "#d55655",
						backgroundColor: "#d55655",
						fill: false,
						data: [
							0,
							cancelado
							],
							yAxisID: 'y-axis-1'
					}]
				},
				options: {
					responsive: true,
					hoverMode: 'index',
					stacked: false,
					title: {
						display: true,
						text: 'Gráfico - Situação de pedidos'
					},
					scales: {
						yAxes: [{
							type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display: true,
							position: 'left',
							id: 'y-axis-1',
						}],
					}
				}
			});
		}
	}else{
		if($("#txtDataFim").val() === ""){
			myLine.destroy()
			
			entregue = 0
			cancelado = 0
			
			inicioE = 0
			inicioA = 0
			
			
			
			var mes = parseInt($("#txtDataInicio").val().substr(5,2))
			var dia = parseInt($("#txtDataInicio").val().substr(8,2))
			
			itens.entidades.forEach(function(item){
				if(item.dataHoraCriacao.date.month === item.dataHoraAtualizacao.date.month){
					if(item.dataHoraCriacao.date.month <= mes){
						if(item.dataHoraCriacao.date.month == mes){
							if(item.dataHoraCriacao.date.day <= dia){
								if(item.statusPedido === "ENTREGUE"){
									inicioE++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									inicioA++
								}
							}else{
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}
						}else{
							if(item.statusPedido === "ENTREGUE"){
								inicioE++
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								inicioA++
							}
						}
					}else{
						if(item.statusPedido === "ENTREGUE"){
							entregue++
						}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
							cancelado++
						}
					}
				}else{
					if(item.dataHoraAtualizacao.date.month <= mes){
						if(item.dataHoraAtualizacao.date.month == mes){
							if(item.dataHoraAtualizacao.date.day <= dia){
								if(item.statusPedido === "ENTREGUE"){
									inicioE++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									inicioA++
								}
							}else{
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}
						}else{
							if(item.statusPedido === "ENTREGUE"){
								inicioE++
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								inicioA++
							}
						}
					}else{
						if(item.statusPedido === "ENTREGUE"){
							entregue++
						}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
							cancelado++
						}
					}
				}
				
			})
						
			var textodata = $("#txtDataInicio").val().substr(8,2) + "/" + $("#txtDataInicio").val().substr(5,2) + "/" + $("#txtDataInicio").val().substr(0,4)
			var ctx = document.getElementById('myChart').getContext('2d');
			window.myLine = Chart.Line(ctx, {
				data: {
					labels: [textodata, 'Fim'],
					datasets: [{
						label: 'Pedidos entregues',
						borderColor: "#51b286",
						backgroundColor: "#51b286",
						fill: false,
						data: [
							inicioE,
							entregue
						],
							yAxisID: 'y-axis-1',
					}, {
						label: 'Pedidos cancelados + reprovados',
						borderColor: "#d55655",
						backgroundColor: "#d55655",
						fill: false,
						data: [
							inicioA,
							cancelado
							],
							yAxisID: 'y-axis-1'
					}]
				},
				options: {
					responsive: true,
					hoverMode: 'index',
					stacked: false,
					title: {
						display: true,
						text: 'Gráfico - Situação de pedidos'
					},
					scales: {
						yAxes: [{
							type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display: true,
							position: 'left',
							id: 'y-axis-1',
						}],
					}
				}
			});
		
		}else{
			myLine.destroy()
			
			entregue = 0
			cancelado = 0
			
			inicioE = 0
			inicioA = 0
			
			var mesInicio = parseInt($("#txtDataInicio").val().substr(5,2))
			var diaInicio = parseInt($("#txtDataInicio").val().substr(8,2))
			
			var mesFim = parseInt($("#txtDataFim").val().substr(5,2))
			var diaFim = parseInt($("#txtDataFim").val().substr(8,2))
			
			itens.entidades.forEach(function(item){
				if(item.dataHoraCriacao.date.month === item.dataHoraAtualizacao.date.month){ // mesmo mes?
					if(item.dataHoraCriacao.date.month <= mesFim && item.dataHoraCriacao.date.month >= mesInicio){ // está entre o range
						if(item.dataHoraCriacao.date.month == mesInicio){
							if(item.dataHoraCriacao.date.day >= diaInicio){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}else{
								if(item.statusPedido === "ENTREGUE"){
									inicioE++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									inicioA++
								}
							}
						}else if(item.dataHoraCriacao.date.month == mesFim){
							if(item.dataHoraCriacao.date.day <= diaFim){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}
						}else{
							if(item.statusPedido === "ENTREGUE"){
								entregue++
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								cancelado++
							}
						}
					}else if(item.dataHoraCriacao.date.month < mesInicio){
						if(item.statusPedido === "ENTREGUE"){
							inicioE++
						}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
							inicioA++
						}
					}
				}else{
					if(item.dataHoraAtualizacao.date.month <= mesFim && item.dataHoraAtualizacao.date.month >= mesInicio){ // está entre o range
						if(item.dataHoraAtualizacao.date.month == mesInicio){
							if(item.dataHoraAtualizacao.date.day >= diaInicio){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}else{
								if(item.statusPedido === "ENTREGUE"){
									inicioE++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									inicioA++
								}
							}
						}else if(item.dataHoraAtualizacao.date.month == mesFim){
							if(item.dataHoraAtualizacao.date.day <= diaFim){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}
						}else{
							if(item.statusPedido === "ENTREGUE"){
								entregue++
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								cancelado++
							}
						}
					}else if(item.dataHoraAtualizacao.date.month < mesInicio){
						if(item.statusPedido === "ENTREGUE"){
							inicioE++
						}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
							inicioA++
						}
					}
				}
				
			})
			
			var textodata = $("#txtDataInicio").val().substr(8,2) + "/" + $("#txtDataInicio").val().substr(5,2) + "/" + $("#txtDataInicio").val().substr(0,4)
			var textodataFim = $("#txtDataFim").val().substr(8,2) + "/" + $("#txtDataFim").val().substr(5,2) + "/" + $("#txtDataFim").val().substr(0,4)

			var ctx = document.getElementById('myChart').getContext('2d');
			window.myLine = Chart.Line(ctx, {
				data: {
					labels: [textodata, textodataFim],
					datasets: [{
						label: 'Pedidos entregues',
						borderColor: "#51b286",
						backgroundColor: "#51b286",
						fill: false,
						data: [
							inicioE,
							entregue
						],
							yAxisID: 'y-axis-1',
					}, {
						label: 'Pedidos cancelados + reprovados',
						borderColor: "#d55655",
						backgroundColor: "#d55655",
						fill: false,
						data: [
							inicioA,
							cancelado
							],
							yAxisID: 'y-axis-1'
					}]
				},
				options: {
					responsive: true,
					hoverMode: 'index',
					stacked: false,
					title: {
						display: true,
						text: 'Gráfico - Situação de pedidos'
					},
					scales: {
						yAxes: [{
							type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display: true,
							position: 'left',
							id: 'y-axis-1',
						}],
					}
				}
			});
		}
	}
})


$("#txtDataFim").change(function(){
	if($("#txtDataFim").val() === ""){
		if($("#txtDataInicio").val() === ""){
			// tudo
			myLine.destroy()
			
			janeiroE = 0
			fevereiroE = 0
			marcoE = 0
			abrilE = 0
			maioE = 0
			junhoE = 0
			
			janeiroA = 0
			fevereiroA = 0
			marcoA = 0
			abrilA = 0
			maioA = 0
			junhoA = 0
			
			itens.entidades.forEach(function(item){
				if(item.statusPedido === "ENTREGUE"){
					if(item.dataHoraCriacao.date.month === item.dataHoraAtualizacao.date.month){
						switch(item.dataHoraCriacao.date.month){
						case 1: 
							janeiroE++
							break;
						case 2: 
							fevereiroE++
							break;
						case 3: 
							marcoE++
							break;
						case 4:
							abrilE++
							break;
						case 5:
							maioE++
							break;
						case 6: 
							junhoE++
							break;
						default:
							break;
						}
					}else{
						switch(item.dataHoraAtualizacao.date.month){
						case 1: 
							janeiroE++
							break;
						case 2: 
							fevereiroE++
							break;
						case 3: 
							marcoE++
							break;
						case 4:
							abrilE++
							break;
						case 5:
							maioE++
							break;
						case 6: 
							junhoE++
							break;
						default:
							break;
						}
					}
				}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
					if(item.dataHoraCriacao.date.month === item.dataHoraAtualizacao.date.month){
						switch(item.dataHoraCriacao.date.month){
						case 1: 
							janeiroA++
							break;
						case 2: 
							fevereiroA++
							break;
						case 3: 
							marcoA++
							break;
						case 4:
							abrilA++
							break;
						case 5:
							maioA++
							break;
						case 6: 
							junhoA++
							break;
						default:
							break;
						}
					}else{
						switch(item.dataHoraAtualizacao.date.month){
						case 1: 
							janeiroA++
							break;
						case 2: 
							fevereiroA++
							break;
						case 3: 
							marcoA++
							break;
						case 4:
							abrilA++
							break;
						case 5:
							maioA++
							break;
						case 6: 
							junhoA++
							break;
						default:
							break;
						}
					}
				}

			})
						
			var ctx = document.getElementById('myChart').getContext('2d');
			window.myLine = Chart.Line(ctx, {
				data: {
					labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho'],
					datasets: [{
						label: 'Pedidos entregues',
						borderColor: "#51b286",
						backgroundColor: "#51b286",
						fill: false,
						data: [
							janeiroE,
							fevereiroE,
							marcoE,
							abrilE,
							maioE,
							junhoE
							],
							yAxisID: 'y-axis-1',
					}, {
						label: 'Pedidos cancelados + reprovados',
						borderColor: "#d55655",
						backgroundColor: "#d55655",
						fill: false,
						data: [
							janeiroA,
							fevereiroA,
							marcoA,
							abrilA,
							maioA,
							junhoA
							],
							yAxisID: 'y-axis-1'
					}]
				},
				options: {
					responsive: true,
					hoverMode: 'index',
					stacked: false,
					title: {
						display: true,
						text: 'Gráfico - Situação de pedidos'
					},
					scales: {
						yAxes: [{
							type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display: true,
							position: 'left',
							id: 'y-axis-1',
						}],
					}
				}
			});

		}else{
			// desde a inicio até o fim do registro
			myLine.destroy()
			
			entregue = 0
			cancelado = 0
			
			inicioE = 0
			inicioA = 0
			
			var mes = parseInt($("#txtDataInicio").val().substr(5,2))
			var dia = parseInt($("#txtDataInicio").val().substr(8,2))
			
			itens.entidades.forEach(function(item){
				
				if(item.dataHoraCriacao.date.month === item.dataHoraAtualizacao.date.month){
					if(item.dataHoraCriacao.date.month >= mes){
						if(item.dataHoraCriacao.date.month == mes){
							if(item.dataHoraCriacao.date.day >= dia){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}else{
								if(item.statusPedido === "ENTREGUE"){
									inicioE++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									inicioA++
								}
							}
						}else{
							if(item.statusPedido === "ENTREGUE"){
								entregue++
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								cancelado++
							}
						}
					}else{
						if(item.statusPedido === "ENTREGUE"){
							inicioE++
						}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
							inicioA++
						}
					}
				}else{
					if(item.dataHoraAtualizacao.date.month >= mes){
						if(item.dataHoraAtualicao.date.month == mes){
							if(item.dataHoraAtualizao.date.day >= dia){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}else{
								if(item.statusPedido === "ENTREGUE"){
									inicioE++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									inicioA++
								}
							}
						}else{
							if(item.statusPedido === "ENTREGUE"){
								entregue++
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								cancelado++
							}
						}
					}else{
						if(item.statusPedido === "ENTREGUE"){
							inicioE++
						}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
							inicioA++
						}
					}
					
				}
				
			})
						
			var textodata = $("#txtDataInicio").val().substr(8,2) + "/" + $("#txtDataInicio").val().substr(5,2) + "/" + $("#txtDataInicio").val().substr(0,4)
			var ctx = document.getElementById('myChart').getContext('2d');
			window.myLine = Chart.Line(ctx, {
				data: {
					labels: [textodata, 'Fim'],
					datasets: [{
						label: 'Pedidos entregues',
						borderColor: "#51b286",
						backgroundColor: "#51b286",
						fill: false,
						data: [
							inicioE,
							entregue
						],
							yAxisID: 'y-axis-1',
					}, {
						label: 'Pedidos cancelados + reprovados',
						borderColor: "#d55655",
						backgroundColor: "#d55655",
						fill: false,
						data: [
							inicioA,
							cancelado
							],
							yAxisID: 'y-axis-1'
					}]
				},
				options: {
					responsive: true,
					hoverMode: 'index',
					stacked: false,
					title: {
						display: true,
						text: 'Gráfico - Situação de pedidos'
					},
					scales: {
						yAxes: [{
							type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display: true,
							position: 'left',
							id: 'y-axis-1',
						}],
					}
				}
			});
		}
	}else{
		if($("#txtDataInicio").val() === ""){
			myLine.destroy()
			
			entregue = 0
			cancelado = 0
			
			inicioE = 0
			inicioA = 0
			
			var mes = parseInt($("#txtDataFim").val().substr(5,2))
			var dia = parseInt($("#txtDataFim").val().substr(8,2))
			
			itens.entidades.forEach(function(item){
				if(item.dataHoraCriacao.date.month === item.dataHoraAtualizacao.date.month){
					if(item.dataHoraCriacao.date.month <= mes){
						if(item.dataHoraCriacao.date.month == mes){
							if(item.dataHoraCriacao.date.day <= dia){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}
						}else{
							if(item.statusPedido === "ENTREGUE"){
								entregue++
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								cancelado++
							}
						}
					}
				}else{
					if(item.dataHoraAtualizacao.date.month <= mes){
						if(item.dataHoraAtualizacao.date.month == mes){
							if(item.dataHoraAtualizacao.date.day <= dia){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}
						}else{
							if(item.statusPedido === "ENTREGUE"){
								entregue++
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								cancelado++
							}
						}
					}else{
						if(item.statusPedido === "ENTREGUE"){
							entregue++
						}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
							cancelado++
						}
					}
				}
				
			})
						
			var textodata = $("#txtDataFim").val().substr(8,2) + "/" + $("#txtDataFim").val().substr(5,2) + "/" + $("#txtDataFim").val().substr(0,4)
			var ctx = document.getElementById('myChart').getContext('2d');
			window.myLine = Chart.Line(ctx, {
				data: {
					labels: ['Início', textodata],
					datasets: [{
						label: 'Pedidos entregues',
						borderColor: "#51b286",
						backgroundColor: "#51b286",
						fill: false,
						data: [
							0,
							entregue
						],
							yAxisID: 'y-axis-1',
					}, {
						label: 'Pedidos cancelados + reprovados',
						borderColor: "#d55655",
						backgroundColor: "#d55655",
						fill: false,
						data: [
							0,
							cancelado
							],
							yAxisID: 'y-axis-1'
					}]
				},
				options: {
					responsive: true,
					hoverMode: 'index',
					stacked: false,
					title: {
						display: true,
						text: 'Gráfico - Situação de pedidos'
					},
					scales: {
						yAxes: [{
							type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display: true,
							position: 'left',
							id: 'y-axis-1',
						}],
					}
				}
			});
		
		}else{
			myLine.destroy()
			
			entregue = 0
			cancelado = 0
			
			inicioE = 0
			inicioA = 0
			
			var mesInicio = parseInt($("#txtDataInicio").val().substr(5,2))
			var diaInicio = parseInt($("#txtDataInicio").val().substr(8,2))
			
			var mesFim = parseInt($("#txtDataFim").val().substr(5,2))
			var diaFim = parseInt($("#txtDataFim").val().substr(8,2))
			
			itens.entidades.forEach(function(item){
				if(item.dataHoraCriacao.date.month === item.dataHoraAtualizacao.date.month){ // mesmo mes?
					if(item.dataHoraCriacao.date.month <= mesFim && item.dataHoraCriacao.date.month >= mesInicio){ // está entre o range
						if(item.dataHoraCriacao.date.month == mesInicio){
							if(item.dataHoraCriacao.date.day >= diaInicio){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}else{
								if(item.statusPedido === "ENTREGUE"){
									inicioE++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									inicioA++
								}
							}
						}else if(item.dataHoraCriacao.date.month == mesFim){
							if(item.dataHoraCriacao.date.day <= diaFim){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}
						}else{
							if(item.statusPedido === "ENTREGUE"){
								entregue++
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								cancelado++
							}
						}
					}else if(item.dataHoraCriacao.date.month < mesInicio){
						if(item.statusPedido === "ENTREGUE"){
							inicioE++
						}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
							inicioA++
						}
					}
				}else{
					if(item.dataHoraAtualizacao.date.month <= mesFim && item.dataHoraAtualizacao.date.month >= mesInicio){ // está entre o range
						if(item.dataHoraAtualizacao.date.month == mesInicio){
							if(item.dataHoraAtualizacao.date.day >= diaInicio){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}else{
								if(item.statusPedido === "ENTREGUE"){
									inicioE++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									inicioA++
								}
							}
						}else if(item.dataHoraAtualizacao.date.month == mesFim){
							if(item.dataHoraAtualizacao.date.day <= diaFim){
								if(item.statusPedido === "ENTREGUE"){
									entregue++
								}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
									cancelado++
								}
							}
						}else{
							if(item.statusPedido === "ENTREGUE"){
								entregue++
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								cancelado++
							}
						}
					}else if(item.dataHoraAtualizacao.date.month < mesInicio){
						if(item.statusPedido === "ENTREGUE"){
							inicioE++
						}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
							inicioA++
						}
					}
				}
				
			})
			
			var textodata = $("#txtDataInicio").val().substr(8,2) + "/" + $("#txtDataInicio").val().substr(5,2) + "/" + $("#txtDataInicio").val().substr(0,4)
			var textodataFim = $("#txtDataFim").val().substr(8,2) + "/" + $("#txtDataFim").val().substr(5,2) + "/" + $("#txtDataFim").val().substr(0,4)

			var ctx = document.getElementById('myChart').getContext('2d');
			window.myLine = Chart.Line(ctx, {
				data: {
					labels: [textodata, textodataFim],
					datasets: [{
						label: 'Pedidos entregues',
						borderColor: "#51b286",
						backgroundColor: "#51b286",
						fill: false,
						data: [
							inicioE,
							entregue
						],
							yAxisID: 'y-axis-1',
					}, {
						label: 'Pedidos cancelados + reprovados',
						borderColor: "#d55655",
						backgroundColor: "#d55655",
						fill: false,
						data: [
							inicioA,
							cancelado
							],
							yAxisID: 'y-axis-1'
					}]
				},
				options: {
					responsive: true,
					hoverMode: 'index',
					stacked: false,
					title: {
						display: true,
						text: 'Gráfico - Situação de pedidos'
					},
					scales: {
						yAxes: [{
							type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
							display: true,
							position: 'left',
							id: 'y-axis-1',
						}],
					}
				}
			});
		}
	}
})




