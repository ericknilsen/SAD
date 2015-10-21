app.controller('AlunoController', function($scope, $http) {

	$scope.aluno = {};	
	$scope.listaAlunos = [];

	$scope.listaTurmas = [];
	

	$http.get('http://localhost:8080/SAD/rest/turmas').success(
			function(dados) {				
				if(Array.isArray(dados.turmaVO))				
					$scope.listaTurmas = dados.turmaVO;			
				else
					$scope.listaTurmas.push(dados.turmaVO);	
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

		var alunoSelecionado = null;
						
		for (var i = 0; i < $scope.listaAlunos.length; i++) {			
			if ($scope.listaAlunos[i].id == aluno.id) {
				alunoSelecionado = aluno;
			};
		};	
			
		$http.put('http://localhost:8080/SAD/rest/alunos', alunoSelecionado).success(
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
				if(Array.isArray(dados.alunoVO))				
					$scope.listaAlunos = dados.alunoVO;			
				else
					$scope.listaAlunos.push(dados.alunoVO);				
			}
		);
		
	};


	$scope.buscarAlunosPorTurma = function() {

		$scope.listaAlunos = [];
		$http.get('http://localhost:8080/SAD/rest/alunos/t/'+$scope.aluno.idTurma).success(
			function(dados) {					
				if(dados != null) {		
					if(Array.isArray(dados.alunoVO))				
						$scope.listaAlunos = dados.alunoVO;				
					else
						$scope.listaAlunos.push(dados.alunoVO);												
				}
			}
		);	
	};

});
