angular.module(appTeclo)
.service('plantillasService',
function($rootScope,$http,$localStorage,$window,$q,$route,config) {

    let myPath = "/plantillas";

    this.obtenerAreaTrabajo = function (objectParam) {
		return $http.post(config.baseUrl + myPath + "/obtenerAreaTrabajo",objectParam);
    };

 });