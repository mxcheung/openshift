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
			var fd = new FormData();
	        fd.append('file', file);
			return $http.post('./report/file-upload', fd,{
				params : {
					  transformRequest: angular.identity,
			            headers: {'Content-Type': undefined}
				}
			});
	    }		
	}
});




