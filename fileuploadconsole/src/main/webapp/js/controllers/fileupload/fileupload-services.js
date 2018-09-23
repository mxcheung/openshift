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
	    }		
		
		
	}
});




