app.controller('FileUploadController', function($scope, $location, $route, $routeParams, FileUploadService) {

	$scope.current = {};
	$scope.current.fileUploadEnquiryDate =  new Date();
	
//	var cat = FileUploadService.getFileUploadSummary();
//	cat.then(function(response) {
//		$scope.data = response.data;// don't forget "this" in the service
//	})

	 $scope.$watch(fileUploadPageScopeChanged, function() {
		 FileUploadService.getFileUploadSummary($scope.current.fileUploadEnquiryDate)
				 .then(function success(response) {
					 $scope.data = response.data;
				 })
				.catch(function error(rejection) {
					$scope.error = rejection;
				});
		    });
	
	
   $scope.deleteFileUpload = function(fileId, index) {
    	if(confirm("Do you want to delete this file upload?")) {
    		FileUploadService.deleteFileUpload(fileId).then(function() {
    		// $scope.current.deposits.splice(index, 1);
    		});
    	}
    }


	function fileUploadPageScopeChanged($scope) {
	    return " File Upload Enquiry Date: " + $scope.current.fileUploadEnquiryDate;
	};
	
});
