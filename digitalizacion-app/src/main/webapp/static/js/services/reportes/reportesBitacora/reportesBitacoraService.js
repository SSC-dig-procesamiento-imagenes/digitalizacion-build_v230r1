angular.module(appTeclo).service('reportesBitacoraService', function($http, config, $timeout) {
	"use strict";
	
	let myPath = "/reportesGenerales";
	
	this.obtenerComponentes = function(){
		return $http.get(config.baseUrl + myPath + "/consultaComponentesBitacora");
	};
	
	this.obtenerConceptos = function(componenteId){
		return $http.get(config.baseUrl + myPath +  "/consultaConceptosBitacora", {
			params : {
				"componenteId" : componenteId
			}
		});
	};
	
	this.consultaBitacoraCambios=function(parametrosBusquedaV){
		let parametrosBusquedaVO={
			componenteId : parametrosBusquedaV.componenteId,
			conceptosId : parametrosBusquedaV.conceptosId,
			fechaInicio: parametrosBusquedaV.fechaInicio,
			fechaFin : parametrosBusquedaV.fechaFin
		};
		return $http.post(config.baseUrl + myPath + "/consultaBitacoraCambios",parametrosBusquedaVO);
	};
	
	this.consultaBitacoraCambiosPorId=function(cambioId){
		return $http.get(config.baseUrl + "/consultaBitacoraCambiosPorIdCambio",{
			params : {
				"cambioId" : cambioId
			}
		});
	};
	
	this.descargaExcelBitacoraCambios = function(parametrosBusqueda){
		
		let parametrosBusquedaVO={
			componenteId : parametrosBusqueda.componenteId,
			conceptosId : parametrosBusqueda.conceptosId,
			fechaInicio: parametrosBusqueda.fechaInicio,
			fechaFin : parametrosBusqueda.fechaFin
		};
		
		return $http({
			method: 'POST',
			data:parametrosBusquedaVO,
			url: config.baseUrl + myPath + "/generarReporteBitacora",
			header :{"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType: 'arraybuffer'
		});
		
	};
	
});