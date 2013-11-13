app
		.controller(
				"reservController",
				function($scope, $rootScope, dataFactory, $location) {

					$("#datepicker").datepicker({
						minDate : "10/07/2012",
						maxDate : "10/30/2019",
						onClose : function(selectedDate) {
							$scope.CheckIn = selectedDate;
							$scope.$apply();
						}
					});

					$("#datepicker1").datepicker({
						beforeShow : setminDate,
						onClose : function(selectedDate) {
							$scope.CheckOut = selectedDate;
							$scope.$apply();
						}

					});

					var start1 = $('#datepicker');
					function setminDate() {
						var p = start1.datepicker('getDate');
						if (p) {
							var k = "11/30/2019";
							return {
								minDate : p,
								maxDate : k
							}
						}
						;
					}
					function clearEndDate(dateText, inst) {
						end1.val('');
					}

					/*
					 * var text1 = 'Single'; $("#mySelect1
					 * option").filter(function() {
					 * 
					 * return this.text == text1;
					 * 
					 * }).attr('selected', true);
					 */



					$(function() {
						$("#datepicker1").datepicker({
							dateFormat : 'mm/dd/yyyy'
						});
						$("#datepicker").datepicker({
							dateFormat : 'mm/dd/yyyy'
						});
					});

					$scope.check = function() {

						alert($scope.CheckIn);

						alert($scope.CheckOut);

						alert($scope.Room_type);
					
						$scope.data = {
							CheckIn : $scope.CheckIn,
							CheckOut : $scope.CheckOut,
							Room_type : $scope.Room_type,
						}
						dataFactory.reservation(JSON.stringify($scope.data))
								.success(function(result) {
									alert("Rooms are available...");

								}).error(function(data2) {
									alert(data + "data");

								});

					}
				});
