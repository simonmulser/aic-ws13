'use strict';

/* Services */
var cloudscaleURI = "http://localhost:8080";

//local
var gaeURI = "http://localhost:8008/rest";
//remote
//var gaeURI = "https://aic20132014.appspot.com/rest";

angular.module('myApp.services', ['ngResource'])
    //******** cloudscale ********
    .factory("Company",["$resource",function($resource){
        return $resource(cloudscaleURI + "/company/:companySlug",{},{
            add: {method:"POST"},
            query: {method:"GET", params:{companySlug:'all'}, isArray:true}
        });
    }])
    .factory("Cloud",["$resource",function($resource){
        return $resource(cloudscaleURI + "/cloud/instancepool/all",{},{
            query: {method:"GET"}
        });
    }])
    .factory("User",["$resource",function($resource){
        return $resource(cloudscaleURI + "/user/all",{},{
            query: {method:"GET", isArray:true}
        });
    }])
    .factory("Analysis", ["$resource",function($resource){
        return $resource(cloudscaleURI + "/analysis/:analysisId",{},{
            start: {method:"POST"},
            query: {method:"GET", params:{analysisId:'all'}, isArray:true}
        });

    }])
    //******** google app engine ********
    .factory("GAECompany",["$resource",function($resource){
        return $resource(gaeURI + "/company/:companySlug",{},{
            add: {method:"POST"},
            query: {method:"GET", params:{companySlug:'all'}, isArray:true}
        });
    }])
    .factory("GAEUser",["$resource",function($resource){
        return $resource(gaeURI + "/user/all",{},{
            query: {method:"GET", isArray:true}
        });
    }])
    .factory("GAEAnalysis", ["$resource",function($resource){
        return $resource(gaeURI + "/analysis/:analysisId",{},{
            start: {method:"POST"},
            query: {method:"GET", params:{analysisId:'all'}, isArray:true}
        });

    }]);
