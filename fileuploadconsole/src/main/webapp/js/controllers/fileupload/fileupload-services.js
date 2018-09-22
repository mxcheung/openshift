app.service('FileUploadService', function($q, $http) {
	return {
		getFileUploadSummary : function() {
			return $http.get('./report/file-upload', {
				params : {
					'enquiryDate' : '2018-09-23'
				}
			});
		}
	}
});
