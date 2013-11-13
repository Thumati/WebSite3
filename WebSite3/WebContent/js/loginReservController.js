/*pp.controller("loginReservController", function($location) {
	
	 $location.path("\Display");
});*/
app.controller("loginReservController", function($scope, dataFactory, $location,
		$rootScope, $cookieStore) {

	alert("first step in login");
	$scope.validate = function() {
		$rootScope.hideNgView();
		$scope.data2 = {
			email : $scope.eMail,
			pass : $scope.password
		};

		dataFactory.validate(JSON.stringify($scope.data2)).success(
				function(result) {
					var data3 = result.email;
					// var data4=result.pass;
					$cookieStore.put('email', result.email);

					$rootScope.emailResult = data3;
					$rootScope.login = "Logout";

					$location.path('/Display');
					$rootScope.showNgView();

				}).error(function(data2) {
			alert(data + "data");
			$rootScope.showNgView();
		});

	};

});
