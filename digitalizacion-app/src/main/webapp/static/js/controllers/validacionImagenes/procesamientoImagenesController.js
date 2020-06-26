angular.module(appTeclo).controller("procesamientoImagenesController",
    function($rootScope, $scope, $location, $window, $timeout,liberacionImagenesService,growl, showAlert, config) {

        "use strict";
        $scope.crearArchivo = function(){
        	liberacionImagenesService
        	.crearArchivo()
    		.success(
    				function(data) {
    					if (data.success) {
    						var jnlpFile = config.sourceJnlp;
    			            deployJava.launchWebStartApplication(jnlpFile,'1.7');
    					} else {
    						console.log(data);
    						
    					}

    				}).error(function(data) {

    		});

        }
       
});/*Fin de liberacionImagenesController*/

