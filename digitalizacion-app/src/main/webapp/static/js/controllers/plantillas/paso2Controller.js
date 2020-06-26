angular.module(appTeclo).controller('paso2Controller', function($controller, $scope, $rootScope, $routeParams, $timeout, $location, $localStorage, WizardHandler, showAlert, growl, configAppService, ModalService) {
	
	 //Variable que nos sirve para detectar que objeto(Marca) se va a mover al darle clic y arrastrarlo de posicion
	 var objetoActual=[];
	 var move = false;
	 $scope.newMarcaVO
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
	            $scope.onPreviewEdit($scope.newMarcaVO);
	          }else{
	            $scope.onEdit($scope.dataCrudStep.stepTwo.marcaBackVO,"revertChange");
	            $scope.onPreviewEdit($scope.newMarcaVO);
	          }
	        }); 
	      });
	    };
	  /*    */

	    //SUPER VO QUE OBTIENE TODOS LOS VALORES DE LA INTERACCION CON EL MODULO DE CREAR Y ACTUALIZAR PLANTILLAS		
	    $scope.dataCrudStep = new Object({
		//VARIABLES DEL PASO 1 DEL WIZARD
		stepOne:$scope.dataCrudStep.stepOne,
		//VARIABLES DEL PASO 2 DEL WIZARD
	      stepTwo: new Object({
			    showViewTwo: true,
				flagCordenadas: "",
				flagEdit: false,
				contador: 0,
				idGenerador: 1,
				marcaBackVO: new Object({}),
				marcaVO:new Object({
					nombre:undefined,forma:undefined,
					orientacion:undefined,tipoMarca:undefined,
					x: undefined, y: undefined,
					width: undefined, height: undefined,
					cdColor: undefined
				  }),
				listObjetos: new Array()
	        ,
	        marcasBasePlantillas: new Object({
				perimetroInterlineadoY: undefined,
				permimetroInterlineadoX: undefined,
				perimetroY: undefined,
				permimetroX: undefined,
				tamImgX: undefined,
				tamImgY: undefined,
				validFilas: undefined,
				validColumnas: undefined,
				msgNotificacion: undefined
	        }),

		  }),
		//VARIABLES DEL PASO 3 DEL WIZARD
	      stepThree: new Object({
	        showViewThree: false,
	        flagCordenadas: "",
	        flagEdit: false,
	        contadorCordenadaSelect: 0,
	        idGenerador: 1,
	        camposBackVO: new Object({ id: "", ix: "", iy: "", fx: "", fy: "" }),
	        camposVO: new Object({ id: "", ix: "", iy: "", fx: "", fy: "" }),
	        listCamposVO: new Array()
	      })

	    });

	
	//Se declaran tipos de busqueda
	$scope.listOrientacion = [
	  {idOri:1,cdOri:"AR-ASB",nbOri:"Arriba-Abajo"},
	  {idOri:2,cdOri:"AB-AR",nbOri:"Abajo-Arriba"},
	  {idOri:1,cdOri:"IZ-DE",nbOri:"Izquierda-Derecha"},
	  {idOri:2,cdOri:"DE-IZ",nbOri:"Derecha-Izquierda"},
	];
	//Se declaran tipos de busqueda
	$scope.listForma = [
	  {id:1,cdForma:"CIRCULO",nombre:"Circulo"},
	  {id:2,cdForma:"CUADRO",nombre:"Cuadrado-Rectangulo"}
	];

		//Se declaran tipos de marca
		$scope.listTipoMarca = [
			{id:1,cdCodigo:"FILA",nombre:"Fila"},
			{id:2,cdCodigo:"COLUMNA",nombre:"Columna"}
		  ];



		$scope.saveStepTwo = function() {
		
		var wizardExample = WizardHandler.wizard('wizardExample');
		wizardExample.next();
		
	}

	//FUNCION PARA AGREGAR UN NUEVO MARCA A LA LISTA DE MARCAS DE LA PLANTILLA
	$scope.addCampo=function(objeto,form){

	if (form.$invalid || $scope.dataCrudStep.stepTwo.flagCordenadas==="") {
	  showAlert.requiredFields(form);
	  growl.error("Formulario Incompleto");
	  
	}else{
	  $scope.marcasForm=form;
	  objeto.id=$scope.dataCrudStep.stepTwo.idGenerador;

	  var existObject=false;
	  var lisObjectVO2=angular.copy($scope.dataCrudStep.stepTwo.listObjetos);

	  for (let i in lisObjectVO2) {
	  if (lisObjectVO2[i].nombre === objeto.nombre) {
	    growl.warning("Nombre: "+"'"+objeto.nombre+"'"+" Existente");
	    existObject=true;	 
	}
	}
	if (!existObject) {
	  let item=angular.copy(objeto);
	  $scope.dataCrudStep.stepTwo.listObjetos.push(item);
	  growl.success("Registro Agregado");
	  $scope.dataCrudStep.stepTwo.idGenerador++;
	  $("#select2-forma-container").text("Seleccione una opcion");
	  $("#select2-orientacion-container").text("Seleccione una opcion");
	  $("#select2-tipoMarca-container").text("Seleccione una opcion");
	  $scope.dataCrudStep.stepTwo.marcaVO={};
	  $scope.dataCrudStep.stepTwo.flagCordenadas="";
	  $scope.dataCrudStep.stepTwo.contador=0;
	  $scope.marcasForm.$setPristine();
	}
	}

	};

	//FUNCION PARA REMOVER TRAZO ANTES DE AGREGAR LA MARCA A LA LISTA
	$scope.removeTrazoBeforeAdd=function(marca){
	    //$scope.removerTrazo(marca);
	      $scope.dataCrudStep.stepTwo.flagCordenadas="";
	      $scope.dataCrudStep.stepTwo.marcaVO.x="";
	      $scope.dataCrudStep.stepTwo.marcaVO.y="";
	      $scope.dataCrudStep.stepTwo.marcaVO.height="";
	      $scope.dataCrudStep.stepTwo.marcaVO.width="";
	      $scope.dataCrudStep.stepTwo.contador=0;	
	      if($scope.dataCrudStep.stepTwo.flagEdit){
	        $scope.onEdit(marca,"beforeAdd");
	      }
	      $scope.actualizar();
	//$scope.recargaPlantilla();
	};


	//FUNCION PARA ELIMINAR DE LA LISTA DE MARCAS ASIGNADOS A LA LISTA DE LA PLANTILLA
	$scope.removeCampo=function(campos){
	//$scope.dibujarFilasxColumnas($scope.dataCrudStep.stepTwo.plantillaVO.filas,$scope.dataCrudStep.stepTwo.plantillaVO.columnas,'canvas2',15,15);
	var eliminaMarca=false;
	var listMarcasVO=angular.copy($scope.dataCrudStep.stepTwo.listObjetos);
	var elementosEncontrados=[];
	      for (var i in listMarcasVO) {
	          if (listMarcasVO[i].id === campos.id) {
	     /// $scope.removerTrazo(campos);
	     $scope.dataCrudStep.stepTwo.listObjetos.splice(i, 1);
	      i--;
	      eliminaMarca=true;
	    }	
	  }
	  if (eliminaMarca) {
	    $scope.actualizar();	
	    growl.warning("registro eliminado");
	    
	      $("#select2-forma-container").text("Seleccione una opcion");
		  $("#select2-orientacion-container").text("Seleccione una opcion");
		  $("#select2-tipoMarca-container").text("Seleccione una opcion");
	      $scope.dataCrudStep.stepTwo.marcaVO={};
	      $scope.dataCrudStep.stepTwo.flagEdit=false;	
	      $scope.dataCrudStep.stepTwo.flagCordenadas="";
	      $scope.marcasForm.$setPristine();
	  }else{
	    growl.warning("No se pudo eliminar el campo");
	  }
	};



	$scope.validarCambios=function(marca,form){
		if ($scope.dataCrudStep.stepTwo.flagEdit && !angular.equals($scope.dataCrudStep.stepTwo.marcaBackVO,$scope.dataCrudStep.stepTwo.marcaVO)) {
	    $scope.showConfirmacion2("Se detectaron Cambios en Campo Actual¿Desea Guardar? ", function(){ $scope.onEdit($scope.dataCrudStep.stepTwo.marcaVO,"afterAdd",form)});
	  //SE OBTIENE UNA NUEVA INSTACNIA DEL OBJETO VO
	  $scope.newMarcaVO=angular.copy(marca);
	  ///	$scope.newFormCampos=form;
	  //$scope.onPreviewEdit(newCamposVO);
	}else{
	  $scope.onPreviewEdit(marca);
	}
	};
	//FUNCION PREVIA QUE RECUPERA LOS VALORES DEL CAMPO ANTES DE SER EDITADO
	$scope.onPreviewEdit=function(marca){
	  if (marca.ix!="" && marca.iy!="" && marca.fx!="" && marca.fy!="") {
	    $scope.dataCrudStep.stepTwo.flagCordenadas="1";
	  }else{
	  $scope.dataCrudStep.stepTwo.flagCordenadas="";
	  }

	  $scope.dataCrudStep.stepTwo.flagEdit=true;
	  $scope.dataCrudStep.stepTwo.marcaVO=angular.copy(marca);

	
	  for (let i in $scope.listForma) {
	    if (angular.equals($scope.listForma[i].cdForma,marca.forma)) {
			$scope.dataCrudStep.stepTwo.marcaVO.forma=$scope.listForma[i].cdForma;
			$("#select2-forma-container").text($scope.listForma[i].nombre);
	    }
	  }		

	 
	  for (let i in $scope.listOrientacion) {
	    if (angular.equals($scope.listOrientacion[i].cdOri,marca.orientacion)) {
			$scope.dataCrudStep.stepTwo.marcaVO.orientacion=$scope.listOrientacion[i].cdOri;
			$("#select2-orientacion-container").text($scope.listOrientacion[i].nbOri);
	    }
	  }

	  for (let i in $scope.listTipoMarca) {
	    if (angular.equals($scope.listTipoMarca[i].cdCodigo,marca.tipoMarca)) {
			$scope.dataCrudStep.stepTwo.marcaVO.tipoMarca=$scope.listTipoMarca[i].cdCodigo;
			$("#select2-tipoMarca-container").text($scope.listTipoMarca[i].nombre);
	    }
	  }
	  $scope.dataCrudStep.stepTwo.marcaBackVO=angular.copy(marca);	
	};
	//FUNCION PARA EDITAR CAMPO
	$scope.onEdit=function(marca,accion,form){
	var formValid=false;

	if (form!=undefined) {
	  if (form.$invalid || $scope.dataCrudStep.stepTwo.flagCordenadas=="") {
	    formValid=false;
	    showAlert.requiredFields(form);
	    growl.error("Formulario Incompleto");
	    
	  }else{
	    formValid=true;
	  }
	}

	if ((formValid && accion=="afterAdd")|| (accion!="afterAdd") ) {

	var actualizar=false;
	for (const k in $scope.dataCrudStep.stepTwo.listObjetos) {
	  if ($scope.dataCrudStep.stepTwo.listObjetos[k].id==marca.id) {
	    $scope.dataCrudStep.stepTwo.listObjetos[k]=angular.copy(marca);
	    actualizar=true;
	  }
	}
	if (actualizar) {
	  if (accion=="afterAdd") {
	    $scope.dataCrudStep.stepTwo.flagEdit=false;
	    $scope.dataCrudStep.stepTwo.flagCordenadas="";
	    $scope.dataCrudStep.stepTwo.marcaVO={};
	    growl.success("Registro Actualizado");
	    $scope.actualizar();
	    $("#select2-forma-container").text("Seleccione una opcion");
		$("#select2-orientacion-container").text("Seleccione una opcion");
		$("#select2-tipoMarca-container").text("Seleccione una opcion");
	    $scope.marcasForm.$setPristine();
	    $scope.dataCrudStep.stepTwo.contador=0;
	  }else if (accion=="revertChange") {
	    $scope.actualizar();	
	  }
	  
	}else if (!actualizar && accion=="afterAdd") {
	  growl.warning("Hubo un Problema Al Actualizar");
	}
	}


	};


	$scope.validarParametros=function(){
		var listMarcas=$scope.dataCrudStep.stepTwo.listObjetos;;
		$scope.obtenerAreaTrabajo(listMarcas);
		};



	///Funciones para trazar sobre canvas

	$scope.cargarCanvas = function() {
	     
	//Varibale para detectar cuando se esta moviendo objeto de posicion en el canvas
 
	var inicioX = 0, inicioY =0;
	//Variable para activar las funcion onmove
	var mouse = false;
	//Se declara Canvas,se obtiene el id de la etiqueta <canvas id="canvas1"> en el html
	var canvas = document.getElementById("canvas2");
 
	if (canvas && canvas.getContext) {
	  //Se obtiene el contexto de el canvas en 2d
	  var ctx = canvas.getContext("2d");
	  if (ctx) {

		$scope.actualizar=function(){
		  ctx.clearRect(0, 0, canvas.width, canvas.height);
		  for (var i = 0; i < $scope.dataCrudStep.stepTwo.listObjetos.length; i++){
		  ctx.lineWidth=2;
		  if ($scope.dataCrudStep.stepTwo.listObjetos[i].forma=="CUADRO") {
			ctx.fillStyle = $scope.dataCrudStep.stepTwo.listObjetos[i].cdColor;
			ctx.fillRect($scope.dataCrudStep.stepTwo.listObjetos[i].x,$scope.dataCrudStep.stepTwo.listObjetos[i].y, $scope.dataCrudStep.stepTwo.listObjetos[i].width, $scope.dataCrudStep.stepTwo.listObjetos[i].height);

		  }else if($scope.dataCrudStep.stepTwo.listObjetos[i].forma=="CIRCULO"){
			var rMarca=$scope.dataCrudStep.stepTwo.listObjetos[i].width;
			ctx.strokeStyle=$scope.dataCrudStep.stepTwo.listObjetos[i].cdColor;
						  ctx.beginPath();
						  ctx.arc($scope.dataCrudStep.stepTwo.listObjetos[i].x,$scope.dataCrudStep.stepTwo.listObjetos[i].y, rMarca, 0, 2 * Math.PI);
						  ctx.stroke();
		  }
		}
		}


		function trazar (){
		 // ctx.clearRect(0, 0, canvas.width, canvas.height);
	  
		ctx.lineWidth=2;
		if ($scope.dataCrudStep.stepTwo.marcaVO.forma=="CUADRO") {
		  ctx.fillStyle = $scope.dataCrudStep.stepTwo.marcaVO.cdColor;
		  ctx.fillRect($scope.dataCrudStep.stepTwo.marcaVO.x, $scope.dataCrudStep.stepTwo.marcaVO.y, $scope.dataCrudStep.stepTwo.marcaVO.width, $scope.dataCrudStep.stepTwo.marcaVO.height);

		}else if ($scope.dataCrudStep.stepTwo.marcaVO.forma=="CIRCULO") {
		  var rMarca=$scope.dataCrudStep.stepTwo.marcaVO.width;
		  ctx.strokeStyle=$scope.dataCrudStep.stepTwo.marcaVO.cdColor;
		  ctx.beginPath();
		  ctx.arc($scope.dataCrudStep.stepTwo.marcaVO.x,$scope.dataCrudStep.stepTwo.marcaVO.y, rMarca, 0, 2 * Math.PI);
		  ctx.stroke();
		}
		///$scope.contador=0;
		$scope.prevlistXY=[];
	   // move=false;
		}   
	///actualizar();
		function fillCell(ix, iy,fx,fy,accion,color,nbCampo,rMarca) {

		  $scope.dataCrudStep.stepTwo.marcaVO.x=accion=="create"  ||
		  accion=="reCreateTrazo"?ix:"";
		  $scope.dataCrudStep.stepTwo.marcaVO.y= accion=="create" ||
		  accion=="reCreateTrazo"?iy:"";
		  $scope.dataCrudStep.stepTwo.marcaVO.width= accion=="create" || 
		  accion=="reCreateTrazo"?fx-ix:"";
		  $scope.dataCrudStep.stepTwo.marcaVO.height= accion=="create" || 
		  accion=="reCreateTrazo"?fy-iy:"";

		  $scope.dataCrudStep.stepTwo.flagCordenadas=
		  $scope.dataCrudStep.stepTwo.marcaVO.x+
		  $scope.dataCrudStep.stepTwo.marcaVO.y;

		  //var objectVO=angular.copy($scope.marcaVO);             
		  //$scope.listObjetos.push(objectVO); 

			if ($scope.dataCrudStep.stepTwo.marcaVO!=undefined) {
			  trazar();
			}   
		};
  
		canvas.onclick = function(e) {
	  if (!move) {

		if ($scope.marcasForm.$invalid) {
		  showAlert.requiredFields($scope.marcasForm);
		  growl.error("Debe completar los campos antes de trazar");
		  
		}else{

		  var canvaspos = canvas.getBoundingClientRect();
		  var xyVO={x:e.clientX - canvaspos.left,y:e.clientY - canvaspos.top};
			  $scope.prevlistXY.push(xyVO);
			  $scope.dataCrudStep.stepTwo.contador++;
		  
		  var r =1;
					  ctx.strokeStyle=$scope.dataCrudStep.stepTwo.marcaVO.cdColor;
					  ctx.lineWidth = 2;
					  ctx.beginPath();
					  ctx.arc(e.clientX - canvaspos.left, e.clientY - canvaspos.top, r, 0, 2 * Math.PI);
					  ctx.stroke();

		 if ( $scope.dataCrudStep.stepTwo.contador==2) {
		  var ix=$scope.prevlistXY[0].x<$scope.prevlistXY[1].x?$scope.prevlistXY[0].x:$scope.prevlistXY[1].x;
		  var iy=$scope.prevlistXY[0].y<$scope.prevlistXY[1].y?$scope.prevlistXY[0].y:$scope.prevlistXY[1].y;
		  var fx=$scope.prevlistXY[1].x>$scope.prevlistXY[0].x?$scope.prevlistXY[1].x:$scope.prevlistXY[0].x;
		  var fy=$scope.prevlistXY[1].y>$scope.prevlistXY[0].y?$scope.prevlistXY[1].y:$scope.prevlistXY[0].y;
		//radio de marca  
		  var rMarca = "";
		  var accion = "create";
		  var nbMarca="";
		  var color=$scope.dataCrudStep.stepTwo.marcaVO.cdColor;
		  fillCell(ix, iy,fx,fy,accion,color,nbMarca,rMarca);
		  
		}else if($scope.dataCrudStep.stepTwo.contador>2){
		  growl.warning("Debe agregar la marca antes de pintar otra cordenada");
		}
	  }
	  
	}
	  
		};
/*
		canvas.onmousedown = function(event){
		  move=false;
		  mouse = true;
		  var canvaspos = canvas.getBoundingClientRect();
		  var cX=event.clientX - canvaspos.left;
		  var cy= event.clientY - canvaspos.top;
		  var canvaspos = canvas.getBoundingClientRect();
		  for (var i = 0; i < $scope.listObjetos.length; i++){
		  if($scope.listObjetos[i].x < cX
		  && ($scope.listObjetos[i].width + $scope.listObjetos[i].x > cX)
		  && $scope.listObjetos[i].y <cy
		  && ($scope.listObjetos[i].height + $scope.listObjetos[i].y > cy)){
		  $scope.marcaVO = $scope.listObjetos[i];
	   
		  move=true;
		  break;
		  }
		  }
		};
*/

		/*
		canvas.onmousemove = function(event){
		  if (mouse) {
		 
		if( $scope.marcaVO != null){
		  var canvaspos = canvas.getBoundingClientRect();
		  var cX=event.clientX - canvaspos.left;
		  var cy= event.clientY - canvaspos.top;

		  $scope.marcaVO.x = cX;
		  $scope.marcaVO.y = cy;
		 // alert("Entro on mouse move" );
		  /// move=true;
		   $scope.actualizar();
   
		}
	  }
	  
		};
		canvas.onmouseup = function(event){
		
		 /// objetoActual = null;
		  mouse = false;
		}
		*/
		$scope.$on('$includeContentRequested', function(event, url) {
			$scope.dataCrudStep = event.currentScope.$parent.dataCrudStep;
			alert("entro");
		});	
	
  

	  } else {
		alert("No se pudo cargar el contexto");
	  }
	}
	else{
	  alert("No se encontro la propiedad canvas");
	}
  };






  $scope.cargarCanvas();

///Terminan funciones de canvas

});