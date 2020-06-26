package mx.com.teclo.digitalizacion.negocio.utils;

public enum PosicionImagen {
	ANVERSO("A"), REVERSO("R");
	
	private String valor;
	
	PosicionImagen(String valor){
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}
}
