package mx.com.teclo.digitalizacion.negocio.utils;

public enum UsuarioLiberador {

	LIBERADOR(9999L);
	
	private Long valor;
	
	public Long getValor() {
		return valor;
	}

	UsuarioLiberador(Long valor) {
		this.valor = valor;
	}
}
