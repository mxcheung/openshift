
/* Common Controllers goes here */
app.controller('DatePickerController', function($scope, DatePickerService) {

    // Initialize the date options with the given values
    $scope.initDateOptions = function() {
    	$scope.dateOptions.maxDate = moment().add(14, 'days').toDate();
    }
    
    $scope.reload = function() {
        $scope.datePicker = {};
        $scope.datePicker.maxDate = moment().add(1, 'days').toDate();
	};
	$scope.reload();
	
	$scope.dateOptions = {
		dateDisabled : disabled,
		formatYear : 'yy',
		maxDate : $scope.datePicker.maxDate,
		minDate : $scope.datePicker.minDate,
		startingDay : 1,
		showWeeks : false
	};
	
	$scope.dateOptionsUnboundedDate = {
		dateDisabled : disabled,
		formatYear : 'yy',
		startingDay : 1,
		showWeeks : false
	};
	
	$scope.dateOptionsFilter = {
		formatYear : 'yy',
		startingDay : 1,
		showWeeks : false,
		clearBtn : true
	}

	// Disable weekend selection
	function disabled (data) {
		var date = data.date, mode = data.mode;
		switch (mode) {
		case 'day':
		//	return DatePickerService.isWeekend(date);
			return false;
		default:
			return false;
		}
	}

	$scope.open = function() {
		$scope.popup.opened = true;
	};

	$scope.popup = {
		opened : false
	};
});

