angular.module(appTeclo).constant("constante", {
	urlWs: "/sscdprocesamiento_v220r1prod_api"
});
angular.module(appTeclo).factory('config',['$http','$location','constante','$rootScope', function ($http,$location,constante,$rootScope) {

	
	var protocol = $location.protocol()+ "://";
	var host = location.host;
	var url = protocol + host + constante.urlWs;
	var absUrl = $location.absUrl();
	$rootScope.applet_route =url;
	$rootScope.codebase=constante.appletClass;

	let contextApp = absUrl.split("/")[3];
	let sourceJnlp=protocol + host +'/'+contextApp+'/jnlp/aplicacion.jnlp';

	return {
 		sourceJnlp:sourceJnlp,
		baseUrl:url,
		absUrl:absUrl,
		contextApp:contextApp
	}
}]);