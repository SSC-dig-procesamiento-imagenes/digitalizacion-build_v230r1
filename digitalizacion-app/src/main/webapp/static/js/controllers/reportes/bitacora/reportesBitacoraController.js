angular.module(appTeclo).controller("reportesBitacoraController", function($scope, config, reportesBitacoraService,
                                                                            ModalService,
                                                                            showAlert, growl, $filter) {
	"use strict";
 
	$scope.listaConponentes=[];
	$scope.listaConceptos=[{conceptoId:0,componenteId:0,conceptoNombre:'Seleccione'}];
	$scope.listaBitacoraCambios=[];
	$scope.parametroBusquedaVO={};
	$scope.parametrosBusquedaExcelTmp={};
	$scope.parametroBusquedaVO={componenteVO:""};
	$scope.fechaInicio="";
	$scope.fechaFin="";
	$scope.disabledComboConceptos=true;
	$scope.componenteId=0;
	$scope.requiredConcepto=false;
	$scope.hideSeleccione=false;
	$scope.filterBusqueda=false;
	$scope.cabeceraFilter="";
	
	let ordenTablaDefault=function(){
		$scope.order="fechaModificacion";
		$scope.reverse=false;
	};
	
	let copyParametrosBusqueda=function(parametroBusquedaVO){
		angular.copy(parametroBusquedaVO, $scope.parametrosBusquedaExcelTmp);
	};
	
	let obtenerListaComponentesBitacora=function(){
		
		reportesBitacoraService.obtenerComponentes()
			.success(function(data){
				inicializaScripts();
				$scope.listaConponentes=data;
				$scope.disabledComboConceptos=true;
			}).error(function(data){
				showAlert.aviso(data.message);
				$scope.listaConponentes=[];
			});
	};
	
	$scope.changeObtenerListaConceptosBitacora=function(idComponente){
		
		
		$scope.limpia();
		
		if(idComponente != null && idComponente !== undefined ){
			reportesBitacoraService.obtenerConceptos(idComponente)
				.success(function(data){
					$scope.listaConceptos=data;
					$scope.requiredConcepto=true;
					$scope.disabledComboConceptos=false;
					$scope.hideSeleccione=true;
				}).error(function(data){
					$scope.hideSeleccione=false;
					showAlert.aviso(data.message);
					$scope.listaConceptos=[];
					$scope.disabledComboConceptos=true;
					$scope.requiredConcepto=false;
				});
		}else{
			$scope.hideSeleccione=false;
			$scope.listaConceptos=[];
			$scope.parametroBusquedaVO.conceptoVO=[];
			$scope.disabledComboConceptos=true;
			$scope.requiredConcepto=false;
		}
	};
	
	
	$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
		
		$('.selectpicker').selectpicker();
		$('.selectpicker').selectpicker('refresh');
		
		if($scope.listaConceptos.length > 4 ){
			$('.selectpicker').selectpicker();
			$('.selectpicker').selectpicker('refresh');
			$('.bs-searchbox').show();
		}else{
			$('.selectpicker').selectpicker();
			$('.selectpicker').selectpicker('refresh');
			$('.bs-searchbox').hide();
		}
		
		if($scope.listaConceptos.length > 1 ){
			$('.selectpicker').selectpicker();
			$('.selectpicker').selectpicker('refresh');
			$('.bs-actionsbox').show();
		}else{
			$('.selectpicker').selectpicker();
			$('.selectpicker').selectpicker('refresh');
			$('.bs-actionsbox').hide();
		}
		
	});
	
	$scope.consultarBitacoraCambios=function(parametrosVO){
		
		if ($scope.form.$invalid) {
			angular.forEach($scope.form.$error, function (field) {
				angular.forEach(field, function(errorField){
					errorField.$setDirty();
				})
			});
			showAlert.aviso('Formulario incompleto');
		}else{
			ordenTablaDefault();
			let objectParametros=
				{   componenteId:   parametrosVO.componenteVO.componenteId,
					conceptosId:    parametrosVO.conceptoVO,
					fechaInicio:    parametrosVO.fechaInicio === undefined ? "" : parametrosVO.fechaInicio+" 00:00:00",
					fechaFin:       parametrosVO.fechaFin === undefined ? "" : parametrosVO.fechaFin+" 23:59:59"
			};
			
			copyParametrosBusqueda(objectParametros);
			
			reportesBitacoraService.consultaBitacoraCambios(objectParametros)
				.success(function(data){
					let listaVO = data;
/*					for (let i = 0; i < listaVO.length; i++) {
						listaVO[i].fechaModificaInputFilter=$filter('date')(listaVO[i].fechaModificacion, "dd/MM/yyyy hh:mm:ss");
					}*/
					$scope.listaBitacoraCambios = listaVO;
					if(data.length === 0){
						showAlert.aviso('No se encontraron registros');
						
					}
				}).error(function(data){
					showAlert.aviso(data.message);
					$scope.listaBitacoraCambios = [];
				});
		}
	};
	
	$scope.consultaBitacoraCambiosDetallePorId=function(cambioId){
		reportesBitacoraService.consultaBitacoraCambiosPorId(cambioId)
			.success(function(data){
				$scope.bitacoraCambiosVO=data;
			}).error(function(data){
				showAlert.aviso(data.message);
				$scope.bitacoraCambiosVO={};
			
			});
	};
	
	function testCancelConfirmacion(){
	
	}
	$scope.confirmarDescargaExcelMovimientos = function(){
		showAlert.confirmacion("¿Seguro que desea exportar a excel los datos de la bitácora?",
			$scope.descargaExcelBitacoraCambios,$scope.object, testCancelConfirmacion);
	};
	$scope.descargaExcelBitacoraCambios = function(){
		reportesBitacoraService.descargaExcelBitacoraCambios($scope.parametrosBusquedaExcelTmp)
			.success(function(data,status, headers){
				let filename = headers('filename');
				let contentType = headers('content-type');
				let file = new Blob([ data ], {type : 'application/vnd.ms-excel;base64'});
				save(file, filename);
				$scope.error = false;
			}).error(function(data) {
				$scope.error = data;
				showAlert.aviso(data.message);
			});
	};
	
	function save(file, fileName) {
		let url = window.URL || window.webkitURL;
		let blobUrl = url.createObjectURL(file);
		let a = document.createElement('a');
		a.href = blobUrl;
		a.target = '_blank';
		a.download = fileName;
		document.body.appendChild(a);
		a.click();
	}
	
	
	$scope.limpia=function(){
		$scope.listaConceptos=[];
		$scope.parametroBusquedaVO.conceptoVO=[];
		$('.selectpicker').selectpicker('refresh');
	};
	
/*	showAviso = function(messageTo) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalAviso.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
		});
	};*/
	
	let inicializaScripts =  function (){
		
		$(".select2").select2();
		
		$("#select2-componenteBitacora-container").text('Seleccione');
		
	};
	
	obtenerListaComponentesBitacora();
	ordenTablaDefault();

});