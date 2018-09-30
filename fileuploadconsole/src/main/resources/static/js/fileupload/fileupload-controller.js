app.controller('FileUploadController', function($scope, $http, $location, $route, $routeParams, FileUploadService) {

	$scope.current = {};
	$scope.myFile = {};
	$scope.current.fileUploadEnquiryDate =  new Date();
    $scope.uploadResult ="";
    $scope.myForm = {
            description: "",
            file,
            files: []
        }
     
    
    $scope.doUploadFile = function() {  
    	 
        var url = "/report/rest/uploadMultiFiles";
 
 
        var data = new FormData();
 
        data.append("description", $scope.myForm.description);
        data.append("file", $scope.myForm.file);
        for (i = 0; i < $scope.myForm.files.length; i++) {
            data.append("files", $scope.myForm.files[i]);
        }
 
        var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }
        
 
        $http.post(url, data, config).then(
            // Success
            function(response) {
                $scope.uploadResult =  response.data;
            },
            // Error
            function(response) {
                $scope.uploadResult = response.data;
            });
    };
	
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
       FileUploadService.uploadFileToUrl(file, uploadUrl);
   };

	function fileUploadPageScopeChanged($scope) {
	    return " File Upload Enquiry Date: " + $scope.current.fileUploadEnquiryDate;
	};
	
});
