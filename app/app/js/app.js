'use strict';


// Declare app level module which depends on filters, and services
var app = angular.module('myApp', [
        'ui.bootstrap',
        'ngRoute',
        'myApp.filters',
        'myApp.services',
        'myApp.directives',
        'myApp.controllers'
    ]).
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/cloudscale', {templateUrl: 'partials/cloudScale.html', controller: 'CloudScaleCtrl'});
        $routeProvider.when('/googleappengine', {templateUrl: 'partials/googleAppEngine.html', controller: 'GoogleAppEngineCtrl'});
        $routeProvider.when('/login', {templateUrl: 'partials/login.html', controller: 'LoginCtrl'});
        $routeProvider.otherwise({redirectTo: '/login'});
    }]).config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
    ]).run( function($rootScope, $location) {
        $rootScope.location = $location;
        // register listener to watch route changes
        $rootScope.$on( "$rootChangeStart", function(event, next, current) {
            if ( $rootScope.loggedUser == null ) {
                // no logged user, we should be going to #login
                if ( next.templateUrl == "partials/login.html" ) {
                    // already going to #login, no redirect needed
                } else {
                    // not going to #login, we should redirect now
                    $location.path( "/login" );
                }
            }
        });
    });

