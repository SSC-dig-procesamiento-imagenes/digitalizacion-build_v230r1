angular.module(appTeclo).controller("consultaPlantillasController",
    function($controller,$rootScope, $scope, $localStorage, WizardHandler,$location, $window, $timeout,growl, showAlert,plantillasService,respuestasService) {
		
			$scope.redirigir = function(){
			$location.path('/plantillas')
			//$scope.dataCrudStep.stepOne.showViewOne=true;
			
	};
		
	$scope.cambioSeleccion = function(marcaVO){
		$scope.valorSeleccionado=marcaVO.nbMarca;
		$scope.imgSeleccionada=marcaVO.ruta;
	
	};
	$scope.listRespuesta=[];

        $scope.listCaracter=undefined;
        $scope.oldcaracterVO=undefined;
        $scope.valor=undefined;
        $scope.parametroBusqueda = {}; 
        var busqueda="";
        var valorBusqueda="";
         //Se declara vo de caracter
        $scope.plantillaVO=new Object({
            caracter:undefined,
            txObservacion:undefined,
            file:new Object({})
        });




    
    //Funcion par observar la variable caracterVO.caracter y evaluar cuando el usuario excede el limite de caractes permitidos al input de nombre de caracter
         $scope.$watch("caracterVO.caracter",function(newValue,oldValue) {
                if (newValue != undefined && newValue.length>=50) {
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



	
    });/*Fin de caracteresController*/

