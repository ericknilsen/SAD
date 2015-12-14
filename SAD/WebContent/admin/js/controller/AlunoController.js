app.controller('AlunoController', function($scope, $http) {

	$scope.files = {};
	$scope.aluno = {};	
	$scope.listaAlunos = [];

	$scope.listaTurmas = [];
	
	$scope.listaAlunos.push({});
	

	$http.get('http://localhost:8080/SAD/rest/turmas').success(
			function(dados) {				
				$scope.listaTurmas = dados;					
		}
	);

	$scope.gravarAlunos = function() {		
		
		$http.post('http://localhost:8080/SAD/rest/alunos', $scope.listaAlunos).success(
			function(dados) {    	
				alert(dados);								
				$scope.listaAlunos = [];
  			}
  		);

	};	
	
	$scope.uploadFile = function(event) {	
		
		var files = event.target.files;		
		
	    var fd = new FormData();	    
	 
	    fd.append("file", files[0]);
	    fd.append("turma", $scope.aluno.idTurma);
	    	  	    
        $http.post('http://localhost:8080/SAD/rest/alunos/f', fd, {	    
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity}).success(
	        	function(dados) {    	
	        		$scope.listaAlunos = dados;
	        	}
	        );
	};
	
	$scope.adicionarAluno = function() {	
		
		$scope.listaAlunos.push({"idTurma":$scope.aluno.idTurma});
	};	
	
	$scope.excluirAluno = function(aluno) {	
		
		remove($scope.listaAlunos,aluno);
	};	
	
	function remove(arr, item) {
	    for(var i = arr.length; i--;) {
	        if(arr[i] === item) {
	            arr.splice(i, 1);
	        }
	    }
	}	

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
	
	
	$scope.listarAvaliacoesPorAluno = function() {
		
		$scope.listaAlunos = [];
		$http.get('http://localhost:8080/SAD/rest/alunos/t/'+$scope.aluno.idTurma).success(
			function(dados) {					
				$scope.listaAlunos = dados;	
				for (var i = 0; i < $scope.listaAlunos.length; i++) {
					$scope.listarAvaliacoes(i)				
				}	
			}
		);	
		
		
	};
	
	$scope.listarAvaliacoes = function(i) {	
			
		$http.get('http://localhost:8080/SAD/rest/avaliacoes/t/'+$scope.aluno.idTurma+'/'+$scope.listaAlunos[i].id).success(
				function(dados) {				
					$scope.listaAlunos[i].listaAvaliacoes = dados;								
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
