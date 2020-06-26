angular.module(appTeclo)
.service('reportesLotesService',
function($rootScope,$http,$localStorage,$window,$q,$route,config) {
    "use strict";

    let myPath = "/liberacionImagenes";

	this.estadosLotesIniciales = function () {
		return $http.get(config.baseUrl + myPath + "/estadosLotes");
    };
    
    this.consultaGeneral = function () {
		return $http.get(config.baseUrl + myPath + "/lotes");
	};

    this.consultaAvanzada = function (fechaInicial,fechaFinal,idEstatus) {
        return $http.get(config.baseUrl + myPath + "/lotesParametrizados",
                        {
                            params: {  "datos": {   "fechaInicial" : fechaInicial,
                                                    "fechaFinal" : fechaFinal,
                                                    "idEstatus" : idEstatus
                                                }
                                    }
                        }
        );
    };
    
    this.consultaAvanzadaPost = function (fechaInicial,fechaFinal,idEstatus) {
        let datos = {   "fechaInicial" : fechaInicial,
                    "fechaFinal" : fechaFinal,
                    "idEstatus" : idEstatus
                };

        return $http.post(config.baseUrl + myPath + "/lotesParametrizadosPost", datos );
    };
    
    this.cancelarLote = function(idLote){
        return $http.post(config.baseUrl + myPath + "/cancelarLote",idLote);
    };

    this.formarParaLiberarLote = function(idLote){
        return $http.post(config.baseUrl + myPath + "/formarParaLiberarLote",idLote);
    };

    this.enValidandoInformacion = function(idLote){
        return $http.post(config.baseUrl + myPath + "/enValidandoInformacion",idLote);
    };

    /*Devuelve las imágenes del idLoteSeleccionado*/
    this.imagenesPorLote = function (idLoteSeleccionado) {
        return $http.get(config.baseUrl + myPath + "/imagenesPorLote",
            {
                params: {  "idLoteSeleccionado": idLoteSeleccionado}
            })
    };

    this.blobsPorImagen = function(idImagenSeleccionada) {
        return $http.get(config.baseUrl + myPath + "/imagenesBlob",
            {
                params: {  "idImagenSeleccionada": idImagenSeleccionada}
            })
    };

    this.getFicheroExcel= function(listaIdLotes) {
        let jsonListaIdLotes = angular.toJson(listaIdLotes);

        let respuesta;
        respuesta = $http({
            method: 'GET',
            url: config.baseUrl + myPath + "/ficheroExcelBusqueda",
            params: {idLotes :listaIdLotes },
            dataType: "json",
            header :{ "Content-type": "application/json",
                "Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            },
            responseType: 'arraybuffer'
        });

        return respuesta;

    };
    
    this.getFicheroExcelB= function(listaIdLotes) {
        let jsonListaIdLotes = angular.toJson(listaIdLotes);
        
        let respuesta;
        respuesta = $http({
            method: 'GET',
            url: config.baseUrl + myPath + "/ficheroExcelBusquedaB",
            params: {idLotes :listaIdLotes },
            dataType: "json",
            header :{ "Content-type": "application/json",
                "Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            },
            responseType: 'arraybuffer'
        });
        
        return respuesta;
        
    }
    
    
});