$(document).ready(function(){
	let inicio = ""
	let fim = ""
	
	$.ajax({
		url: `http://localhost:8085/schoolstore/app?tarefa=relatorio&txtDataInicio=${inicio}&txtDataFim=${fim}`,
		type: "GET",
		async: false,
		success: response => {

			var map = new Map()
			var meses = new Map()
			
			meses.set(1, "Janeiro")
			meses.set(2, "Fevereiro")
			meses.set(3, "Março")
			meses.set(4, "Abril")
			meses.set(5, "Maio")
			meses.set(6, "Junho")
			meses.set(7, "Julho")
			meses.set(8, "Agosto")
			meses.set(9, "Setembro")
			meses.set(10, "Outubro")
			meses.set(11, "Novembro")
			meses.set(12, "Dezembro")
			
			response.entidades.forEach(function(data){
				if(map.get(data.dataHoraAtualizacao.date.month) === undefined){
					map.set(data.dataHoraAtualizacao.date.month, new Array())
					map.get(data.dataHoraAtualizacao.date.month).push(data)
				}else{
					map.get(data.dataHoraAtualizacao.date.month).push(data)
				}		

			})
			
			var arrayMap = new Array()
			var arrayMeses = new Array()
			for(let i = 1 ; i <= map.size ; i++){
				var mapMes = new Map()
				var mapValor = new Map()
				var countEntregue = 0
				var countReprovado = 0
				
				mapMes.set(i, mapValor)
				
				map.get(i).forEach(function(item){
					if(item.statusPedido === "ENTREGUE"){
						mapMes.get(i).set("Entregue", ++countEntregue)				
					}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
						mapMes.get(i).set("Reprovado", ++countReprovado)	
					}
				})
				
				arrayMeses.push(meses.get(i))
				arrayMap.push(mapMes)
			}
			
			var arrayValoresEntregues = new Array()
			var arrayValoresReprovados = new Array()
			for(let i = 0 ; i < arrayMap.length ; i++){
				arrayValoresEntregues.push(arrayMap[i].get(i+1).get("Entregue"))
				arrayValoresReprovados.push(arrayMap[i].get(i+1).get("Reprovado"))
			}
			
			var ctx = document.getElementById('myChart').getContext('2d');
			window.myLine = Chart.Line(ctx, {
				data: {
					labels: arrayMeses,
					datasets: [{
						label: 'Pedidos entregues',
						borderColor: "#51b286",
						backgroundColor: "#51b286",
						fill: false,
						data: arrayValoresEntregues,
						yAxisID: 'y-axis-1',
					}, {
						label: 'Pedidos cancelados + reprovados',
						borderColor: "#d55655",
						backgroundColor: "#d55655",
						fill: false,
						data: arrayValoresReprovados,
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
	})
})

class Relatorio {
	constructor(qtddInicial, qtddFinal){
		this.qtddInicial = qtddInicial
		this.qtddFinal = qtddFinal
	}
	
	getQtddInicialSituacao(){
		var entregue = 0
		var cancelado = 0
		this.qtddInicial.entidades.forEach(function(data){
			if(data.statusPedido === "ENTREGUE"){
				entregue++
			}else if(data.statusPedido === "REPROVADO" || (data.statusPedido === "ATUALIZADO" && data.ativo === false)){
				cancelado++
			}
		})
		var array = []
		array.push(entregue)
		array.push(cancelado)
		
		return array
	}
	
	getQtddFinalSituacao(){
		var entregue = 0
		var cancelado = 0
		this.qtddFinal.entidades.forEach(function(data){
			if(data.statusPedido === "ENTREGUE"){
				entregue++
			}else if(data.statusPedido === "REPROVADO" || (data.statusPedido === "ATUALIZADO" && data.ativo === false)){
				cancelado++
			}
		})
		var array = []
		array.push(entregue)
		array.push(cancelado)
		
		return array
	}

}




$(".buscarRelatorio").click(function(){
	let inicio = $("#txtDataInicio").val()
	let fim = $("#txtDataFim").val()
	
	var qtddFinal = [];
	var qtddInicial = [];
	
	if(window.myLine !== undefined)
		window.myLine.destroy()
	
	$.ajax({
		url: `http://localhost:8085/schoolstore/app?tarefa=relatorio&txtDataInicio=${inicio}&txtDataFim=${fim}`,
		type: "GET",
		async: false,
		success: response => {
			qtddFinal = response
		}
	})
	
	if(fim !== "" && inicio !== ""){
		let final = ""
			
		$.ajax({
			url: `http://localhost:8085/schoolstore/app?tarefa=relatorio&txtDataInicio=${final}&txtDataFim=${inicio}`,
			type: "GET",
			async: false,
			success: response => {
				qtddInicial = response
			}
		})
		
		let rel = new Relatorio(qtddInicial, qtddFinal)
		let dadosInicial = rel.getQtddInicialSituacao()
		let dadosFinal = rel.getQtddFinalSituacao()
		
		
		var ctx = document.getElementById('myChart').getContext('2d');
		window.myLine = Chart.Line(ctx, {
			data: {
				labels: ['Começo', 'Final'],
				datasets: [{
					label: 'Pedidos entregues',
					borderColor: "#51b286",
					backgroundColor: "#51b286",
					fill: false,
					data: [
						dadosInicial[0],
						dadosFinal[0]
					],
					yAxisID: 'y-axis-1',
				}, {
					label: 'Pedidos cancelados + reprovados',
					borderColor: "#d55655",
					backgroundColor: "#d55655",
					fill: false,
					data: [
						dadosInicial[1],
						dadosFinal[1]
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
		
	}else if(fim !== ""){
		let rel = new Relatorio(qtddInicial, qtddFinal)
		let dadosInicial = 0
		let dadosFinal = rel.getQtddFinalSituacao()
		
		
		var ctx = document.getElementById('myChart').getContext('2d');
		window.myLine = Chart.Line(ctx, {
			data: {
				labels: ['Começo', 'Final'],
				datasets: [{
					label: 'Pedidos entregues',
					borderColor: "#51b286",
					backgroundColor: "#51b286",
					fill: false,
					data: [
						dadosInicial,
						dadosFinal[0]
					],
					yAxisID: 'y-axis-1',
				}, {
					label: 'Pedidos cancelados + reprovados',
					borderColor: "#d55655",
					backgroundColor: "#d55655",
					fill: false,
					data: [
						dadosInicial,
						dadosFinal[1]
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
	}else if(inicio !== ""){
			let final = ""
			
			$.ajax({
				url: `http://localhost:8085/schoolstore/app?tarefa=relatorio&txtDataInicio=${final}&txtDataFim=${inicio}`,
				type: "GET",
				async: false,
				success: response => {
					qtddInicial = response
				}
			})
			
			let rel = new Relatorio(qtddInicial, qtddFinal)
			let dadosInicial = rel.getQtddInicialSituacao()
			let dadosFinal = rel.getQtddFinalSituacao()
			
			
			var ctx = document.getElementById('myChart').getContext('2d');
			window.myLine = Chart.Line(ctx, {
				data: {
					labels: ['Começo', 'Final'],
					datasets: [{
						label: 'Pedidos entregues',
						borderColor: "#51b286",
						backgroundColor: "#51b286",
						fill: false,
						data: [
							dadosInicial[0],
							dadosFinal[0]
						],
						yAxisID: 'y-axis-1',
					}, {
						label: 'Pedidos cancelados + reprovados',
						borderColor: "#d55655",
						backgroundColor: "#d55655",
						fill: false,
						data: [
							dadosInicial[1],
							dadosFinal[1]
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
		let inicio = ""
			let fim = ""
			
			$.ajax({
				url: `http://localhost:8085/schoolstore/app?tarefa=relatorio&txtDataInicio=${inicio}&txtDataFim=${fim}`,
				type: "GET",
				async: false,
				success: response => {

					var map = new Map()
					var meses = new Map()
					
					meses.set(1, "Janeiro")
					meses.set(2, "Fevereiro")
					meses.set(3, "Março")
					meses.set(4, "Abril")
					meses.set(5, "Maio")
					meses.set(6, "Junho")
					meses.set(7, "Julho")
					meses.set(8, "Agosto")
					meses.set(9, "Setembro")
					meses.set(10, "Outubro")
					meses.set(11, "Novembro")
					meses.set(12, "Dezembro")
					
					response.entidades.forEach(function(data){
						if(map.get(data.dataHoraAtualizacao.date.month) === undefined){
							map.set(data.dataHoraAtualizacao.date.month, new Array())
							map.get(data.dataHoraAtualizacao.date.month).push(data)
						}else{
							map.get(data.dataHoraAtualizacao.date.month).push(data)
						}		

					})
					
					var arrayMap = new Array()
					var arrayMeses = new Array()
					for(let i = 1 ; i <= map.size ; i++){
						var mapMes = new Map()
						var mapValor = new Map()
						var countEntregue = 0
						var countReprovado = 0
						
						mapMes.set(i, mapValor)
						
						map.get(i).forEach(function(item){
							if(item.statusPedido === "ENTREGUE"){
								mapMes.get(i).set("Entregue", ++countEntregue)				
							}else if(item.statusPedido === "REPROVADO" || (item.statusPedido === "ATUALIZADO" && item.ativo === false)){
								mapMes.get(i).set("Reprovado", ++countReprovado)	
							}
						})
						
						arrayMeses.push(meses.get(i))
						arrayMap.push(mapMes)
					}
					
					var arrayValoresEntregues = new Array()
					var arrayValoresReprovados = new Array()
					for(let i = 0 ; i < arrayMap.length ; i++){
						arrayValoresEntregues.push(arrayMap[i].get(i+1).get("Entregue"))
						arrayValoresReprovados.push(arrayMap[i].get(i+1).get("Reprovado"))
					}
					
					var ctx = document.getElementById('myChart').getContext('2d');
					window.myLine = Chart.Line(ctx, {
						data: {
							labels: arrayMeses,
							datasets: [{
								label: 'Pedidos entregues',
								borderColor: "#51b286",
								backgroundColor: "#51b286",
								fill: false,
								data: arrayValoresEntregues,
								yAxisID: 'y-axis-1',
							}, {
								label: 'Pedidos cancelados + reprovados',
								borderColor: "#d55655",
								backgroundColor: "#d55655",
								fill: false,
								data: arrayValoresReprovados,
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
			})
	}
	


})