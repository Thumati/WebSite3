angular.module('plunker', ['ui.bootstrap']);
var ModalDemoCtrl = function ($scope, $modal, $log) {

  

  $scope.open = function () {

    var modalInstance = $modal.open({
      templateUrl: 'myModalContent.html',
      controller: ModalInstanceCtrl,
      resolve: {
        items: function () {
          return $scope.items;
        }
      }
    });

  /* modalInstance.result.then(function (selectedItem) {
      $scope.selected = selectedItem;
    }, function () {
      $log.info('Modal dismissed at: ' + new Date());
    });*/
  };
};

var ModalInstanceCtrl = function ($scope, $modalInstance, items) {
  console.log("I am in ModalInstanceCtrl");
  $scope.items = items;
 // $scope.selected = {
   // item: $scope.items[0]
  //};

 // $scope.ok = function () {
   // $modalInstance.close();
    //alert("hi");
  //};

  $scope.Reserv = function () {
    $modalInstance.dismiss('Reserv');
  };
};