angular.module(appTeclo).controller("cambioContrasenaController", function($scope,config, cambioContrasenaService,
																		   growl, showAlert,$document ) {

	$scope.objeto = {
			contra: "",
			nuevo : "",
			repetir : ""	
	};
	
	$scope.tituloSug="<b>Sugerencia de Contraseña</b></br>";
	$scope.mayusculas="<div style='width: 70%; min-width:310px; text-align: justify;'>- Al menos una letra mayúscula </br>";
	$scope.minuscula="- Al menos una letra minúscula </br>";
	$scope.digitos="- Al menos un dígito </br>";
	$scope.caracteresMinimos="- Mínimo una longitud de 8 caracteres </br>";
	$scope.caracEpecials="- Al menos un carácter del conjunto:<br/>" +
	"<center>!&nbsp#&nbsp$&nbsp%&nbsp&&nbsp(&nbsp)&nbsp*&nbsp+&nbsp,&nbsp-&nbsp.&nbsp/&nbsp:&nbsp;&nbsp" +
	"<&nbsp=&nbsp>&nbsp?&nbsp@&nbsp[&nbsp\\&nbsp]&nbsp_&nbsp|</center>";
	$scope.tituloEjemplo="<br/><center><b>Ejemplo de Contraseñas:</b></center>";
	$scope.pasInvalid="Contraseña no válida: </br><center> teclo1+ </center>";
	$scope.passValid="Contraseña válida: </br> <center>Teclo3Logistica@</center> </div>";
	
	$scope.guardar = function()	{
		if ($scope.formChgPass.$invalid) {
            angular.forEach($scope.formChgPass.$error, function (field) {
              angular.forEach(field, function(errorField){
            	  errorField.$setDirty();
            	  
              })
            });

            showAlert.aviso('Formulario incompleto', testAviso);
            
		}else{
			if($scope.objeto.nuevo !== $scope.objeto.repetir)
			{
                showAlert.aviso("Las contraseña nueva no coincide en ambos campos llenados", testAviso);
				$scope.objeto.contra = "";  $scope.objeto.nuevo = "";  $scope.objeto.repetir = "";
				$scope.formChgPass.$setPristine();
			}
			else{
				if($scope.objeto.contra === $scope.objeto.nuevo){
                    showAlert.aviso("La contraseña nueva no puede ser igual a la actual", testAviso);
					$scope.objeto.contra = "";  $scope.objeto.nuevo = "";  $scope.objeto.repetir = "";
					$scope.formChgPass.$setPristine();
				}
				else{
					$scope.cambiarClave();
				}
			}
		}
	};
    let testAviso = function(){

	};
	$scope.cambiarClave = function(){
		cambioContrasenaService.cambioClave($scope.objeto.nuevo, $scope.objeto.contra)
		.success(function(data) {
            showAlert.aviso(data.mensaje, testAviso);

			$scope.objeto.contra = "";  $scope.objeto.nuevo = "";  $scope.objeto.repetir = "";
			$scope.formChgPass.$setPristine();
		}).error(function(data) {
			if(data.message){
                showAlert.aviso(data.message, testAviso);
			}else{
                showAlert.aviso(data, testAviso);
			}
			$scope.objeto.contra = "";  $scope.objeto.nuevo = "";  $scope.objeto.repetir = "";
			$scope.formChgPass.$setPristine();
		});	
	};
	

});