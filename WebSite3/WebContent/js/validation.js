var app = angular.module("myApp", ['ngCookies']);

app.controller("appController", function($scope,$http,$location,$rootScope,$cookieStore,$cookies) {	  
 
  $scope.validate = function() {
	  alert("hi");
	  $scope.data2= {
		email: $scope.EMAIL,
		pass:$scope.password
	  };

      $http.post("/WebSite/ValidationServlet",
           JSON.stringify($scope.data2))        
           .success(function(result) {
        	   var data3=result.email;
        	   var data4=result.pass;
        	   var login="login";
        	   if (($scope.EMAIL==data3)&&($scope.password==data4))  { 
        		   alert("valid email");
        		 var emailResult=$cookieStore.get('emailResult');
        	      $rootScope.emailResult=data3;
        		  alert($rootScope.emailResult);
        		  
      
        		 $cookieStore.put('emailResult',$rootScope.emailResult);
        		/* $cookies.$rootScope.emailResult=data3;
        		  $scope.getCookie=function(){
        			 return  $cookies.$rootScope.emailResult;
        		  }*/
        	   
        	   }
        	   
        	   
           }).error(function(data) {
               alert(data);
             });
   
  }
});

