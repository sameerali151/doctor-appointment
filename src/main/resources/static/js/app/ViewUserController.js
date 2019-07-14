'use strict'

var module = angular.module('appointment.controllers');
module.controller("ViewUserController", ["$scope", "UserService",
    function ($scope, UserService) {


        $scope.userDto = {
            userId: null,
            firstName: null,
            lastName: null,
            dob: null,
            appointmentDtos: []
        };
        $scope.appointments = [];

        $scope.userId = null;


        $scope.getUserById = function (id) {
            UserService.getUserById($scope.userId).then(function (value) {
                //console.log(value.data);
                $scope.allUsers = value.data;
            }, function (reason) {
                console.log("error occured");
            }, function (value) {
                console.log("no callback");
            });
        }


    }
]);
