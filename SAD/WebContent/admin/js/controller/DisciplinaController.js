app.controller('DisciplinaController', function($scope, $http) {

	$scope.disciplina = {};	
	$scope.listaDisciplinas = [];

	$http.get('http://localhost:8080/SAD/rest/disciplinas').success(
			function(dados) {				
				if(Array.isArray(dados))				
					$scope.listaDisciplinas = dados;			
				else
					$scope.listaDisciplinas.push(dados);
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

		var disciplinaSelecionada = null;
				
		for (var i = 0; i < $scope.listaDisciplinas.length; i++) {			
			if ($scope.listaDisciplinas[i].id == disciplina.id) {
				disciplinaSelecionada = disciplina;
			};
		};	
	
		$http.put('http://localhost:8080/SAD/rest/disciplinas', disciplinaSelecionada).success(
			function(dados) {									
				alert(dados);	
				$scope.listarDisciplinas();
			}
		);    	
	};


	$scope.listarDisciplinas = function() {

		$http.get('http://localhost:8080/SAD/rest/disciplinas').success(
			function(dados) {				
				if(Array.isArray(dados.disciplinaVO))				
					$scope.listaDisciplinas = dados.disciplinaVO;			
				else
					$scope.listaDisciplinas.push(dados.disciplinaVO);

				
			}
		);
		
	};

});
