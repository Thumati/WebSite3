app.controller("reservController",
				function($scope, $rootScope, dataFactory, $location,$timeout,$log,$dialog) {
		  

	            $scope.showButton=false;
					$("#datepicker").datepicker({
						minDate : "+0d",
						maxDate : "+1y",
						defaultDate: "+0d",
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


					
					$('#calculate')
							.click(
									function() {
										alert("hi");
										var a = $("#datepicker").datepicker(
												'getDate').getTime(), b = $(
												"#datepicker1").datepicker(
												'getDate').getTime(), c = 24 * 60 * 60 * 1000, diffDays = Math
												.round(Math.abs((a - b) / (c)));
										alert(diffDays);
										// alert($('#preview').text(99));
									
										if (($("#mySelect1 option:selected")
												.text()) == "Single") {
											
											alert("in the function");
											$rootScope.total=diffDays*99;
											$rootScope.string="***********Room For a Day is:99*************";
											$scope.total=diffDays*99;
											$scope.string="***********Room For a Day is:99*************";
											$scope.$apply();
																	

										} else if (($("#mySelect1 option:selected")
												.text()) == "Double") {

											$rootScope.total=diffDays*199;
											$rootScope.string="***********Room For a Day is:199*************";
											$scope.total=diffDays*199;
											$scope.string="***********Room For a Day is:199*************";
											$scope.$apply();
												
										} else if (($("#mySelect1 option:selected")
												.text()) == "TripleRoom") {

											$rootScope.total=diffDays*179;
											$rootScope.string="***********Room For a Day is:179*************";
											$scope.total=diffDays*179;
											$scope.string="***********Room For a Day is:179*************";
											$scope.$apply();
													}
										else if (($("#mySelect1 option:selected")
												.text()) == "Suite") {
														$rootScope.total=diffDays*299;
														$rootScope.string="***********Room For a Day is:299*************";
														$scope.total=diffDays*299;
														$scope.string="***********Room For a Day is:299*************";
														$scope.$apply();
															
									
                   
										} else if (($("#mySelect1 option:selected")
												.text()) == "HoneyMoonRoom") {

											$rootScope.total=diffDays*399;
											$rootScope.string="***********Room For a Day is:399*************";
											$scope.total=diffDays*399;
											$scope.string="***********Room For a Day is:399*************";
											$scope.$apply();
												
										
										}

										else {
											$('div.total-title').text("select the Room Type");
											alert("select the room type");
										}

									});

					$(function() {
						$("#datepicker1").datepicker({
							dateFormat : 'mm/dd/yyyy'
						});
						$("#datepicker").datepicker({
							dateFormat : 'mm/dd/yyyy'
						});
					});
					
					
					
					 
					
					$('.dt').datepicker();					
					
					var dialogPushProfile = {
						controller : 'ModalInstanceCtrl',
						templateUrl : 'html/mymodel.html'
					};
					
					$scope.reserve = function() {


						$dialog.dialog(angular.extend(dialogPushProfile, {
							resolve : {
							},
							backdropFade : true,
							dialogFade : true
						})).open().then(function(result) {
							if (result) {
								angular.copy(result);
							}
						
						});
					};


		
		

				//	$scope.reserve = function() {
						 
//							    var modalInstance = $modal.open({
//							      templateUrl: 'html/mymodel.html',
//							      controller: 'ModalInstanceCtrl',
//							      resolve: {
//							          items:angular.copy(items)
//							        
//							      }
//							    });
//
//							   modalInstance.result.then(function (selectedItem) {
//							      $scope.selected = selectedItem;
//							    }, function () {
//							      $log.info('Modal dismissed at: ' + new Date());
//							    });
				//};
				
});
						




 function ModalInstanceCtrl($scope,dialog) {
							  console.log("I am in ModalInstanceCtrl");
							 // $scope.selected = {
							   // item: $scope.items[0]
							  //};

							 // $scope.ok = function () {
							   // $modalInstance.close();
							    //alert("hi");
							  //};

							  $scope.close = function () {
								  dialog.close();
							  };
							};
						
						
					/*	$rootScope.data = {
							CheckIn : $scope.CheckIn,
							CheckOut : $scope.CheckOut,
							Room_type : $scope.Room_type,
							email:"",
							total:$scope.total,
							
						  
						}
						/*if(($scope.Room_type=="single")||($scope.Room_type=="double")||($scope.Room_type=="TripleRoom")||($scope.Room_type=="Suite")||($scope.Room_type=="HoneyMoonRoom"))
						{
						dataFactory.reservation(JSON.stringify($rootScope.data))
								.success(function(result) {
									console.log(result);
									
										 $rootScope.availableRoom =  result.availableRoom;
									    $location.path("\Display");
									   
									
								// $location.path("\Display");

								}).error(function(data2) {
									alert(data + "data");

								});
						}
else
	{
	  alert("Please enter the CkeckIn, CheckOut And Room_type for Check availability");
	}
					};*/
