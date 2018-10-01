'use strict';

angular.module('cashmgmtApp.navbar', [ 'ngRoute' ])
.directive('cashmgmtNavbar', [ function() {
	return {
		templateUrl : 'views/navbar.html',
		restrict : 'EA'
	};
}])
.controller('TabController', function($scope) {
	$scope.tab;
	
	$scope.selectTab = function(setTab) {
		$scope.tab = setTab;
	};

	$scope.isSelected = function(checkTab) {
		return $scope.tab === checkTab;
	};
});