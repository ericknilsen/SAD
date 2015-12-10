app.controller('QuestaoController', function($scope, $http, $location) {

	$scope.questao = {};		
	$scope.questao.listaAssuntos = [];
	$scope.listaQuestoes = [];
	$scope.listaDisciplinas = [];
	$scope.alternativaA = {};
	$scope.alternativaB = {};
	$scope.alternativaC = {};
	$scope.alternativaD = {};
	$scope.alternativaE = {};

	$scope.assunto = {};	
	$scope.listaAssuntos = [];
	

	$http.get('http://localhost:8080/SAD/rest/disciplinas').success(
		function(dados) {				
			$scope.listaDisciplinas = dados;			
		}
	);

	$scope.buscarQuestao = function() {
		$http.get('http://localhost:8080/SAD/rest/questoes/q/'+$location.search().id).success(
			function(dados) {									
				$scope.questao = dados;									
				$scope.questao.id = $location.search().id;
				
				$scope.alternativaA = dados.listaAlternativas[0];		
				$scope.alternativaB = dados.listaAlternativas[1];
				$scope.alternativaC = dados.listaAlternativas[2];
				$scope.alternativaD = dados.listaAlternativas[3];
				$scope.alternativaE = dados.listaAlternativas[4];

				$scope.buscarAssuntosPorDisciplina();				
			}
		);	

	};
	
	$scope.existeAssuntoSelecionado = function() {
		
		for (var i = 0; i < $scope.listaAssuntos.length; i++) {
			if($scope.listaAssuntos[i].checked) { 				
				return true;			
			}	
		};   
		
		return false;
	}	


	$scope.gravarQuestao = function() {

		$scope.alternativaA.letra = 'A';
		$scope.alternativaB.letra = 'B';
		$scope.alternativaC.letra = 'C';
		$scope.alternativaD.letra = 'D';
		$scope.alternativaE.letra = 'E';

		$scope.questao.listaAlternativas = [];

		$scope.questao.listaAlternativas.push($scope.alternativaA);
		$scope.questao.listaAlternativas.push($scope.alternativaB);
		$scope.questao.listaAlternativas.push($scope.alternativaC);
		$scope.questao.listaAlternativas.push($scope.alternativaD);
		$scope.questao.listaAlternativas.push($scope.alternativaE);

		$scope.questao.listaAssuntos = [];

		for (var i = 0; i < $scope.listaAssuntos.length; i++) {
			if($scope.listaAssuntos[i].checked) { 				
				$scope.questao.listaAssuntos.push($scope.listaAssuntos[i]);			
			}	
		};   		
   		
		$http.post('http://localhost:8080/SAD/rest/questoes', $scope.questao).success(
			function(dados) {
				alert(dados);
    			$scope.questao = {};
    			$scope.alternativaA = {};
				$scope.alternativaB = {};
				$scope.alternativaC = {};
				$scope.alternativaD = {};
				$scope.alternativaE = {};
				$scope.listaAssuntos = [];
				$scope.questao.listaAssuntos = [];    			
  			}
  		);		
	};


	$scope.buscarAssuntosPorDisciplina = function() {

		$scope.listaAssuntos = [];		
		$http.get('http://localhost:8080/SAD/rest/assuntos/d/'+$scope.questao.idDisciplina).success(
			function(dados) {					
					$scope.listaAssuntos = dados;				
					
					if($scope.questao.listaAssuntos != null) {	

						listaAssuntosSelecionados = $scope.questao.listaAssuntos;						

						for (var i = 0; i < $scope.listaAssuntos.length; i++) 
							for (var j = 0; j < listaAssuntosSelecionados.length; j++) 
								if(listaAssuntosSelecionados[j].id == $scope.listaAssuntos[i].id)
									$scope.listaAssuntos[i].checked = true;						 
					}											
			}
		);	
	};

	$scope.buscarQuestoesPorDisciplina = function() {
		$scope.listaQuestoes = [];
		$http.get('http://localhost:8080/SAD/rest/questoes/d/'+$scope.questao.idDisciplina).success(
			function(dados) {						
				$scope.listaQuestoes = dados;			
			}
		);
		$scope.buscarAssuntosPorDisciplina();
		
	};

	$scope.buscarQuestoesPorAssuntos = function() {
		
		$scope.listaQuestoes = [];
		$scope.questao.listaAssuntos = [];
		for (var i = 0; i < $scope.listaAssuntos.length; i++) {
			if($scope.listaAssuntos[i].checked) { 				
				$scope.questao.listaAssuntos.push($scope.listaAssuntos[i]);							
			}	
		};		

		if($scope.questao.listaAssuntos.length > 0) {

			$http.post('http://localhost:8080/SAD/rest/questoes/a', $scope.questao).success(
				function(dados) {	
					$scope.listaQuestoes = dados;
  				}
  			);
  		}	
   		
	};	

	$scope.removerQuestao = function(questao) {		
		
		var r = confirm("Excluir questao?");
		if (r == true) {
			$http.delete('http://localhost:8080/SAD/rest/questoes/'+questao.id).success(
				function(dados) {									
					$scope.buscarQuestoesPorDisciplina();
				}
			);    		
		} 	

	};

	if($location.search().id != null) {
		$scope.buscarQuestao();		
	}

});
