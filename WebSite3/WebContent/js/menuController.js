

var app = angular.module("hotelData", [ 'ngCookies','ui.bootstrap.dialog']).config(
		function($routeProvider) {
			$routeProvider.when('/Home', {
				templateUrl : 'html/Welcome.html',
				controller : 'mainController'
			});
			
			$routeProvider.when('/room/:id', {
				templateUrl : 'html/room.html',
				controller : 'roomController'
			});
			$routeProvider.when('/aboutus', {
				templateUrl : 'html/aboutus.html',
				controller : 'aboutusController'
			});
			$routeProvider.when('/reservation', {
				templateUrl : 'html/reservation.html',
				controller : 'reservController'
			});
			
			$routeProvider.when('/ViewRooms', {
				templateUrl : 'html/ViewRooms.html',
				controller : 'mainController'
			});
			$routeProvider.when('/registration', {
				templateUrl : 'html/registration.html',
				controller : 'formController'
			});
			$routeProvider.when('/Login', {
				templateUrl : 'html/login.html',
				controller : 'loginController'
					
			});

			$routeProvider.when('/LoginReserv', {
				templateUrl : 'html/loginReserv.html',
				controller : 'loginReservController'
			});
			$routeProvider.when('/usr', {
				templateUrl : 'html/modifyUsrDetail.html',
				controller : 'usrController'
			});

			$routeProvider.when('/Logout', {
				templateUrl : 'html/Welcome.html',
				controller : 'logoutController'
			});
			$routeProvider.when('/Display', {
				templateUrl : 'html/dispalyAvailableRoom.html',
				controller : 'displayController'
			});
			$routeProvider.when('/ReservModify', {
				templateUrl : 'html/ReservModify.html',
				controller : 'reservmodifyController'
			});
			$routeProvider.when('/deleteReserv', {
				templateUrl : 'html/delete.html',
				controller : 'deleteController'
			});
			$routeProvider.when('/feedback', {
				templateUrl : 'html/feedback.html',
				controller : 'feedbackController'
			});
			$routeProvider.otherwise({
				redirectTo : '/Home'
			});
		});



app.controller("indexController", function($scope, $rootScope, $cookieStore) {
	
	//Checking if a user is logged in
	var userEmail = $cookieStore.get('email');
	
	$rootScope.emailResult = $cookieStore.get('email');

	$rootScope.login = "Login";
	
	if (userEmail != null) {
		$rootScope.login = "Logout";	
	}
	
	//End - User check
	
	$scope.regShow=false;
	$scope.showNgHide = false;
	$rootScope.regShowToggle=function(){
		
		$scope.regShow = !$scope.regShow;
	}
	$rootScope.hideNgView = function() {
		$scope.showNgHide = true;

	}
	$rootScope.showNgView = function() {
		$scope.showNgHide = false;

	}
	
});


app.controller("aboutusController", function($scope, $rootScope) {

	
});
app.controller("reservmodifyController", function($scope, $rootScope,$location,dataFactory) {
	$scope.Reserv_id=$rootScope.Reserv_id;
	
	$scope.Close=function()
	{
		$location.path('/Home');
	//	dataFactory.deleteReserv
	}
	
	$scope.Modify=function()
	{
	alert("alert reservation modified ");	
	
	$location.path('/Home');		
	}

	
});

app.controller("mainController", function($scope,$rootScope, $cookieStore,dataFactory) {
	
	$scope.hotelRoomsDetailslist = {};

	dataFactory.viewRooms().success(function(hotelRoomsDetails) {

		$scope.hotelRoomsDetailslist = hotelRoomsDetails;
		$rootScope.hotelRoomsDetailslist = hotelRoomsDetails;

	}).error(function($scope) {
		alert(data);

	});
	//dataFactory.commentView.success(function)
	dataFactory.commentView().success(function(Details) {

		alert("i am successfull");
$scope.Detailslist=Details;
$rootScope.Detailslist=Details;
		
		
				
		
	}).error(function(details) {
		alert("data"+details);

	});


});
app.controller("roomController", function($scope, $routeParams,$rootScope) {
 
	$scope.room=$rootScope.hotelRoomsDetailslist[$routeParams.id];
	$rootScope.data2=$scope.room;
	/*$scope.today = function() {
	    $scope.CheckIn = new Date();
	  };
	  $scope.today();
	  

	  $scope.showWeeks = true;
	  $scope.toggleWeeks = function () {
	    $scope.showWeeks = ! $scope.showWeeks;
	  };

	  $scope.clear = function () {
	    $scope.CheckIn = null;
	  };

	  // Disable weekend selection
	  $scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	  };

	  $scope.toggleMin = function() {
	    $scope.minDate = ( $scope.minDate ) ? null : new Date();
	  };
	  $scope.toggleMin();

	  $scope.open = function() {
	    $timeout(function() {
	      $scope.opened = true;
	    });
	  };

	  $scope.dateOptions = {
	    'year-format': "'yy'",
	    'starting-day': 1
	  };
	  $scope.today = function() {
		    $scope.CheckOut = new Date();
		  };
		  $scope.today();
		  

		  $scope.showWeeks = true;
		  $scope.toggleWeeks = function () {
		    $scope.showWeeks = ! $scope.showWeeks;
		  };

		  $scope.clear = function () {
		    $scope.CheckOut = null;
		  };

		  // Disable weekend selection
		  $scope.disabled = function(date, mode) {
		    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
		  };

		  $scope.toggleMin = function() {
		    $scope.minDate = ( $scope.minDate ) ? null : new Date();
		  };
		  $scope.toggleMin();

		  $scope.open = function() {
		    $timeout(function() {
		      $scope.opened = true;
		    });
		  };

		  $scope.dateOptions = {
		    'year-format': "'yy'",
		    'starting-day': 1
		  };
*/
	
});

app.controller("deleteController", function($scope, $rootScope,$location,dataFactory,$cookieStore) {
//	$scope.Reserv_id=$rootScope.Reserv_id;
	$scope.cancel=function()
	{
		alert("HI");
		  var userEmail = $cookieStore.get("email");
		  
	       $scope.data = {Reserv_id : $scope.Reserve_id};
	        if (userEmail != null){
	        	dataFactory.deleteReserv(
	                    JSON.stringify($scope.data))
	                    .success(function(result){
	                    	alert("Hi rreservation cancled successfully");
	                        $location.path("/Home");
	                    })
	                    .error(function(result){
	                        alert(result+"result");
	                    });
	           }
	            else{
	               $location.path("/Login");
	            }
	}
	


	
});

