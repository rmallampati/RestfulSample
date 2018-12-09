/**
 * 
 */
angular.module('AngularSample', []).controller('loginController', function($scope) {
  alert($scope.user_id);
  alert($scope.password);
});