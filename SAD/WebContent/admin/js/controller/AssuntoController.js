app.controller('AssuntoController', function($scope, $http) {

	$scope.assunto = {};	
	$scope.listaAssuntos = [];

	$scope.listaDisciplinas = [];
	

	$http.get('http://localhost:8080/SAD/rest/disciplinas').success(
			function(dados) {				
				if(Array.isArray(dados.disciplinaVO))				
					$scope.listaDisciplinas = dados.disciplinaVO;			
				else
					$scope.listaDisciplinas.push(dados.disciplinaVO);
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

		var assuntoSelecionado = null;
						
		for (var i = 0; i < $scope.listaAssuntos.length; i++) {			
			if ($scope.listaAssuntos[i].id == assunto.id) {
				assuntoSelecionado = assunto;
			};
		};	
			
		$http.put('http://localhost:8080/SAD/rest/assuntos', assuntoSelecionado).success(
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
				if(Array.isArray(dados.assuntoVO))				
					$scope.listaAssuntos = dados.assuntoVO;			
				else
					$scope.listaAssuntos.push(dados.assuntoVO);				
			}
		);
		
	};


	$scope.buscarAssuntosPorDisciplina = function() {

		$scope.listaAssuntos = [];
		$http.get('http://localhost:8080/SAD/rest/assuntos/d/'+$scope.assunto.idDisciplina).success(
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

});
