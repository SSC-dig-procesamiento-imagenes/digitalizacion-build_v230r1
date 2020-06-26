package mx.com.teclo.digitalizacion.negocio.utils;

public enum ValoresEstadosLotes {
	
	PROCESADO("PROCESADO"), VALIDANDO_INFORMACION("VALINFORMACION"),
	INFORMACION_VALIDADA("INFOVALIDADA"),FORMADO_PARA_LIBERACION("FORMLIBERACION"),
	EN_LIBERACION("ENLIBERACION"),LIBERADO("LIBERADO"),PROCESAMIENTO("PROCESAMIENTO"),
	PAUSA("PAUSA"),VALIDACION1("VALIDACION1"),VALIDACION2("VALIDACION2"), CANCELADO("CANCELADO");
	
	private String valor;
	
	public String getValor() {
		return valor;
	}

	ValoresEstadosLotes(String valor) {
		this.valor = valor;
	}
}
