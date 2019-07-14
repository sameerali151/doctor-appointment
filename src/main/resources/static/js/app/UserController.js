'use strict'

var module = angular.module('appointment.controllers', []);
module.controller("UserController", ["$scope", "UserService",
    function ($scope, UserService) {

        //$scope.view1 = true;

        $scope.userDto = {
            userId: null,
            firstName: null,
            lastName: null,
            dob: null,
            appointmentDttm: null
        };
        $scope.appointments = [];

        $scope.saveUser = function () {
            /*$scope.userDto.appointmentDtos = $scope.appointments.map(appointment => {
                return {appointmentId: null, appointmentDateTime: appointment};
        })
            ;*/
            UserService.saveUser($scope.userDto).then(function () {
                console.log("works");
                UserService.getAllUsers().then(function (value) {
                    $scope.allUsers = value.data;
                }, function (reason) {
                    console.log("error occured");
                }, function (value) {
                    console.log("no callback");
                });

                $scope.appointments = [];
                $scope.userDto = {
                    userId: null,
                    firstName: null,
                    lastName: null,
                    dob: null,
                    appointmentDttm: null,
                };
            }, function (reason) {
                console.log("error occured");
            }, function (value) {
                console.log("no callback");
            });
        }
    }]);
