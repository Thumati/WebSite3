app.controller("displayController", function($scope,$rootScope, $cookieStore,$location,dataFactory) {
	//$rootScope.hideNgView();
	$scope.showButton=false;
	
	if($rootScope.availableRoom > 0)
	 {
		 $scope.showButton=true;
	 }
	
	$scope.reserve=function(){
		
	var userEmail = $cookieStore.get('email');
	console.log(userEmail);
	$rootScope.emailResult = $cookieStore.get('email');
	$rootScope.data.email=userEmail;
	console.log($rootScope.data.email);
	
	if (userEmail != null) {
		console.log($rootScope.emailResult);
		
		dataFactory.reservationUpdation(JSON.stringify($rootScope.data))
		.success(function(result) {
			
		     	alert("hi");
		     	
				alert(result.Reserv_id);
				$scope.Reserv_id=result.Reserv_id;
				$rootScope.Reserv_id=$scope.Reserv_id;
				$location.path("/ReservModify");
				$rootScope.showNgView();
			
		}).error(function(data2) {
			$rootScope.showNgView();
			alert(data + "data");
		
		
		});

		
	} else {
		$location.path('/LoginReserv');
	}
	
//	alert($rootScope.availableRoom);
	};
});
