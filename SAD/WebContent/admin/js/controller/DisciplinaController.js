app.controller('DisciplinaController', function($scope, $http) {

	$scope.disciplina = {};	
	$scope.listaDisciplinas = [];

	$http.get('http://localhost:8080/SAD/rest/disciplinas').success(
		function(dados) {				
			$scope.listaDisciplinas = dados;			
		}
	);

	$scope.adicionarDisciplina = function() {		
		$http.post('http://localhost:8080/SAD/rest/disciplinas', $scope.disciplina).success(
			function(dados) {    				
				$scope.listarDisciplinas();
				$scope.disciplina = {};			
  			}
  		);

	};

	$scope.removerDisciplina = function(disciplina) {		
		
		var r = confirm("Excluir disciplina?");
		if (r == true) {
			$http.delete('http://localhost:8080/SAD/rest/disciplinas/'+disciplina.id).success(
				function(dados) {									
					$scope.listarDisciplinas();
				}
			);    		
		} 	

	};

	$scope.alterarDisciplina = function(disciplina) {
			
		$http.put('http://localhost:8080/SAD/rest/disciplinas', disciplina).success(
			function(dados) {									
				alert(dados);	
				$scope.listarDisciplinas();
			}
		);    	
	};


	$scope.listarDisciplinas = function() {

		$http.get('http://localhost:8080/SAD/rest/disciplinas').success(
			function(dados) {							
				$scope.listaDisciplinas = dados;
			}
		);
		
	};

});
