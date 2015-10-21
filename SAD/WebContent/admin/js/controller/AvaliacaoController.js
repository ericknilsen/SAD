app.controller('AvaliacaoController', function($scope, $http, $location) {

	$scope.avaliacao = {};	
	$scope.avaliacao.listaQuestoes = [];
	$scope.listaAvaliacoes = [];	
	$scope.listaTurmas = [];	

	$scope.listaAlternativas = [];
	$scope.questao = {};
	$scope.alternativa = {};	

	$scope.listaStatus = [
							 {codigo:"0",descricao:"Fechada"},
							 {codigo:"1",descricao:"Aberta"}
							];

	$http.get('http://localhost:8080/SAD/rest/turmas').success(
		function(dados) {				
			if(Array.isArray(dados.turmaVO))				
				$scope.listaTurmas = dados.turmaVO;			
			else
				$scope.listaTurmas.push(dados.turmaVO);	
		}
	);

	$scope.buscarQuestoesPorAvaliacao = function() {
				
		$http.get('http://localhost:8080/SAD/rest/questoes/a/'+$location.search().id).success(		
			function(dados) {					
				if(dados != null) {		
					if(Array.isArray(dados.questaoVO))				
						$scope.avaliacao.listaQuestoes = dados.questaoVO;				
					else
						$scope.avaliacao.listaQuestoes.push(dados.questaoVO);																	
				}								
			}
		);
			
	};

	if($location.search().id) {
		$scope.avaliacao.id = $location.search().id;
		$scope.buscarQuestoesPorAvaliacao();
	}

	$scope.buscarAssuntosPorTurma = function() {
		
		$scope.listaAssuntos = [];							
		
		$http.get('http://localhost:8080/SAD/rest/assuntos/t/'+$scope.avaliacao.idTurma).success(
			function(dados) {					
				if(dados != null) {		
					if(Array.isArray(dados.assuntoVO))				
						$scope.listaAssuntos = dados.assuntoVO;				
					else
						$scope.listaAssuntos.push(dados.assuntoVO);																	
				}								
			}
		);	
	};

	$scope.adicionarAvaliacao = function() {		

		$http.post('http://localhost:8080/SAD/rest/avaliacoes', $scope.avaliacao).success(
			function(dados) {    	
				alert(dados);								
  			}
  		);

	};

	$scope.removerAvaliacao = function(avaliacao) {		
		
		var r = confirm("Excluir avaliação?");
		if (r == true) {
			$http.delete('http://localhost:8080/SAD/rest/avaliacoes/'+avaliacao.id).success(
				function(dados) {									
					$scope.buscarAvaliacoesPorTurma();
				}
			);    		
		} 	

	};

	$scope.alterarAvaliacao = function(avaliacao) {
				
		$http.put('http://localhost:8080/SAD/rest/avaliacoes', avaliacao).success(
			function(dados) {									
				alert(dados);	
				$scope.buscarAvaliacoesPorTurma();
			}
		);    	
	};


	$scope.listarAvaliacoes = function() {

		$scope.listaAvaliacoes = [];
		$http.get('http://localhost:8080/SAD/rest/avaliacoes').success(
			function(dados) {				
				if(Array.isArray(dados.avaliacaoVO))				
					$scope.listaAvaliacoes = dados.avaliacaoVO;			
				else
					$scope.listaAvaliacoes.push(dados.avaliacaoVO);				
			}
		);
		
	};


	$scope.buscarAvaliacoesPorTurma = function() {

		$scope.listaAvaliacoes = [];
		$http.get('http://localhost:8080/SAD/rest/avaliacoes/t/'+$scope.avaliacao.idTurma).success(
			function(dados) {					
				if(dados != null) {		
					if(Array.isArray(dados.avaliacaoVO))				
						$scope.listaAvaliacoes = dados.avaliacaoVO;				
					else
						$scope.listaAvaliacoes.push(dados.avaliacaoVO);												
				}
			}
		);	
	};

	$scope.responderAvaliacao = function() {
				
		$http.put('http://localhost:8080/SAD/rest/avaliacoes/resposta', $scope.avaliacao).success(
			function(dados) {									
				alert(dados);					
			}
		);   
	};	


	$scope.buscarQuestoesPorAssuntos = function() {
				
		$scope.questao.listaAssuntos = [];		
		for (var i = 0; i < $scope.listaAssuntos.length; i++) {
			if($scope.listaAssuntos[i].checked) { 				
				$scope.questao.listaAssuntos.push($scope.listaAssuntos[i]);							
			}	
		};		

		if($scope.questao.listaAssuntos.length > 0) {

			$scope.avaliacao.listaQuestoes = [];
			$http.post('http://localhost:8080/SAD/rest/questoes/a', $scope.questao).success(
				function(dados) {				
					if(dados != null) {							
						if(Array.isArray(dados.questaoVO))				
							$scope.avaliacao.listaQuestoes = dados.questaoVO;											
						else
							$scope.avaliacao.listaQuestoes.push(dados.questaoVO);																		
					}			
  				}
  			);
  		}	
   		
	};		

});
