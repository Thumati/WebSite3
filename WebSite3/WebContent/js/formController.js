app.controller("formController", function($scope, $location, dataFactory) {
/*	//alert("hi");
	// check for input type
	//$scope.Ctrl = function($scope) {
		//$scope.name = '';
		//$scope.word = /^\s*\w*\s*$/;

//};
	$( "#myform" ).validate({
	  rules: {
	    name: {
	      required: true
	    },
	    email: {
		      required: true
		    }
		 
	    
	  }
	});

	// $scope.data={};
	//$scope.name=null;
	

	$scope.registration = function() {
		
		
		$scope.data = {
			name : $scope.name,
			email : $scope.email,
			pass : $scope.pass,
			age : $scope.userAge,
			address : $scope.address,
			ccn:$scope.ccn
		};
	

		dataFactory.registration(JSON.stringify($scope.data)).success(
				function(result) {
					setTimeout(function() {
						$location.path = "/Home";
					}, 2000);
				}).error(function(data2) {
			alert(data + "data");

		});

		console.log(JSON.stringify($scope.data));

	};*/
	$scope.registration=function()
	{
		
		alert("hi");
		$scope.data = {
				name : $scope.name,
				email : $scope.email,
				pass : $scope.pass,
				age : $scope.age,
				address : $scope.address,
				ccn:$scope.ccn
			};
		

			dataFactory.registration(JSON.stringify($scope.data)).success(
					function(result) {
						
							$location.path = "/Home";
						
					}).error(function(data2) {
				alert(data + "data");

			});

			console.log(JSON.stringify($scope.data));

	}
});
