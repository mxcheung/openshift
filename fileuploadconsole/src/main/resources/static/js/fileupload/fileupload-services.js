app.service('FileUploadService', function($q, $http, DatePickerService) {
	return {
		getFileUploadSummary : function(enquiryDate) {
			return $http.get('./report/file-upload', {
				params : {
					'enquiryDate' : DatePickerService
									.formatDateForRequest(enquiryDate)
				}
			});
		},
		
	    deleteFileUpload : function (fileId) {
	    	return $http.delete("report/"+fileId)
	    },		
		
		uploadFileToUrl : function (file, uploadUrl) {
			var data = new FormData();
			data.append('file', file);
	        
	        var config = {
	                transformRequest: angular.identity,
	                transformResponse: angular.identity,
	                headers: {
	                    'Content-Type': undefined
	                }
	            }
	        
	        var url = "./report/file-upload";
	        
	        return $http.post(url, data, config).then(
	                // Success
	                function(response) {
	                    $scope.uploadResult =  response.data;
	                },
	                // Error
	                function(response) {
	                    $scope.uploadResult = response.data;
	                });
	    }		
	}
});




