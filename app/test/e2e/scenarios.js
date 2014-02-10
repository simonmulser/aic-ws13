'use strict';

/* http://docs.angularjs.org/guide/dev_guide.e2e-testing */

describe('my app', function() {

    describe('company list view', function(){
        beforeEach(function() {
            browser().navigateTo('app/index.html');
        });

        it('should be 3 companies in the company list', function(){
            expect(repeater('.companies div.panel').count()).toBe(3);
        });

        it('should be 1 company when entering IBM', function(){
            input('query').enter('IBM');

            expect(repeater('.companies div.panel').count()).toBe(1);
        });

        it('should be possible to control the ordering over the dropdown', function(){
            select('orderProperty').option('name');

            expect(repeater('.companies div.panel').column('company.name')).toEqual(['Google','IBM','Motorola']);

            select('orderProperty').option('sentiment');

            expect(repeater('.companies div.panel').column('company.name')).toEqual(['IBM','Google','Motorola']);
        });

        it('should redirect index.html to index.html#/companies', function(){
            expect(browser().location().url()).toBe('/companies');
        });

        it("should render company specific links", function(){
            input("query").enter("IBM");

            element('.companies div.panel a').click();
            expect(browser().location().url()).toBe("/companies/ibm");
        });

        describe('add companies IBM,Google,Motorola to analysisList', function(){
            beforeEach(function() {
                input("query").enter("IBM");
                element('.companies div.panel-heading button:contains(analyze)').click();
                input("query").enter("Google");
                element('.companies div.panel-heading button:contains(analyze)').click();
                input("query").enter("Motorola");
                element('.companies div.panel-heading button:contains(analyze)').click();
            });


            it("should add companies to analysisList", function(){
                expect(repeater('.analysisCompanies div.panel').column('item.name')).toEqual(['IBM','Google','Motorola']);
            });

            describe('and then remove Motorola from analysisList', function(){
                beforeEach(function() {

                    element('.analysisCompanies div.panel-heading button:contains(remove):last').click();

                });

                it("should remove Motorola", function(){

                    expect(repeater('.analysisCompanies div.panel').column('item.name')).toEqual(['IBM','Google']);
                });
            });


//            describe('start analysis', function(){
//                beforeEach(function() {
//                    element('button:contains(Start Analysis)').click();
//                });
//
//
//                it("remove all items from analysis list", function(){
//                    expect(element('.analysisCompanies div.panel').count()).toBe(0);
//                });
//
//            });
        });
    });

    describe('company detail view', function(){

        beforeEach(function(){
            browser().navigateTo('app/index.html#companies/ibm');
        });

        it('should display IBM company', function(){
            expect(element('h1').text()).toBe('IBM');
        });
    });
});
