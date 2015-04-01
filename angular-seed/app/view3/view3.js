'use strict';

angular.module('myApp.view3', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view3', {
            templateUrl: 'view3/view3.html',
            controller: 'View3Ctrl'
        });
    }])

    .controller('View3Ctrl', ['$scope', '$http', function ($scope, $http) {
        $scope.name = "Tingxian";
        $scope.company = "EMC";

        //var stocks = new Array();
        //$scope.stocks = stocks;

        var url = 'http://webapp.perfsh6.example.com/rest/stocks/';
        var codes = ['sh600675', 'sh600489', 'sh600737', 'sh600884', 'sh601919',
            'sz002063'];
        var data = {
            id: codes
        };

        var config = {
            method: 'POST',
            url: url,
            header: {
                'Content-Type': 'application/json'
            },
            data: data
        };

        // send http request
        $http(config).
            success(function (data, status, headers, config) {
                console.log(status);
                console.log(data);
                $scope.stocks = data;
            }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    }]);