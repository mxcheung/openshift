app.controller('FileUploadController', function($scope, $http, $location, $route, $routeParams, FileUploadService) {

	$scope.current = {};
	$scope.current.fileUploadCounter = 0;
	$scope.current.fileUploadEnquiryDate =  new Date();
	$scope.current.uploadedFile = {};
	$scope.current.fileDescription = "";
    $scope.uploadResult ="";
    $scope.current.applicationCds = ["General", "Accounting", "Banking", "Reconciliation"];  
    $scope.current.typeCds = ["Default Type", "Main", "Other", "Bank Statement"];  
    $scope.current.subTypeCds = ["Default Sub Type", "Deposit", "GL", "Withdrawl",  "Other"];  
    
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
		 FileUploadService.getFileUploadSummary($scope.current.fileUploadEnquiryDate, $scope.current.fileUploadApplicationCd)
				 .then(function success(response) {
					 $scope.data = response.data;
					 $scope.rowCollection = $scope.data.reports;
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
       var file = $scope.current.uploadedFile;

       console.log('file is ' );
       console.dir(file);
       var fileDTO = {};
       fileDTO.applicationCd =  $scope.current.applicationCd;
       fileDTO.type =  $scope.current.type;
       fileDTO.subtype =  $scope.current.subtype;
       fileDTO.description =  $scope.current.fileDescription;
       fileDTO.file =  $scope.current.uploadedFile;
       FileUploadService.uploadFileToUrl(fileDTO);
   	   $scope.current.fileUploadCounter++;
   };

	function fileUploadPageScopeChanged($scope) {
	    return " File Upload Enquiry Date: " + $scope.current.fileUploadEnquiryDate;
	};
	
	
	function fileUploadPageScopeChanged($scope) {
	    return " Display Value Date: " + $scope.current.fileUploadEnquiryDate +
	    	   " Display Application Cd: " + $scope.current.fileUploadApplicationCd +
	    	   " File Upload Counter: " + $scope.current.fileUploadCounter; 
	};
	
});
