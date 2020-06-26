package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.tipopixcel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TDP020C_TIPOPIXEL")
public class TipoPixelDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7264916862829541240L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long idTipoPixel;

	@Column(name = "VALOR")
	private Integer valor;

	@Column(name = "NOMBRE")
	private String nombre;

	/**
	 * @return the idTipoPixel
	 */
	public Long getIdTipoPixel() {
		return idTipoPixel;
	}

	/**
	 * @param idTipoPixel the idTipoPixel to set
	 */
	public void setIdTipoPixel(Long idTipoPixel) {
		this.idTipoPixel = idTipoPixel;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}