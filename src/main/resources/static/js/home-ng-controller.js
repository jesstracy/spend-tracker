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
                        //$scope.allSubmissions = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data...");
                    });
        };

        $scope.submitTransaction = function(date, name, amount, type, medium, category) {
            console.log("In submitTransaction function in home-ng-controller");

            var newTransaction = {
                date: date,
                name: name,
                amount: amount,
                type: type,
                medium: medium,
                category: category
            }

            console.log(newTransaction);

            $http.post("/submitTransaction.json", newTransaction)
                .then(
                    function successCallback(response) {
                        //console.log(response.data);
                        //console.log("Adding data to scope");
                        //$scope.allSubmissions = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data...");
                    });
        };

        $scope.getAllTransactions = function() {
            console.log("In getAllTransactions function in home-ng-controller");

            $http.post("/getAllTransactions.json")
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.allTransactions = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data...");
                    });
        };

        $scope.getTransactionsByType = function(type) {
            console.log("In getTransactionsByType function in home-ng-controller");

            $http.post("/getTransactionsByType.json", type)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.allTransactions = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data...");
                    });
        };

    });