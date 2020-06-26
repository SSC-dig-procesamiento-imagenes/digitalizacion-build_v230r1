angular.module(appTeclo).controller(
		"gestionAsignacionesController",
		function($rootScope, $scope, $location, $window, $timeout,
                 gestionAsignacionesService,growl, showAlert) {

        "use strict";
			/*INICIO. Inicializaciones*/
            $scope.existenAsignaciones = false;
            $scope.listaAsignaciones = null;
            $scope.idValidadorSeleccionado = null;
            $scope.object = {co:'Confirmación ',de:'de ',us:'usuario'};
            $scope.mostrarAlgo = false;
            /*FIN. Inicializaciones*/

            $scope.recargar = function(){
                $scope.init();
            };

			$scope.clickTablaAsignaciones = function(idValidador){
                $scope.idValidadorSeleccionado = idValidador;
			};
            $scope.init = function () {
                gestionAsignacionesService.getAsignaciones()
                    .success(function (data) {
                        $scope.mostrarAlgo = true;
                        $scope.listaAsignaciones = data;
                        if($scope.listaAsignaciones.length > 0){
                            $scope.existenAsignaciones = true;
						}
                    }).error(function (data) {
						if (data.message) {
                            showAlert.error(data.message);
						} else {
                            showAlert.error(data);
						}
                	});
            };

            $scope.init();

            $scope.eliminarAsignaciones = function(){
                showAlert.confirmacion("¿Seguro que desea cancelar las asignaciones del validador seleccionado?", procederEliminarAsignaciones,
                    $scope.object, testCancelConfirmacion);
			};

            let procederEliminarAsignaciones = function () {
                gestionAsignacionesService.deleteAsignaciones($scope.idValidadorSeleccionado)
                    .success(function (data) {
                        $scope.listaAsignaciones = data;
                        if ($scope.listaAsignaciones.length > 0) {
                            $scope.existenAsignaciones = true;
                            growl.info("Se eliminaron las asignaciones del validador seleccionado");
                        } else {
                            $scope.existenAsignaciones = false;
                        }
                    }).error(function (data) {
                    if (data.message) {
                        $scope.existenAsignaciones = false;
                        showAlert.error("Debe contactar con el administrador del sistema");
                    } else {
                        $scope.existenAsignaciones = false;
                        showAlert.error("Debe contactar con el administrador del sistema");
                    }

                });
            };

            let testCancelConfirmacion = function () {
                //vacío
            };
            let testAviso = function () {
                //growl.info('',{title:'Alerta de aviso'});
            };

		});/*Fin de gestionLotesController*/

		