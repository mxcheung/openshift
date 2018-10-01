app.service('FileUploadService', function($q, $http, DatePickerService) {
	return {
		getFileUploadSummary : function(enquiryDate, applicationCd) {
			return $http.get('./report/file-upload', {
				params : {
					'enquiryDate' : DatePickerService.formatDateForRequest(enquiryDate),
					'applicationCd' :applicationCd
				}
			});
		},
		
	    deleteFileUpload : function (fileId) {
	    	return $http.delete("report/"+fileId)
	    },		
		
		uploadFileToUrl : function ( fileDTO) {
			
			
			var data = new FormData();
			data.append('applicationCd', fileDTO.applicationCd);
			data.append('type', fileDTO.type);
			data.append('subtype', fileDTO.subtype);
			data.append('description', fileDTO.description);
			data.append('uploadfile', fileDTO.file);
	        
	        var config = {
	                transformRequest: angular.identity,
	                transformResponse: angular.identity,
	                headers: {
	                    'Content-Type': undefined
	                }
	            }
	        
	        var url = "./report/file-upload";
	        var url3 = "./api/uploadfile";
	        
	        
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




