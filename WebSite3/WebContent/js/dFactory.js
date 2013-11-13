angular.module('hotelData').factory('dataFactory', [ '$http', function($http) {
	var dataFactory = {};

	dataFactory.viewRooms = function() {
		return $http.get('/WebSite3/RoomDataBase');
	};

	dataFactory.validate = function(json) {
		return $http.post('/WebSite3/MailServlet', json);
	};

	dataFactory.reservation = function(json) {
		return $http.post('/WebSite3/ReservationServlet', json);
	};
	
	dataFactory.reservationUpdation = function(json) {
		return $http.post('/WebSite3/SampleReservation', json);
	};

	dataFactory.registration = function(json) {
		return $http.post('/WebSite3/RegistrationPage', json);
	};

	dataFactory.deleteReserv = function(json) {
		return $http.post('/WebSite3/DeleteServlet', json);
	};
	dataFactory.feedback = function(json) {
		return $http.post('/WebSite3/FeedBackServlet', json);
	};
	dataFactory.commentView = function() {
		return $http.post('/WebSite3/CommentServlet');
	};
	dataFactory.deletefeedback = function(json) {
		return $http.post('/WebSite3/DeleteFeedBackServlet',json);
	};
	return dataFactory;
} ]);