app.controller('FileUploadController', function($scope, $location, $route, $routeParams, FileUploadService) {

	
	var cat = FileUploadService.getFileUploadSummary();
	cat.then(function(response) {
		$scope.data = response.data;// don't forget "this" in the service
	})

   $scope.deleteFileUpload = function(fileId, index) {
    	if(confirm("Do you want to delete this file upload?")) {
    		FileUploadService.deleteFileUpload(fileId).then(function() {
    		//	$scope.current.deposits.splice(index, 1);
    		});
    	}
    }


});
