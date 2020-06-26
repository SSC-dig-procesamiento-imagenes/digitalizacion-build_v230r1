angular.module(appTeclo)
.service('validacionImagenesService',
function($rootScope,$http,$localStorage,$window,$q,$route,config) {
    "use strict";
    const myPath = "/validacionImagenes";

    this.tieneAsignacionesRest = function () {
        let retorno = $http.get(config.baseUrl + myPath + "/tieneAsignaciones");
	    return retorno;
    };

	this.getTodasMarcasVehiculos = function () {
		return $http.get(config.baseUrl + myPath + "/todasMarcasVehiculos");
    };

	this.asignacionInicial = function () {
		return $http.get(config.baseUrl + "/validacionImagenes/asignacionInicialVO");
	};
	
	this.getImagenVO = function(imagenSimple){
		return $http.get(config.baseUrl + "/validacionImagenes/imagenRealPorIdentificador",
				{
					params:{"idImagenSeleccionada" : imagenSimple.idImagen}
				}
		);
	};
	
	this.marcarImagenAceptada = function(imagenRealSeleccionada){
	    let retorno = $http.post(config.baseUrl + "/validacionImagenes/marcarImagenAceptada", imagenRealSeleccionada);
		return retorno;
	};

    this.isImagenAceptable = function(imagenRealSeleccionada){
        let retorno = $http.post(config.baseUrl + "/validacionImagenes/isImagenAceptable", imagenRealSeleccionada);
        return retorno;
    };
	this.marcarImagenRechazadaOld = function(idImagenSeleccionada, causa){

		let cancelarImagenVO = {
			"idImagenSeleccionada":idImagenSeleccionada,
			"causa" : causa,
		};
		return $http.post(config.baseUrl + "/validacionImagenes/marcarImagenRechazadaPorId", cancelarImagenVO);
	};

	this.marcarImagenRechazada = function(cancelarImagenVO){
		return $http.post(config.baseUrl + "/validacionImagenes/marcarImagenRechazadaPorId", cancelarImagenVO);
	};


	this.pruebaPersistencia = function(){
		return $http.get(config.baseUrl + "/validacionImagenes/pruebaPersistencia");
	};

	this.cambiaOrdenImagenes = function(idLBImagen){
		return $http.post(config.baseUrl + "/validacionImagenes/cambiarPosicionImagen", idLBImagen);
	};

	this.posponerBoleta = function(boletaPosponerVO){
		return $http.post(config.baseUrl + "/validacionImagenes/posponerBoleta", boletaPosponerVO);
	};

    this.posponerBoleta2 = function(causa, idImagen){
        return $http.post(config.baseUrl + "/validacionImagenes/posponerBoleta2",
            {
                params:{
                        "causa"     : causa,
                        "idImagen"  : idImagen
                }
            });
    };
});