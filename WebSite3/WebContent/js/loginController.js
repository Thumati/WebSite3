app.controller("loginController", function($scope, dataFactory, $location,
		$rootScope, $cookieStore) {

	alert("first step in login");
	$scope.validate = function() {
		$rootScope.hideNgView();
		$scope.data2 = {
			email : $scope.eMail,
			pass : $scope.password
		};
alert($scope.eMail);
alert($scope.password);
		dataFactory.validate(JSON.stringify($scope.data2)).success(
				function(result) {
					var data3 = result.email;
					// var data4=result.pass;
					$cookieStore.put('email', result.email);

					$rootScope.emailResult = data3;
					$rootScope.login = "Logout";
				    $rootScope.deleteReserv="deleteReserv";

					$location.path('/usr');
					$rootScope.showNgView();

				}).error(function(data2) {
			alert(data + "data");
			$rootScope.showNgView();
		});

	};
	
	$scope.Register=function()
	{
		
		$location.path('/registration');
	};

});
