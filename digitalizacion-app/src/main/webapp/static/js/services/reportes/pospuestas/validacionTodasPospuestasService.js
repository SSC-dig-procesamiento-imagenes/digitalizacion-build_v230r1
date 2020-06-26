angular.module(appTeclo).service('validacionTodasPospuestasService',
	function($rootScope,$http,$localStorage,$window,$q,$route,config) {
    "use strict";
    const myPath = "/validacionImagenes";

/*	this.tieneAsignacionesPospuestas = function () {
		let retorno = $http.get(config.baseUrl + myPath + "/tieneAsignacionesPospuestas");
		return retorno;
	};*/

	this.existenAsignacionesPospuestas = function(){
		let retorno = $http.get(config.baseUrl + myPath + "/existenAsignacionesPospuestas");
		return retorno;
	};

	this.getTodasMarcasVehiculos = function () {
		return $http.get(config.baseUrl + myPath + "/todasMarcasVehiculos");
    };

	this.buscarPospuestas = function () {
		return $http.get(config.baseUrl + myPath+ "/todasImagenesPospuestas");
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

	this.buscarTodosValidadores = function () {
		return $http.get(config.baseUrl + myPath+ "/todosValidadores");
	};

	this.imagenesPospuestasParam = function (buscaBoletasPospuestasVO) {
		return $http.get(config.baseUrl + myPath+ "/imagenesPospuestasParam",
			{
				params:{"idValidador" : buscaBoletasPospuestasVO.idValidador,
						"fechaInicial" : buscaBoletasPospuestasVO.fechaInicial,
						"fechaFinal" : buscaBoletasPospuestasVO.fechaFinal,
				}
			});
	}



});