angular.module(appTeclo).controller(
		"validacionTodasPospuestasController",function($rootScope, $scope, $location, $window, $timeout,
                                                       validacionTodasPospuestasService,growl, showAlert, $routeParams) {
            "use strict";

            $scope.idImagenPospuestaSeleccionada = $routeParams.idImagenPospuestaSeleccionada;
            $scope.listaImagenesVO = [];
            $scope.imagenRealSeleccionada=null;
			$scope.indiceTablaImagenesSeleccionado = 0;
			$scope.imagenObtenida = "static/dist/img/logoTeclo.png";
			$scope.imagenesAsignadas = false;
			$scope.imagenBlobSeleccionada = null;
			$scope.object = {co:'Confirmación ',de:'de ',us:'usuario'};
            $scope.parteImagen = "Anverso";
            $scope.isOpen = false;
            $scope.mostrarAlgo = false;
            $scope.todasMarcasVehiculos = [];
            $scope.marcaVehiculoSeleccionada = null;
            $scope.tieneAsignaciones = false;
            //$scope.enterDentro = false;
            $scope.lugaresEn = [{   "id":"9", "lugar":"DF"},
                                {   "id":"15", "lugar":"ESTADO DE MEXICO"},
                                {   "id":"99", "lugar" : "OTRO"},
                                {   "id":null, "lugar" : "VACÍO"}];
            $scope.nbPlacaExpEn =  null;
            $scope.nbLicExpEn = null;
            $scope.tiposLicencia = ["A","B","C","D","E","OTRO","VACÍO"];
            $scope.codigoTipoLicenciaSeleccionado =  null;
            $scope.validaFraccion = false;
            $scope.showParametros = false;
            $scope.fechaInicial = "";
            $scope.fechaFinal = "";
            $scope.listaValidadores = [];
            $scope.validadorSeleccionado = null;
            $scope.showDetalles = true;
            $scope.buscaBoletasPospuestasVO = {};
            $scope.txtCausaRechazo = "";
            $scope.showModalCancelar = false;
            $scope.cancelarImagenVO = {};

            /*Fin de declaraciones*/

            let actualizaSelectsIn = function () {

                switch ($scope.imagenRealSeleccionada.codigoTipo) {//Tipo de licencia de conducción
                    case "A":
                        $scope.codigoTipoLicenciaSeleccionado = $scope.tiposLicencia[0];
                        break;
                    case "B":
                        $scope.codigoTipoLicenciaSeleccionado = $scope.tiposLicencia[1];
                        break;
                    case "C":
                        $scope.codigoTipoLicenciaSeleccionado = $scope.tiposLicencia[2];
                        break;
                    case "D":
                        $scope.codigoTipoLicenciaSeleccionado = $scope.tiposLicencia[3];
                        break;
                    case "E":
                        $scope.codigoTipoLicenciaSeleccionado = $scope.tiposLicencia[4];
                        break;
                    default:
                        $scope.codigoTipoLicenciaSeleccionado = $scope.tiposLicencia[6];
                        break;
                }
                switch ($scope.imagenRealSeleccionada.nbPlacaExpEn) {//Placa expedida en
                    case "DF":
                        $scope.nbPlacaExpEn = $scope.lugaresEn[0];
                        break;
                    case "ESTADO DE MEXICO":
                        $scope.nbPlacaExpEn = $scope.lugaresEn[1];
                        break;
                    case "OTRO":
                        $scope.nbPlacaExpEn = $scope.lugaresEn[2];
                        break;
                    case"99":
                        $scope.nbPlacaExpEn = $scope.lugaresEn[2];
                        break;
                    default:
                        $scope.nbPlacaExpEn = $scope.lugaresEn[3];
                        break;

                }
                switch ($scope.imagenRealSeleccionada.nbLicExpEn) {
                    case "DF":
                        $scope.nbLicExpEn = $scope.lugaresEn[0];
                        break;
                    case "ESTADO DE MEXICO":
                        $scope.nbLicExpEn = $scope.lugaresEn[1];
                        break;
                    case "OTRO":
                        $scope.nbLicExpEn = $scope.lugaresEn[2];
                        break;
                    case"99":
                        $scope.nbLicExpEn = $scope.lugaresEn[2];
                        break;
                    default:
                        $scope.nbLicExpEn = $scope.lugaresEn[3];
                        break;
                }
                $('#select2-tipoLicencia-container').text($scope.codigoTipoLicenciaSeleccionado);
                $('#select2-placaExp-container').text($scope.nbPlacaExpEn.lugar);
                $('#select2-marcaVehiculo-container').text($scope.marcaVehiculoSeleccionada.nombreMarca);
                $('#select2-licExp-container').text($scope.nbLicExpEn.lugar);
            };

            let actualizaSelectsOut = function () {
                switch ($scope.codigoTipoLicenciaSeleccionado) {
                    case $scope.tiposLicencia[0]:
                        $scope.imagenRealSeleccionada.codigoTipo = "A";
                        break;
                    case $scope.tiposLicencia[1]:
                        $scope.imagenRealSeleccionada.codigoTipo = "B";
                        break;
                    case $scope.tiposLicencia[2]:
                        $scope.imagenRealSeleccionada.codigoTipo = "C";
                        break;
                    case $scope.tiposLicencia[3]:
                        $scope.imagenRealSeleccionada.codigoTipo = "D";
                        break;
                    case $scope.tiposLicencia[4]:
                        $scope.imagenRealSeleccionada.codigoTipo = "E";
                        break;
                    default:$scope.imagenRealSeleccionada.codigoTipo = null;
                        break;
                }
                switch ($scope.nbPlacaExpEn) {
                    case $scope.lugaresEn[0]:
                        $scope.imagenRealSeleccionada.nbPlacaExpEn = "9";
                        break;
                    case $scope.lugaresEn[1]:
                        $scope.imagenRealSeleccionada.nbPlacaExpEn = "15";
                        break;
                    case $scope.lugaresEn[2]:
                        $scope.imagenRealSeleccionada.nbPlacaExpEn = "99";
                        break;
                    default:
                        $scope.imagenRealSeleccionada.nbPlacaExpEn = "99";
                        break;
                }
                switch ($scope.nbLicExpEn) {
                    case $scope.lugaresEn[0]:
                        $scope.imagenRealSeleccionada.nbLicExpEn = "9";
                        break;
                    case $scope.lugaresEn[1]:
                        $scope.imagenRealSeleccionada.nbLicExpEn = "15";
                        break;
                    case $scope.lugaresEn[2]:
                        $scope.imagenRealSeleccionada.nbLicExpEn = "99";
                        break;
                    default:
                        $scope.imagenRealSeleccionada.nbLicExpEn = "99";
                        break;
                }
            };

            let verificaCamposObligatorios = function () {
                if(!$scope.imagenRealSeleccionada.nuNumeroFolio || $scope.imagenRealSeleccionada.nuNumeroFolio.length === 0){
                    growl.error("El número de folio no puede estar vacío","Entrada requerida");
                    return false;
                }
                if(!$scope.imagenRealSeleccionada.codigoPlaca || $scope.imagenRealSeleccionada.codigoPlaca.length === 0){
                    growl.error("La placa vehicular no puede estar vacía","Entrada requerida");
                    return false;
                }
                if(!$scope.imagenRealSeleccionada.nuFraccion || $scope.imagenRealSeleccionada.nuFraccion.length === 0){
                    growl.error("La fracción no puede estar vacía","Entrada requerida");
                    return false;
                }
                if(!$scope.imagenRealSeleccionada.nuArtInfrac || $scope.imagenRealSeleccionada.nuArtInfrac.length === 0){
                    growl.error("El artículo no puede estar vacía","Entrada requerida");
                    return false;
                }
                if(!$scope.imagenRealSeleccionada.codigoPlacaOficial || $scope.imagenRealSeleccionada.codigoPlacaOficial.length === 0){
                    growl.error("La placa del oficial no puede estar vacía","Entrada requerida");
                    return false;
                }

                if(!$scope.imagenRealSeleccionada.fhInfraccion || $scope.imagenRealSeleccionada.fhInfraccion.length === 0){
                    growl.error("La fecha no puede estar vacía","Entrada requerida");
                    return false;
                }

                return true;
            };

            let buscaDesconocido = function(value, index, array) {
                return value.codigoMarca === "DESC";
            };

        let buscaImagenSeleccionadaEnLista = function(value, index, array) {
            return value.idImagen === $scope.imagenRealSeleccionada.idImagen;
        };

            let buscarTodasMarcasVehiculos = function () {
                validacionTodasPospuestasService.getTodasMarcasVehiculos()
                    .success(function (data) {
                        $scope.todasMarcasVehiculos = data;
                        $scope.marcaVehiculoSeleccionada = $scope.todasMarcasVehiculos.find(buscaDesconocido);
                    }).error(function (data) {
                        if (data.message) {
                            showAlert.aviso(data.message, testAviso);
                        } else {
                            showAlert.aviso(data, testAviso);
                        }
                    })
            };

            let primeraAsignacionFunction = function () {
                validacionTodasPospuestasService.buscarPospuestas()
                    .success(function (data) {
                        //$scope.enterDentro = true;
                        $scope.mostrarAlgo = true;
                        $scope.listaImagenesVO = data;
                        if ($scope.listaImagenesVO.length !== 0) {//Hay boletas pospuestas
                            $scope.imagenesAsignadas = true;

                            if($scope.idImagenPospuestaSeleccionada != null){//Enviaron una imagen para validar como parámetro
                                let idNumber = parseInt($scope.idImagenPospuestaSeleccionada );
                                let imgEnLista = $scope.listaImagenesVO.find(function (value, index, array) {
                                    return value.idImagen === idNumber;
                                });
                                $scope.imagenRealSeleccionada = imgEnLista;
                            }else{
                                $scope.imagenRealSeleccionada = $scope.listaImagenesVO[0];
                            }

                            $scope.clickEnTablaImagenes($scope.imagenRealSeleccionada);
                            actualizaSelectsIn();
                        } else {
                            $scope.imagenesAsignadas = false;
                            $scope.imagenRealSeleccionada = null;
                            $scope.imagenObtenida = null;
                            $scope.imagenBlobSeleccionada = null;
                            $scope.ffTest($scope.imagenBlobSeleccionada);
                            showAlert.aviso("No tiene imágenes pospuestas", testAviso);
                        }
                        //$scope.enterDentro = false;

                    }).error(function (data) {
                        if (data.message) {
                            showAlert.aviso(data.message, testAviso);
                        } else {
                            showAlert.aviso(data, testAviso);
                        }
                    //$scope.enterDentro = false;
                    })

            };

            let cancelarEntrada = function () {
                $scope.imagenesAsignadas = false;
            };

            let procederProcesoInicial2 = function () {
                validacionTodasPospuestasService.existenAsignacionesPospuestas()
                    .success(function (data) {
                        $scope.tieneAsignaciones = data;
                        if($scope.tieneAsignaciones === false){
                            showAlert.aviso("No existen boletas pospuestas.",testAviso);

                        }else
                            primeraAsignacionFunction();
                    }).error(function (data) {
                        if (data.message) {
                            showAlert.aviso(data.message, testAviso);
                        } else {
                            showAlert.aviso(data, testAviso);
                        }
                    });

                //$scope.mostrarAlgo = true;
            };

            let testCancelAsignacion = function(){
                $scope.mostrarAlgo = true;
            };


            let testAviso = function () {
                //growl.info('',{title:'Alerta de aviso'});
            };
            let testAviso2 = function () {
                growl.error("Verifique los parámetros introducidos.");
            };
			$scope.clickEnTablaImagenes = function(imagenVO){
				$scope.imagenRealSeleccionada = imagenVO;
				$scope.indiceTablaImagenesSeleccionado = $scope.listaImagenesVO.indexOf(imagenVO);
                let idImagenSeleccionada = $scope.imagenRealSeleccionada.idImagen;
				if($scope.imagenRealSeleccionada.lbImagenAnverso !== null){//Ya tiene blob asignado
                    $scope.imagenBlobSeleccionada = $scope.imagenRealSeleccionada.lbImagenAnverso.lbImagen;
                    $scope.ffTest($scope.imagenBlobSeleccionada);
                    $scope.parteImagen = "Anverso";
                }else{//No tiene blob asignado. TODO:Buscar blob en servicio
                    validacionTodasPospuestasService.blobsByIdImagen(idImagenSeleccionada)
                        .success(function (data) {
                            if(data[0].codigoImagen === "A"){
                                $scope.imagenRealSeleccionada.lbImagenAnverso = data[0];
                                $scope.imagenRealSeleccionada.lbImagenReverso = data[1];
                            }else{
                                $scope.imagenRealSeleccionada.lbImagenAnverso = data[1];
                                $scope.imagenRealSeleccionada.lbImagenReverso = data[0];
                            }
                            $scope.imagenBlobSeleccionada = $scope.imagenRealSeleccionada.lbImagenAnverso.lbImagen;
                            $scope.ffTest($scope.imagenBlobSeleccionada);
                            $scope.parteImagen = "Anverso";
                        }).error(function (data) {
                            showAlert.aviso("Ocurrió un error al traer los blobs", testAviso);
                        })
                }

                $scope.ffTest($scope.imagenBlobSeleccionada);
                $scope.parteImagen = "Anverso";

			};

			$scope.isImagenAceptable = function (imagen) {
                validacionTodasPospuestasService.isImagenAceptable(imagen)
                    .success(function (data) {
                        return data;
                    }).error(function (data) {
                        if (data.message) {
                            showAlert.aviso("Debe contactar con su administrador.", testAviso);
                        } else {
                            showAlert.aviso("Debe contactar con su administrador.", testAviso);
                        }

                    })
            };

            let procederAceptarImagen = function () {
                $scope.imagenRealSeleccionada.vehiculoMarca = $scope.marcaVehiculoSeleccionada;
                actualizaSelectsOut();

                validacionTodasPospuestasService.marcarImagenAceptada($scope.imagenRealSeleccionada)
                    .success(function (data) {
                            //$scope.enterDentro = true;
                            $scope.resultadoImagenAceptada = data;
                            if ($scope.resultadoImagenAceptada === true) {
                                /*Se procesó correctamente la imagen como aceptada.*/
                                /*Eliminar de la tabla de imágenes la imagen procesada:*/
                                $scope.listaImagenesVO.splice($scope.indiceTablaImagenesSeleccionado, 1);

                                /*Si la tabla queda vacía debe ser llenada nuevamente:*/
                                if ($scope.listaImagenesVO.length === 0) {
                                    /*Si ya se vació la lista hay que llenarla de nuevo
                                    otra línea más */
                                    primeraAsignacionFunction();
                                }
                                /*Hago la primera de la lista la seleccionada */
                                if ($scope.listaImagenesVO.length !== 0) {
                                    $scope.imagenRealSeleccionada = $scope.listaImagenesVO[0];
                                    let idImagenSeleccionada = $scope.imagenRealSeleccionada.idImagen;
                                    if($scope.imagenRealSeleccionada.lbImagenAnverso !== null){//Ya tiene blob asignado
                                        $scope.imagenBlobSeleccionada = $scope.imagenRealSeleccionada.lbImagenAnverso.lbImagen;
                                        $scope.ffTest($scope.imagenBlobSeleccionada);
                                        $scope.parteImagen = "Anverso";
                                    }else{//No tiene blob asignado. TODO:Buscar blob en servicio
                                        validacionTodasPospuestasService.blobsByIdImagen(idImagenSeleccionada)
                                            .success(function (data) {
                                                $scope.imagenRealSeleccionada.lbImagenAnverso = data [0];
                                                $scope.imagenRealSeleccionada.lbImagenReverso = data [1];
                                                $scope.imagenBlobSeleccionada = $scope.imagenRealSeleccionada.lbImagenAnverso.lbImagen;
                                                $scope.ffTest($scope.imagenBlobSeleccionada);
                                                $scope.parteImagen = "Anverso";
                                            }).error(function (data) {
                                            showAlert.aviso("Ocurrió un error al traer los blobs", testAviso);
                                        })
                                    }

                                    //$scope.ffTest($scope.imagenBlobSeleccionada);
                                    //$scope.parteImagen = "Anverso";
                                } else {
                                    //$scope.imagenRealSeleccionada = null;
                                }

                                actualizaSelectsIn();
                                growl.info('Imagen aceptada.', {title: 'Acción terminada'});
                                //$scope.enterDentro = false;
                            }
                        //$scope.enterDentro = false;

                    }).error(function (data) {
                    if (data.message) {
                        $scope.imagenesAsignadas = false;
                        actualizaSelectsIn();
                        showAlert.aviso("Debe contactar con su administrador.", testAviso);
                        //$scope.enterDentro = false;
                    } else {
                        $scope.imagenesAsignadas = false;
                        actualizaSelectsIn();
                        showAlert.aviso("Debe contactar con su administrador.", testAviso);
                        //$scope.enterDentro = false;
                    }

                });
                //$scope.enterDentro = false;
                $scope.marcaVehiculoSeleccionada = $scope.todasMarcasVehiculos.find(buscaDesconocido);
            };

			$scope.clickEnBotonAceptarImagen = function(){
                if(!verificaCamposObligatorios()){
                    return;
                }
                if($scope.myform.$invalid) {
                    showAlert.requiredFields($scope.myform);
                    growl.error('Formulario incompleto', {
                        ttl: 3000,
                        disableCountDown: true
                    });
                    return;
                }

				if($scope.imagenRealSeleccionada ){
				    //if(!$scope.enterDentro){
                      //  $scope.enterDentro = true;

                    validacionTodasPospuestasService.isImagenAceptable($scope.imagenRealSeleccionada)
                        .success(function (data) {
                            if(data){
                                $scope.validaFraccion = false;
                                showAlert.confirmacion("¿Está seguro de que aceptará la imagen "+$scope.imagenRealSeleccionada.nombreImagen+"?",
                                    procederAceptarImagen, $scope.object, testCancelConfirmacion);
                            }else{
                                $scope.validaFraccion = true;
                                growl.error("Hay campos inconsistentes en el formulario.", {
                                    ttl: 3000,
                                    disableCountDown: true
                                });
                               return;
                            }
                        }).error(function (data) {
                            showAlert.aviso("Debe contactar con su administrador.", testAviso);
                        })

                    //}
				}else{
					showAlert.aviso("Debe seleccionar una imagen",testAviso);;
				}

			};

            $scope.saveModal = function(){
                $scope.showModal = false;
                $scope.boletaPosponerVO.idImagen = $scope.imagenRealSeleccionada.idImagen;
                procederPosponerImagen();
            };

            $scope.saveModalCancelar = function(){
                $scope.showModalCancelar  = false;
                $scope.cancelarImagenVO.idImagenSeleccionada = $scope.imagenRealSeleccionada.idImagen;
                $scope.procederRechazarImagen();
            };

            $scope.procederRechazarImagen = function () {
                validacionTodasPospuestasService.marcarImagenRechazada($scope.cancelarImagenVO)
                    .success(function (data) {
                        $scope.cancelarImagenVO.causa = "";
                        $scope.resultadoImagenRechazada = data;
                        if ($scope.resultadoImagenRechazada === true) {
                            /*Se procesó correctamente la imagen como rechazada.*/
                            /*Eliminar de la tabla de imágenes la imagen procesada:*/
                            $scope.listaImagenesVO.splice($scope.indiceTablaImagenesSeleccionado, 1);
                            /*Si la tabla queda vacía debe ser llenada nuevamente:*/
                            if ($scope.listaImagenesVO.length === 0) {
                                /*Si ya se vació la lista hay que llenarla de nuevo
                                otra línea más */
                                primeraAsignacionFunction();
                            }
                            /*Hago la primera de la lista la seleccionada */
                            if ($scope.listaImagenesVO.length !== 0) {
                                $scope.imagenRealSeleccionada = $scope.listaImagenesVO[0];
                                let idImagenSeleccionada = $scope.imagenRealSeleccionada.idImagen;
                                if($scope.imagenRealSeleccionada.lbImagenAnverso !== null){//Ya tiene blob asignado
                                    $scope.imagenBlobSeleccionada = $scope.imagenRealSeleccionada.lbImagenAnverso.lbImagen;
                                    $scope.ffTest($scope.imagenBlobSeleccionada);
                                    $scope.parteImagen = "Anverso";
                                }else{//No tiene blob asignado. TODO:Buscar blob en servicio
                                    validacionTodasPospuestasService.blobsByIdImagen(idImagenSeleccionada)
                                        .success(function (data) {
                                            $scope.imagenRealSeleccionada.lbImagenAnverso = data [0];
                                            $scope.imagenRealSeleccionada.lbImagenReverso = data [1];
                                            $scope.imagenBlobSeleccionada = $scope.imagenRealSeleccionada.lbImagenAnverso.lbImagen;
                                            $scope.ffTest($scope.imagenBlobSeleccionada);
                                            $scope.parteImagen = "Anverso";
                                        }).error(function (data) {
                                        showAlert.aviso("Ocurrió un error al traer los blobs", testAviso);
                                    })
                                }

                            } else {
                                //$scope.imagenRealSeleccionada = null;
                            }
                            actualizaSelectsIn();
                            growl.info('Imagen rechazada', {title: 'Acción terminada'});
                        }
                    }).error(function (data) {
                        $scope.cancelarImagenVO.causa = "";
                        if (data.message) {
                            $scope.imagenesAsignadas = false;
                            actualizaSelectsIn();
                            showAlert.aviso("Debe contactar con su administrador.", testAviso);
                        } else {
                            $scope.imagenesAsignadas = false;
                            actualizaSelectsIn();
                            showAlert.aviso("Debe contactar con su administrador.", testAviso);
                        }
                    });
                $scope.marcaVehiculoSeleccionada = $scope.todasMarcasVehiculos.find(buscaDesconocido);
            };

            $scope.clickEnBotonPosponerImagen = function(){
                if($scope.imagenRealSeleccionada ){
                    showAlert.confirmacion("¿Está seguro de que pospondrá la evaluación de la boleta?",
                        procederPosponerImagen, $scope.object, testCancelConfirmacion);
                }else{
                    showAlert.aviso("Debe seleccionar una imagen", testAviso);
                }
            };
			
			$scope.clickEnBotonRechazarImagen = function(){
					if($scope.imagenRealSeleccionada ){
						showAlert.confirmacion("¿Está seguro de que rechazará la imagen?",
                            $scope.procederRechazarImagen, $scope.object, testCancelConfirmacion);
					}else{
						showAlert.aviso("Debe seleccionar una imagen", testAviso);
					}
			};

			$scope.clickEnBotonMostrarAnverso = function(){
				$scope.imagenBlobSeleccionada = $scope.imagenRealSeleccionada.lbImagenAnverso.lbImagen;
				$scope.ffTest($scope.imagenBlobSeleccionada);
                $scope.parteImagen = "Anverso";

			};

			$scope.clickEnBotonMostrarReverso = function(){
				$scope.imagenBlobSeleccionada = $scope.imagenRealSeleccionada.lbImagenReverso.lbImagen;
				$scope.ffTest($scope.imagenBlobSeleccionada);
                $scope.parteImagen = "Reverso";
			};

			$scope.clickEnBotonCambiaOrdenImagenes = function(){
				showAlert.confirmacion("¿Está seguro de cambiar el orden de anverso y reverso?", 
							procederCambiarOrdenImagenes, $scope.object, testCancelConfirmacion);
			};

            let procederCambiarOrdenImagenes = function () {
                let idLbImagenSeleccionada = $scope.imagenRealSeleccionada.lbImagenAnverso.idLbImagen;
                validacionTodasPospuestasService.cambiaOrdenImagenes(idLbImagenSeleccionada)
                    .success(function (data) {
                        $scope.imagenRealSeleccionada.lbImagenAnverso = data[0];
                        $scope.imagenRealSeleccionada.lbImagenReverso = data[1];
                        $scope.clickEnBotonMostrarAnverso();
                        growl.info('Cambiado el orden correctamente', {title: 'Acción terminada'});
                    }).error(function (data) {
                    if (data.message) {
                        showAlert.aviso(data.message, testAviso);
                    } else {
                        showAlert.aviso(data, testAviso);
                    }

                    })
            };

            let testCancelConfirmacion = function () {
                //$scope.enterDentro = false;
            };

            let buscarTodosValidadores = function(){
                validacionTodasPospuestasService.buscarTodosValidadores()
                    .success(function (data) {
                        $scope.listaValidadores = data;
                    }).error(function (data) {
                    showAlert.aviso("Ocurrió un error al buscar los validadores:" + data.message, testAviso);

                    })
            };

            let hayDosFechasSeleccionadas = function(){
                return !($scope.fechaFinal !== null && $scope.fechaFinal === null ||
                    $scope.fechaFinal === null && $scope.fechaFinal !== null);
            };

            let estanVaciosCamposBusqueda = function(){
                return $scope.fechaFinal === null && $scope.fechaFinal === null &&
                    ($scope.validadorSeleccionado === null || $scope.validadorSeleccionado === "");
            };

            function retornaElementosFecha(fechaStr){
                return fechaStr.split("/") ;
            }

            $scope.clickBotonBuscarBoletas = function () {
                if($scope.myform.$invalid || ($scope.fechaInicial !== '' && $scope.fechaFinal === '' ) ||
                    ($scope.fechaInicial === '' && $scope.fechaFinal !== '' )) {
                    showAlert.requiredFields($scope.myform);
                    $scope.fechaError = true;
                    growl.error('Formulario incompleto', {
                        ttl: 3000,
                        disableCountDown: true
                    });
                    return;
                }

                let fechaFinal = $scope.fechaFinal;
                let fechaFinalFormat = retornaElementosFecha($scope.fechaFinal);
                let fFinal = new Date(fechaFinalFormat[2], fechaFinalFormat[1]-1, fechaFinalFormat[0]);
                let fechaInicial = $scope.fechaInicial;
                let fechaInicialFormat = retornaElementosFecha($scope.fechaInicial);
                let fInicial = new Date(fechaInicialFormat[2], fechaInicialFormat[1]-1, fechaInicialFormat[0]);
                $scope.listaImagenes = null;
                $scope.imagenSeleccionada = null;

                if(($scope.fechaFinal === '' && $scope.fechaInicial !== '') ||
                    ($scope.fechaFinal !== '' && $scope.fechaInicial === '') ){
                    $scope.fechaError = true;
                    showAlert.error("No puede seleccionarse solamente una fecha");
                    return;
                }
                if (fFinal < fInicial){
                    $scope.fechaError = true;
                    showAlert.error("La fecha inicial debe ser anterior a la final");
                    return;
                }
                $scope.fechaError = false;
                if($scope.showParametros === false){
                    primeraAsignacionFunction();
                }else{//La búsqueda es avanzada
                    if($scope.validadorSeleccionado){//Hay un validador seleccionado
                        if($scope.fechaFinal !== "" && $scope.fechaInicial !== ""){
                            fechaFinal = $scope.fechaFinal + " 23:59:59" ;
                            fechaInicial = $scope.fechaInicial + " 00:00:00";
                        }
                        $scope.buscaBoletasPospuestasVO.idValidador = $scope.validadorSeleccionado.idValidador;
                        $scope.buscaBoletasPospuestasVO.fechaInicial = fechaInicial;
                        $scope.buscaBoletasPospuestasVO.fechaFinal = fechaFinal;

                        validacionTodasPospuestasService.imagenesPospuestasParam($scope.buscaBoletasPospuestasVO)
                            .success(function(data) {
                                if(data.length === 0){
                                    showAlert.error("No existen imágenes que cumplan los parámetros de búsqueda.");
                                    return;
                                }
                                $scope.listaImagenesVO = data;
                                $scope.imagenRealSeleccionada = $scope.listaImagenesVO[0];
                                $scope.clickEnTablaImagenes($scope.imagenRealSeleccionada);
                            }).error(function(data) {
                            if(data.message){
                                showAlert.error(data.message);
                            }else{
                                showAlert.error(data);
                            }

                        })

                    }else{/*No hay validador seleccionado, solo fechas*/
                        if($scope.fechaFinal !== "" && $scope.fechaInicial !== ""){
                            fechaFinal = $scope.fechaFinal + " 23:59:59" ;
                            fechaInicial = $scope.fechaInicial + " 00:00:00";
                        }
                        $scope.buscaBoletasPospuestasVO.idValidador = $scope.validadorSeleccionado.idValidador;
                        $scope.buscaBoletasPospuestasVO.fechaInicial = fechaInicial;
                        $scope.buscaBoletasPospuestasVO.fechaFinal = fechaFinal;

                        validacionTodasPospuestasService.imagenesPospuestasParam($scope.buscaBoletasPospuestasVO)
                            .success(function(data) {
                                if(data.length === 0){
                                    showAlert.error("No existen imágenes que cumplan los parámetros de búsqueda.");
                                    return;
                                }
                                $scope.listaImagenesVO = data;
                                $scope.imagenRealSeleccionada = $scope.listaImagenesVO[0];
                                $scope.clickEnTablaImagenes($scope.imagenRealSeleccionada);

                            }).error(function(data) {
                            if(data.message){
                                showAlert.error(data.message);
                            }else{
                                showAlert.error(data);
                            }

                        })
                    }
                }

            };

			/*Inicio de la funcion para crear el blob y su URL*/						
			$scope.ffTest = function(data){
				if(!data){
					$scope.imagenObtenida = "static/dist/img/logoTeclo.png"
				}else{
					let binary = $scope.fixBinary(atob(data));
					let blob = new Blob([binary], {type: 'image/jpg'});
					$scope.imagenObtenida = URL.createObjectURL(blob);
				}
				
			};

			$scope.fixBinary = function(bin) {
				  let length = bin.length;
				  let buf = new ArrayBuffer(length);
				  let arr = new Uint8Array(buf);
				  for (let i = 0; i < length; i++) {
					arr[i] = bin.charCodeAt(i);
				  }
				  return buf;
			};

               let viewer = new Viewer(document.getElementById('imagenMostrar'), {
                                            inline: false,
                                            viewed: function () {
                                                viewer.zoomTo(1);
                                            }
                                        });


/*            $document.bind('keyup', function (e) {
                e.stopPropagation();
                if($scope.enterDentro)
                    return;
                if(e.keyCode === 13){
                    if(!$scope.enterDentro){
                        $scope.clickEnBotonAceptarImagen();
                    }
                }
            });*/
      $scope.validaCambios = function(){
    	  $scope.validaFraccion = false;
      };

      /*Inicializaciones*/
        //buscarTodosValidadores();
        buscarTodasMarcasVehiculos();
        procederProcesoInicial2();

            
});/*Fin de validacionImagenesController*/

