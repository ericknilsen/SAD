app.controller('TurmaController', function($scope, $http) {

	$scope.turma = {};	
	
	$scope.listaDisciplinas = [];
	$scope.listaSemestres = [
							 {semestre:"2015.2"},
							 {semestre:"2016.1"},
							 {semestre:"2016.2"}
							];
	

	$http.get('http://localhost:8080/SAD/rest/disciplinas').success(
			function(dados) {								
				$scope.listaDisciplinas = dados;			
				
			}
	);



	$scope.adicionarTurma = function() {		
				
		$http.post('http://localhost:8080/SAD/rest/turmas', $scope.turma).success(
			function(dados) {    	
				alert(dados);	
  			}
  		);

	};

	$scope.removerTurma = function(turma) {		
		
		var r = confirm("Excluir turma?");
		if (r == true) {
			$http.delete('http://localhost:8080/SAD/rest/turmas/'+turma.id).success(
				function(dados) {									
					$scope.buscarTurmasPorSemestreDisciplina();
				}
			);    		
		} 	

	};

	$scope.alterarTurma = function(turma) {
			
		$http.put('http://localhost:8080/SAD/rest/turmas', turma).success(
			function(dados) {									
				alert(dados);	
				$scope.buscarTurmasPorSemestreDisciplina();
			}
		);    	
	};


	$scope.listarTurmas = function() {

		$scope.listaTurmas = [];
		$http.get('http://localhost:8080/SAD/rest/turmas').success(
			function(dados) {					
				$scope.listaTurmas = dados;								
			}
		);
		
	};


	$scope.buscarTurmasPorSemestreDisciplina = function() {

		$scope.listaTurmas = [];		
		$http.post('http://localhost:8080/SAD/rest/turmas/t/', $scope.turma).success(
			function(dados) {							
				$scope.listaTurmas = dados;				
			}
		);	
	};

});
