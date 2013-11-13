app.controller("feedbackController", function($scope, $cookieStore,dataFactory,$rootScope,$location) {
	
		

	$scope.submit=function(){
		alert($scope.name);
		$rootScope.data = {
				name:$scope.name,
				email:$scope.email,
				comment:$scope.suggestions
				
				
		};
		console.log("I am in controller")

	dataFactory.feedback(JSON.stringify($rootScope.data)).success(function(Details) {

		alert("i am successfull");
$scope.Detailslist=Details;
$rootScope.Detailslist=Details;

		
$location.path('/feedback');
				
		
	}).error(function(details) {
		alert("data"+details);

	});
	};
	
	$scope.showRemove=false;
var userEmail =$cookieStore.get('email');
	
	$rootScope.emailResult = $cookieStore.get('email');

	if(userEmail != null){
		
		$scope.showRemove=true;
	}
	$scope.remove=function(email,comment){
		alert(email);
		alert(comment);
		
		//alert($scope.data.email);
		if(userEmail == email){
		alert(email);
		$scope.data={
				email:email,
				comment:comment
			};
		dataFactory.deletefeedback(JSON.stringify($scope.data)).success(function(Details) {

			alert("i am successfull");
		$location.path('/feedback');
					
			
		}).error(function(Details) {
			alert("data"+Details);

		});

		
		}
		else
			{
			alert("You can't delete others feedback");
			
			}
		alert("i am in remove function");
		
	}
});
