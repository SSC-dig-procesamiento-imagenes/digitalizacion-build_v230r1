package mx.com.teclo.digitalizacion.negocio.utils;

public enum ValoresImagenesLiberadas {

	LIBERADA(1), NO_LIBERADA(0);
	private int valor;
	
	public int getValor() {
		return valor;
	}

	ValoresImagenesLiberadas(int valor) {
		this.valor = valor;
	}
}
