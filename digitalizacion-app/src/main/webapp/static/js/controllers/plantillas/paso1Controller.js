angular.module(appTeclo).controller('paso1Controller', function($controller, $scope, $rootScope, $routeParams, $timeout, $location, $localStorage, WizardHandler, showAlert, growl, configAppService, ModalService,plantillasService) {
	var move = false;
	var contador=0;
	$scope.flagCordenadas='';
	$scope.imgBoleta=undefined;
	$scope.anchoBoleta=undefined;
	$scope.altoBoleta=undefined;
	var img = new Image();
	var canvasImage=new Image();


$scope.plantillaVO= new Object({
	filas: 0,
	columnas: 0,
	boleta: undefined,
	extencion: "",
	nombre: "",
	calidad: 2,
	marcaVO:new Object({
		x: undefined, y: undefined,
		width: undefined, height: undefined,
		color: undefined
	  })
  });

$scope.dataCrudStep = new Object({
		//VARIABLES DEL PASO 1 DEL WIZARD
		  stepOne: new Object({ showViewOne: true,
				plantillaVO: new Object({
				filas: 0,
				columnas: 0,
				boleta: undefined,
				extencion: "",
				nombre: "",
				calidad: 2,
				marcaVO:new Object({
					x: undefined, y: undefined,
					width: undefined, height: undefined,
					color: undefined
				  })
			  }),
		}),
		//VARIABLES DEL PASO 2 DEL WIZARD
	      stepTwo: new Object({
				showViewTwo: false,
					paramPlantillaVO:new Object({
					marcasVO:new Object({}),
					filas:0,
					columnas:0,
					boleta:undefined,
					extencion:"",
					nombre:"",
					calidad:2,
				}),
				marcasBasePlantillas:new Object({
					perimetroInterlineadoY:undefined, 
					permimetroInterlineadoX:undefined,
					perimetroY:undefined,
					permimetroX:undefined,
					tamImgX:undefined,
					tamImgY:undefined,
					validFilas:undefined,
					validColumnas:undefined,
					msgNotificacion:undefined
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

	
	$scope.flags = new Object({
		showViewPasoUno: false,
		showViewPasoDos: false,
		showViewPasoTres: false,

	});


	$scope.general = new Object({
		setting: new Object()
	});

	
	$scope.$on('$includeContentRequested', function(event, url) {
		$scope.dataCrudStep = event.currentScope.$parent.dataCrudStep;
	

	});	


    $scope.plantillaVO=new Object({
		caracter:undefined,
		txObservacion:undefined
	});

	$scope.pictureValidation = new Object({
		txWich: 'Seleccionar Archivo',
		txnumPictu: undefined
	  });



	$scope.obtenerAreaTrabajo=function(plantillaVO){
			var filas =plantillaVO.filas;
			var columnas=plantillaVO.columnas;
			//SE RESTA -1 A LAS FILAS PORQUE LA PRIMERA EMPIEZA EN 0
			$scope.dataCrudStep.stepTwo.paramPlantillaVO.filas=filas-1;
			//LA PRIMERA POCISION DE LAS COLUMNAS SI EMPIECE EN 1 
			$scope.dataCrudStep.stepTwo.paramPlantillaVO.columnas=columnas;
			$scope.dataCrudStep.stepTwo.paramPlantillaVO.marcasVO=plantillaVO.marcaVO;
			
			plantillasService.obtenerAreaTrabajo($scope.dataCrudStep.stepTwo.paramPlantillaVO).success(function(data) {
				$scope.dataCrudStep.stepTwo.marcasBasePlantillas=angular.copy(data);
				if (!data.validFilas) {
						growl.warning(data.msgNotificacion,{ttl:5000});
				}
				else{
				growl.success(data.msgNotificacion);
			
				var perimetroInterlineadoY =$scope.dataCrudStep.stepTwo.marcasBasePlantillas.perimetroInterlineadoY;
				var permimetroInterlineadoX=$scope.dataCrudStep.stepTwo.marcasBasePlantillas.permimetroInterlineadoX;
				var perimetroY=$scope.dataCrudStep.stepTwo.marcasBasePlantillas.perimetroY;
				var	permimetroX=$scope.dataCrudStep.stepTwo.marcasBasePlantillas.permimetroX;
				
				$scope.dibujaGridForPlantilla(perimetroY,permimetroX,perimetroInterlineadoY, permimetroInterlineadoX); 
			}
			}).error(function(data) {
			  growl.error(data.message,{ttl:5000});
			});
	};
	
	
	
	//FUNCION PARA REMOVER TRAZO ANTES DE AGREGAR LA MARCA A LA LISTA
	$scope.removeTrazoBeforeAdd=function(marca){
	    //$scope.removerTrazo(marca);
	      $scope.flagCordenadas="";
	      $scope.plantillaVO.marcaVO.x="";
	      $scope.plantillaVO.marcaVO.y="";
	      $scope.plantillaVO.marcaVO.height="";
	      $scope.plantillaVO.marcaVO.width="";
	      contador=0;	
		  $scope.limpiaTrazo();
	//$scope.recargaPlantilla();
	};


	$scope.saveStepOne = function(form,createAccountVO) {
		if (form.$invalid ||  $scope.imgBoleta==undefined) {
			showAlert.requiredFields(form);
			growl.error("Formulario Incompleto");
			
		}else{
 	    $scope.dataCrudStep.stepTwo.showViewTwo=true;		
		var wizardExample = WizardHandler.wizard('wizardExample');
		wizardExample.next();
		var imgExtension=$scope.dataCrudStep.stepTwo.paramPlantillaVO.extencion;
		$scope.activarBandera($scope.dataCrudStep.stepTwo.marcasBasePlantillas,$scope.imgBoleta,$scope.anchoBoleta,$scope.altoBoleta,"2",imgExtension);
	
		//$scope.dibujaGridForCampo(perimetroY,permimetroX,perimetroInterlineadoY, permimetroInterlineadoX); 
		$timeout(() => {
			$scope.dibujarFilasxColumnas('canvas3','canvas2');
		  }, 200);
		}
	};


	
	//FUNCION PARA TRATAR LA IMAGEN DE LA BOLETA Y EXTRAER SU AREGLO DE BYTES,BASE64 Y ANCHO Y ALTURA REAL
	handleFiles=function(fileList) {
		var reader = new FileReader();
		
	  if (!fileList.length) {
		console.log("No files selected!");
	  } else {
		//Se obtiene el bloob de la imagen
		readFileAsDataURL(fileList[0]) 
		console.log(fileList.length);
		console.log(fileList[0]);
		$scope.dataCrudStep.stepTwo.paramPlantillaVO.extencion=fileList[0].type;
		$scope.dataCrudStep.stepTwo.paramPlantillaVO.nombre=fileList[0].name;
		reader.readAsDataURL(fileList[0]);
			reader.onload = function () {
		//	$scope.dataCrudStep.stepThree.camposVO.file=reader.result;
		//Imagen de que se muestra en el canvas
			$scope.imgBoleta =reader.result;
			//let gallery = new Viewer(document.getElementById('images'));
		  };
	    
		img.src = window.URL.createObjectURL(fileList[0]);
		console.log("src: " + img.src);
		
		img.onload = function() {
		  window.URL.revokeObjectURL(this.src);
		  $scope.anchoBoleta=img.naturalWidth+"px";
		  $scope.altoBoleta=img.naturalHeight+"px";
		 // canvasImage=img;
		 $timeout(() => {
			$scope.cargarCanvas();
		  }, 200);
		
	};
		
		  console.log("width: " + img.naturalWidth + " / height: " + img.naturalHeight);
		  
//		}
	  }
	  async function readFileAsDataURL(file) {
		let result_base64 = await new Promise((resolve) => {
		let fileReader = new FileReader();
		fileReader.onload = (e) => resolve(fileReader.result);
		fileReader.readAsArrayBuffer(file);
		//$scope.cargarCanvas();
	});
	var byteArray = new Uint8Array(result_base64);
	//Se guarda el bloob de la boleta en la siguiente variable
	$scope.dataCrudStep.stepTwo.paramPlantillaVO.boleta = Array.from(byteArray);
	
};

};



///Funciones para trazar sobre canvas

$scope.cargarCanvas = function() {    
	//Varibale para detectar cuando se esta moviendo objeto de posicion en el canvas
 
	var inicioX = 0, inicioY =0;
	//Variable para activar las funcion onmove
	var mouse = false;
	//Se declara Canvas,se obtiene el id de la etiqueta <canvas id="canvas1"> en el html
	var canvas = document.getElementById("canvas1");
 
	if (canvas && canvas.getContext) {
	  //Se obtiene el contexto de el canvas en 2d
	  var ctx = canvas.getContext("2d");
//	  var image=document.getElementById("idImagen");
//	  ctx.drawImage(image, 10,10);
	
	  if (ctx) {


		$scope.dibujaGridForPlantilla=function(perimetroY,permimetroX,perimetroInterlineadoY, permimetroInterlineadoX) {

		  var columnas = [];
		  var filas = [];
		  //Descomentar para visualizar interlineado de cruse en punto intermedio de campo
/*
		  for (i = 0;i< permimetroX.length; i ++) {
			ctx.strokeStyle = "#FE0000";
			ctx.beginPath();
			ctx.moveTo(permimetroX[i],perimetroY[0]);
			ctx.lineTo(permimetroX[i],perimetroY[perimetroY.length-1]);
			ctx.stroke();
			columnas.push(permimetroX[i]);
		  }
		  for (i = 0;i<perimetroY.length; i++) {
			ctx.strokeStyle = "#FE0000";
			ctx.beginPath();
			ctx.moveTo(permimetroX[0], perimetroY[i]);
			ctx.lineTo(permimetroX[permimetroX.length-1], perimetroY[i]);
			ctx.stroke();
			filas.push(perimetroY[i]);
		  }
		
*/
		  
		  for (i = 0;i< permimetroInterlineadoX.length; i ++) {
			ctx.strokeStyle = "#0905FA";
			ctx.beginPath();
			ctx.moveTo(permimetroInterlineadoX[i], perimetroInterlineadoY[0]);
			ctx.lineTo(permimetroInterlineadoX[i], perimetroInterlineadoY[perimetroInterlineadoY.length-1]);
			ctx.stroke();
			columnas.push(permimetroInterlineadoX[i]);
		  }
		  for (i = 0;i<perimetroInterlineadoY.length; i++) {
			ctx.strokeStyle = "#0905FA";
			ctx.beginPath();
			ctx.moveTo(permimetroInterlineadoX[0], perimetroInterlineadoY[i]);
			ctx.lineTo(permimetroInterlineadoX[permimetroInterlineadoX.length-1], perimetroInterlineadoY[i]);
			ctx.stroke();
			filas.push(perimetroInterlineadoY[i]);
		  }


		};


		function trazar (){
		  ctx.clearRect(0, 0, canvas.width, canvas.height);
		  ctx.lineWidth=2;
		  ctx.fillStyle = $scope.plantillaVO.marcaVO.cdColor;
		  ctx.fillRect($scope.plantillaVO.marcaVO.x, $scope.plantillaVO.marcaVO.y, $scope.plantillaVO.marcaVO.width, $scope.plantillaVO.marcaVO.height);

		}

		$scope.limpiaTrazo=function(){
			ctx.clearRect(0, 0, canvas.width, canvas.height);
			$scope.prevlistXY=[];
		};

		function fillCell(ix, iy,fx,fy,accion,color,nbCampo,rMarca) {

		  $scope.plantillaVO.marcaVO.x=accion=="create"  ||
		  accion=="reCreateTrazo"?ix:"";
		  $scope.plantillaVO.marcaVO.y= accion=="create" ||
		  accion=="reCreateTrazo"?iy:"";
		  $scope.plantillaVO.marcaVO.width= accion=="create" || 
		  accion=="reCreateTrazo"?fx-ix:"";
		  $scope.plantillaVO.marcaVO.height= accion=="create" || 
		  accion=="reCreateTrazo"?fy-iy:"";

		  $scope.flagCordenadas=
		  $scope.plantillaVO.marcaVO.x+
		  $scope.plantillaVO.marcaVO.y;


			if ($scope.plantillaVO.marcaVO!=undefined) {
			  trazar();
			}   
		};
  
		canvas.onclick = function(e) {
	  if (!move) {

		if ($scope.plantillaForm.$invalid) {
		  showAlert.requiredFields($scope.plantillaForm);
		  growl.error("Debe completar los campos antes de trazar");
		  
		}else{

		  var canvaspos = canvas.getBoundingClientRect();
		  var xyVO={x:e.clientX - canvaspos.left,y:e.clientY - canvaspos.top};
			  $scope.prevlistXY.push(xyVO);
			  contador++;
		  
		  var r =1;
					  ctx.strokeStyle=$scope.plantillaVO.marcaVO.cdColor;
					  ctx.lineWidth = 2;
					  ctx.beginPath();
					  ctx.arc(e.clientX - canvaspos.left, e.clientY - canvaspos.top, r, 0, 2 * Math.PI);
					  ctx.stroke();

		 if (contador==2) {
		  var ix=$scope.prevlistXY[0].x<$scope.prevlistXY[1].x?$scope.prevlistXY[0].x:$scope.prevlistXY[1].x;
		  var iy=$scope.prevlistXY[0].y<$scope.prevlistXY[1].y?$scope.prevlistXY[0].y:$scope.prevlistXY[1].y;
		  var fx=$scope.prevlistXY[1].x>$scope.prevlistXY[0].x?$scope.prevlistXY[1].x:$scope.prevlistXY[0].x;
		  var fy=$scope.prevlistXY[1].y>$scope.prevlistXY[0].y?$scope.prevlistXY[1].y:$scope.prevlistXY[0].y;
		//radio de marca  
		  var rMarca = "";
		  var accion = "create";
		  var nbMarca="";
		  var color=$scope.plantillaVO.marcaVO.cdColor;
		  fillCell(ix, iy,fx,fy,accion,color,nbMarca,rMarca);
		  
		}else if(contador>2){
		  growl.warning("Debe agregar la marca antes de pintar otra cordenada");
		}
	  }
	  
	}
	  
		};

		$scope.$on('$includeContentRequested', function(event, url) {
			$scope.dataCrudStep = event.currentScope.$parent.dataCrudStep;
			alert("entro");
		});	
	
  

	  } else {
		alert("No se pudo cargar el contexto");
	  }
	 
	}else{
	  alert("No se encontro la propiedad canvas");
	}
  };


  $scope.cargarCanvas();

/*Termina controller*/
});