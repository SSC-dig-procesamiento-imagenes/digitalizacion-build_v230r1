angular.module(appTeclo)
.service('gestionLotesService',
function($rootScope,$http,$localStorage,$window,$q,$route,config) {
    "use strict";
    const myPath = "/gestionLotes";

    /*Devuelve las imágenes del idLoteSeleccionado*/
    this.imagenesPorLote = function (idLoteSeleccionado) {
        return $http.get(config.baseUrl + myPath + "/imagenesPorLote",
                    {
                        params: {  "idLoteSeleccionado": idLoteSeleccionado}
                    })
    };

    this.blobsPorImagen = function(idImagenSeleccionada) {
        return $http.get(config.baseUrl + myPath + "/imagenesBlob",
            {
                params: {  "idImagenSeleccionada": idImagenSeleccionada}
            })
    };


});