angular.module(appTeclo)
.service('respuestasService',
function($rootScope,$http,$localStorage,$window,$q,$route,config) {

    let myPath = "/respuestas";
	this.getRespuestas = function (tipoBusqueda,valor) {
    return $http.get(config.baseUrl + myPath + "/getRespuestas", 
    {params:{"tipoBusqueda": tipoBusqueda,"valor": valor}});
    };

    this.saveOrUpdate = function (respuestaCaracterVO) {
		return $http.post(config.baseUrl + myPath + "/saveRespuesta",respuestaCaracterVO);
    };

    
    this.descargaExcel = function(tipoBusqueda,valor) {
      return $http({
        method : 'GET',
        url : config.baseUrl +myPath+"/descargaExcel",
        
      params:{"tipoBusqueda": tipoBusqueda,"valor":valor},

        dataType : "json",
        header : {
          "Content-type" : "application/json",
          "Accept" : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        },
        responseType : 'arraybuffer'
      });
    };

});