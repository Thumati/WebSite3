app.controller("logoutController", function($cookieStore, $rootScope) {
	$cookieStore.remove("email");
	$rootScope.login = "Login";
	$rootScope.emailResult = "";
});