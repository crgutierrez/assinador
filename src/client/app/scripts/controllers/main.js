'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('MainCtrl', function ($scope, $http) {

    $scope.msg = "";
    $scope.open = function(){
      $http.get("http://localhost:4567/config.gif").error(function(data,status) {
        console.log(data);
        console.log(status);
        $scope.msg = "Erro na Requisicao";
      }).success(function(data){
        console.log(data)
        $scope.msg = "Aberto a requisicao";
      });
    };
  });
