

var app=angular.module("Hoteldata",[]);

//app.config(function($routeProvider,$locationProvider){
	//$routeProvider.html5Mode(true);
	
	//$routeProvider.when('/View',{
		//templateUrl:'/HotelWebSite/view.html',
		//controller:'viewController'
		
	//});
	
	
//});
app.controller("viewController",function($scope,$http){
	//alert("hi");
	
	$scope.hotelRoomsDetailslist={};
	$http.get('/HotelWebSite/RoomDataBase')
	.success(function(hotelRoomsDetails){
	
		$scope.hotelRoomsDetailslist=hotelRoomsDetails;
 		
	}).error(function($scope){
		
		//alert(data);
		
	});
	
	
	
	
});