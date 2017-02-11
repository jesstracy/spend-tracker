angular.module('SpendTrackerApp', [])
   .controller('SpendTrackerController', function($scope, $http, $window) {
        $scope.angularTest = "yes";

        $scope.login = function(email, password) {
            console.log("In login function in home-ng-controller");

            var userInfo = {
                email: email,
                password: password
            }

            $http.post("/login.json", userInfo)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.currentUser = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data...");
                    });
        };

        $scope.register = function(email, password, paycheck) {
            console.log("In register function in home-ng-controller");

            var userInfo = {
                email: email,
                password: password,
                paycheck = paycheck
            }

            $http.post("/register.json", userInfo)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.currentUser = response.data;
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

        $scope.getTransactionsByDate = function(date) {
            console.log("In getTransactionsByDate function in home-ng-controller");

            $http.post("/getTransactionsByDate.json", date)
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

        $scope.getBalance = function(month) {
            console.log("In getBalance function in home-ng-controller");

            $http.post("/getBalance.json", month)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.monthlyBalance = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data...");
                    });
        };

        $scope.deleteTransaction = function(id) {
            console.log("In deleteTransaction function in home-ng-controller");

            $http.post("/deleteTransaction.json", id)
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