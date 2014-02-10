"use strict";

/* jasmine specs for controllers go here */

describe("myApp", function(){

    beforeEach(module("myApp"));

    beforeEach(function(){
        this.addMatchers({
            toEqualData: function(expected) {
                return angular.equals(this.actual, expected);
            }
        });
    });

    describe("CompanyListCtrl", function(){
        var scope, ctrl, $httpBackend;


        beforeEach(inject(function(_$httpBackend_, $rootScope, $controller){
            $httpBackend  = _$httpBackend_;
            $httpBackend.expectGET("http://localhost:8080/companies/all").respond([
                {   "slug":"google",
                    "name": "Google",
                    "sentiment":0.9},
                {   "slug":"motorola",
                    "name": "Motorola",
                    "sentiment":0.7},
                {   "slug":"ibm",
                    "name": "IBM",
                    "sentiment":0.4}
            ]);

//            $httpBackend.expectGET("/rest/companies/analyses").respond([
//                {   "slug":"google",
//                    "name": "Google",
//                    "sentiment":0.9},
//                {   "slug":"motorola",
//                    "name": "Motorola",
//                    "sentiment":0.7},
//                {   "slug":"ibm",
//                    "name": "IBM",
//                    "sentiment":0.4}
//            ]);

            scope = $rootScope.$new();
            ctrl = $controller("CompanyListCtrl",{$scope:scope});
        }));

        it("should create company list with 3 companies",function(){
            $httpBackend.flush();

            expect(scope.companies.length).toBe(3);
        });

        it("should set default value of orderProperty", function(){
            expect(scope.orderProperty).toBe("name");
        });

        it("should add company to analysisCompanies on function addCompany", function(){
            expect(scope.analysisCompanies.length).toBe(0);
            scope.addCompany("IBM");
            expect(scope.analysisCompanies[0]).toBe("IBM")
            expect(scope.analysisCompanies.length).toBe(1);
        });

        it("should add company to analysisCompanies on function addCompany", function(){
            expect(scope.analysisCompanies.length).toBe(0);
            scope.addCompany("IBM");
            expect(scope.analysisCompanies[0]).toBe("IBM")
            expect(scope.analysisCompanies.length).toBe(1);
        });

        it("should remove company from analysisCompanies on function removeCompany", function(){
            expect(scope.analysisCompanies.length).toBe(0);
            scope.removeItem("IBM");
            expect(scope.analysisCompanies.length).toBe(0);
            scope.addCompany("IBM");
            expect(scope.analysisCompanies.length).toBe(1);
            scope.removeItem("IBM");
            expect(scope.analysisCompanies.length).toBe(0);
        });
    });

    describe("CompanyDetailCtrl", function(){
        var scope, ctrl, $httpBackend;

        beforeEach(module("myApp.controllers"));

        beforeEach(inject(function(_$httpBackend_, $rootScope,  $routeParams, $controller){
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET("http://localhost:8080/companies/ibm").respond(
                {"slug":"ibm","name":"IBM","sentiment":0.4}
            );

            $routeParams.companySlug = "ibm";
            scope = $rootScope.$new();
            ctrl = $controller("CompanyDetailCtrl",{$scope:scope});
        }));

        it("should fetch company detail",function(){
            $httpBackend.flush();

            expect(scope.company).toEqualData({"slug":"ibm","name":"IBM","sentiment":0.4});
        });

    });
});