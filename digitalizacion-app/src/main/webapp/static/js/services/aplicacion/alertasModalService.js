angular.module(appTeclo).service("showAlert", function(ModalService) {
	
	var cancelDefault = function() {
		return;
	}
	
	this.aviso = function(messageTo,success,paramSuccess) {
		
		var properties = setProperties(messageTo,success,paramSuccess,null,null,"aviso");
		
		ModalService.showModal({
			templateUrl : 'views/templatemodal/templateShowAlertas.html',
			controller : 'modalMensajeController',
			inputs : {
				pa: properties
			}
		}).then(function(modal) {
			modal.element.modal();
		});

		return ModalService.showModal;
	}

	/* Modal error */
	this.error = function(messageTo,success,paramSuccess) {
		
		var properties = setProperties(messageTo,success,paramSuccess,null,null,"error");
		
		ModalService.showModal({
			templateUrl : 'views/templatemodal/templateShowAlertas.html',
			controller : 'modalMensajeController',
			inputs : {
				pa: properties
			}
		}).then(function(modal) {
			modal.element.modal();
		});
		
		return ModalService.showModal;
	}

	/* Modal confirmacion */
//	this.confirmacion = function(messageTo, action, param, cancel) {
//		
//		if(messageTo === undefined) {
//			return;
//		}else if(typeof param === 'function') {
//			cancel = param;
//		}else if(cancel === undefined) {
//			cancelDefault = function() {
//				return;
//			}
//		}
//		
//		ModalService.showModal({
//			templateUrl : 'views/templatemodal/templateShowAlertas.html',
//			controller : 'modalMensajeController',
//			inputs : {
//				message : messageTo,
//				typeModal : 'confirmacion',
//				actionCancel: cancel !== undefined ? cancel : cancelDefault
//			}
//		}).then(function(modal) {
//			validateParams(modal, messageTo, action, param);
//		});
//		
//		return ModalService.showModal;
//	}
	
this.confirmacion = function(messageTo,success,paramSuccess,cancel,paramCancel) {
		
		var properties = setProperties(messageTo,success,paramSuccess,cancel,paramCancel,"confirmacion");
		var arg = arguments.length;
		
		if(properties.message !== undefined) {
			
			if(arg === 3) {
				if(typeof properties.paramSuccess === 'function') {
					properties.cancel = properties.paramSuccess;
				}
			}else if(arg === 4) {
				if(typeof properties.cancel !== 'function') {
					properties.paramCancel = angular.copy(properties.cancel);
					properties.cancel = properties.paramSuccess;
				}
			}else{
				if(arg > 5) {
					return;
				}
			}
			
		} else {
			return;
		}
		
		ModalService.showModal({
			templateUrl : 'views/templatemodal/templateShowAlertas.html',
			controller : 'modalMensajeController',
			inputs : {
				pa: properties
			}
		}).then(function(modal) {
			modal.element.modal();
		});
		
		return ModalService.showModal;
	}
	
	this.requiredFields = function(formName) {
		angular.forEach(formName.$error, function (field) {
			angular.forEach(field, function(errorField){
				errorField.$setDirty();
			})
		});
	}
	
	validateParams = function(modal, m, a, p) {
		modal.element.modal();
		if (a) {
			modal.close.then(function() {
				if(p != undefined) {
					a(p);
				}else{
					a();
				}
			});
		}
	}
	
	setProperties = function(m,a,pa,c,ca,tm) {
		return properties = {
			message:m,
			success:a,
			paramSuccess:pa,
			cancel:c,
			paramCancel:ca,
			typeModal:tm
		};
	}

});