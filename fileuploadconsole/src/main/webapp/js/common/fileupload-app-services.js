app.service('DatePickerService', function($http, $q) {
	return {
        /*
         * Formats a date as an ISO-8601 date (yyyy-MM-dd) for use in a request.
         */
        formatDateForRequest: function(date) {
        	if(date) {
        		return moment(date).format('YYYY-MM-DD');
        	}
        },

        /*
         * Formats a date for start-of-day comparison.
         */
        formatDateForComparison: function(date) {
            return moment(date).startOf('day').format();
        },
        
        /*
         * Formats a date for date and time
         */
        formatDateWithTime: function(date) {
        	return moment(date).format("YYYY-MM-DDTHH:mm:ss.SSS");
        },
        
        /*
         * Checks if given date is weekend or not. Return true if weekend
         */
    	isWeekend : function(date) {
    		var day = date.getDay();
    		var weekend = (day == 6) || (day == 0); 
    		return weekend;
    	},
    	
	};
});


