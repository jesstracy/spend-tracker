angular.module('SpendTrackerApp', ["chart.js"])
    .config(['ChartJsProvider', function (ChartJsProvider) {
        // Configure all charts
        ChartJsProvider.setOptions({
          chartColors: ['#FF5252', '#4286f4'],
          responsive: false
//          type: bar
        });
        // Configure all line charts
        ChartJsProvider.setOptions('line', {
          showLines: true
        });
        ChartJsProvider.setOptions('bar', {
          showLines: true
        });
    }])
   .controller('SpendTrackerController', function($scope, $http, $window) {
        $scope.angularTest = "yes";

        // When for realz-- make sure you set loginScreen here to true and take out currentUser!!!
        $scope.loginScreen = false;
        $scope.currentUser = {
            id: 11,
            email: "jt@tiy.com",
            password: "pass",
            paycheck: 1634.36
        }

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
                        $scope.loginScreen = false;
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
                paycheck: paycheck
            }

            $http.post("/register.json", userInfo)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.currentUser = response.data;
                        $scope.loginScreen = false;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data...");
                    });
        };

        $scope.logout = function() {
            console.log("In logout function in home-ng-controller");
            $scope.loginScreen = true;
            $scope.allTransactions = {};
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

            if (amount === "PYCHK-AMT") {
                newTransaction.amount = $scope.currentUser.paycheck;
            }

            console.log(newTransaction);

            $http.post("/submitTransaction.json", newTransaction)
                .then(
                    function successCallback(response) {
                        //console.log(response.data);
                        //console.log("Adding data to scope");
                        //$scope.allSubmissions = response.data;
                        $scope.date = undefined;
                        $scope.name = undefined;
                        $scope.amount = undefined;
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

        $scope.submitDisplayOptions = function(date, type, medium, category) {
            console.log("In submitDisplayOptions function in home-ng-controller");

            if (date === "") {
                date = undefined;
            }
            if (type === "Deselect") {
                type = undefined;
            }
            if (medium === "Deselect") {
                medium = undefined;
            }
            if (category === "Deselect") {
                category = undefined;
            }

            var displayCriteriaAsTransaction = {
                date: date,
                type: type,
                medium: medium,
                category: category
            }

            console.log("This is the object I'm sending to backend:");
            console.log(displayCriteriaAsTransaction);

            $http.post("/submitDisplayOptions.json", displayCriteriaAsTransaction)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.allTransactions = response.data.transactions;
                        $scope.balance = response.data.balance;
                        updateGraph(response.data)
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data...");
                    });
        };


        // it's getting passed all the transactions!! A ContainerTransactionsAndBalance object.
        // An arraylist of transactions and an overall balance.
        // Each transaction has name and amount etc.
        var updateGraph = function(container) {
            // call this in submit display options!
            console.log("In updateGraph function in home-ng-controller");
            // we get back an arrayList of transactions and overall balance.
            // For now we'll just make it show each category and how much we've spent?
            // Also eventually can maybe either not return balance separately and do all logic on js side, OR do all logic on backend.

//            getCorrectAverage(assignment);
//            var gradeCategories = ["0-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60-69", "70-79", "80-89", "90-99", "100+"];
//
//            $scope.labels = gradeCategories;
            var categoryLabels = ["Grocery", "Gas", "Restaurant", "Entertainment", "Monthly Bill", "Other"];
//            var catGrocery = new Array();
//            var catGas = new Array();
//            var catRestaurant = new Array();
//            var catEntertainment = new Array();
//            var catMonthlyBill = new Array();
//            var catPaycheck = new Array();
//            var catOther = new Array();
            // [0] grocery
            // [1] gas
            // [2] restaurant
            // [3] entertainment
            // [4] monthly bill
            // [5] paycheck
            // [6] other
            var balances = [0, 0, 0, 0, 0, 0];

            // this way gets only the categories that are present!! saving for if I want it later. For now just want to show all, even if $0
//            for (i = 0; i < container.transactions.length; i++) {
//                //check if the category of the transaction is already in the list
//                alreadyInCategoryList = false;
//                for (j = 0; j < categoryLabels.length; j++)  {
//                    if (container.transactions[i].category === categoryLabels[j]) {
//                        alreadyInCategoryList = true;
//                        break;
//                    }
//                }
//                //If it's not in the category list, add it.
//                if (!alreadyInCategoryList) {
//                    categoryLabels.push(container.transactions[i].category);
//                }
//                //Add the transaction to the correct group to get the balance
//            }

            for (i = 0; i < container.transactions.length; i++) {
                if (container.transactions[i].category === "Grocery") {
                    if (container.transactions[i].type === "Deposit") {
                        balances[0] -= container.transactions[i].amount;
                    } else if (container.transactions[i].type === "Withdrawal") {
                        balances[0] += container.transactions[i].amount;
                    }
                } else if (container.transactions[i].category === "Gas") {
                    if (container.transactions[i].type === "Deposit") {
                        balances[1] -= container.transactions[i].amount;
                    } else if (container.transactions[i].type === "Withdrawal") {
                        balances[1] += container.transactions[i].amount;
                    }
                } else if (container.transactions[i].category === "Restaurant") {
                    if (container.transactions[i].type === "Deposit") {
                        balances[2] -= container.transaction[i].amount;
                    } else if (container.transactions[i].type === "Withdrawal") {
                        balances[2] += container.transactions[i].amount;
                    }
                } else if (container.transactions[i].category === "Entertainment") {
                    if (container.transactions[i].type === "Deposit") {
                        balances[3] -= container.transactions[i].amount;
                    } else if (container.transactions[i].type === "Withdrawal") {
                        balances[3] += container.transactions[i].amount;
                    }
                } else if (container.transactions[i].category === "Monthly Bill") {
                    if (container.transactions[i].type === "Deposit") {
                        balances[4] -= container.transactions[i].amount;
                    } else if (container.transactions[i].type === "Withdrawal") {
                        balances[4] += container.transactions[i].amount;
                    }
//                } else if (container.transactions[i].category === "Paycheck") {
//                    if (container.transactions[i].type === "Deposit") {
//                        balances[5] -= container.transactions[i].amount;
//                    } else if (container.transactions[i].type === "Withdrawal") {
//                        balances[5] += container.transactions[i].amount;
//                    }
                } else if (container.transactions[i].category === "Other") {
                    if (container.transactions[i].type === "Deposit") {
                        balances[5] -= container.transactions[i].amount;
                    } else if (container.transactions[i].type === "Withdrawal") {
                        balances[5] += container.transactions[i].amount;
                    }
                } else {
                    console.log("transaction category did not match: " + container.transactions[i].category)
                }
            }
            console.log(categoryLabels);
            console.log(balances);
            // Next will actually make these the graph labels.
            // Eventually what I really want is each category on the x-axis and amount spent on the y-axis.

            $scope.labels = categoryLabels;
//
//            $scope.series = ['Original Grades', 'Curved/Modified Grades'];
//            $scope.data = [
//                4,1,3
//            ];

            $scope.data = balances;

            $scope.onClick = function (points, evt) {
                console.log(points, evt);
            };
            $scope.datasetOverride = [
                {
                    yAxisID: 'y-axis-1'
                },
                {
                    xAxisID: 'x-axis-1'
                }

            ];
            $scope.options = {
//                title: {
//                    display: true,
//                    text: 'Grade Distribution Graph for ' + assignment.name
//                },
//                legend: {
//                    display: true,
//                    position: 'top'
//                },
                scales: {
                  yAxes: [
                    {
//                      id: 'y-axis-1',
//                      type: 'linear',
//                      display: true,
//                      position: 'left',
                      ticks: {
                        min: 0,
//                        max: 100,
                        beginAtZero: true
                      },
                      scaleLabel: {
                              display: true,
                              labelString: 'dollars'
                      }
                    }
                  ],
                  xAxes: [
//                      {
//                        id: 'x-axis-1',
//                        display: true,
//                        position: 'bottom',
//                        scaleLabel: {
//                                display: true,
//                                labelString: 'Grade on assignment'
//                        }
//                      }
                    ]
                }
            }
        };

    });




























