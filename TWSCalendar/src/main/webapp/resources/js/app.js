angular.module('app',[])

angular.module('app').controller("MainController",['$scope', '$http',function($scope, $http){
	var vm = this;
	vm.selectedItem = "";
	vm.calendars=[];
	
	vm.getCalendars = function(){
		var response = $http.post('/getCalendar',vm.selectedItem);
		response.success(function(data, status, headers, config){
			vm.calendars=data;
//			console.log(data);
		});
		response.error(function(data,status,headers,config){
			alert("AJAX FAILED");
//			console.log(data);
		});
	}
	
	$scope.jobCode=[];
	$scope.getJobCodes = function(){
		var response = $http.get('/TWSCalendar/getJobCodes');
		response.success(function(data, status, headers, config){
			vm.jobCode=data;
//			console.log(data);
		});
		response.error(function(data,status,headers,config){
			alert("AJAX FAILED");
//			console.log(data);
		});
	}
	
}]);
