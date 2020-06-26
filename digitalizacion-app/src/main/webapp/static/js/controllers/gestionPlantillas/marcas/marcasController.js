angular.module(appTeclo).controller("marcasController",
  function ($rootScope, $scope, $location, $window, $timeout, growl, showAlert,ModalService) {
 //Variable donde se guardan los objetos(Marcas) que pintamos en el canvas
 $scope.listObjetos=[];
 //Variable que nos sirve para detectar que objeto(Marca) se va a mover al darle clic y arrastrarlo de posicion
 var objetoActual=[];

 $scope.newMarcaVO

 $scope.flagCordenadas="";
 $scope.contador=0;
 $scope.idGenerador=1;
 $scope.flagEdit=false;
 var move = false;
 $scope.marcaBackVO=new Object({ id: "", ix: "", iy: "", fx: "", fy: "" });

 $scope.marcaVO=new Object({
  x: undefined, y: undefined,
  width: undefined, height: undefined,
  color: undefined
});

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
            $scope.onEdit($scope.marcaBackVO,"revertChange");
            $scope.onPreviewEdit($scope.newMarcaVO);
          }
        }); 
      });
    };
  /*    */



    //SUPER VO QUE OBTIENE TODOS LOS VALORES DE LA INTERACCION CON EL MODULO DE CREAR Y ACTUALIZAR PLANTILLAS		
    $scope.dataCrudStep = new Object({
      stepOne: new Object({ showViewOne: true }),
      stepTwo: new Object({
        showViewTwo: false,
        paramPlantillaVO: new Object({
          filas: 0,
          columnas: 0,
          boleta: undefined,
          extencion: "",
          nombre: "",
          calidad: 2,
        }),
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

    $scope.imgBoleta=undefined;
		$scope.anchoBoleta=undefined;
    $scope.altoBoleta=undefined;
    var existCampo=false;
    var img = new Image();
     $scope.contador=0;
     $scope.prevlistXY=[];



    $scope.redirigir = function () {
      $location.path('/marcasPlantilla')
      //$scope.dataCrudStep.stepOne.showViewOne=true;

    }


    //FUNCION PARA TRATAR LA IMAGEN DE LA BOLETA Y EXTRAER SU AREGLO DE BYTES,BASE64 Y ANCHO Y ALTURA REAL
    handleFiles = function (fileList) {
      var reader = new FileReader();

      if (!fileList.length) {
        console.log("No files selected!");
      } else {
        //Se obtiene el bloob de la imagen
        readFileAsDataURL(fileList[0])
        console.log(fileList.length);
        console.log(fileList[0]);
        $scope.dataCrudStep.stepTwo.paramPlantillaVO.extencion = fileList[0].type;
        $scope.dataCrudStep.stepTwo.paramPlantillaVO.nombre = fileList[0].name;
        reader.readAsDataURL(fileList[0]);
        reader.onload = function () {
          //	$scope.dataCrudStep.stepThree.camposVO.file=reader.result;
          //Imagen de que se muestra en el canvas
          $scope.imgBoleta = reader.result;
        };

        img.src = window.URL.createObjectURL(fileList[0]);
        console.log("src: " + img.src);

        img.onload = function () {
          window.URL.revokeObjectURL(this.src);
          $scope.anchoBoleta = img.naturalWidth + "px";
          $scope.altoBoleta = img.naturalHeight + "px";
          console.log("width: " + img.naturalWidth + " / height: " + img.naturalHeight);

        }
      }
      async function readFileAsDataURL(file) {
        let result_base64 = await new Promise((resolve) => {
          let fileReader = new FileReader();
          fileReader.onload = (e) => resolve(fileReader.result);
          fileReader.readAsArrayBuffer(file);
        });
        var byteArray = new Uint8Array(result_base64);
        //Se guarda el bloob de la boleta en la siguiente variable
        $scope.dataCrudStep.stepTwo.paramPlantillaVO.boleta = Array.from(byteArray);
      };

    };


//Se declaran tipos de busqueda
$scope.listOrientacion = [
  {idOri:1,cdOri:"AR-AB",nbOri:"Arriba-Abajo"},
  {idOri:2,cdOri:"AB-AR",nbOri:"Abajo-Arriba"},
  {idOri:1,cdOri:"IZ-DE",nbOri:"Izquierda-Derecha"},
  {idOri:2,cdOri:"DE-IZ",nbOri:"Derecha-Izquierda"},
];

//Se declaran tipos de busqueda
$scope.listForma = [
  {id:1,cdForma:"CIRCULO",nombre:"Circulo"},
  {id:2,cdForma:"CUADRO",nombre:"Cuadrado-Rectangulo"}
];

//FUNCION PARA AGREGAR UN NUEVO MARCA A LA LISTA DE MARCAS DE LA PLANTILLA
$scope.addCampo=function(objeto,form){

if (form.$invalid || $scope.flagCordenadas==="") {
  showAlert.requiredFields(form);
  growl.error("Formulario Incompleto");
  
}else{
  $scope.marcasForm=form;
  objeto.id=$scope.idGenerador;

  var existObject=false;
  var lisObjectVO2=angular.copy($scope.listObjetos);

  for (let i in lisObjectVO2) {
  if (lisObjectVO2[i].nombre === objeto.nombre) {
    growl.warning("Nombre: "+"'"+objeto.nombre+"'"+" Existente");
    existObject=true;	 
}
}
if (!existObject) {
  let item=angular.copy(objeto);
  $scope.listObjetos.push(item);
  growl.success("Registro Agregado");
  $scope.idGenerador++;
  $("#select2-respuesta-container").text("Seleccione una opcion");
  $("#select2-orientacion-container").text("Seleccione una opcion");
  $scope.marcaVO={};
  $scope.flagCordenadas="";
  $scope.contador=0;
  $scope.marcasForm.$setPristine();
}
}

};

//FUNCION PARA REMOVER TRAZO ANTES DE AGREGAR LA MARCA A LA LISTA
$scope.removeTrazoBeforeAdd=function(marca){
    //$scope.removerTrazo(marca);
      $scope.flagCordenadas="";
      $scope.marcaVO.x="";
      $scope.marcaVO.y="";
      $scope.marcaVO.height="";
      $scope.marcaVO.width="";
      $scope.contador=0;	
      if($scope.flagEdit){
        $scope.onEdit(marca,"beforeAdd");
      }
      $scope.actualizar();
//$scope.recargaPlantilla();
};


//FUNCION PARA ELIMINAR DE LA LISTA DE MARCAS ASIGNADOS A LA LISTA DE LA PLANTILLA
$scope.removeCampo=function(campos){
//$scope.dibujarFilasxColumnas($scope.dataCrudStep.stepTwo.plantillaVO.filas,$scope.dataCrudStep.stepTwo.plantillaVO.columnas,'canvas2',15,15);
var eliminaMarca=false;
var listMarcasVO=angular.copy($scope.listObjetos);
var elementosEncontrados=[];
      for (var i in listMarcasVO) {
          if (listMarcasVO[i].id === campos.id) {
     /// $scope.removerTrazo(campos);
      $scope.listObjetos.splice(i, 1);
      i--;
      eliminaMarca=true;
    }	
  }
  if (eliminaMarca) {
    $scope.actualizar();	
    growl.warning("registro eliminado");
    
      $("#select2-forma-container").text("Seleccione una opcion");
      $("#select2-orientacion-container").text("Seleccione una opcion");
      $scope.marcaVO={};
      $scope.flagEdit=false;	
      $scope.flagCordenadas="";
      $scope.marcasForm.$setPristine();
  }else{
    growl.warning("No se pudo eliminar el campo");
  }
};



$scope.validarCambios=function(marca,form){
	if ($scope.flagEdit && !angular.equals($scope.marcaBackVO,$scope.marcaVO)) {
    $scope.showConfirmacion2("Se detectaron Cambios en Campo Actual¿Desea Guardar? ", function(){ $scope.onEdit($scope.marcaVO,"afterAdd",form)});
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
    $scope.flagCordenadas="1";
  }else{
  $scope.flagCordenadas="";
  }

  $scope.flagEdit=true;
  $scope.marcaVO=angular.copy(marca);
  $("#select2-forma-container").text(marca.forma.nombre);

  for (let i in $scope.listRespuesta) {
    if (angular.equals($scope.listRespuesta[i],marca.forma)) {
      $scope.marcaVO.forma=$scope.listRespuesta[i];
    }
  }		

  $("#select2-orientacion-container").text(marca.orientacion.nbOri);
  for (let i in $scope.listOrientacion) {
    if (angular.equals($scope.listOrientacion[i],marca.orientacion)) {
      $scope.marcaVO.orientacion=$scope.listOrientacion[i];
    }
  }
  $scope.marcaBackVO=angular.copy(marca);	
};
//FUNCION PARA EDITAR CAMPO
$scope.onEdit=function(marca,accion,form){
var formValid=false;

if (form!=undefined) {
  if (form.$invalid || $scope.flagCordenadas=="") {
    formValid=false;
    showAlert.requiredFields(form);
    growl.error("Formulario Incompleto");
    
  }else{
    formValid=true;
  }
}

if ((formValid && accion=="afterAdd")|| (accion!="afterAdd") ) {

var actualizar=false;
for (const k in $scope.listObjetos) {
  if ($scope.listObjetos[k].id==marca.id) {
    $scope.listObjetos[k]=angular.copy(marca);
    actualizar=true;
  }
}
if (actualizar) {
  if (accion=="afterAdd") {
    $scope.flagEdit=false;
    $scope.flagCordenadas="";
    $scope.marcaVO={};
    growl.success("Registro Actualizado");
    $scope.actualizar();
    $("#select2-respuesta-container").text("Seleccione una opcion");
    $("#select2-orientacion-container").text("Seleccione una opcion");
    $scope.marcasForm.$setPristine();
    $scope.contador=0;
  }else if (accion=="revertChange") {
    $scope.actualizar();	
  }
  
}else if (!actualizar && accion=="afterAdd") {
  growl.warning("Hubo un Problema Al Actualizar");
}
}


};





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
        if (ctx) {



          $scope.actualizar=function(){
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            for (var i = 0; i < $scope.listObjetos.length; i++){
            ctx.lineWidth=2;
            if ($scope.listObjetos[i].forma.cdForma=="CUADRO") {
              ctx.fillStyle = $scope.listObjetos[i].cdColor;
              ctx.fillRect($scope.listObjetos[i].x, $scope.listObjetos[i].y, $scope.listObjetos[i].width, $scope.listObjetos[i].height);

            }else if($scope.listObjetos[i].forma.cdForma=="CIRCULO"){
              var rMarca=$scope.listObjetos[i].width;
              ctx.strokeStyle=$scope.listObjetos[i].cdColor;
							ctx.beginPath();
							ctx.arc($scope.listObjetos[i].x,$scope.listObjetos[i].y, rMarca, 0, 2 * Math.PI);
							ctx.stroke();
            }
          }
          }


       
          function trazar (){
           // ctx.clearRect(0, 0, canvas.width, canvas.height);
        
          ctx.lineWidth=2;
          if ($scope.marcaVO.forma.cdForma=="CUADRO") {
            ctx.fillStyle = $scope.marcaVO.cdColor;
            ctx.fillRect($scope.marcaVO.x, $scope.marcaVO.y, $scope.marcaVO.width, $scope.marcaVO.height);

          }else if ($scope.marcaVO.forma.cdForma=="CIRCULO") {
            var rMarca=$scope.marcaVO.width;
            ctx.strokeStyle=$scope.marcaVO.cdColor;
            ctx.beginPath();
            ctx.arc($scope.marcaVO.x,$scope.marcaVO.y, rMarca, 0, 2 * Math.PI);
            ctx.stroke();
          }
          ///$scope.contador=0;
          $scope.prevlistXY=[];
         // move=false;
          }   
      ///actualizar();
          function fillCell(ix, iy,fx,fy,accion,color,nbCampo,rMarca) {

            $scope.marcaVO.x=accion=="create"  ||
            accion=="reCreateTrazo"?ix:"";
            $scope.marcaVO.y= accion=="create" ||
            accion=="reCreateTrazo"?iy:"";
            $scope.marcaVO.width= accion=="create" || 
            accion=="reCreateTrazo"?fx-ix:"";
            $scope.marcaVO.height= accion=="create" || 
            accion=="reCreateTrazo"?fy-iy:"";
  
            $scope.flagCordenadas=
                $scope.marcaVO.x+
                $scope.marcaVO.y;

            //var objectVO=angular.copy($scope.marcaVO);             
            //$scope.listObjetos.push(objectVO); 

              if ($scope.marcaVO!=undefined) {
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
            $scope.contador++;
            
            var r =1;
						ctx.strokeStyle=$scope.marcaVO.cdColor;
						ctx.lineWidth = 2;
						ctx.beginPath();
						ctx.arc(e.clientX - canvaspos.left, e.clientY - canvaspos.top, r, 0, 2 * Math.PI);
						ctx.stroke();

           if ( $scope.contador==2) {
            var ix=$scope.prevlistXY[0].x<$scope.prevlistXY[1].x?$scope.prevlistXY[0].x:$scope.prevlistXY[1].x;
            var iy=$scope.prevlistXY[0].y<$scope.prevlistXY[1].y?$scope.prevlistXY[0].y:$scope.prevlistXY[1].y;
            var fx=$scope.prevlistXY[1].x>$scope.prevlistXY[0].x?$scope.prevlistXY[1].x:$scope.prevlistXY[0].x;
            var fy=$scope.prevlistXY[1].y>$scope.prevlistXY[0].y?$scope.prevlistXY[1].y:$scope.prevlistXY[0].y;
          //radio de marca  
            var rMarca = "";
            var accion = "create";
            var nbMarca="";
            var color=$scope.marcaVO.cdColor;
            fillCell(ix, iy,fx,fy,accion,color,nbMarca,rMarca);
            
          }else if($scope.contador>2){
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
    

        } else {
          alert("No se pudo cargar el contexto");
        }
      }
    };
    $scope.cargarCanvas();


  });/*Fin de caracteresController*/

