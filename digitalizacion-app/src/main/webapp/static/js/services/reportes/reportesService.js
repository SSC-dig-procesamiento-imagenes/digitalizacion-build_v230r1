angular.module(appTeclo).service('reportesService', function($http, config,$timeout) {
	"use strict";
	
	let myPath = "/reportesGenerales";
	/* ----- Inicia Cognos  ---- */
	// Reporte Cognos
	this.reporteCognos = function(){
		return $http.get(config.baseUrl + "/obtenerCognos");
	};
	
	/* ----- Inicia reportes Turnos  ---- */
	
	// Reporte Cognos
	this.opcionesTurno = function(){
		return $http.get(config.baseUrl + "/opcionesReporteTurno", {});
	};
	
	// Buscar por estatus
	this.buscaEstatusActivoInactivo = function(){
		return $http.get(config.baseUrl + "/buscaEstatusActivoInactivoTurnos",{});
	};
	
	// Buscar por Fecha de creacion y estatus
	this.buscaTurnos = function(parametrosBusqueda){
		var json = " ";
		
		if(parametrosBusqueda){
			json =   angular.toJson(parametrosBusqueda);
		}
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/buscaTurnosFechas",
			params: { parametrosBusqueda :json },
			dataType: "json",
			contentType: {'Accept' : 'application/json'}
		});
		
	};
	
	this.descargaExcelTurnos = function(parametrosBusqueda){
		var json =   angular.toJson(parametrosBusqueda);
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/generarReporteTurnos",
			params: {parametrosBusqueda :json },
			dataType: "json",
			header :{ "Content-type": "application/json",
				"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType: 'arraybuffer'
		});
		
	};
	
	
	
	/* ----- Inicia reportes Movimientos por Empleado y turno  ---- */
	
	// Mostrar movimientos
	this.movimientosEmp = function () {
		return $http.get(config.baseUrl + "/movimientosEmp");
	};
	
	
	// Filtrar movimientos
	this.filtroMovEmp = function(valoresBusquedaVO){
		var json =  angular.toJson(valoresBusquedaVO);
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/filtrosMovEmp",
			params: {"valoresBusquedaVO": json}
		});
		
	};
	// Mostrar Nombre de empleados
	this.empleadosMovTurno = function(estatus){
		return $http.get(config.baseUrl + "/buscaEmpleadosTurnoPorNombre", {
			params : {
				"estatus" : estatus
			}
		});
	};
	
	
	// Mostrar movimientos de los empleados por turno
	this.movimientosEmpTurno = function () {
		return $http.get(config.baseUrl + "/movimientosEmpTurno");
	};
	
	// Mostrar movimientos de los empleados por turno
	this.movimientosEmpleados = function () {
		return $http.get(config.baseUrl + "/empleadosMovimientos");
	};
	
	// Mostrar movimientos de los empleados por turno
	this.empleadosTurno = function(valor){
		return $http.get(config.baseUrl + "/empleadosTurno", {
			params : {
				"valor" : valor
			}
		});
	};
	
	// Filtrar movimientos
	this.empleadosTurnoFiltros = function(filtrosBusquedaVO){
		var json =  angular.toJson(filtrosBusquedaVO);
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/filtrosEmpleadoTurno",
			params: {"filtrosBusquedaVO": json}
		});
		
	};
	
	
	// Descargar Excel Empleados por turno
	this.descargaExcelEmpTurnos = function(parametrosBusqueda){
		var json =   angular.toJson(parametrosBusqueda);
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/generarReporteEmpTurno",
			params: {parametrosBusqueda :json },
			dataType: "json",
			header :{ "Content-type": "application/json",
				"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType: 'arraybuffer'
		});
		
	};
	
	// Descargar Excel Movimientos por Empleado
	this.descargaExcelMovimientos = function(parametrosBusqueda){
		var json =   angular.toJson(parametrosBusqueda);
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/generarReporteMovi",
			params: {parametrosBusqueda :json },
			dataType: "json",
			header :{ "Content-type": "application/json",
				"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType: 'arraybuffer'
		});
		
	};
	
	//ÁREAS OPERATIVAS
	this.bucarAreasOperativas=function(paramBusquedaVO){
		var paramVOjson="";
		if(paramBusquedaVO){
			paramBusquedaVOjson =   angular.toJson(paramBusquedaVO);
		}
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/obtenerAreasOperativas",
			params: { paramBusquedaVO :paramBusquedaVOjson },
			dataType: "json",
			contentType: {'Accept' : 'application/json'}
		});
	}
	
	this.descargaExcelAreasOperativas=function(paramsBusquedaVO){
		var paramVOjson="";
		if(paramsBusquedaVO){
			paramVOjson =   angular.toJson(paramsBusquedaVO);
		}
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/creaExcelAreasOperativas",
			params: { paramsBusquedaVO :paramVOjson },
			dataType: "json",
			header :{ "Content-type": "application/json",
				"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType: 'arraybuffer'
		});
		
	};
	
	
	
	/*REPORTE ASISTENCIA*/
	
	this.comboAreaTrabjoReportes = function () {
		return $http.get(config.baseUrl + "/buscaAreasCombo");
	};
	
	this.getCatalogoCodigosAsistencia = function(){
		return $http.get(config.baseUrl + "/codigosAsistencia",{});
	}
	
	
	this.getReporteAsistencia = function(parametrosBusquedaVO){
		
		var paramBusquedaJSON = " ";
		
		if(parametrosBusquedaVO){
			paramBusquedaJSON =   angular.toJson(parametrosBusquedaVO);
		}
		return $http({
			method: 'GET',
			url: config.baseUrl + "/getReporteAsistencia",
			params: {"paramsBusquedaVO": paramBusquedaJSON}
		});
	};
	
	
	this.getReporteAusencias = function(parametrosBusquedaVO){
		
		var paramBusquedaJSON = " ";
		
		if(parametrosBusquedaVO){
			paramBusquedaJSON =   angular.toJson(parametrosBusquedaVO);
		}
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/getReporteAusencias",
			params: {"paramsBusquedaVO": parametrosBusquedaVO}
		});
	};
	
	this.getReporteRetardos = function(parametrosBusquedaVO){
		
		var paramBusquedaJSON = " ";
		
		if(parametrosBusquedaVO){
			paramBusquedaJSON =   angular.toJson(parametrosBusquedaVO);
		}
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/getReporteRetardos",
			params: {"paramsBusquedaVO":paramBusquedaJSON}
		});
	};
	
	this.getReporteVariacionesTm = function(parametrosBusquedaVO){
		
		var paramBusquedaJSON = " ";
		
		if(parametrosBusquedaVO){
			paramBusquedaJSON =   angular.toJson(parametrosBusquedaVO);
		}
		
		
		return $http.get(config.baseUrl + "/getReporteVariacionesTm",{
			params : {
				"paramsBusquedaVO" : paramBusquedaJSON
			}
		});
		
		
	};
	
	this.descargaExcelAsistencia = function(parametrosBusqueda){
		
		var paramBusquedaJSON = " ";
		
		if(parametrosBusqueda){
			paramBusquedaJSON =   angular.toJson(parametrosBusqueda);
		}
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/generarReporteAsistencia",
			params: {"paramsBusquedaVO": paramBusquedaJSON},
			dataType: "json",
			header :{ "Content-type": "application/json",
				"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType: 'arraybuffer'
		});
		
	};
	
	this.descargaExcelAusencias = function(parametrosBusqueda){
		
		var paramBusquedaJSON = " ";
		
		if(parametrosBusqueda){
			paramBusquedaJSON =   angular.toJson(parametrosBusqueda);
		}
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/generarReporteAusencias",
			params: {"paramsBusquedaVO": paramBusquedaJSON},
			dataType: "json",
			header :{ "Content-type": "application/json",
				"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType: 'arraybuffer'
		});
		
	};
	
	
	this.descargaExcelRetardos = function(parametrosBusqueda){
		
		var paramBusquedaJSON = " ";
		
		if(parametrosBusqueda){
			paramBusquedaJSON =   angular.toJson(parametrosBusqueda);
		}
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/generarReporteRetardos",
			params: {"paramsBusquedaVO": paramBusquedaJSON},
			dataType: "json",
			header :{ "Content-type": "application/json",
				"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType: 'arraybuffer'
		});
		
	};
	
	
	this.descargaExcelVariaciones = function(parametrosBusqueda){
		
		var paramBusquedaJSON = " ";
		
		if(parametrosBusqueda){
			paramBusquedaJSON =   angular.toJson(parametrosBusqueda);
		}
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/generarReporteVariacionesTm",
			params: {"paramsBusquedaVO": paramBusquedaJSON},
			dataType: "json",
			header :{ "Content-type": "application/json",
				"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType: 'arraybuffer'
		});
		
	};
	
	/*Función para mandar petición y comprobar si
	 * hay alguna configuración de búsqueda existente*/
	this.compruebaPermisosAcceso = function(){
		return $http.get(config.baseUrl + "/compruebaPermisosAcceso");
	};
	
	/*Módulo de consulta de asistencia*/
	this.buscarEmpeladosAreaPuesto = function(parametros){
		var json =   angular.toJson(parametros);
		return $http.get(config.baseUrl + "/buscarEmpeladosAreaPuesto",{
			params:{
				'parametros': json
			}
		});
	};
	
	this.consultaMovimientoEmpleados = function(parametros){
		var json =   angular.toJson(parametros);
		return $http.get(config.baseUrl + "/consultaMovimientoEmpleados",{
			params:{
				'parametros': json
			}
		});
	};
	this.descargaExcelMovimientos = function(parametrosBusqueda){
		var json =   angular.toJson(parametrosBusqueda);
		
		return $http({
			method: 'GET',
			url: config.baseUrl + "/generarReporteConsultaMovimiento",
			params: {'parametros' :json },
			dataType: "json",
			header :{ "Content-type": "application/json",
				"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType: 'arraybuffer'
		});
	};
	var configuracion;
	
	this.getConfiguracion = function(){
		return this.configuracion;
	};
	
	this.setConfiguracion = function(configuracion){
		return this.configuracion = configuracion;
	};
	
	/*Obtener los reportes configurados*/
	this.reportesConfigurados = function(){
		return $http.get(config.baseUrl + myPath+ "/reportesConfigurados");
	};
	
	this.save = function(file, fileName) {
		var url = window.URL || window.webkitURL;
		var blobUrl = url.createObjectURL(file);
		var a = document.createElement('a');
		a.href = blobUrl;
		a.target = '_blank';
		a.download = fileName;
		document.body.appendChild(a);
		//a.click();
		$timeout(function() {
			a.click();
		},100);
	};
	
});