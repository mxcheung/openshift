app.controller('FileUploadController', function($scope, $location, $route, $routeParams, FileUploadService) {

	
	var cat = FileUploadService.getFileUploadSummary();
	cat.then(function(response) {
		$scope.data = response.data;// don't forget "this" in the service
	})



});
