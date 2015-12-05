app.controller('AssuntoController', function($scope, $http) {

	$scope.assunto = {};	
	$scope.listaAssuntos = [];

	$scope.listaDisciplinas = [];
	
	$http.get('http://localhost:8080/SAD/rest/disciplinas').success(
		function(dados) {				
			$scope.listaDisciplinas = dados;				
		}
	);
	
	$scope.adicionarAssunto = function() {		
		
		$http.post('http://localhost:8080/SAD/rest/assuntos', $scope.assunto).success(
			function(dados) {    	
				alert(dados);								
  			}
  		);

	};

	$scope.removerAssunto= function(assunto) {		
		
		var r = confirm("Excluir assunto?");
		if (r == true) {
			$http.delete('http://localhost:8080/SAD/rest/assuntos/'+assunto.id).success(
				function(dados) {									
					$scope.buscarAssuntosPorDisciplina();
				}
			);    		
		} 	

	};

	$scope.alterarAssunto = function(assunto) {
			
		$http.put('http://localhost:8080/SAD/rest/assuntos', assunto).success(
			function(dados) {									
				alert(dados);	
				$scope.buscarAssuntosPorDisciplina();
			}
		);    	
	};


	$scope.listarAssuntos = function() {

		$scope.listaAssuntos = [];
		$http.get('http://localhost:8080/SAD/rest/assuntos').success(
			function(dados) {				
				$scope.listaAssuntos = dados;								
			}
		);
		
	};


	$scope.buscarAssuntosPorDisciplina = function() {

		$scope.listaAssuntos = [];
		$http.get('http://localhost:8080/SAD/rest/assuntos/d/'+$scope.assunto.idDisciplina).success(
			function(dados) {					
				if(dados != null) {								
					$scope.listaAssuntos = dados;																
				}
			}
		);	
	};

});
