package mx.com.teclo.digitalizacion.negocio.utils;

public enum ValoresFicheroExcel {

	LOTES(1), LOTESB(0);
	private Integer valor;
	
	public Integer getValor() {
		return valor;
	}

	ValoresFicheroExcel(Integer valor) {
		this.valor = valor;
	}
	
}
