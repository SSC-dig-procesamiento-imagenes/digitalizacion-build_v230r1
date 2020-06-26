angular.module(appTeclo).controller(
    "estadisticasValidadoresController",
    function($rootScope, $scope, $location, $window, $timeout,estadisticasValidadoresService,growl, showAlert) {

        $scope.listadoEstadisticoVO = null;
        $scope.listado = [];
        $scope.showParametros = true;
        $scope.fechaInicial = '';
        $scope.fechaFinal = '';
        $scope.cantTotalRechazadas = 0;
        $scope.cantTotalAceptadas = 0;
        $scope.cantTotalPospuestas = 0;
        $scope.mostrarTabla = true;
        $scope.mostrarBarras = false;
        $scope.mostrarPastel = false;
        $scope.hayEstadisticas = false;
        $scope.validacionFechasOk=true;

        /*Inicio.Variables del gráfico*/
        let validadoresLista = [];
        let aceptadasLista = [];
        let rechazadasLista = [];
        let pospuestasLista = [];
        let myChart = echarts.init(document.getElementById('mainEcharts'));
        let pieChart = echarts.init(document.getElementById('pieEcharts'));
        /*Fin.Variables del gráfico*/


        let init = function () {
            $scope.listado = [];
            estadisticasValidadoresService.getListadoEstadisticoCompleto()
                .success(function (data) {
                    $scope.listadoEstadisticoVO = data;
                    if(!$scope.listadoEstadisticoVO || $scope.listadoEstadisticoVO.length === 0){
                        $scope.hayEstadisticas = false;
                        showAlert.aviso("No hay estadísticas disponibles en este momento", testAviso);
                        return;
                    }else {
                        $scope.hayEstadisticas = true;
                    }

                    llenadoDeVariables();
                    llenadoVariablesGrafico();
                    llenadoVariablesPieGrafico();
                }).error(function (data) {
                    if (data.message) {
                        showAlert.aviso(data.message, testAviso);
                    } else {
                        showAlert.aviso(data, testAviso);
                    }
                })
        };

        init();
        let testAviso = function () {
            //growl.info('',{title:'Alerta de aviso'});
        };

        let llenadoDeVariables = function () {
            let elemento;
            let item;
            $scope.cantTotalAceptadas = 0;
            $scope.cantTotalRechazadas = 0;
            $scope.cantTotalPospuestas = 0;

            for (let i = 0; i < $scope.listadoEstadisticoVO.length; i++) {
                elemento = $scope.listadoEstadisticoVO[i];
                item =
                    {   usuario: elemento.codigoUsuario,
                        nombres: elemento.nombre + " " + elemento.apellidoP + " " + elemento.apellidoM,
                        email: elemento.email
                    };
                item.aceptadas = 0;
                item.rechazadas = 0;
                item.pospuestas = 0;
                item.total = 0;


                if(elemento.evaluacion === "ACEPTADAS"){
                    item.aceptadas = elemento.cantidad;
                    $scope.cantTotalAceptadas += item.aceptadas;
                } else if(elemento.evaluacion === "POSPUESTAS"){
                    item.pospuestas = elemento.cantidad;
                    $scope.cantTotalPospuestas += item.pospuestas;
                }
                else if (elemento.evaluacion === "RECHAZADAS"){
                    item.rechazadas = elemento.cantidad;
                    $scope.cantTotalRechazadas += item.rechazadas;
                }

                if($scope.listadoEstadisticoVO[i+1]){
                    if($scope.listadoEstadisticoVO[i+1].codigoUsuario === item.usuario){
                        if($scope.listadoEstadisticoVO[i+1].evaluacion === "ACEPTADAS"){
                            item.aceptadas = $scope.listadoEstadisticoVO[i+1].cantidad;
                            $scope.cantTotalAceptadas += item.aceptadas;
                        }else if($scope.listadoEstadisticoVO[i+1].evaluacion === "POSPUESTAS"){
                            item.pospuestas = $scope.listadoEstadisticoVO[i+1].cantidad;
                            $scope.cantTotalPospuestas += item.pospuestas;
                        }
                        else if($scope.listadoEstadisticoVO[i+1].evaluacion === "RECHAZADAS"){
                            item.rechazadas = $scope.listadoEstadisticoVO[i+1].cantidad;
                            $scope.cantTotalRechazadas += item.rechazadas;
                        }
                        i=i+1;
                    }
                }
                if($scope.listadoEstadisticoVO[i+1]){
                    if($scope.listadoEstadisticoVO[i+1].codigoUsuario === item.usuario){
                        if($scope.listadoEstadisticoVO[i+1].evaluacion === "ACEPTADAS"){
                            item.aceptadas = $scope.listadoEstadisticoVO[i+1].cantidad;
                            $scope.cantTotalAceptadas += item.aceptadas;
                        }else if($scope.listadoEstadisticoVO[i+1].evaluacion === "POSPUESTAS"){
                            item.pospuestas = $scope.listadoEstadisticoVO[i+1].cantidad;
                            $scope.cantTotalPospuestas += item.pospuestas;
                        }
                        else if($scope.listadoEstadisticoVO[i+1].evaluacion === "RECHAZADAS"){
                            item.rechazadas = $scope.listadoEstadisticoVO[i+1].cantidad;
                            $scope.cantTotalRechazadas += item.rechazadas;
                        }
                        i=i+1;
                    }
                }

                item.total = item.aceptadas + item.rechazadas + item.pospuestas;
                $scope.listado.push(item);

            }
        };

        function retornaElementosFecha(fechaStr){
            return fechaStr.split("/") ;
        }

        $scope.clickBontonBuscar = function () {
    
            if($scope.form.$invalid || ($scope.fechaInicial !== '' && $scope.fechaFinal === '' ) ||
                ($scope.fechaInicial === '' && $scope.fechaFinal !== '' )) {
                showAlert.requiredFields($scope.form);
                $scope.validacionFechasOk = false;
                growl.error('Formulario incompleto', {
                    ttl: 3000,
                    disableCountDown: true
                });
                return;
            }
            
            if(($scope.fechaInicial !== '' && $scope.fechaFinal === '' ) ||
                ($scope.fechaInicial === '' && $scope.fechaFinal !== '' )){
                showAlert.aviso("Debe seleccionarse un rango de fechas.", testAviso);
                return;
            }

            let fechaFinalFormat = retornaElementosFecha($scope.fechaFinal);
            let fFinal = new Date(fechaFinalFormat[2], fechaFinalFormat[1]-1, fechaFinalFormat[0]);
            let fechaInicialFormat = retornaElementosFecha($scope.fechaInicial);
            let fInicial = new Date(fechaInicialFormat[2], fechaInicialFormat[1]-1, fechaInicialFormat[0]);

            if (fFinal < fInicial){
                showAlert.error("La fecha inicial debe ser anterior a la final");
                return;
            }
    
            $scope.validacionFechasOk = true;
            $scope.listado = [];
            if($scope.fechaInicial !== '' && $scope.fechaFinal !== '' ){
                estadisticasValidadoresService.getListadoEstadisticoParametrizado($scope.fechaInicial, $scope.fechaFinal)
                    .success(function (data) {
                        $scope.listadoEstadisticoVO = data;
                        if(!$scope.listadoEstadisticoVO || $scope.listadoEstadisticoVO.length === 0){
                            $scope.hayEstadisticas = false;
                            showAlert.aviso("No hay estadísticas disponibles en este momento", testAviso);
                            return;
                        }else{
                            $scope.hayEstadisticas = true;
                        }

                        llenadoDeVariables();
                        llenadoVariablesGrafico();
                        llenadoVariablesPieGrafico();
                    }).error(function (data) {
                    if (data.message) {
                        showAlert.aviso(data.message, testAviso);
                    } else {
                        showAlert.aviso(data, testAviso);
                    }
                });
            }else
                init();


        };


        $scope.changeViewTab = function(tab) {
            switch(tab) {
                case 'tabla':
                    $scope.mostrarTabla = true;
                    $scope.mostrarBarras = false;
                    $scope.mostrarPastel = false;
                    break;
                case 'barras':
                    $scope.mostrarTabla = false;
                    $scope.mostrarBarras = true;
                    $scope.mostrarPastel = false;
                    break;
                case 'pastel':
                    $scope.mostrarTabla = false;
                    $scope.mostrarBarras = false;
                    $scope.mostrarPastel = true;
                    break;
            }
        };

        let llenadoVariablesGrafico = function () {
            validadoresLista = [];
            aceptadasLista = [];
            rechazadasLista = [];
            pospuestasLista = [];
            for(let i = 0; i< $scope.listado.length; i++){
                validadoresLista.push($scope.listado[i].usuario);
                aceptadasLista.push($scope.listado[i].aceptadas);
                rechazadasLista.push($scope.listado[i].rechazadas);
                pospuestasLista.push($scope.listado[i].pospuestas);
            }

            /*Inicio. Gráfico de echarts*/
            let posList = [
                'left', 'right', 'top', 'bottom',
                'inside',
                'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
                'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
            ];

            app.configParameters = {
                rotate: {
                    min: -90,
                    max: 90
                },
                align: {
                    options: {
                        left: 'left',
                        center: 'center',
                        right: 'right'
                    }
                },
                verticalAlign: {
                    options: {
                        top: 'top',
                        middle: 'middle',
                        bottom: 'bottom'
                    }
                },
                position: {
                    options: echarts.util.reduce(posList,
                            function (map, pos) {
                                map[pos] = pos;
                                return map;
                            }, {})
                },
                distance: {
                    min: 0,
                    max: 100
                }
            };

            app.config = {
                rotate: 90,
                align: 'left',
                verticalAlign: 'middle',
                position: 'insideBottom',
                distance: 15,
                onChange: function () {
                    let labelOption = {
                        normal: {
                            rotate: app.config.rotate,
                            align: app.config.align,
                            verticalAlign: app.config.verticalAlign,
                            position: app.config.position,
                            distance: app.config.distance
                        }
                    };
                    myChart.setOption({
                        series: [{
                            label: labelOption
                        }, {
                            label: labelOption
                        },{
                            label: labelOption
                        }]
                    });
                }
            };


            let labelOption = {
                normal: {
                    show: true,
                    position: app.config.position,
                    distance: app.config.distance,
                    align: app.config.align,
                    verticalAlign: app.config.verticalAlign,
                    rotate: app.config.rotate,
                    formatter: '{c}  {name|{a}}',
                    fontSize: 10,
                    rich: {
                        name: {
                            textBorderColor: '#fff'
                        }
                    }
                }
            };

            let option = {
                title : {
                    text: 'Datos de las boletas procesadas',
                    subtext: 'Resultados por validador',
                    x:'center'
                },

                color: ['#003366', '#e50216', '#49cbe3'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    orient: 'horizontal',
                    left: 0,
                    top: 0,
                    right : 0,
                    bottom: 100,
                    data: ['Aceptadas', 'Rechazadas', 'Pospuestas']
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: false, readOnly: false, title:'Visor de datos'},
                        magicType: {show: false, type: [ 'bar', 'stack'], title:{bar:"bar",stack:'pila'}},
                        restore: {show: false, title:'Restaurar'},
                        saveAsImage: {show: true, title:'Exportar'}
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {show: false},
                        data: validadoresLista
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: 'Aceptadas',
                        type: 'bar',
                        barGap: 0,
                        label: labelOption,
                        data: aceptadasLista
                    },
                    {
                        name: 'Rechazadas',
                        type: 'bar',
                        label: labelOption,
                        data: rechazadasLista
                    },
                    {
                        name: 'Pospuestas',
                        type: 'bar',
                        label: labelOption,
                        data: pospuestasLista
                    },
                ]
            };


            myChart.setOption(option);
        };

        let llenadoVariablesPieGrafico = function () {
            let option = {
                title : {
                    text: 'Datos de las boletas procesadas',
                    subtext: 'Aceptadas vs Rechazadas vs Pospuestas',
                    x:'center'
                },
                toolbox: {
                    feature: {
                        saveAsImage: {show: true, title:'Exportar'}
                    }
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                color: ['#003366', '#e50216', '#49cbe3'],
                legend: {
                    orient: 'horizontal',
                    left: 'left',
                    data: ['Aceptadas','Rechazadas','Pospuestas']
                },
                series : [
                    {
                        name: 'Boletas',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:$scope.cantTotalAceptadas, name:'Aceptadas'},
                            {value:$scope.cantTotalRechazadas, name:'Rechazadas'},
                            {value:$scope.cantTotalPospuestas, name:'Pospuestas'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            pieChart.setOption(option);
        };
/*Final. Gráfico de echarts*/
        $(window).on('resize', function(){
            pieChart.resize();
            myChart.resize();
        });
    });/*Fin de estadisticasValidadoresController*/

