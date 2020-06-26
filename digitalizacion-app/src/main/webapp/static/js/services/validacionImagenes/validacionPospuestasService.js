angular.module(appTeclo)
.service('validacionPospuestasService',
function($rootScope,$http,$localStorage,$window,$q,$route,config) {
    "use strict";
    const myPath = "/validacionImagenes";

    let imagenPospuestaSeleccionada = null;

    this.setImagenPospuestaSeleccionada = function (param) {
		imagenPospuestaSeleccionada = param;
	};

    this.getImagenPospuestaSeleccionada = function () {
		return 	imagenPospuestaSeleccionada;
	};

    this.setNullImagenPospuestaSeleccionada = function () {
		imagenPospuestaSeleccionada = null;
	};

	this.tieneAsignacionesPospuestas = function () {
		let retorno = $http.get(config.baseUrl + myPath + "/tieneAsignacionesPospuestas");
		return retorno;
	};

	this.getTodasMarcasVehiculos = function () {
		return $http.get(config.baseUrl + myPath + "/todasMarcasVehiculos");
    };

	this.buscarPospuestas = function () {
		return $http.get(config.baseUrl + "/validacionImagenes/imagenesPospuestasValidadorActivo");
	};
	
	this.marcarImagenAceptada = function(imagenRealSeleccionada){
	    let retorno = $http.post(config.baseUrl + "/validacionImagenes/marcarImagenAceptada", imagenRealSeleccionada);
		return retorno;
	};

    this.isImagenAceptable = function(imagenRealSeleccionada){
        let retorno = $http.post(config.baseUrl + "/validacionImagenes/isImagenAceptable", imagenRealSeleccionada);
        return retorno;
    };

	this.marcarImagenRechazadaOld = function(idImagenSeleccionada){
		return $http.post(config.baseUrl + "/validacionImagenes/marcarImagenRechazadaPorId", idImagenSeleccionada);
	};

	this.marcarImagenRechazada = function(cancelarImagenVO){
		return $http.post(config.baseUrl + "/validacionImagenes/marcarImagenRechazadaPorId", cancelarImagenVO);
	};

	this.cambiaOrdenImagenes = function(idLBImagen){
		return $http.post(config.baseUrl + "/validacionImagenes/cambiarPosicionImagen", idLBImagen);
	};

	this.blobsByIdImagen = function(idImagenSeleccionada){
		return $http.get(config.baseUrl + myPath+ "/blobsByIdImagen",
			{
				params:{"idImagenSeleccionada" : idImagenSeleccionada}
			});
	};

});