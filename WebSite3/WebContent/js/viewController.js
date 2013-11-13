app.controller("viewController", function($scope, dataFactory,$rootScope) {
	
//alert("hi");
	$scope.hotelRoomsDetailslist = {};

	dataFactory.viewRooms().success(function(hotelRoomsDetails) {

		$scope.hotelRoomsDetailslist = hotelRoomsDetails;
		$rootScope.hotelRoomsDetailslist = hotelRoomsDetails;

	}).error(function($scope) {
		alert(data);

	});
	/*$scope.reserv=function(){
		
		$rootScope.Room=hotelRoomsDetails.Room_type;
		$rootScope.Price=hotelRoomsDetails.Price_reg;
		alert($rootScope.Room);
		alert("hi");
	};*/
});