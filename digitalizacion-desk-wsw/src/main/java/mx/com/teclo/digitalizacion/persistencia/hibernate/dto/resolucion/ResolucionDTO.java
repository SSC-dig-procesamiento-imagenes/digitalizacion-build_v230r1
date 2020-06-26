package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.resolucion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TDP019C_RESOLUCION")
public class ResolucionDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7366670650630950050L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long idResolucion;

	@Column(name = "VALOR")
	private Integer valor;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "WIDTH")
	private String ancho;
	
	@Column(name = "HEIGHT")
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}