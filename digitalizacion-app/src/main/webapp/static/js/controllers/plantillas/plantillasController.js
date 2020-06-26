angular.module(appTeclo).controller("plantillasController",
    function($controller,$rootScope, $scope, $localStorage, WizardHandler,$location, $window, $timeout,growl, showAlert,plantillasService,respuestasService) {
		
//SUPER VO QUE OBTIENE TODOS LOS VALORES DE LA INTERACCION CON EL MODULO DE CREAR Y ACTUALIZAR PLANTILLAS		
		$scope.dataCrudStep=new Object({
            stepOne:new Object({showViewOne:true}),
			stepTwo:new Object({showViewTwo:false,
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


		$scope.showModalCrud=false;
        $scope.codeOpeCrud=undefined;
        $scope.txtModal=undefined;
        $scope.listCaracter=undefined;
        $scope.oldcaracterVO=undefined;
        $scope.valor=undefined;
        $scope.parametroBusqueda = {}; 
        var busqueda="";
		var valorBusqueda="";

		var objetoActual=[];
		var move = false;
		
	
		$scope.cordenadaSeleccionada="";
		$scope.listXY=[];
		$scope.prevlistXY=[];

		$scope.imgBoleta=undefined;
		$scope.imgBoletaPreview=undefined;
		$scope.anchoBoleta=undefined;
		$scope.altoBoleta=undefined;
		$scope.imgExtension=undefined;
		var img = new Image();

		$scope.listRespuesta=[];
		var DIR = './static/dist/img/';
    
    
		//Se declara vo de caracter
	   $scope.plantillaVO=new Object({
		   caracter:undefined,
		   txObservacion:undefined,
		   file:new Object({})
	   });
		//MOSTRAR SOLAMENTE 3 ITEMS EN LA TABLA DE CAMPOS AGREGADOS A LA PLANTILLA
		$scope.view = new Object({
		rowsPerPageTwo: 3
		});

	//Se declaran tipos de marcas
	  $scope.listMarca= [
			{idMarca:1,cdMarca:"SSP1",nbMarca:"SSP1",ruta:DIR+"circulo.png"},
			{idMarca:2,cdMarca:"SSP2",nbMarca:"SSP2",ruta:DIR+"cuadrado.png"},
			{idMarca:3,cdMarca:"SSP3",nbMarca:"SSP3",ruta:DIR+"otro.png"},
		];
//Funcion para activar pasos del wisard
		$scope.activarBandera=function(objectVO,imgBoleta,anchoBoleta,altoBoleta,bandera,imgExtension){
			switch (bandera) {
				  case "1":
				 
				  $scope.dataCrudStep.stepOne.showViewOne=true;
				   break;
				  case "2":
				  $scope.dataCrudStep.stepTwo.marcasBasePlantillas=objectVO;
				  $scope.dataCrudStep.stepTwo.showViewTwo=true;
				  $scope.imgBoleta=imgBoleta;
				  $scope.anchoBoleta=anchoBoleta;
				  $scope.altoBoleta=altoBoleta;
				  $scope.imgExtension=imgExtension;
				  let gallery = new Viewer(document.getElementById('images'));
				  break;
				   case "3":
				  $scope.dataCrudStep.stepThree.showViewThree=true;
				  break;
			
				default:
					break;
			}
		};
	

   
      //	Angular wizard
	$scope.saveWizard = function() {
		
		var wizardExample = WizardHandler.wizard('wizardExample');
		wizardExample.setEditMode(true);
		
		growl.info("Fin del angular wizard");
		
  };
 
	$scope.cancelWizard = function() {
		alert('Cancel');
  };

  $scope.goBack=function(listCampos){
	$scope.dataCrudStep.stepThree.listCamposVO=listCampos
  };

  $scope.pintarCampos=function(){
	var listCamposVO=angular.copy($scope.dataCrudStep.stepThree.listCamposVO);
	if (listCamposVO.length>0) {
		for (var i in listCamposVO) {
			$scope.trazarCordenadas(listCamposVO[i].ix, listCamposVO[i].iy,listCamposVO[i].fx,listCamposVO[i].fy,"chargeCampos",listCamposVO[i].cdColor,listCamposVO[i].nombre,listCamposVO[i].marca);
			}
		}
  };

  $scope.removeListXY=function(){
		$scope.listXY=[];
  }


//FUNCION UTILIZADA PARA REDIRECCIONAR Y  PINTAR FILAS Y COLUMNAS ASI MISMO DENTRO DE ESTA FUNCION SE ENCUENTRA EL MARCADO DE CAMPOS EN LA PLANTILLA
    $scope.dibujarFilasxColumnas=function(nbCanvas,origen) {
		// Creamos nuestro canvas
		var perimetroInterlineadoY = $scope.dataCrudStep.stepTwo.marcasBasePlantillas.perimetroInterlineadoY;
		var permimetroInterlineadoX= $scope.dataCrudStep.stepTwo.marcasBasePlantillas.permimetroInterlineadoX;
		var perimetroY= $scope.dataCrudStep.stepTwo.marcasBasePlantillas.perimetroY;
		var	permimetroX= $scope.dataCrudStep.stepTwo.marcasBasePlantillas.permimetroX;

		$scope.plantillaVO;
		var canvas = document.getElementById(nbCanvas);
		var ctx = canvas.getContext("2d");
		ctx.clearRect(0, 0, canvas.width, canvas.height);
        dibujarPlantilla(perimetroY,permimetroX,perimetroInterlineadoY, permimetroInterlineadoX,nbCanvas,origen); 
	};
	
	//DIBUJAR LA PLAN
	dibujarPlantilla = function(perimetroY,permimetroX,perimetroInterlineadoY, permimetroInterlineadoX,nbCanvas,origen) {

		var mouse = false;
		var canvas = document.getElementById(nbCanvas);
		var contenedor = document.getElementById("Contenedor");
		var cuadritos = [];	
		var sizeCuadro = { ancho:27.8, alto:27.8 };
		
	//var color = "#000000";

		if (canvas && canvas.getContext) {
		  var ctx = canvas.getContext("2d");
		  var image = new Image();
		image.src = $scope.imgBoleta;
		ctx.drawImage(image, $scope.anchoBoleta,$scope.altoBoleta);
		  if (ctx) {

			$scope.dibujaGridForCampo=function(perimetroY,permimetroX,perimetroInterlineadoY, permimetroInterlineadoX,origen) {
		
			 // ctx.lineWidth = anchoLinea;
			  var columnas = [];
			  var filas = [];

			  for (i = 0;i< permimetroX.length; i ++) {
				/*ctx.strokeStyle = "#FE0000";
				ctx.beginPath();
				ctx.moveTo(permimetroX[i],perimetroY[0]);
				ctx.lineTo(permimetroX[i],perimetroY[perimetroY.length-1]);
				ctx.stroke();
				*/
				columnas.push(permimetroX[i]);
			  }
			  for (i = 0;i<perimetroY.length; i++) {
				  /*
				ctx.strokeStyle = "#FE0000";
				ctx.beginPath();
				ctx.moveTo(permimetroX[0], perimetroY[i]);
				ctx.lineTo(permimetroX[permimetroX.length-1], perimetroY[i]);
				ctx.stroke();
				*/
				filas.push(perimetroY[i]);
			  }
			  
			  for (i = 0;i< permimetroInterlineadoX.length; i ++) {
				ctx.strokeStyle = "#0905FA";
				ctx.beginPath();
				ctx.moveTo(permimetroInterlineadoX[i], perimetroInterlineadoY[0]);
				ctx.lineTo(permimetroInterlineadoX[i], perimetroInterlineadoY[perimetroInterlineadoY.length-1]);
				ctx.stroke();
				//columnas.push(permimetroInterlineadoX[i]);
			  }
			  for (i = 0;i<perimetroInterlineadoY.length; i++) {
				ctx.strokeStyle = "#0905FA";
				ctx.beginPath();
				ctx.moveTo(permimetroInterlineadoX[0], perimetroInterlineadoY[i]);
				ctx.lineTo(permimetroInterlineadoX[permimetroInterlineadoX.length-1], perimetroInterlineadoY[i]);
				ctx.stroke();
				//filas.push(perimetroInterlineadoY[i]);
			  }

			columnas.push(0);
			filas.push(0);

			  for (x = 0; x < columnas.length; x++) {
				for (y = 0; y < filas.length; y++) {
				  cuadritos.push([columnas[x], filas[y],sizeCuadro.ancho,sizeCuadro.alto]);
				}
			  }
			  if(origen!='canvas3'){
				$scope.pintarCampos();
			  }
		$scope.imageX = canvas.toDataURL($scope.imgExtension);
		$scope.imgBoletaPreview=$scope.imageX;
		
	
			};
			
	  
	 function fillCell(x, y) {

		var nbCampo=$scope.dataCrudStep.stepThree.camposVO.nombre!=undefined?$scope.dataCrudStep.stepThree.camposVO.nombre:"";
		var color=$scope.dataCrudStep.stepThree.camposVO.cdColor;
		var accion="create";
		var xyVO={x:x,y:y};
		$scope.prevlistXY.push(xyVO);
		$scope.dataCrudStep.stepThree.contadorCordenadaSelect++;

		if ($scope.dataCrudStep.stepThree.contadorCordenadaSelect<=2) {
			for (i = 0; i < cuadritos.length; i++) {   
				var cuadro = cuadritos[i];
				if (
					x > cuadro[0] &&
		  			x < cuadro[0] + cuadro[2] &&
		 			y > cuadro[1] &&
		 			y < cuadro[1] + cuadro[3]
					) {

						var r = $scope.dataCrudStep.stepThree.camposVO.marca;
						ctx.strokeStyle=color;
						ctx.lineWidth = 2;
						ctx.beginPath();
						ctx.arc(cuadro[0], cuadro[1], r, 0, 2 * Math.PI);
						ctx.stroke();
						alert("pinto");
/*
					ctx.strokeStyle = color;
					ctx.lineWidth = 2
					ctx.strokeStyle = 'green';
					ctx.strokeRect(cuadro[0],cuadro[1], 28.5,28.5);
					*/
			}	
		}
	}
	
        // if (contador>0 && arrayDeCadenas.length==0 && $scope.contadorCordenadaSelect==2) {
          //   growl.error("La respuesta ya no tine caracteres disponibles");
         //}else{
			
       	if ($scope.dataCrudStep.stepThree.contadorCordenadaSelect==2) {
			   var ix=$scope.prevlistXY[0].x<$scope.prevlistXY[1].x?$scope.prevlistXY[0].x:$scope.prevlistXY[1].x;
			   var iy=$scope.prevlistXY[0].y<$scope.prevlistXY[1].y?$scope.prevlistXY[0].y:$scope.prevlistXY[1].y;
			   var fx=$scope.prevlistXY[1].x>$scope.prevlistXY[0].x?$scope.prevlistXY[1].x:$scope.prevlistXY[0].x;
			   var fy=$scope.prevlistXY[1].y>$scope.prevlistXY[0].y?$scope.prevlistXY[1].y:$scope.prevlistXY[0].y;
			 //radio de marca  
			   var rMarca = $scope.dataCrudStep.stepThree.camposVO.marca;
			   $scope.trazarCordenadas(ix, iy,fx,fy,accion,color,nbCampo,rMarca);

		   }else if($scope.dataCrudStep.stepThree.contadorCordenadaSelect>2){
			growl.warning("Agregar campo a la lista");
		   };
            // arrayDeCadenas = contador==-1?cadenaADividir.split(','):arrayDeCadenas;
             //ctx.fillStyle = color;
             
             // ctx.font = "10px Arial";
            /** 
			  for (i = 0; i < cuadritos.length; i++) {
               
				var cuadro = cuadritos[i];
				if (
				  x > cuadro[0] &&
				  x < cuadro[0] + cuadro[2] &&
				  y > cuadro[1] &&
				  y < cuadro[1] + cuadro[3]
				) {
					contador++;
					contadorCordenadaSelect++;
					ctx.drawImage(img,  cuadro[0], cuadro[1], 3, 3);
					ctx.textAlign = arrayDeCadenas[0].length>1?"end":"center";
				
                   // ctx.fillText(arrayDeCadenas[0], x, y);
                    //arrayDeCadenas.splice(0,1);
                
				/* ctx.arc( cuadro[0], cuadro[1],sizeCuadro.ancho,0,Math.PI,true); // Círculo externo*/
				 /*
				  ctx.fillRect(
					cuadro[0],
					cuadro[1],
					sizeCuadro.ancho,
					sizeCuadro.alto
				  );
				  */
			//	  break;
			//}


			//  }
			  /** */
             // dibujaGrid(sizeCuadro.ancho, sizeCuadro.alto, 0.4, "#a85958");
            //}
			};

			$scope.trazarCordenadas=function(ix, iy,fx,fy,accion,color,nbCampo,rMarca) {
				
			

					$scope.dataCrudStep.stepThree.camposVO.ix=accion=="create"  ||
					accion=="reCreateTrazo"?ix:"";
					$scope.dataCrudStep.stepThree.camposVO.iy= accion=="create" ||
					accion=="reCreateTrazo"?iy:"";
					$scope.dataCrudStep.stepThree.camposVO.fx= accion=="create" || 
					accion=="reCreateTrazo"?fx:"";
					$scope.dataCrudStep.stepThree.camposVO.fy= accion=="create" || 
					accion=="reCreateTrazo"?fy:"";

					$scope.dataCrudStep.stepThree.flagCordenadas=$scope.dataCrudStep.stepThree.camposVO.ix+
							$scope.dataCrudStep.stepThree.camposVO.iy+
							$scope.dataCrudStep.stepThree.camposVO.fx+
							$scope.dataCrudStep.stepThree.camposVO.fy;				
					
				//var img = new Image();
				//img.src = $scope.imgSeleccionada;
			  	//	var arrayDeCadenas = contador==-1?cadenaADividir.split(','):arrayDeCadenas;
					for (i = 0; i < cuadritos.length; i++) {
					 var cuadro = cuadritos[i];
					 
					 if (
						cuadro[0]<=fx &&
						cuadro[1]<=fy &&
						cuadro[0] +cuadro[2]>=ix &&
						cuadro[1]+cuadro[3]>=iy 
						)
						
					 {
						/// contador++;
						//contadorCordenadaSelect++;

						/* DESCOMENTAR
						 ctx.strokeStyle = color;
						 ctx.lineWidth = 2;
						 ctx.strokeRect(cuadro[0],cuadro[1], 28.5, 28.5);
							*/

							
							ctx.strokeStyle=color;
							ctx.lineWidth = 2;
							ctx.beginPath();
							ctx.arc(cuadro[0], cuadro[1], rMarca, 0, 2 * Math.PI);
							ctx.stroke();

						// ctx.drawImage(img,  cuadro[0], cuadro[1], 15, 15);
						if (accion=="create" || accion=="reCreate") {
							var xyVO={x:cuadro[0],y:cuadro[1],campo:nbCampo};
							$scope.listXY.push(xyVO); 
						}
						
						 //ctx.textAlign = arrayDeCadenas[0].length>1?"end":"center";
						// ctx.fillText(arrayDeCadenas[0], cuadro[0], cuadro[1]);
						 //arrayDeCadenas.splice(0,1);
					
					 }
					  }
					  //dibujaGrid(sizeCuadro.ancho, sizeCuadro.alto, 0.4, "#a85958");		
					  $scope.prevlistXY=[];
					  $scope.imageX = canvas.toDataURL($scope.imgExtension);
					$scope.imgBoletaPreview=$scope.imageX;
					};

		
	/*
			canvas.onmousemove = function(e) {
			
			  if (mouse) {
		
				fillCell(e.clientX - canvaspos.left, e.clientY - canvaspos.top); 
	
			  }
			};
			*/
			canvas.onclick = function(e) {
			if ($scope.dataCrudStep.stepThree.flagCordenadas=="") {
					
			  //var canvaspos = canvas.getBoundingClientRect();
			  let areaInvadida=false;
			  var canvaspos = canvas.getBoundingClientRect();
			  if ($scope.dataCrudStep.stepThree.camposVO.cdColor==undefined || $scope.dataCrudStep.stepThree.camposVO.marca==undefined ||$scope.dataCrudStep.stepThree.camposVO.nombre==undefined) {
				growl.warning("Debe completar los campos antes de trazar el area del campo")
			  }else{

					 for (let i in $scope.listXY) {

						if (
							e.clientX - canvaspos.left >$scope.listXY[i].x &&
							e.clientX - canvaspos.left < $scope.listXY[i].x + sizeCuadro.alto &&
							e.clientY - canvaspos.top > $scope.listXY[i].y &&
							e.clientY - canvaspos.top < $scope.listXY[i].y + sizeCuadro.alto
				  			){
							areaInvadida=true;
				  	 }		 
			 }
			 if (!areaInvadida) {
				fillCell(e.clientX - canvaspos.left, e.clientY - canvaspos.top); 
			 }else
			 growl.warning("Area Invadida");
			}
		}else{
			growl.warning("Debe agregar , actualizar o eliminar el area marcada,para volver a marcar");
	}
				
};
			
	  
			canvas.onmousedown = function() {
			  mouse = true;
			};
	  
			canvas.onmouseup = function() {
			  mouse = false;
			};
	  
			//canvas.width = contenedor.offsetWidth - 400;
			$scope.dibujaGridForCampo(perimetroY,permimetroX,perimetroInterlineadoY, permimetroInterlineadoX,origen);
		  } else {
			alert("No se pudo cargar el contexto");
		  }
		}
	  };




    //Funcion que se ejecuta al cerrrar modal de actualizar o nuevo caracter
     $scope.funcionCerrar=function() {
                $scope.txtModal=undefined; 
                $scope.plantillaForm.$setPristine();
		};
		

		$scope.obtenerRespuestas=function(){
			respuestasService.getRespuestas('ESTATUS','ACTIVO').success(function(data) {
				$scope.listRespuesta=angular.copy(data);
			}).error(function(data) {
			  $scope.listRespuesta=[];
			  growl.warning(data.message);
			});
			};



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
					$scope.dibujarFilasxColumnas($scope.plantillaVO.filas,$scope.plantillaVO.columnas,'canvas1',27.8,27.8);
				}
				}).error(function(data) {
				  growl.error(data.message,{ttl:5000});
				});
				};
		
		
		$scope.obtenerRespuestas();
		
	
    });/*Fin de caracteresController*/

