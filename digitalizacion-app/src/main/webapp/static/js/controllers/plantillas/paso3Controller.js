angular.module(appTeclo).controller('paso3Controller', function($controller, $scope, $rootScope, $routeParams, $timeout, $location, $localStorage, WizardHandler, showAlert, growl, configAppService, ModalService) {
	$scope.dataCrudStep=new Object({
		stepOne:new Object({showViewOne:false}),
		stepTwo:new Object({showViewTwo:false}),
		stepThree:new Object({
			showViewThree:false,
			flagCordenadas:"",
			flagEdit:false,
			contadorCordenadaSelect:0,
			idGenerador:1,
			camposBackVO:new Object({id:"",ix:"",iy:"",fx:"",fy:""}),
			camposVO:new Object({id:"",ix:"",iy:"",fx:"",fy:""}),
			listCamposVO:new Array()		
		})
	});

	$scope.newCamposVO={};
	$scope.newFormCampos=undefined;
//VARIABLE DE MENSAJE DE AYUDA PARA TRAZAR EL AREA DEL CAMPO EN LA PLANTILLA
	$scope.view = new Object({
		rowsPerPageTwo: 3
		});
	$scope.txtMensaje="Selecciona cordenada de inicio y fin dando clic en la plantilla del lado izquierdo";

	  /* Modal Confirmacion */
				  $scope.showConfirmacion2 = function(messageTo, action){
					ModalService.showModal({
						templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
						controller: 'mensajeModalController',
							inputs:{ message: messageTo}
					}).then(function(modal) {
						modal.element.modal();
						modal.close.then(function(result) {
							if(result){
								action();
								$scope.onPreviewEdit($scope.newCamposVO);
							}else{
								$scope.onEdit($scope.dataCrudStep.stepThree.camposBackVO,"revertChange");
								$scope.onPreviewEdit($scope.newCamposVO);
							}
						}); 
					});
				};
			/*    */
		
	$scope.$on('$includeContentRequested', function(event, url) {
		$scope.dataCrudStep = event.currentScope.$parent.dataCrudStep;
	});	


	$scope.informationContactVO = {};
	$scope.listCamposVO=[];

	$scope.dataCrudStep.stepThree.flagEdit=false;
	
	$scope.showModalCrudCampo=false;
	$scope.codeOpeCrudCampo=undefined;
	$scope.txtModalCampo=undefined;

	//Funcion que continua con el wizar de asistente de pasos
	$scope.saveStepThree = function() {
				
		var wizardExample = WizardHandler.wizard('wizardExample');
		
		if($scope.formStepThree.$invalid) {
			showAlert.requiredFields($scope.formStepThree);
			growl.error('Formulario incompleto');
		} else {
			wizardExample.next();
		}
	};

	 //Funcion que se ejecuta al abrir modal de actualizar o nuevo campo
	 $scope.modalSaveOrUpdateCampo=function(ope,datos) {
        $scope.oldcaracterVO=angular.copy(datos);
        $scope.showModalCrudCampo=true;
        $scope.codeOpeCrudCampo=ope;
            if (ope=="C") {
                  $scope.txtModalCampo="Nueva Campo";
            }else{
            $scope.campoVO=angular.copy(datos);
            $scope.txtModalCampo="Actualizar Campo";
        }
	};
	//Funcion que se ejecuta al cerrrar modal de actualizar o nuevo campo
	$scope.funcionCerrarCampo=function() {
		$scope.modalSaveOrUpdate("C",undefined);
		$scope.campoVO={}; 
		$scope.txtModalCampo=undefined; 
		//$scope.campoForm.$setPristine();
};

//Se declaran tipos de busqueda
$scope.listOrientacion = [
		{idOri:1,cdOri:"AR-AB",nbOri:"Arriba-Abajo"},
		{idOri:2,cdOri:"AB-AR",nbOri:"Abajo-Arriba"},
		{idOri:1,cdOri:"IZ-DE",nbOri:"Izquierda-Derecha"},
		{idOri:2,cdOri:"DE-IZ",nbOri:"Derecha-Izquierda"},
	];

//FUNCION PARA AGREGAR UN NUEVO CAMPO A LA LISTA DE CAMPOS DE LA PLANTILLA
$scope.addCampo=function(campos,form){

	if (form.$invalid || $scope.dataCrudStep.stepThree.flagCordenadas==="") {
		showAlert.requiredFields(form);
		growl.error("Formulario Incompleto");
		
	}else{
		$scope.camposForm=form;
		campos.id=$scope.dataCrudStep.stepThree.idGenerador;

		var existCampo=false;
		var listCamposVO=angular.copy($scope.dataCrudStep.stepThree.listCamposVO);

		for (let i in listCamposVO) {
		if (listCamposVO[i].nombre === campos.nombre) {
			growl.warning("Nombre: "+"'"+campos.nombre+"'"+" Existente");
			existCampo=true;	 
	}
}
	if (!existCampo) {
		let item=angular.copy(campos);
		$scope.dataCrudStep.stepThree.listCamposVO.push(item);
		growl.success("Registro Agregado");
		$scope.dataCrudStep.stepThree.idGenerador++;
		$("#select2-respuesta-container").text("Seleccione una opcion");
		$("#select2-orientacion-container").text("Seleccione una opcion");
		$scope.dataCrudStep.stepThree.camposVO={};
		$scope.dataCrudStep.stepThree.flagCordenadas="";
		$scope.dataCrudStep.stepThree.contadorCordenadaSelect=0;
		$scope.camposForm.$setPristine();
	}
}
	
};
//FUNCION PARA REMOVER TRAZO ANTES DE AGREGAR EL CAMPO A LA LISTA
$scope.removeTrazoBeforeAdd=function(campos){
///	$scope.removerTrazo(campos);
	$scope.dataCrudStep.stepThree.flagCordenadas="";
	$scope.dataCrudStep.stepThree.camposVO.ix="";
	$scope.dataCrudStep.stepThree.camposVO.iy="";
	$scope.dataCrudStep.stepThree.camposVO.fx="";
	$scope.dataCrudStep.stepThree.camposVO.fy="";
	$scope.dataCrudStep.stepThree.contadorCordenadaSelect=0;	
	if($scope.dataCrudStep.stepThree.flagEdit){
		$scope.onEdit(campos,"beforeAdd");
	  }
	$scope.recargaPlantilla();
};

$scope.recargaPlantilla=function(){
	$scope.dibujarFilasxColumnas('canvas3','canvas3');
	if ($scope.dataCrudStep.stepThree.listCamposVO.length>0) {
		//Se llama funcion para trazar campos si el campo ya esta en la lista 
		$scope.trazarCordenadasAll('reCreate');
}else{
	$scope.removeListXY();
}

};


//FUNCION PARA ELIMINAR DE LA LISTA DE CAMPOS ASIGADOS A LA LISTA DE LA PLANTILLA
$scope.removeCampo=function(campos){
	$scope.dibujarFilasxColumnas('canvas3','canvas3');
	var eliminaCampo=false;
	var listCamposVO=angular.copy($scope.dataCrudStep.stepThree.listCamposVO);
	var elementosEncontrados=[];
        for (var i in listCamposVO) {
            if (listCamposVO[i].id === campos.id) {
				//$scope.removerTrazo(campos);
				$scope.dataCrudStep.stepThree.listCamposVO.splice(i, 1);
				i--;
				eliminaCampo=true;
			}	
		}
		if (eliminaCampo) {
			$scope.trazarCordenadasAll('reCreate');	
			growl.warning("registro eliminado");
			
				$("#select2-respuesta-container").text("Seleccione una opcion");
				$("#select2-orientacion-container").text("Seleccione una opcion");
				$scope.dataCrudStep.stepThree.camposVO={};
				$scope.dataCrudStep.stepThree.flagEdit=false;	
				$scope.dataCrudStep.stepThree.flagCordenadas="";
				$scope.camposForm.$setPristine();
		}else{
			growl.warning("No se pudo eliminar el campo");
		}
};


//FUNCION PARA TRAZAR TODAS LAS CORDENADAS
$scope.trazarCordenadasAll=function(accion){
	$scope.removeListXY();
	var trazaCordenada=false;
	var listCamposVO=angular.copy($scope.dataCrudStep.stepThree.listCamposVO);
	for (var i in listCamposVO) {
		if (listCamposVO[i].ix!="" && listCamposVO[i].iy!="" && listCamposVO[i].fx !="" && listCamposVO[i].fy!="")  {
			$scope.trazarCordenadas(listCamposVO[i].ix, listCamposVO[i].iy,listCamposVO[i].fx,listCamposVO[i].fy,accion,listCamposVO[i].cdColor,listCamposVO[i].nombre,listCamposVO[i].marca);
			trazaCordenada=true;
		}
	}
	return trazaCordenada;
};

$scope.validarCambios=function(campos,form){
	if ($scope.dataCrudStep.stepThree.flagEdit && !angular.equals($scope.dataCrudStep.stepThree.camposBackVO,$scope.dataCrudStep.stepThree.camposVO)) {
		$scope.showConfirmacion2("Se detectaron Cambios en Campo Actual¿Desea Guardar? ", function(){ $scope.onEdit($scope.dataCrudStep.stepThree.camposVO,"afterAdd",form)});
		//SE OBTIENE UNA NUEVA INSTACNIA DEL OBJETO VO
		$scope.newCamposVO=angular.copy(campos);
		///	$scope.newFormCampos=form;
		//$scope.onPreviewEdit(newCamposVO);
	}else{
		$scope.onPreviewEdit(campos);
	}
};
//FUNCION PREVIA QUE RECUPERA LOS VALORES DEL CAMPO ANTES DE SER EDITADO
$scope.onPreviewEdit=function(campos){
		if (campos.ix!="" && campos.iy!="" && campos.fx!="" && campos.fy!="") {
			$scope.dataCrudStep.stepThree.flagCordenadas="1";
		}else{
		$scope.dataCrudStep.stepThree.flagCordenadas="";
		}
	
		$scope.dataCrudStep.stepThree.flagEdit=true;
		$scope.dataCrudStep.stepThree.camposVO=angular.copy(campos);
		$("#select2-respuesta-container").text(campos.respuesta.nombre);
	
		for (let i in $scope.listRespuesta) {
			if (angular.equals($scope.listRespuesta[i],campos.respuesta)) {
				$scope.dataCrudStep.stepThree.camposVO.respuesta=$scope.listRespuesta[i];
			}
		}		
	
		$("#select2-orientacion-container").text(campos.orientacion.nbOri);
		for (let i in $scope.listOrientacion) {
			if (angular.equals($scope.listOrientacion[i],campos.orientacion)) {
				$scope.dataCrudStep.stepThree.camposVO.orientacion=$scope.listOrientacion[i];
			}
		}
		$scope.dataCrudStep.stepThree.camposBackVO=angular.copy(campos);	
};
//FUNCION PARA EDITAR CAMPO
$scope.onEdit=function(campos,accion,form){
	var formValid=false;

	if (form!=undefined) {
		if (form.$invalid || $scope.dataCrudStep.stepThree.flagCordenadas=="") {
			formValid=false;
			showAlert.requiredFields(form);
			growl.error("Formulario Incompleto");
			
		}else{
			formValid=true;
		}
	}
  
if ((formValid && accion=="afterAdd")|| (accion!="afterAdd") ) {
	

	var actualizar=false;
	for (const k in $scope.dataCrudStep.stepThree.listCamposVO) {
		if ($scope.dataCrudStep.stepThree.listCamposVO[k].id==campos.id) {
			$scope.dataCrudStep.stepThree.listCamposVO[k]=angular.copy(campos);
			actualizar=true;
		}
	}
	if (actualizar) {
		if (accion=="afterAdd") {
			$scope.dataCrudStep.stepThree.flagEdit=false;
			$scope.dataCrudStep.stepThree.flagCordenadas="";
			$scope.dataCrudStep.stepThree.camposVO={};
			growl.success("Registro Actualizado");
			$scope.recargaPlantilla();
			$("#select2-respuesta-container").text("Seleccione una opcion");
			$("#select2-orientacion-container").text("Seleccione una opcion");
			$scope.camposForm.$setPristine();
			$scope.dataCrudStep.stepThree.contadorCordenadaSelect=0;
		}else if (accion=="revertChange") {
			$scope.recargaPlantilla();	
		}
		
	}else if (!actualizar && accion=="afterAdd") {
		growl.warning("Hubo un Problema Al Actualizar");
	}
}

	
};

$scope.reCrearTrazoSinAgregar=function(){
	if ($scope.dataCrudStep.stepThree.flagCordenadas!="") {
		var id=$scope.dataCrudStep.stepThree.camposVO.id;
		var ix=$scope.dataCrudStep.stepThree.camposVO.ix;
		var iy=	$scope.dataCrudStep.stepThree.camposVO.iy;
		var fx= $scope.dataCrudStep.stepThree.camposVO.fx;
		var fy=$scope.dataCrudStep.stepThree.camposVO.fy;
		var accion='create';
		var cdColor=$scope.dataCrudStep.stepThree.camposVO.cdColor;
		var nombre=$scope.dataCrudStep.stepThree.camposVO.nombre;
		var marca=$scope.dataCrudStep.stepThree.camposVO.marca;
		$scope.removeListXY();
		$scope.dibujarFilasxColumnas('canvas3','canvas3');
	if ($scope.dataCrudStep.stepThree.listCamposVO.length>0) {
			//Se llama funcion para trazar campos si el campo ya esta en la lista 
			$scope.trazarCordenadasAllByRadio('reCreate',id);
	}
		//$scope.recargaPlantilla();
		$scope.trazarCordenadas(ix,iy,fx,fy,accion,cdColor,nombre,marca);
	}
};

//FUNCION PARA TRAZAR TODAS LAS CORDENADAS
$scope.trazarCordenadasAllByRadio=function(accion,id){
	var trazaCordenada=false;
	var listCamposVO=angular.copy($scope.dataCrudStep.stepThree.listCamposVO);
	for (var i in listCamposVO) {
		if (listCamposVO[i].ix!="" && listCamposVO[i].iy!="" && listCamposVO[i].fx !="" && listCamposVO[i].fy!="")  {
			if(id!=listCamposVO[i].id){
				$scope.trazarCordenadas(listCamposVO[i].ix, listCamposVO[i].iy,listCamposVO[i].fx,listCamposVO[i].fy,accion,listCamposVO[i].cdColor,listCamposVO[i].nombre,listCamposVO[i].marca);
				trazaCordenada=true;
			}
			
		}
	}
	return trazaCordenada;
};



/*
$scope.ingresarCordenada=function(){
		var xyVO={x:x,y:y};
		$scope.prevlistXY.push(xyVO);
		$scope.contadorCordenadaSelect++;
		$scope.pintarCordenadasFaltantes($scope.prevlistXY[1].x, $scope.prevlistXY[1].y,$scope.prevlistXY[2].x,$scope.prevlistXY[2].y);

};

$scope.dibujarFilasxColumnas2=function() {
	$scope.dibujarFilasxColumnas($scope.dataCrudStep.stepTwo.filas,$scope.dataCrudStep.columnas);
};
*/

});