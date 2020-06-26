package mx.com.teclo.digitalizacion.bitacora;

public enum BitacoraComponentesEnum {

	DIGITALIZACION_DIGITALIZACION_Y_PROCESAMIENTO((long) 1),
	APP_DE_DIGITALIZACION_Y_PROCESAMIENTO_DE_IMAGENES((long) 2),
	IMAGENES_CONSULTA_DE_LOTES((long) 3),
	IMAGENES_VALIDACION_IMAGENES((long) 4),
	IMAGENES_GESTION_DE_ASIGNACIONES((long) 5),
	ADMINISTRACION_CAMBIO_DE_CONTRASENA((long) 6);

	private Long valor;
	
	private BitacoraComponentesEnum(Long valor) {
		this.setValor(valor);
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
}
