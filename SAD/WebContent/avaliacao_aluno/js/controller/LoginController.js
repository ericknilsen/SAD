app.controller('LoginController', function($scope, $http, $cookies, $rootScope, $sce) {


	$scope.aluno = {};	
	

	$scope.verificarCredenciais = function() {	
		
		$http.get('http://'+$rootScope.ip+':8080/SAD/rest/alunos/m/'+$scope.aluno.matricula).success(
			function(dados) {					
				if(dados != null) {					
					$scope.aluno = dados.alunoVO;											
					$scope.buscaAvaliacaoAtualPorTurmaAluno();				
				} else {					
					alert('Aluno não cadastrado');						
				}
			}
		);	

	};
	

	$scope.buscaAvaliacaoAtualPorTurmaAluno = function() {

				
		$http.get('http://'+$rootScope.ip+':8080/SAD/rest/avaliacoes/turma_aluno/'+$scope.aluno.idTurma).success(
			function(dados) {					
				$cookies.put('idAluno', $scope.aluno.id);
				if(dados.id != null) {																				
					window.location.href = "menu.html#/aplica_avaliacao?id="+dados.id;					
				} else {	
					//para quando implementar rendimento.html				
					//window.location.href = "menu.html#/rendimento?id="+$scope.aluno.id;
					window.location.href = "menu.html";											
				}
			}
		);	
		
	};	

});
