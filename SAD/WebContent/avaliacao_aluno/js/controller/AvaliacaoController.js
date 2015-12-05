app.controller('AvaliacaoController', function($scope, $http, $location, $cookies, $rootScope, $sce) {

	$scope.avaliacao = {};	
	$scope.avaliacao.listaQuestoes = [];
	$scope.listaAvaliacoes = [];	
	$scope.aluno = {};

	$scope.listaAlternativas = [];
	$scope.questao = {};
	
	$scope.alternativa = {};	

	$scope.avaliacaoRespondida = false;

	$scope.listaStatus = [
							 {codigo:"0",descricao:"Fechada"},
							 {codigo:"1",descricao:"Aberta"}
							];

	

	$scope.buscarAvaliacao = function() {
		$http.get('http://'+$rootScope.ip+':8080/SAD/rest/avaliacoes/'+$scope.avaliacao.id).success(
			function(dados) {		
							
				$scope.avaliacao.idAluno = $cookies.get('idAluno');				
				if($location.path() == "/corrige_avaliacao" && dados.status == "1") {
					window.location.href = "menu.html#/lista_avaliacao";					
				} else if($location.path() == "/aplica_avaliacao" && dados.status == "0") {
					window.location.href = "menu.html#/lista_avaliacao";					
				} else {
					$scope.avaliacao = dados;	
					$scope.avaliacao.idAluno = $cookies.get('idAluno');			
					$scope.buscarQuestoesPorAvaliacao();					
				}					
			}
		);	
	};	

	$scope.listarAvaliacoesPorTurmaAluno = function() {

		$http.get('http://'+$rootScope.ip+':8080/SAD/rest/alunos/'+$cookies.get('idAluno')).success(
			function(dados) {								
				$scope.aluno = dados;
				$scope.listarAvaliacoesPorTurma();
			}
		);		

	};
	
	$scope.listarAvaliacoesPorTurma = function() {		
				
		$http.get('http://'+$rootScope.ip+':8080/SAD/rest/avaliacoes/t/'+$scope.aluno.idTurma+'/'+$scope.aluno.id).success(
			function(dados) {				
				$scope.listaAvaliacoes = dados;			
			}
		);
		
	};	

	$scope.buscarQuestoesPorAvaliacao = function() {		
				
		$http.post('http://'+$rootScope.ip+':8080/SAD/rest/questoes/questoes_avaliacao/',$scope.avaliacao).success(
			function(dados) {					
				if(dados != null) {						
					lQuestoes = dados;					
					$scope.avaliacao.listaQuestoes = lQuestoes;
					
					if(lQuestoes[0].idAlternativaResposta != null)
						$scope.avaliacaoRespondida = true;

					for (var i = 0; i < $scope.avaliacao.listaQuestoes.length; i++) {						
						var enunciado = $scope.avaliacao.listaQuestoes[i].enunciado;
						enunciado = enunciado.replace(/\n/g, '<br />');
						$scope.avaliacao.listaQuestoes[i].enunciado = $sce.trustAsHtml(enunciado);						
					};
					
				}								
			}
		);
			
	};

	if($location.search().id) {			
		$scope.avaliacao.id = $location.search().id;
		$scope.buscarAvaliacao();			
	} else {
		$scope.listarAvaliacoesPorTurmaAluno();		
	}	


	$scope.responderAvaliacao = function() {
		
		var r = confirm("Finalizar avaliação?");
		if (r == true) {							
			$http.put('http://'+$rootScope.ip+':8080/SAD/rest/avaliacoes/resposta', $scope.avaliacao).success(
				function(dados) {														
					$scope.avaliacaoRespondida = true;				
				}
			);   
		}		
	};		

});
