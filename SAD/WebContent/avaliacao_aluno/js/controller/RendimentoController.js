app.controller('RendimentoController', function($scope, $http, $cookies, $rootScope, $sce) {


	var options = {
        responsive:true
    };

    var data = {
        labels: ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
        datasets: [
        	{
                label: "Meu rendimento",
                fillColor: "rgba(151,187,205,0.2)",
                strokeColor: "rgba(151,187,205,1)",
                pointColor: "rgba(151,187,205,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(151,187,205,1)",
                data: [28, 48, 40, 19, 86, 27, 90, 200, 87, 20, 50, 20]
            },
            {
                label: "Rendimento da turma",                
                fillColor: "rgba(220,220,220,0.2)",
                strokeColor: "rgba(220,220,220,1)",
                pointColor: "rgba(220,220,220,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: [18, 28, 10, 59, 46, 57, 90, 200, 87, 20, 50, 20]
            }            
        ]
    };

	
    var ctx = document.getElementById("grafico").getContext("2d");
    var chart = new Chart(ctx);      
    var lineChart = chart.Line(data, options);  

    document.getElementById("legenda").innerHTML = lineChart.generateLegend();

    
	$scope.aluno = {};	

	$scope.gerarGraficoRendimento = function() {
		$http.get('http://'+$rootScope.ip+':8080/SAD/rest/avaliacoes/'+$scope.avaliacao.id).success(
			function(dados) {		
							
				$scope.aluno.id = $cookies.get('idAluno');							
			}
		);	
	};	
	

	

});
