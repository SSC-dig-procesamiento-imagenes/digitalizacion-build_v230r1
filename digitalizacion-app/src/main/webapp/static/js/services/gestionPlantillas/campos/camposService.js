angular.module(appTeclo)
.service('camposService',
function($rootScope,$http,$localStorage,$window,$q,$route,config) {

    let myPath = "/campos";
	this.getCampos = function (tipoBusqueda,valor) {
    return $http.get(config.baseUrl + myPath + "/getCampos",
    {params:{"tipoBusqueda": tipoBusqueda,"valor": valor}});
    };

    this.saveOrUpdate = function (camposVO) {
		return $http.post(config.baseUrl + myPath + "/saveOrUpdate",camposVO);
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