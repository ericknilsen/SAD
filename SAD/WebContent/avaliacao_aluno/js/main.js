var app = angular.module('sad', ['ngRoute','ngCookies','ngSanitize']);

app.run(function($rootScope) {
    $rootScope.ip = 'localhost';
    //$rootScope.ip = '10.255.0.19';    
    //$rootScope.ip = '10.255.0.4';
})

app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/aplica_avaliacao?', {
        templateUrl: 'aplica_avaliacao.html',
        controller: 'AvaliacaoController'
      }).
      when('/lista_avaliacao', {
        templateUrl: 'lista_avaliacao.html',
        controller: 'AvaliacaoController'
      }).
      when('/corrige_avaliacao?', {
        templateUrl: 'corrige_avaliacao.html',
        controller: 'AvaliacaoController'
      }).
      when('/relatorio_rendimento?', {
        templateUrl: 'relatorio_rendimento.html',
        controller: 'RendimentoController'
      });
  }]);


