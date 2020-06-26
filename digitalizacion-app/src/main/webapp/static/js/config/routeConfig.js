angular.module(appTeclo).config(function($routeProvider, $locationProvider) {
	
	$routeProvider.when("/", {
		templateUrl : "login.html",
		controller: "loginController"
	});
	
	$routeProvider.when("/login", {
		templateUrl : "login.html",
		controller: "loginController"
	});
	
	$routeProvider.when("/error", {
		templateUrl : "views/error.html",
	});
	
	$routeProvider.when("/index",{
		templateUrl : "views/index.html",
	});
	
	$routeProvider.when("/accesoNegado", {
		templateUrl : "views/accesoNegado.html",
	});
	
	$routeProvider.otherwise({redirectTo: "/index"});
	
	
	/*___________________________________________________________
	________** INICIO -> ADMINISTRACIÓN CONTROLLERS ** ________*/
	$routeProvider.when("/administracionModificaClave",{
		templateUrl : "views/administracion/administracionModificaClave.html",
		controller : "administracionModificaClaveController"
	});
	
//	Configurar Aplicación
	$routeProvider.when("/configuracion", {
		templateUrl : "views/administracion/configuracionApp.html",
		controller: "configuracionAppController"
    });
	
//	Componentes Web
	$routeProvider.when("/componentesWeb",{
		templateUrl : "views/administracion/resources/pluginsWeb.html",
		controller : "pluginsWebController"
	});
	
//	Validación de imágenes
	$routeProvider.when("/validacionImagen",{
		templateUrl : "views/validacionImagenes/validacionImagenes.html",
		controller : "validacionImagenesController"
	});

	//	Aplicación de procesamiento (Gibrán)
	$routeProvider.when("/aplicacionProcesamiento", {
		templateUrl : "views/aplicacionProcesamientoImagenes/appProcesamientoImagenes.html",
		controller : "procesamientoImagenesController"
	});
	//Liberación de imágenes
	$routeProvider.when("/consultaLotes", {
		templateUrl : "views/liberacionImagenes/consultaLotes.html",
		controller : "liberacionImagenesController"
	});
    //Gestión de asignaciones
    $routeProvider.when("/gestionAsignaciones", {
        templateUrl : "views/gestionAsignaciones/gestionAsignaciones.html",
        controller : "gestionAsignacionesController"
    });
    //Estadísticas de los validadores
    $routeProvider.when("/estadisticasValidadores", {
        templateUrl : "views/estadisticasValidadores/estadisticasValidadores.html",
        controller : "estadisticasValidadoresController"
    });

    //Cambio de contraseña
    $routeProvider.when("/cambioContrasena", {
        templateUrl : "views/administracion/cambiocontrasena/cambioContrasena.html",
        controller : "cambioContrasenaController"
    });

    //Reportes de lotes
    $routeProvider.when("/reportesLotes", {
        templateUrl : "views/estadisticasValidadores/reportesLotes.html",
        controller : "reportesLotesController"
    });
	
	//Reportes generales
	$routeProvider.when("/reportesGenerales", {
		templateUrl : "views/reportes/reportesGenerales.html",
		controller : "reportesGeneralesController"
	});

	//	Validación de boletas pospuestas
	$routeProvider.when("/validacionPospuestas/:idImagenPospuestaSeleccionada?",{
		templateUrl : "views/validacionImagenes/validacionPospuestas.html",
		controller : "validacionPospuestasController"
	});

	//	Validación de todas las boletas pospuestas
	$routeProvider.when("/validacionTodasPospuestas",{
		templateUrl : "views/reportes/pospuestas/validacionTodasPospuestas.html",
		controller : "validacionTodasPospuestasController"
	});

	
//	Caracteres 
	$routeProvider.when("/caracteres",{
		templateUrl : "views/caracteres/caracteres.html",
		controller : "caracteresController"
	});
	
//	Respuestas 
	$routeProvider.when("/respuestas",{
		templateUrl : "views/respuestas/respuestas.html",
		controller : "respuestasController"
	});

	
//	Plantillas 
	$routeProvider.when("/consultaPlantillas",{
		templateUrl : "views/plantillas/consultaPlantillas.html",
		controller : "consultaPlantillasController"
	});

	
	$routeProvider.when("/plantillas", {
		templateUrl : "views/plantillas/plantillas.html",
		controller: "plantillasController"
			/*,
		resolve: {
			detalleEntrada: function (consultaEntradaService, $route) {
				return consultaEntradaService.buscarDetalleEntrada($route.current.params.id);
			}
        }*/
			
    });
	
	//Campos Para Plantilla
	$routeProvider.when("/campos", {
		templateUrl : "views/gestionPlantillas/campos/campos.html",
		controller: "camposController"
			/*,
		resolve: {
			detalleEntrada: function (consultaEntradaService, $route) {
				return consultaEntradaService.buscarDetalleEntrada($route.current.params.id);
			}
        }*/
			
    });
	
		//Marcas Para Plantilla
	$routeProvider.when("/marcas", {
		templateUrl : "views/gestionPlantillas/marcas/marcas.html",
		controller: "marcasController"
			/*,
		resolve: {
			detalleEntrada: function (consultaEntradaService, $route) {
				return consultaEntradaService.buscarDetalleEntrada($route.current.params.id);
			}
        }*/
			
    });
	
	//Crear Marcas Para Plantilla
	$routeProvider.when("/marcasPlantilla", {
		templateUrl : "views/gestionPlantillas/marcas/crearMarcas.html",
		controller: "marcasController"
			/*,
		resolve: {
			detalleEntrada: function (consultaEntradaService, $route) {
				return consultaEntradaService.buscarDetalleEntrada($route.current.params.id);
			}
        }*/
			
    });
	

	
	/*___________________________________________________________
    ________** FIN -> ADMINISTRACIÓN CONTROLLERS ** ___________*/
	
});