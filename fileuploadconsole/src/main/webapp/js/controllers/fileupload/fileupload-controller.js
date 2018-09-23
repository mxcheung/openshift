app.controller('FileUploadController', function($scope, $location, $route, $routeParams, FileUploadService) {

	$scope.current = {};
	$scope.myFile = {};
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

   $scope.uploadFile = function(){
       var file = $scope.myFile;
       console.log('file is ' );
       console.dir(file);
       var uploadUrl = "/fileUpload";
       fileUpload.uploadFileToUrl(file, uploadUrl);
   };

	function fileUploadPageScopeChanged($scope) {
	    return " File Upload Enquiry Date: " + $scope.current.fileUploadEnquiryDate;
	};
	
});
