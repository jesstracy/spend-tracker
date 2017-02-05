angular.module('SpendTrackerApp', [])
   .controller('SpendTrackerController', function($scope, $http, $window) {
        $scope.angularTest = "yes";

        $scope.getData = function() {
            console.log("In getData function in home-ng-controller");

            $http.post("/getData.json")
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.allSubmissions = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data...");
                    });
        };


    });