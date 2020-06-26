package mx.com.teclo.digitalizacion.bitacora;

public enum BitacoraConceptosEnum {

	INICIAR_AP_DIGITALIZACION_Y_PROC_DE_IMAGENES((long) 1),
	CAMBIO_DE_ESTATUS_DE_LOTE_APP((long) 2),
	ACTUALIZAR_LOTE((long) 3),
	CAMBIO_DE_ESTATUS_DE_LOTES_CONSULTA_LOTES((long) 4),
	CAMBIO_DE_VALORES_DE_BOLETA((long) 5),
	CAMBIO_MARCA_VEHICULO((long) 6),
	CAMBIO_DE_POSICION_DE_LAS_IMAGENES((long) 7),
	VALIDACION_DE_BOLETA((long) 8),
	CAMBIO_DE_ESTATUS_DE_LOTES_VALIDACION_IMAGENES((long) 9),
	CANCELACION_DE_ASIGNACIONES((long) 10),
	CAMBIO_DE_CONTRASENA((long) 11),
	POSPONER_BOLETA((long) 12),
	CAMBIO_FECHA((long) 13),
	CAMBIO_NUMERO_FOLIO((long) 14),
	CAMBIO_PLACA((long) 15),
	CAMBIO_FRACCION((long) 16),
	CAMBIO_PARRAFO((long) 17),
	CAMBIO_NUMERO_LICENCIA((long) 18),
	CAMBIO_LUGAR_EXP_LICENCIA((long) 19),
	CAMBIO_INCISO((long) 20),
	CAMBIO_UNIDAD_TERRITORIAL((long) 21);

	private Long valor;
	
	private BitacoraConceptosEnum(Long valor) {
		this.setValor(valor);
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
}
