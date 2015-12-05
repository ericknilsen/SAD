app.controller('AlunoController', function($scope, $http) {

	$scope.aluno = {};	
	$scope.listaAlunos = [];

	$scope.listaTurmas = [];
	

	$http.get('http://localhost:8080/SAD/rest/turmas').success(
			function(dados) {				
				$scope.listaTurmas = dados;					
		}
	);



	$scope.adicionarAluno = function() {		
		
		$http.post('http://localhost:8080/SAD/rest/alunos', $scope.aluno).success(
			function(dados) {    	
				alert(dados);								
				$scope.aluno = {};
  			}
  		);

	};

	$scope.removerAluno = function(aluno) {		
		
		var r = confirm("Excluir aluno?");
		if (r == true) {
			$http.delete('http://localhost:8080/SAD/rest/alunos/'+aluno.id).success(
				function(dados) {									
					$scope.buscarAlunosPorTurma();
				}
			);    		
		} 	

	};

	$scope.alterarAluno = function(aluno) {
					
		$http.put('http://localhost:8080/SAD/rest/alunos', aluno).success(
			function(dados) {									
				alert(dados);	
				$scope.buscarAlunosPorTurma();
			}
		);    	
	};


	$scope.listarAlunos = function() {

		$scope.listaAlunos = [];
		$http.get('http://localhost:8080/SAD/rest/alunos').success(
			function(dados) {				
				$scope.listaAlunos = dados;
			}
		);
		
	};


	$scope.buscarAlunosPorTurma = function() {

		$scope.listaAlunos = [];
		$http.get('http://localhost:8080/SAD/rest/alunos/t/'+$scope.aluno.idTurma).success(
			function(dados) {					
				$scope.listaAlunos = dados;
			}
		);	
	};

});
