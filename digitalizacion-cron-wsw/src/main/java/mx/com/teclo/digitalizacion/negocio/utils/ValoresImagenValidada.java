package mx.com.teclo.digitalizacion.negocio.utils;

public enum ValoresImagenValidada {

	ACEPTADA(1), RECHAZADA(0), INICIALIZADA(null);
	private Integer valor;
	
	public Integer getValor() {
		return valor;
	}

	ValoresImagenValidada(Integer valor) {
		this.valor = valor;
	}
}
