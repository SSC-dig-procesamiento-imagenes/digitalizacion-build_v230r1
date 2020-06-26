angular.module(appTeclo)
.service('caracteresService',
function($rootScope,$http,$localStorage,$window,$q,$route,config) {

    let myPath = "/caracteres";
	this.getCaracteres = function (tipoBusqueda,valor) {
    return $http.get(config.baseUrl + myPath + "/getCaracteres",
    {params:{"tipoBusqueda": tipoBusqueda,"valor": valor}});
    };

    this.getCaracteresByRespuesta = function (idRespuesta) {
      return $http.get(config.baseUrl + myPath + "/getCaracteresByRespuesta",
      {params:{"idRespuesta": idRespuesta}});
      };

    this.saveOrUpdate = function (caracterVO) {
		return $http.post(config.baseUrl + myPath + "/saveOrUpdate",caracterVO);
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