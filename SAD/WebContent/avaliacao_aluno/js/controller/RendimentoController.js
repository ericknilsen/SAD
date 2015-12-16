app.controller('RendimentoController', function($scope, $http, $cookies,
		$rootScope, $sce) {

	var options = {
		responsive : true
	};

	var data = {
		labels : [],
		datasets : [ {
			label : "Meu rendimento",
			fillColor : "rgba(151,187,205,0.2)",
			strokeColor : "rgba(151,187,205,1)",
			pointColor : "rgba(151,187,205,1)",
			pointStrokeColor : "#fff",
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(151,187,205,1)",
			data : []
		}, {
			label : "Rendimento médio da turma",
			fillColor : "rgba(220,220,220,0.2)",
			strokeColor : "rgba(220,220,220,1)",
			pointColor : "rgba(220,220,220,1)",
			pointStrokeColor : "#fff",
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(220,220,220,1)",
			data : []
		} ]
	};

	$scope.aluno = {};	

	$scope.listarAvaliacoesPorTurmaAluno = function() {

		$http.get(
				'http://' + $rootScope.ip + ':8080/SAD/rest/alunos/'
						+ $cookies.get('idAluno')).success(function(dados) {
			$scope.aluno = dados;
			$scope.listarAvaliacoesPorTurma();
		});

	};

	$scope.listarAvaliacoesPorTurma = function() {

		$http.get(
				'http://' + $rootScope.ip + ':8080/SAD/rest/avaliacoes/t/'
						+ $scope.aluno.idTurma + '/' + $scope.aluno.id)
				.success(function(dados) {
					$scope.listaAvaliacoes = dados;
					$scope.gerarGraficoRendimento();
				});

	};

	$scope.gerarGraficoRendimento = function() {

		var rendimentoAluno = [];
		var avaliacoes = [];
		for (var i = 0; i < $scope.listaAvaliacoes.length; i++) {		
			 rendimentoAluno.push(parseFloat($scope.listaAvaliacoes[i].rendimento));
			 avaliacoes.push($scope.listaAvaliacoes[i].dataCriacao);
			 
		}

		data.datasets[0].data = rendimentoAluno;
		data.labels = avaliacoes;
		
		var ctx = document.getElementById("grafico").getContext("2d");
		var chart = new Chart(ctx);
		var lineChart = chart.Line(data, options);

		document.getElementById("legenda").innerHTML = lineChart.generateLegend();
	}

	$scope.listarAvaliacoesPorTurmaAluno();

	

});
