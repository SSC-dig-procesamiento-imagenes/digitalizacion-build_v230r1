angular.module(appTeclo)
.service('estadisticasValidadoresService',
function($rootScope,$http,$localStorage,$window,$q,$route,config) {
    const myPath = "/estadisticas";

    this.getListadoEstadisticoParametrizado = function(fechaInicial, fechaFinal){
        return $http.get(config.baseUrl + myPath +"/validadoresImagenesParametrizado",
                            {params:{"fechaInicial" : fechaInicial,
                                     "fechaFinal" : fechaFinal
                                    }
                            }
                        );
    };


    this.getListadoEstadisticoCompleto = function(){
        return $http.get(config.baseUrl + myPath +"/validadoresImagenes");
    };
});