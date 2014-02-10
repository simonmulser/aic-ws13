'use strict';

/* Controllers */

var myApp = angular.module('myApp.controllers', []);

myApp.controller('CloudScaleCtrl', ['$scope', '$rootScope','Company','Analysis','Cloud','$timeout','$location',
    function($scope, $rootScope, Company, Analysis, Cloud, $timeout,$location) {
        var activeAnalysisTab;
        var activeInstanceTab;
        $scope.orderProperty = 'name';

        $scope.companies = Company.query(function(){
            angular.forEach($scope.companies, function(value,key){
                value.addButtonDisabled = false;
            });
        });

        $scope.analysisCompanies = [];

        var pollerAnalyses = function() {
            Analysis.query(function(result){
                angular.forEach(result, function(value,key){
                    if(activeAnalysisTab == value.id){
                        value.activeAnalysisTab = true;
                    }
                    else{
                        value.activeAnalysisTab = false;
                    }
                });
                $scope.analyses = result;

            });

            $timeout(function () {
                pollerAnalyses();
            },4000);

        };
        pollerAnalyses();

        var pollerInstancePool = function () {
            Cloud.query(function(result){
                angular.forEach(result, function(value,key){
                    if(activeInstanceTab == value.id){
                        value.activeInstanceTab = true;
                    }
                    else{
                        value.activeInstanceTab = false;
                    }
                });
                $scope.instancePool = result;
            });
            $timeout(function () {
                pollerInstancePool();
            },4000);

        };
        pollerInstancePool();

        $scope.addCompanyToAnalysis = function(company) {
            company.addButtonDisabled = true;
            $scope.analysisCompanies.push(company);
        };

        $scope.removeItem = function(index) {
            var company = $scope.analysisCompanies.splice(index,1)[0];
            if(company != null){
                company.addButtonDisabled = false;
            }
        };

        $scope.saveAnalysisTab = function(id){
            activeAnalysisTab = id;
        };

        $scope.saveInstanceTab = function(id){
            activeInstanceTab = id;
        };

        $scope.startAnalysis = function(companies){
            var analysis = {};
            analysis.companies = {};
            analysis.companies = companies;
            Analysis.start(analysis);
            $scope.analysisCompanies = [];
            angular.forEach($scope.companies, function(value,key){
                value.addButtonDisabled = false;
            });
        };

        $scope.addCompany = function(company){
            Company.add(company,function(){
                Company.query(function(result){
                    angular.forEach($scope.companies, function(value,key){
                        value.addButtonDisabled = false;
                    });
                    $scope.companies = result;
                });
            });
        };


    }]);

myApp.controller('GoogleAppEngineCtrl', ['$scope', '$rootScope','GAECompany','GAEAnalysis','$timeout','$location',
    function($scope, $rootScope, Company, Analysis, $timeout, $location) {
        var activeAnalysisTab;
        var activeInstanceTab;
        $scope.orderProperty = 'name';

        Company.query(function(result){
            $scope.companies = result;
                angular.forEach($scope.companies, function(value,key){
                value.addButtonDisabled = false;
            });
        });

        $scope.analysisCompanies = [];

        var pollerAnalyses = function() {
            Analysis.query(function(result){
                angular.forEach(result, function(value,key){
                    if(activeAnalysisTab == value.id){
                        value.activeAnalysisTab = true;
                    }
                    else{
                        value.activeAnalysisTab = false;
                    }
                });
                $scope.analyses = result;

            });

            $timeout(function () {
                pollerAnalyses();
            },4000);

        };
        pollerAnalyses();

        $scope.addCompanyToAnalysis = function(company) {
            company.addButtonDisabled = true;
            $scope.analysisCompanies.push(company);
        };

        $scope.removeItem = function(index) {
            var company = $scope.analysisCompanies.splice(index,1)[0];
            if(company != null){
                company.addButtonDisabled = false;
            }
        };

        $scope.saveAnalysisTab = function(id){
            activeAnalysisTab = id;
        };

        $scope.saveInstanceTab = function(id){
            activeInstanceTab = id;
        };

        $scope.startAnalysis = function(companies){
            var analysis = {};
            analysis.companies = {};
            analysis.companies = companies;
            Analysis.start(analysis);
            $scope.analysisCompanies = [];
            angular.forEach($scope.companies, function(value,key){
                value.addButtonDisabled = false;
            });
        };

        $scope.addCompany = function(company){
            Company.add(company,function(){
                Company.query(function(result){
                    angular.forEach($scope.companies, function(value,key){
                        value.addButtonDisabled = false;
                    });
                    $scope.companies = result;
                });
            });
        };


    }]);

myApp.controller('LoginCtrl', ['$scope', '$rootScope', '$location','User','GAEUser',
    function($scope, $rootScope, $location, User, GAEUser) {
        $rootScope.loggedUser = "";
        User.query(function(result){
            $scope.users = result;
        });
        GAEUser.query(function(result){
            $scope.users = result;
        });

        $scope.attemptLogin = function(user) {
            if(user != null){
                angular.forEach($scope.users, function(value,key){
                    if(user.username == value.username && user.password == value.password){
                        $rootScope.loggedUser = value.username;
                        $location.path( "/cloudscale" );
                        return;
                    }
                });
            }
            $scope.loginError = "invalid user/pass.";
        };
    }]);

