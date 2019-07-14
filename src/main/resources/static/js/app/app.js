'use strict'

var appointmentApp = angular.module('appointment', ['ngRoute', 'ui.bootstrap', 'appointment.controllers',
    'appointment.services']);
appointmentApp.constant("CONSTANTS", {
    getUserByIdUrl: "/user/getUser/",
    getAllUsers: "/user/getAllUsers",
    saveUser: "/user/saveUser"
});

appointmentApp.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "../../views/add-user.html",
            controller: "AddUserController"
        })
        .when("/view-user", {
            templateUrl: "../../views/view-user.html",
            controller: "ViewUserController"
        })
});
