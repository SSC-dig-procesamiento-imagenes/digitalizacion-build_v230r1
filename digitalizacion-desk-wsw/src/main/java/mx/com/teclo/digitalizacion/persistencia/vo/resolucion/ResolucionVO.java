package mx.com.teclo.digitalizacion.persistencia.vo.resolucion;

public class ResolucionVO {

	private Long idResolucion;
	private Integer valor;
	private String nombre;
	private String ancho;
	private String alto;
	
	/**
	 * @return the idResolucion
	 */
	public Long getIdResolucion() {
		return idResolucion;
	}
	/**
	 * @param idResolucion the idResolucion to set
	 */
	public void setIdResolucion(Long idResolucion) {
		this.idResolucion = idResolucion;
	}
	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the ancho
	 */
	public String getAncho() {
		return ancho;
	}
	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(String ancho) {
		this.ancho = ancho;
	}
	/**
	 * @return the alto
	 */
	public String getAlto() {
		return alto;
	}
	/**
	 * @param alto the alto to set
	 */
	public void setAlto(String alto) {
		this.alto = alto;
	}
}