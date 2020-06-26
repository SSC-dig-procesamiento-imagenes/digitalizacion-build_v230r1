angular.module(appTeclo).controller("respuestasController",
    function($rootScope, $scope, $filter,$location, $window, $timeout,growl, showAlert,respuestasService,caracteresService) {
 
    $scope.caracterRespuestaVO = new Object({
       respuesta:null,
       id:null,
       orden: null,
       listCaracterNew: new Array(),
       listCaracterOld: new Array(),
       plantillasrespuestasId: new Object({})
      });
      $scope.name="";
      $scope.listRespuesta=undefined;
      $scope.listCaracterLeft=[];
      $scope.listCaracterLeftByUpdate=[];
      $scope.listCaracterRigtByUpdate=[];
      $scope.listCaracterRigth=[];
      $scope.respuestaVOBack=[];

      $scope.valor=undefined;
      $scope.parametroBusqueda = {}; 
      var busqueda="";
      var valorBusqueda="";


   $scope.respuestaVO=new Object({
    id:null,
    nombre:null,
    descripcion:"",
    stActivo:null,
    idUsrCreacion:null,
    fhCreacion:null,
    idUsrModifica:null,
    fhModificacion:null,
    contador:null,
    caracteres:null

});
$scope.showModalConfig=false;
$scope.codeOpeConfig=undefined;

//Funcioanes whatch para obervar cuando la variable respuestaVO.nombre es mayor a 50 caracteres
 $scope.$watch("respuestaVO.nombre",function(newValue,oldValue) {
      if (newValue.length>=50) {
     growl.error("50 Caracteres Maximo Permitido");
      }
    });
//Funcioanes whatch para obervar cuando la variable rrespuestaVO.descripcion es mayor a 50 caracteres
   $scope.$watch("respuestaVO.descripcion",function(newValue,oldValue) {
        if (newValue.length>=50) {
       growl.error("50 Caracteres Maximo Permitido");
        }
      });

//Funcion que se ejecuta al dar click en el swict de tipo de busqueda avanzada a todos ,busca todas las respuestas
$scope.swBusqueda=function(newValue, oldValue) {
  if (!newValue) {
      $scope.getRespuestas("TODOS","*");    
      $scope.parametroBusqueda.valor=null; 
      $scope.busquedaForm.valor.$valid = false;
      $scope.busquedaForm.valor.$setPristine(); 
      
  }else{
      $("#select2-comboTipoBusqueda-container").text($scope.listTipoBusqueda.tipoBusqueda[0].nbTipo);
      $scope.parametroBusqueda.tipoBusqueda=$scope.listTipoBusqueda.tipoBusqueda[0].cdTipo;
  }
};

//Funcion onchance para detectar el cambio de tipo busqueda y resetear campos
$scope.onchange=function(tipoBusqueda,valor) {
$scope.parametroBusqueda.valor = null;
$scope.busquedaForm.valor.$valid = false;
$scope.busquedaForm.valor.$setPristine();   
};
//Funcion que se ejecuta al hacer una busqueda en opcion avanzada se le mandan los parametros de busqueda
$scope.buscarRespuesta=function(tipoBusqueda,valor,form) {
  if (form.$invalid) {
      showAlert.requiredFields(form);
      growl.error("Formulario Incompleto");
  }else{
  $scope.getRespuestas(tipoBusqueda,valor);  
}
};

//Se declaran lista para tipos de busqueda
$scope.listTipoBusqueda ={
  tipoBusqueda: [
      {idTipo:1,cdTipo:"NOMBRE",nbTipo:"Nombre"},
      {idTipo:2,cdTipo:"ESTATUS",nbTipo:"Estatus"},
  ],
  tipoEstatus: [
      {idTipo:1,cdTipo:"ACTIVO",nbTipo:"Activo"},
      {idTipo:2,cdTipo:"INACTIVO",nbTipo:"Inactivo"},
  ]
};
//Funcion para validar campos requeridos en form de campos y lista de caracteres asociados
validarFormulario = function (formulario) {
  let duaListValid=valiDualList();
  if (!duaListValid) {
    return false;
  } else if (formulario.$invalid) {
    showAlert.requiredFields(formulario);
    growl.error('Formulario Incompleto');
    return false;
  } else {
    return true;
  }
};

valiDualList = function () {
    if ($scope.myList[1].items.length == 0) {
      $scope.myList[1].isDirty = true;
      return false;
    }
    return true;
  }
//Se declara lista con la que se interactuaran con los duallist
  $scope.myList = [
    { codList: "listIzq", isDirty: false, listName: "Lista de Caracteres", items: [], dragging: false, filter: "", label: "" },
    { codList: "listDer", isDirty: false, listName: "Caracteres Asignados", items: [], dragging: false, filter: "", label: "" }
  ];

//Funcion que se ejecuta al abrir el modal de actualizar respuesta o nueva respuesta
$scope.modalConfig=function(ope,datos) {
   // $scope.oldrespuestaVO=angular.copy(datos);
    $scope.showModalConfig=true;
    $scope.codeOpeConfig=ope;
        if (ope=="N") {
              $scope.txtModal="Nueva Respuestas";
              $scope.myList[0].items=angular.copy($scope.listCaracterLeft);
              $scope.myList[1].items=[];
        }else{
        $scope.respuestaVO=angular.copy(datos);
        $scope.respuestaVOBack=angular.copy(datos);
        $scope.txtModal="Actualizar Respuesta";
        $scope.getCaracteresByRespuesta(datos.id);
    }
};

//Funcion para guardar o actualizar respuestas y lista de caracteres asociados
$scope.saveOrUpdate=function(form,datos) {
  if (!validarFormulario(form)) {
      showAlert.requiredFields(form);
      growl.error("Formulario Incompleto"); 
  }else 
      if ($scope.codeOpeConfig=="E"&& angular.equals($scope.listCaracterRigtByUpdate,$scope.myList[1].items) && angular.equals($scope.respuestaVOBack,datos)) {
      growl.warning('No se ha detectado ningún cambio', { ttl: 4000 });
  }else{
  $scope.caracterRespuestaVO.plantillasrespuestasId=datos;
  $scope.caracterRespuestaVO.listCaracterNew=$scope.myList[1].items;
  $scope.caracterRespuestaVO.listCaracterOld=$scope.codeOpeConfig=="E"?$scope.listCaracterRigtByUpdate:null;
  respuestasService.saveOrUpdate($scope.caracterRespuestaVO).success(function(data) {   
      if (data!=null) {
          growl.success("Se guardo registro");        
          $scope.showModalConfig=false;
          $scope.getRespuestas(busqueda,valorBusqueda);
      }
  }).error(function(data) {   
      growl.error(data.message);   
  });
}
};


//Funcion par cambiar d e estatus
$scope.changeEstatus=function(data) {
  $scope.caracterRespuestaVO.plantillasrespuestasId=data;
  $scope.caracterRespuestaVO.listCaracterNew=null;
  $scope.caracterRespuestaVO.listCaracterOld=null;
  respuestasService.saveOrUpdate( $scope.caracterRespuestaVO).success(function(data) {   
      if (data!=null) {
          growl.success("Actualizacion Exitosa");   
      }
  }).error(function(data) { 
      growl.error(data.message);      
  });
  
};

//Funcion de cambiar estatus
$scope.changeStatus=function(newValue, oldValue, data){
$timeout(() => {
  data.stActivo = oldValue;
}, 0);
if (newValue==0) {
  showAlert.confirmacion('El registro se desactivara, ¿Desea continuar?',
      confirm = () => {
          data.stActivo = newValue;
          $scope.changeEstatus(data);
      }, cancelaNotificar = () => {
          return;
      });
} else {
  showAlert.confirmacion('El registro se activará, ¿Desea continuar?',
      confirm = () => {
          data.stActivo = newValue;
          $scope.changeEstatus(data);
      }, cancelaNotificar = () => {
          return;
      });
}
};


//Funcion que se ejecuta al cerrar el Modal de actualizar o nueva respuesta
$scope.funcionCerrar=function() {
    $scope.txtModal=undefined; 
    $scope.respuestasForm.$setPristine();
    $scope.myList[0].items=[];
    $scope.myList[1].items=[];
    $scope.myList[0].isDirty = false;
    $scope.myList[1].isDirty = false;
    $scope.myList[0].label = "";
    $scope.myList[1].label = "";
    //Se inicializa respuestaVO
    $scope.respuestaVO=new Object({
      id:null,
      nombre:null,
      descripcion:"",
      stActivo:null,
      idUsrCreacion:null,
      fhCreacion:null,
      idUsrModifica:null,
      fhModificacion:null,
      contador:null,
      caracteres:null
  });
  ///  $scope.listCaracterRigth=angular.copy($scope.listCaracterRigthOld);
    $scope.listCaracterLeftByUpdate=new Object({});
};
//Funcion obtiene las respuestas por parametros de busqueda
$scope.getRespuestas=function(tipoBusqueda,valor) {
  busqueda=tipoBusqueda==undefined || tipoBusqueda==null || tipoBusqueda==""?"TODOS":tipoBusqueda;
  valorBusqueda=valor==undefined || valor==null || valor==""?"*":valor;
    respuestasService.getRespuestas(busqueda,valorBusqueda).success(function(data) {
        $scope.listRespuesta=angular.copy(data);
    }).error(function(data) {
      $scope.listRespuesta=[];
      growl(data.message);
    });
};
//Funcion Obtiene los caracteres  por parametros de busqueda en este controller se traen todos los caracteres con estatus activo al cargar el modulo
$scope.getCaracteres=function(tipoBusqueda,valor) {
  busqueda=tipoBusqueda==undefined || tipoBusqueda==null || tipoBusqueda==""?"TODOS":tipoBusqueda;
  valorBusqueda=valor==undefined || valor==null || valor==""?"*":valor;
    caracteresService.getCaracteres(busqueda,valorBusqueda).success(function(data) {
        $scope.listCaracterLeft=angular.copy(data);
       // $scope.listCaracterRigthOld=angular.copy(data);
    }).error(function(data) {
        $scope.listCaracterLeft=[];
        growl.error(data.message);
    });    
};
//Funcion Obtiene todos los caracteres asociados a una respuesta
$scope.getCaracteresByRespuesta=function(idRespuesta) {
  caracteresService.getCaracteresByRespuesta(idRespuesta).success(function(data) {
      $scope.listCaracterRigtByUpdate=angular.copy(data);
      var R = angular.copy($scope.listCaracterRigtByUpdate);
      var L =angular.copy($scope.listCaracterLeft);
      //For para eliminar caracteres de yautlizadosde la lista de caracters principal
      if (R.length>0) {
        for (var i in L) {
          for (var j in R) {
            if (L[i] && (L[i].id === R[j].id)) {
              L.splice(i, 1);
            }
          }
        }
      }  
    $scope.listCaracterRigtByUpdate=angular.copy(R);
    $scope.listCaracterLeftByUpdate=angular.copy(L);
    $scope.myList[0].items=angular.copy(L);
    $scope.myList[1].items=angular.copy(R);

  }).error(function(data) {
    $scope.listCaracterLeftByUpdate=[];
    $scope.myList[0].items=angular.copy($scope.listCaracterLeft);
      growl.error(data.message);
  });    
};
//Funcion Para descargar hoja de calculo de lista de respuesta mostrada
$scope.downloadReporte = function() {	
  busqueda=busqueda==undefined || busqueda==null || busqueda==""?"TODOS":busqueda;
  valorBusqueda=valorBusqueda==undefined || valorBusqueda==null || valorBusqueda==""?"*":valorBusqueda;
  respuestasService.descargaExcel(busqueda,valorBusqueda)
  .success(function(data, status, headers) {
      var filename = headers('filename');
      var contentType = headers('content-type');
      var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
      save(file , filename);
      $scope.error = false;
  })
  .error(function(data) {
      $scope.error = data;
  });
};

function save(file, fileName) {
   var url = window.URL || window.webkitURL;
   var blobUrl = url.createObjectURL(file);
   var a         = document.createElement('a');
   a.href        = blobUrl; 
   a.target      = '_blank';
   a.download    = fileName;
   document.body.appendChild(a);
   a.click();   
};

//Funcion para reiniciar lista de caracteres a valores anteriores a la interacion con este
$scope.reiniciarListaCaracteres = function () {
    if ($scope.codeOpeConfig == "N") {
        $scope.myList[1].items = [];
        $scope.myList[0].items = angular.copy($scope.listCaracterLeft);
      }else{
        $scope.myList[0].items = angular.copy($scope.listCaracterLeftByUpdate);
        $scope.myList[1].items = angular.copy($scope.listCaracterRigtByUpdate);
      }
    // $scope.listaSeleccionCatalogo[1].items=[];
    $scope.myList[0].isDirty = false;
    $scope.myList[1].isDirty = false;
    $scope.myList[0].label = "";
    $scope.myList[1].label = "";
};

  function resetElementsSelected(list) {
    angular.forEach(list, function (element, key) {
      if (angular.isDefined(element.selected))
        element.selected = false;
    });
  };

    /*
   *Inician Metodos para dualList
   */
  $scope.getSelectedItemsIncluding = function (list, item) {
    item.selected = true;

    return list.items.filter(function (item) { return item.selected; });
  };

  $scope.selectedItems = function (item, list) { item.selected = !item.selected;}

  $scope.onDragstart = function (list, event) {list.dragging = true;
    if (event.dataTransfer.setDragImage) {
        var img = new Image()
     img.src = $scope.imgCursor;
      event.dataTransfer.setDragImage(img, 0, 0);
    }

  };

  $scope.onDrop = function (list, items, index, nameOrderProperty) {

    list.originalList = false;

    angular.forEach(items, function (item) {
      item.selected = false;
    });

    list.items = list.items.slice(0, index).concat(items).concat(list.items.slice(index));


    list.items = reloadListOrdenada(list.items, nameOrderProperty);

    list.isDirty = true;
    return true;
  };

  $scope.onMoved = function (list, nameOrderProperty) {

    list.originalList = false;

    list.items = list.items.filter(function (item) {
      return !item.selected;
    });

    list.items = reloadListOrdenada(list.items, nameOrderProperty);
    list.isDirty = true;
  };

  reloadListOrdenada = function (lista, nameOrderProperty) {
    return $filter('orderBy')(lista, nameOrderProperty, false);
  }

  $scope.transferItems = function (dir, listModel, classLiRepeat, namePropertyOrder) {

    var li = $(classLiRepeat);

    if (dir === 'ltr') {

      if (listModel[0].items.length > 0) {

        for (var i = 0; i < listModel[0].items.length; i++) {
          var elment = {};
          if (listModel[0].items[i].type != 'unknown') {
            elment = angular.copy(listModel[0].items[i]);
            listModel[0].items.splice(i, 1);
            listModel[1].items.push(elment);
            i--;
          }
        }

        let size = listModel[1].items.length;

        li.hide();

        showElement(size, li);
      }

    } else if (dir === 'rtl') {


      if (listModel[1].items.length > 0) {

        for (var n = 0; n < listModel[1].items.length; n++) {
          var elmentD = {};
          if (listModel[1].items[n].type != 'unknown') {
            elmentD = angular.copy(listModel[1].items[n]);
            listModel[1].items.splice(n, 1);
            listModel[0].items.push(elmentD);
            n--;
          }
        }

        let size = listModel[0].items.length;

        li.hide();

        showElement(size, li);

      }
    }

    listModel[0].items = reloadListOrdenada(listModel[0].items, namePropertyOrder);
    listModel[1].items = reloadListOrdenada(listModel[1].items, namePropertyOrder);
    listModel[0].isDirty = true;
    listModel[1].isDirty = true;
  }

  showElement = function (s, l) {
    switch (true) {
      case (s < 100):
        $timeout(function () { l.show(); }, 500);
        break;
      case (s < 300):
        $timeout(function () { l.show(); }, 1000);
        break;
      case (s < 500):
        $timeout(function () { l.show(); }, 1500);
        break;
      case (s < 800):
        $timeout(function () { l.show(); }, 2000);
        break;
      case (s > 800):
        $timeout(function () { l.show(); }, 3500);
        break;
    }
  }
/*
   *Terminan Metodos para dualList
   */


$scope.getCaracteres("ESTATUS","ACTIVO");
$scope.getRespuestas("TODOS","*");

    });/*Fin de respuestasController*/

