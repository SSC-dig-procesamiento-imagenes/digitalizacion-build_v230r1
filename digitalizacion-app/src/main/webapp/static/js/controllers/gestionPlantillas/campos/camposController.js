angular.module(appTeclo).controller("camposController",
    function($rootScope, $scope, $filter, $location, $window, $timeout,growl, showAlert,camposService,respuestasService) {
  
        $scope.showModalCrud=false;
        $scope.codeOpeCrud=undefined;
        $scope.txtModal=undefined;
        $scope.listCampo=undefined;
        $scope.oldcaracterVO=undefined;
        $scope.valor=undefined;
        $scope.parametroBusqueda = {}; 
        var busqueda="";
        var valorBusqueda="";
         //Se declara vo de caracter
        $scope.caracterVO=new Object({
            caracter:undefined,
            txObservacion:undefined
        });

        $scope.listRespuestaLeft=[];
        $scope.listRespuestaLeftByUpdate=[];
        $scope.listRespuestaRigtByUpdate=[];
        $scope.listRespuestaRigth=[];
        $scope.respuestaVOBack=[];
        $scope.showModalConfig=false;
        $scope.codeOpeConfig=undefined;



        valiDualList = function () {
            if ($scope.myList[1].items.length == 0) {
              $scope.myList[1].isDirty = true;
              return false;
            }
            return true;
          }
        //Se declara lista con la que se interactuaran con los duallist
          $scope.myList = [
            { codList: "listIzq", isDirty: false, listName: "Lista de Respuestas", items: [], dragging: false, filter: "", label: "" },
            { codList: "listDer", isDirty: false, listName: "Respuestas Asignadas", items: [], dragging: false, filter: "", label: "" }
          ];


//Funcion que se ejecuta al abrir el modal de actualizar respuesta o nueva respuesta
$scope.modalConfig=function(ope,datos) {
    // $scope.oldrespuestaVO=angular.copy(datos);
     $scope.showModalConfig=true;
     $scope.codeOpeConfig=ope;
         if (ope=="N") {
               $scope.txtModal="Nuevo Campo";
               $scope.myList[0].items=angular.copy($scope.listRespuestaLeft);
               $scope.myList[1].items=[];
         }else{
         $scope.respuestaVO=angular.copy(datos);
         $scope.respuestaVOBack=angular.copy(datos);
         $scope.txtModal="Actualizar Campo";
         $scope.getCaracteresByRespuesta(datos.id);
     }
 };


    
    //Funcion par observar la variable caracterVO.caracter y evaluar cuando el usuario excede el limite de caractes permitidos al input de nombre de caracter
         $scope.$watch("camposVO.caracter",function(newValue,oldValue) {
                if (newValue.length>=50) {
               growl.error("50 Caracteres Maximo Permitido");
                }
              });
    //Funcion que se ejecuta al dar clic al switch de busqueda avanzada si es en todas se buscan todos los caracteres
        $scope.swBusqueda=function(newValue, oldValue) {
            if (!newValue) {
                $scope.getCampos("TODOS","*");    
                $scope.parametroBusqueda.valor=null; 
                $scope.parametroBusqueda.tipoBusqueda=null;
                $scope.busquedaForm.valor.$valid = false;
                $scope.busquedaForm.valor.$setPristine(); 
                
            }else{
                $("#select2-comboTipoBusqueda-container").text($scope.listTipoBusqueda.tipoBusqueda[0].nbTipo);
                $scope.parametroBusqueda.tipoBusqueda=$scope.listTipoBusqueda.tipoBusqueda[0].cdTipo;
            }
        };
    //Funcion que se ejecuta al  cambiar el tipo  de busqueda en busqueda avanzada
    $scope.onchange=function(tipoBusqueda,valor) {
        $scope.parametroBusqueda.tipoBusqueda=null;
        $scope.parametroBusqueda.valor = null;
        $scope.busquedaForm.valor.$valid = false;
        $scope.busquedaForm.valor.$setPristine();   
    
    };
    
    
    //Funcion para busqueda de caracter por tipo de busqueda
    $scope.buscarCampos=function(tipoBusqueda,valor,form) {
        if (form.$invalid) {
            showAlert.requiredFields(form);
            growl.error("Formulario Incompleto");
        }else{
        $scope.getCampos(tipoBusqueda,valor);  
    }
    };
    
    //Se declaran tipos de busqueda
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
    
    //Funcion que se ejecuta al abrir modal de actualizar o nuevo caracter
        $scope.modalSaveOrUpdate=function(ope,datos) {
            $scope.oldcaracterVO=angular.copy(datos);
            $scope.showModalCrud=true;
            $scope.codeOpeCrud=ope;
                if (ope=="C") {
                      $scope.txtModal="Nuevo Campo";
                }else{
                $scope.caracterVO=angular.copy(datos);
                $scope.txtModal="Actualizar Campo";
            }
        };


            //Funcion que obtiene caracteres por tipo de busqueda
    $scope.getCampos=function(tipoBusqueda,valor) {
        busqueda=tipoBusqueda==undefined || tipoBusqueda==null || tipoBusqueda==""?"TODOS":tipoBusqueda;
        valorBusqueda=valor==undefined || valor==null || valor==""?"*":valor;
        camposService.getCampos(busqueda,valorBusqueda).success(function(data) {
            $scope.listCampo=angular.copy(data);
        }).error(function(data) {
            $scope.listCampo=[];
            growl.error(data.message);
        });    
    };


    //Funcion obtiene las respuestas por parametros de busqueda
$scope.getRespuestas=function(tipoBusqueda,valor) {
    busqueda=tipoBusqueda==undefined || tipoBusqueda==null || tipoBusqueda==""?"TODOS":tipoBusqueda;
    valorBusqueda=valor==undefined || valor==null || valor==""?"*":valor;
      respuestasService.getRespuestas(busqueda,valorBusqueda).success(function(data) {
          $scope.listRespuestaLeft=angular.copy(data);
      }).error(function(data) {
        $scope.listRespuesta=[];
        growl(data.message);
      });
  };






    $scope.getCampos("TODOS","*");
    $scope.getRespuestas("TODOS","*");





//Funcion para reiniciar lista de respuesta a valores anteriores a la interacion con este
$scope.reiniciarListaCaracteres = function () {
    if ($scope.codeOpeConfig == "N") {
        $scope.myList[1].items = [];
        $scope.myList[0].items = angular.copy($scope.listRespuestaLeft);
      }else{
        $scope.myList[0].items = angular.copy($scope.listRespuestaLeftByUpdate);
        $scope.myList[1].items = angular.copy($scope.listRespuestaRigtByUpdate);
      }

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

    });/*Fin de caracteresController*/

