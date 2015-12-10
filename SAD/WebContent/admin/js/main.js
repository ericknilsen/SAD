var app = angular.module('sad', ['ngRoute']);

app.directive('fileInput', ['$parse', function($parse) {
	return {
		restrict: "A",
		link:function(scope,elm,attrs) {
			elm.bind('change', function() {
				$parse(attrs.fileInput)
				.assign(scope,elm[0].files)
				scope.$apply()				
			})			
		}
	}						
	
}]);

app.directive('customOnChange', function() {
	  return {
	    restrict: 'A',
	    link: function (scope, element, attrs) {
	      var onChangeHandler = scope.$eval(attrs.customOnChange);
	      element.bind('change', onChangeHandler);
	    }
	  };
});

app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/cadastro_questao?', {
        templateUrl: 'cadastro_questao.html',
        controller: 'QuestaoController'
      }).
      when('/busca_questao', {
        templateUrl: 'busca_questao.html',
        controller: 'QuestaoController'
      }).
      when('/cadastro_disciplina', {
        templateUrl: 'cadastro_disciplina.html',
        controller: 'DisciplinaController'
      }).
      when('/cadastro_assunto', {
        templateUrl: 'cadastro_assunto.html',
        controller: 'AssuntoController'
      }).
      when('/busca_assunto', {
        templateUrl: 'busca_assunto.html',
        controller: 'AssuntoController'
      }).
      when('/cadastro_turma', {
        templateUrl: 'cadastro_turma.html',
        controller: 'TurmaController'
      }).
      when('/busca_turma', {
        templateUrl: 'busca_turma.html',
        controller: 'TurmaController'
      }).
      when('/cadastro_aluno', {
        templateUrl: 'cadastro_aluno.html',
        controller: 'AlunoController'
      }).
      when('/busca_aluno', {
        templateUrl: 'busca_aluno.html',
        controller: 'AlunoController'
      }).
      when('/cadastro_avaliacao', {
        templateUrl: 'cadastro_avaliacao.html',
        controller: 'AvaliacaoController'
      }).
      when('/busca_avaliacao', {
        templateUrl: 'busca_avaliacao.html',
        controller: 'AvaliacaoController'
      }).
      when('/detalhe_avaliacao?', {
        templateUrl: 'detalhe_avaliacao.html',
        controller: 'AvaliacaoController'
      }).
      when('/aplica_avaliacao?', {
        templateUrl: 'aplica_avaliacao.html',
        controller: 'AvaliacaoController'
      }).
      when('/lista_avaliacao', {
        templateUrl: 'lista_avaliacao.html',
        controller: 'AvaliacaoController'
      });
  }]);


