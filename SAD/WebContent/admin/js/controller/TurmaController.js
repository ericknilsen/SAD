app.controller('TurmaController', function($scope, $http) {

	$scope.turma = {};	
	$scope.turma.listaTurmas = [];
	$scope.listaDisciplinas = [];
	$scope.listaSemestres = [
							 {semestre:"2015.2"},
							 {semestre:"2016.1"},
							 {semestre:"2016.2"}
							];
	

	$http.get('http://localhost:8080/SAD/rest/disciplinas').success(
			function(dados) {				
				if(Array.isArray(dados.disciplinaVO))				
					$scope.listaDisciplinas = dados.disciplinaVO;			
				else
					$scope.listaDisciplinas.push(dados.disciplinaVO);
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

		var turmaSelecionada = null;
						
		for (var i = 0; i < $scope.listaTurmas.length; i++) {			
			if ($scope.listaTurmas[i].id == turma.id) {
				turmaSelecionada = turma;
			};
		};	
			
		$http.put('http://localhost:8080/SAD/rest/turmas', turmaSelecionada).success(
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
				if(Array.isArray(dados.turmaVO))				
					$scope.listaTurmas = dados.turmaVO;			
				else
					$scope.listaTurmas.push(dados.turmaVO);				
			}
		);
		
	};


	$scope.buscarTurmasPorSemestreDisciplina = function() {

		$scope.listaTurmas = [];		
		$http.post('http://localhost:8080/SAD/rest/turmas/t/', $scope.turma).success(
			function(dados) {					
				if(dados != null) {		
					if(Array.isArray(dados.turmaVO))				
						$scope.listaTurmas = dados.turmaVO;				
					else
						$scope.listaTurmas.push(dados.turmaVO);												
				}
			}
		);	
	};

});
