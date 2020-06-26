angular.module(appTeclo)
.service('gestionAsignacionesService',
function($rootScope,$http,$localStorage,$window,$q,$route,config) {
    "use strict";
    const myPath = "/liberacionImagenes";

    this.getAsignaciones = function(){
        return $http.get(config.baseUrl + myPath +"/asignacionesActivas");
    };

    this.deleteAsignaciones = function (idValidador) {
        return $http.delete(config.baseUrl + myPath +"/asignacionesActivasValidador",
                            {
                                params:{"idValidadorSeleccionado" : idValidador}
                            }
                            );

    }


});