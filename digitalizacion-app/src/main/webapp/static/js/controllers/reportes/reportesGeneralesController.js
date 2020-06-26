angular.module(appTeclo).controller("reportesGeneralesController", function($scope, config, reportesService,
                                                                            ModalService,
                                                                            showAlert, growl, $filter) {
	"use strict";
	/*Inicialización*/
/*	if(currentConfiguration.data === '' || currentConfiguration.data === null || currentConfiguration.data.reportesAsig.length <= 0){
		$scope.currentConfiguration = null;
		$scope.stPermisoAcceso = false;
		growl.error($scope.mensajeModal('No tiene ningún reporte configurado para visualizar'),{
			ttl: 4000,
			disableCountDown: true
		});
		return;
	}else{
		$scope.stPermisoAcceso = true;
		$scope.currentConfiguration = currentConfiguration.data;
		reportesService.setConfiguracion($scope.currentConfiguration);
		listaOpciones($scope.currentConfiguration.permisoAccesoUsuario.idPermisoAcceso);
	}*/
	
	
	$scope.direccionCatalogos = undefined;
	$scope.listCatReportes = [];
	$scope.mostrarBotonMinus=true;
	$scope.nombreReporte="";
	$scope.isCollapsed=false;
	$scope.stPermisoAcceso = true;
	$scope.listaAgrupada = [];
	
	
	function listaOpciones() {
		reportesService.reportesConfigurados()
			.success(function(data) {
				if(data == null || data === ""){
					$scope.listCatReportes = [];
					growl.warning($scope.mensajeModal('No tiene ningún reporte configurado para visualizar'),{
						ttl: 4000,
						disableCountDown: true
					});
				}else{
					$scope.listCatReportes = configReporte(data);
				}
			}).error(function(data) {
				$scope.error = data;
				$scope.listCatReportes = [];
			});
		
	}
	/*Llamadas de inicialización*/
	listaOpciones();
	
	
	let configReporte = function(allReportes) {
		let listReturn = [];
		angular.forEach(allReportes, function(reporte, key){
			if (reporte.idPadre === 0) {
				reporte.subReportes = [];
				angular.forEach(allReportes, function(subReporte, key){
					if (reporte.idCtrlCatReporte === subReporte.idPadre) {
						reporte.subReportes.push(subReporte);
					}
				});
				listReturn.push(reporte);
			}
		});
		
		if(listReturn.length > 0){
			$scope.lAux = [];
			for(var i = 0; i< listReturn.length; i++){
				if(listReturn[i].subReportes.length >= 1){
					$scope.lAux.push(listReturn[i]);
				}
			}
		}
		angular.copy($scope.lAux,listReturn);
		listReturn=reloadListOrdenada(listReturn,'nombre');
		
		agruparLista(listReturn);
		
		return listReturn;
	};
	
	function reloadListOrdenada(lista,nameOrderProperty){
		return $filter('orderBy')(lista, nameOrderProperty,false);
	}
	
	function agruparLista (l){
		let many = 3, object;
		$scope.listaAgrupada = [];
		
		if(l.length > 0){
			for(var i = 0; i < l.length; i+= many){
				object = {
					reporte1: l[i]
				};
				
				if (l[i + 1] && (many === 2 || many === 3)) {
					object.reporte2 = l[i + 1];
					
				}
				if (l[i + (many - 1)] && many === 3) {
					object.reporte3 = l[i + 2];
				}
				$scope.listaAgrupada.push(object);
			}
		}
	}
	
	$scope.mostrarPanelTipoReporte=function(tipoReporte,ruta,nbReporte){
		
		if(tipoReporte !== undefined && tipoReporte==="REGRESAR"){
			$scope.minimPanelBusqueda=false;
			$scope.classIconBoxPrincipal="fa fa-minus";
			$scope.mostrarBotonMinus=true;
			$scope.isCollapsed=false;
		}else if(tipoReporte === "minPanel"){
			$scope.isCollapsed=true;
			$scope.classIconBoxPrincipal="fa fa-plus";
			$scope.mostrarBotonMinus=false;
		}else{
			$scope.minimPanelBusqueda=true;
			$scope.nombreReporte=nbReporte;
			$scope.direccionCatalogos =ruta;
			$scope.classIconBoxPrincipal="fa fa-plus";
			$scope.mostrarBotonMinus=false;
			$scope.isCollapsed=true;
		}
	};
	
	
	$scope.cambiaIcono=function(fafIconReportes){
		
		if(fafIconReportes === "fa fa-chevron-down")
			fafIconReportes="fa fa-chevron-up";
		else
			fafIconReportes="fa fa-chevron-down";
		
		return fafIconReportes;
	}
});