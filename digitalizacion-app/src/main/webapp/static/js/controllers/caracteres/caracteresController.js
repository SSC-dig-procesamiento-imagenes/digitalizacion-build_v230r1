angular.module(appTeclo).controller("caracteresController",
    function($rootScope, $scope, $location, $window, $timeout,growl, showAlert,caracteresService) {
    $scope.showModalCrud=false;
    $scope.codeOpeCrud=undefined;
    $scope.txtModal=undefined;
    $scope.listCaracter=undefined;
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

//Funcion par observar la variable caracterVO.caracter y evaluar cuando el usuario excede el limite de caractes permitidos al input de nombre de caracter
     $scope.$watch("caracterVO.caracter",function(newValue,oldValue) {
            if (newValue.length>=50) {
           growl.error("50 Caracteres Maximo Permitido");
            }
          });
//Funcion que se ejecuta al dar clic al switch de busqueda avanzada si es en todas se buscan todos los caracteres
    $scope.swBusqueda=function(newValue, oldValue) {
        if (!newValue) {
            $scope.getCaracteres("TODOS","*");    
            $scope.parametroBusqueda.valor=null; 
            $scope.busquedaForm.valor.$valid = false;
            $scope.busquedaForm.valor.$setPristine(); 
            
        }else{
            $("#select2-comboTipoBusqueda-container").text($scope.listTipoBusqueda.tipoBusqueda[0].nbTipo);
            $scope.parametroBusqueda.tipoBusqueda=$scope.listTipoBusqueda.tipoBusqueda[0].cdTipo;
        }
    };
//Funcion que se ejecuta al  cambiar el tipo  de busqueda en busqueda avanzada
$scope.onchange=function(tipoBusqueda,valor) {
    $scope.parametroBusqueda.valor = null;
    $scope.busquedaForm.valor.$valid = false;
    $scope.busquedaForm.valor.$setPristine();   
};


//Funcion para busqueda de caracter por tipo de busqueda
$scope.buscarCaracter=function(tipoBusqueda,valor,form) {
    if (form.$invalid) {
        showAlert.requiredFields(form);
        growl.error("Formulario Incompleto");
    }else{
    $scope.getCaracteres(tipoBusqueda,valor);  
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
                  $scope.txtModal="Nuevo Caracter";
            }else{
            $scope.caracterVO=angular.copy(datos);
            $scope.txtModal="Actualizar Caracter";
        }
    }
    //Funcion de guarda o actualizar caracter
    $scope.saveOrUpdate=function(form,datos) {
        if (form.$invalid) {
            showAlert.requiredFields(form);
            growl.error("Formulario Incompleto");
            
        }else
            if ($scope.codeOpeCrud="U" && angular.equals($scope.oldcaracterVO,datos)) {
            growl.warning('No se ha detectado ningún cambio', { ttl: 4000 });
        }else
        caracteresService.saveOrUpdate(datos).success(function(data) {   
            if (data==true) {
                growl.success("Se guardo registro");   
                busqueda=busqueda==undefined || busqueda==null || busqueda==""?"TODOS":busqueda;
                valorBusqueda=valorBusqueda==undefined || valorBusqueda==null || valorBusqueda==""?"*":valorBusqueda;
                $scope.getCaracteres(busqueda,valorBusqueda);
                $scope.showModalCrud=false;
            }
        }).error(function(data) {   
            growl.error(data.message);   
        });
    };

    //Funcion de cambio de estatus de caracter activo o inactivo
    $scope.changeEstatus=function(data) {
        caracteresService.saveOrUpdate(data).success(function(data) {   
            if (data==true) {
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


//Funcion que se ejecuta al cerrrar modal de actualizar o nuevo caracter
 $scope.funcionCerrar=function() {
            $scope.caracterVO={}; 
            $scope.txtModal=undefined; 
            $scope.caracterForm.$setPristine();
    };

    //Funcion que obtiene caracteres por tipo de busqueda
    $scope.getCaracteres=function(tipoBusqueda,valor) {
        busqueda=tipoBusqueda==undefined || tipoBusqueda==null || tipoBusqueda==""?"TODOS":tipoBusqueda;
        valorBusqueda=valor==undefined || valor==null || valor==""?"*":valor;
        caracteresService.getCaracteres(busqueda,valorBusqueda).success(function(data) {
            $scope.listCaracter=angular.copy(data);
        }).error(function(data) {
            $scope.listCaracter=[];
            growl.error(data.message);
        });    
    };

    //Funcion para descargar hoja de calculo de lista de caracteres  mostrada
    $scope.downloadReporte = function() {	
        busqueda=busqueda==undefined || busqueda==null || busqueda==""?"TODOS":busqueda;
        valorBusqueda=valorBusqueda==undefined || valorBusqueda==null || valorBusqueda==""?"*":valorBusqueda;
        caracteresService.descargaExcel(busqueda,valorBusqueda)
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

    $scope.getCaracteres("TODOS","*");


    });/*Fin de caracteresController*/

