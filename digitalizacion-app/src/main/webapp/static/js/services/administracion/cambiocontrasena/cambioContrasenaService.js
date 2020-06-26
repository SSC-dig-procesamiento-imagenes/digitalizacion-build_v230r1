angular.module(appTeclo).service('cambioContrasenaService', function($http, config) {

	var myPath = "/gestionUsuarios";
	/**
	 * Rest - ???
	 */
	this.cambiarClave = function (clave, nueva, repetir){
		return $http.get(config.baseUrl + "/cambiarClave",
				{params:{"clave": clave, "nueva": nueva, "repetir": repetir}});
	};
	
	/**
	 * Rest - ???
	 */
	this.cambiarClave = function (clave, nueva, repetir){
		return $http.get(config.baseUrl + "/cambioContraseñaController/cambiarClave",
				{params:{"clave": clave, "nueva": nueva, "repetir": repetir}});
	};
	
	this.cambioClave = function (nuevaContrasenia, antiguaContrasenia) {
        var cambioContraseniaVO = {"nuevaContrasenia": nuevaContrasenia, "antiguaContrasenia": antiguaContrasenia};
		return $http.post(config.baseUrl + myPath + "/cambiarContrasenia",cambioContraseniaVO);
    }
	
});