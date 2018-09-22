app.controller('FileUploadController', function($scope, $location, $route, $routeParams) {

	
	$scope.data = {
			   row1: [ "Sample_PDF.pdf", "application pdf", "Sample PDF file" ],
			   row2: ["Sample_image.png", "imaghe/png", "Picture file" ],
			   row3: ["Sample_zip.zip", "application/x-zip-compressed", "Zip file" ]
			};
	
	
});
