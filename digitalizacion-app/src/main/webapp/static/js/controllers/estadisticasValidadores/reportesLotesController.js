angular.module(appTeclo).controller(
    "reportesLotesController",
    function($rootScope, $scope, $location, $window, $timeout,reportesLotesService,growl, showAlert) {
        "use strict";
        $scope.showParametros = false;
        $scope.estatusProcesoSeleccionado = null;
        $scope.loteSeleccionado = null;
        $scope.fechaInicial = '' ;
        $scope.fechaFinal = '' ;
        $scope.estadosLote = null;
        $scope.results = null;
        $scope.listaImagenes = null;
        $scope.imagenSeleccionada = null;
        $scope.idEstadoInformacionValidada = -1;
        $scope.showModal = false;
        $scope.anverso = null;
        $scope.reverso = null;
        $scope.imagenRealSeleccionada=null;
        $scope.indiceTablaImagenesSeleccionado = 0;
        $scope.imagenObtenida = "static/dist/img/logoTeclo.png";
        $scope.imagenesAsignadas = false;
        $scope.imagenBlobSeleccionada = null;
        $scope.object = {co:'Confirmación ',de:'de ',us:'usuario'};
        $scope.showDetalles = false;
        $scope.listaIdLotes = "";
        $scope.fechaError = false;
        $scope.cantidadLotesSeleccionados = 0;
        $scope.error = null;
        $scope.showExportarExcelBoton = false;
        $scope.setListaLotesSeleccionados = new Set();
        let excelIconoNormal = "static/dist/img/excel.png";
        let excelIconoDegradado = "static/dist/img/excelsm.png";
        $scope.excelIcono = excelIconoDegradado;
        let cursorPointer = "cursor:pointer;";
        let cursorNoPointer = "cursor:not-allowed;";
        $scope.cursorType = cursorNoPointer;
        $scope.cantidadMaximaLotes = 20;

        $scope.tipoLicenciaSeleccionada = null;
        $scope.licenciaExpEnSeleccionada = null;
        $scope.placaExpEnSeleccionada = null;

        $scope.showDetallesChange = function(){
            $scope.showDetalles = !$scope.showDetalles;
        };

        $scope.clickTablaLotes = function(lote){
            $scope.loteSeleccionado = lote;
        };

        $scope.onChange = function(newValue, oldValue){
            if(newValue === true){
                ++$scope.cantidadLotesSeleccionados;
                $scope.setListaLotesSeleccionados.add($scope.loteSeleccionado.numeroLote);
                $scope.loteSeleccionado.isSelected = true;
            }else{
                --$scope.cantidadLotesSeleccionados;
                $scope.setListaLotesSeleccionados.delete($scope.loteSeleccionado.numeroLote);
                $scope.loteSeleccionado.isSelected = false;
            }

            $scope.showExportarExcelBoton = $scope.cantidadLotesSeleccionados > 0;
            if($scope.cantidadLotesSeleccionados > 0){
                $scope.excelIcono = excelIconoNormal;
                $scope.cursorType = cursorPointer;
            }else{
                $scope.excelIcono = excelIconoDegradado;
                $scope.cursorType = cursorNoPointer;
            }

        };

        let inicializarEstadosLotes = function () {
            $scope.imagenSeleccionada = null;
            reportesLotesService.estadosLotesIniciales()
                .success(function (data) {
                    $scope.estadosLote = data;

                }).error(function (data) {
                    if (data.message) {
                        showAlert.error(data.message);
                    } else {
                        showAlert.error(data);
                    }

                });

            reportesLotesService.consultaGeneral()
                .success(function (data) {
                    $scope.results = data;
                    if(data.length === 0){
                        showAlert.error("No existen lotes en este momento.");
                    }

                }).error(function (data) {
                if (data.message) {
                    showAlert.error(data.message);
                } else {
                    showAlert.error(data);
                }

            })
        };
        inicializarEstadosLotes();

        
        $scope.isLoteLiberable = function(lote){
         /*    estadoInformacionValidada = 
                    $scope.estadosLote.find(function(element){
                                                if(element.codigoEstatus == "Informacion Validada"){
                                                    return element;
                                                }
                                            }); */
            return lote.estatusProceso.codigoEstatus === "INFOVALIDADA";
        };  

        $scope.isLoteProcesado = function(lote){
            return lote.estatusProceso.codigoEstatus === "PROCESADO";
        };
        
        $scope.isLoteCancelable = function(lote){
            
            return lote.estatusProceso.codigoEstatus === "PROCESADO" ||
                lote.estatusProceso.codigoEstatus === "INFOVALIDADA" ;
            //|| lote.estatusProceso.codigoEstatus === "FORMLIBERACION";
        };

        $scope.isLoteOtro = function(lote){
            return !isLoteCancelable(lote) && !isLoteLiberable(lote);
        };

        function retornaElementosFecha(fechaStr){
            return fechaStr.split("/") ;
        }

        $scope.clickShowParametros = function(){
            $scope.fechaFinal = '';
            $scope.fechaInicial = '';

        };

        $scope.clickBontonBuscar = function(){
            if($scope.form.$invalid || ($scope.fechaInicial !== '' && $scope.fechaFinal === '' ) ||
                ($scope.fechaInicial === '' && $scope.fechaFinal !== '' )) {
                showAlert.requiredFields($scope.form);
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
                reportesLotesService.consultaGeneral()
                    .success(function(data) {/**/ 
                        $scope.results = data;
                        if(data.length === 0){
                            showAlert.error("No existen lotes en este momento.");
                        }
                    }).error(function(data) {
                        if(data.message){
                            showAlert.error(data.message);
                        }else{
                            showAlert.error(data);
                        }
                        
                    })
            }else{
                if($scope.estatusProcesoSeleccionado){
                    if($scope.fechaFinal !== "" && $scope.fechaInicial !== ""){
                        fechaFinal = $scope.fechaFinal + " 23:59:59" ;
                        fechaInicial = $scope.fechaInicial + " 00:00:00";
                    }

                    reportesLotesService.consultaAvanzadaPost(fechaInicial, fechaFinal,
                                                                $scope.estatusProcesoSeleccionado.idEstatus)
                        .success(function(data) {
                            $scope.results = data;
                            if(data.length === 0){
                                showAlert.error("No existen lotes que cumplan los parámetros de búsqueda.");

                            }
                            //$scope.estatusProcesoSeleccionado = null;
                        }).error(function(data) {
                            if(data.message){
                                showAlert.error(data.message);
                            }else{
                                showAlert.error(data);
                            }
                            
                        })
                }else{
                    if($scope.fechaFinal !== "" && $scope.fechaInicial !== ""){
                        fechaFinal = $scope.fechaFinal + " 23:59:59" ;
                        fechaInicial = $scope.fechaInicial + " 00:00:00";
                    }
                    reportesLotesService.consultaAvanzadaPost(fechaInicial, fechaFinal,
                        $scope.estatusProcesoSeleccionado)
                            .success(function(data) {
                                $scope.results = data;
                                if(data.length === 0){
                                    showAlert.error("No existen lotes que cumplan los parámetros de búsqueda.");
                                }
                                //$scope.estatusProcesoSeleccionado = null;
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

        $scope.object = {co:'Confirmación ',de:'de ',us:'usuario'};

        $scope.formarParaLiberarLoteSeleccionado = function(){
            showAlert.confirmacion("¿Seguro que desea liberar el lote seleccionado?", testConfirmacionFormarParaLiberarLote, 
                                    $scope.object, testCancelConfirmacion);
        };
        
        $scope.cancelarLoteSeleccionado = function(){
            showAlert.confirmacion("¿Seguro que desea cancelar el lote seleccionado?", testConfirmacionCancelarLote, 
                                    $scope.object, testCancelConfirmacion);
        };

        $scope.ponerLoteEnValidandoInformacion = function(){
            showAlert.confirmacion("¿Está seguro de cambiar el lote seleccionado al estatus Validando Información?", testConfirmacionPonerValidandoInformacion,
                                    $scope.object, testCancelConfirmacion);
        };

        /*Función para hacer que el lote seleccionado esté en Validando Información*/
        let testConfirmacionPonerValidandoInformacion = function (o) {
            let idLoteSeleccionado = $scope.loteSeleccionado.numeroLote;
            reportesLotesService.enValidandoInformacion(idLoteSeleccionado)
                .success(function (data) {
                    $scope.clickBontonBuscar();
                    //showAlert.aviso("El lote cambió a estatus Validando Información correctamente", testAviso);
                    growl.info('El lote cambió a estatus Validando Información correctamente', {title: 'Acción terminada'});
                }).error(function (data) {
                if (data.message) {
                    showAlert.error(data.message);
                } else {
                    showAlert.error(data);
                }

            })
        };
        

        /*Función para cancelar el lote seleccionado*/
        let testConfirmacionCancelarLote = function (o) {
            let idLoteSeleccionado = $scope.loteSeleccionado.numeroLote;
            reportesLotesService.cancelarLote(idLoteSeleccionado)
                .success(function (data) {
                    $scope.clickBontonBuscar();
                    //showAlert.aviso("El lote se canceló correctamente", testAviso);
                    growl.info('El lote se canceló correctamente', {title: 'Acción terminada'});
                }).error(function (data) {
                if (data.message) {
                    showAlert.error(data.message);
                } else {
                    showAlert.error(data);
                }
            })
        };

        let testConfirmacionFormarParaLiberarLote = function () {
            let idLoteSeleccionado = $scope.loteSeleccionado.numeroLote;
            reportesLotesService.formarParaLiberarLote(idLoteSeleccionado)
                .success(function (data) {
                    $scope.clickBontonBuscar();
                    //showAlert.aviso("El lote se formó para ser liberado correctamente", testAviso);
                    growl.info('El lote se formó para ser liberado correctamente', {title: 'Acción terminada'});
                }).error(function (data) {
                if (data.message) {
                    showAlert.error(data.message);
                } else {
                    showAlert.error(data);
                }
            })

        };

        let testCancelConfirmacion = function () {
            //vacío
        };
        let testAviso = function () {
            //growl.info('',{title:'Alerta de aviso'});
        };

        /*Función para mostrar los detalles del lote seleccioando*/
        $scope.detallesLote= function(lote){
            let idLoteSeleccionado = lote.numeroLote;
            reportesLotesService.imagenesPorLote(idLoteSeleccionado)
                .success(function (data) {
                    $scope.listaImagenes = data;
                    $scope.showDetalles = true;
                    actualizaValoresParaUI();
                }).error(function (data) {
                if (data.message) {
                    showAlert.error(data.message);
                } else {
                    showAlert.error(data);
                }
            })
        };

        let actualizaValoresParaUI = function(){
            for(let i=0; i<$scope.listaImagenes.length; i++){
                if($scope.listaImagenes[i].fechaLiberacion ==='' || $scope.listaImagenes[i].fechaLiberacion == null){
                    $scope.listaImagenes[i].fechaLiberacion = "No liberada";
                }
            }

        };

        let actualizaLugaresEn = function(){

            switch ($scope.imagenSeleccionada.nbLicExpEn) {
                case "9":
                    $scope.licenciaExpEnSeleccionada = "DF";
                    break;
                case "15":
                    $scope.licenciaExpEnSeleccionada = "ESTADO DE MÉXICO";
                    break;
                case "99":
                    $scope.licenciaExpEnSeleccionada = "OTRO";
                    break;
                case null:
                    $scope.licenciaExpEnSeleccionada = "VACÍO";
                    break;
                default: $scope.licenciaExpEnSeleccionada = $scope.imagenSeleccionada.nbLicExpEn;
                    break
            }

            switch ($scope.imagenSeleccionada.nbPlacaExpEn) {
                case "9":
                    $scope.placaExpEnSeleccionada = "DF";
                    break;
                case "15":
                    $scope.placaExpEnSeleccionada = "ESTADO DE MÉXICO";
                    break;
                case "99":
                    $scope.placaExpEnSeleccionada = "OTRO";
                    break;
                case null:
                    $scope.placaExpEnSeleccionada = "VACÍO";
                    break;
                default:
                    $scope.placaExpEnSeleccionada = $scope.imagenSeleccionada.nbPlacaExpEn;
                    break;
            }

        };

        $scope.clickTablaImagenes = function(imagen){
            $scope.imagenSeleccionada = imagen;
            actualizaLugaresEn();
        };

        $scope.clickDetallesImagenes = function(imagen){
            let idImagenSeleccionada = imagen.idImagen;
            reportesLotesService.blobsPorImagen(idImagenSeleccionada)
                .success(function (data) {
                    $scope.anverso = data[0];
                    $scope.reverso = data[1];

                    $scope.listaImagenesVO = data;
                    $scope.imagenRealSeleccionada = $scope.listaImagenesVO[0];
                    $scope.anverso =  $scope.ffTest($scope.listaImagenesVO[0].lbImagen);
                    $scope.reverso =  $scope.ffTest($scope.listaImagenesVO[1].lbImagen);
                    //$scope.imagenBlobSeleccionada = $scope.imagenRealSeleccionada.lbImagen;
                    //$scope.ffTest($scope.imagenBlobSeleccionada);
                }).error(function (data) {
                if (data.message) {
                    showAlert.error(data.message);
                } else {
                    showAlert.error(data);
                }
            })
        };


        function llenaListaIdLotes(){
/*            $scope.listaIdLotes="";
            $scope.setListaLotesSeleccionados.forEach((value, valueAgain, set) => {
                $scope.listaIdLotes = $scope.listaIdLotes + value + ",";
            });
            let lastIndex = $scope.listaIdLotes.lastIndexOf(",");
            $scope.listaIdLotes = $scope.listaIdLotes.slice(0,lastIndex);*/
    
            $scope.listaIdLotes="";
    
            let valorMaximo = $scope.setListaLotesSeleccionados.size > $scope.cantidadMaximaLotes ? $scope.cantidadMaximaLotes : $scope.setListaLotesSeleccionados.size ;
            let arregloLotes = Array.from($scope.setListaLotesSeleccionados);
           
            for(let i = 0; i < valorMaximo; i++){
                if(i === valorMaximo - 1){
                    $scope.listaIdLotes = $scope.listaIdLotes + arregloLotes[i];
                }else{
                    $scope.listaIdLotes = $scope.listaIdLotes + arregloLotes[i] + ",";
                }
            }

        }

        function save(file, fileName) {
            let url = window.URL || window.webkitURL;
            let blobUrl = url.createObjectURL(file);
            let a = document.createElement('a');
            a.href = blobUrl;
            a.target = '_blank';
            a.download = fileName;
            document.body.appendChild(a);
            a.click();
        }
        
        $scope.confirmarDescargaExcelMovimientos = function(){
            showAlert.confirmacion("¿Seguro que desea exportar a excel la selección de lotes?", $scope.descargaExcelMovimientos,
                $scope.object, testCancelConfirmacion);
        };
        
        $scope.descargaExcelMovimientos = function(){
            llenaListaIdLotes();
            reportesLotesService.getFicheroExcelB($scope.listaIdLotes)
                .success(function (data,status, headers) {
                    let filename = headers('filename');
                    let contentType = headers('content-type');
                    let file = new Blob([ data ], {
                        type : 'application/vnd.ms-excel;base64'
                    });

                    save(file, filename);
                    $scope.error = false;

                }).error(function (data) {
                    $scope.error = data;
                });

        }

    });/*Fin de liberacionImagenesController*/

