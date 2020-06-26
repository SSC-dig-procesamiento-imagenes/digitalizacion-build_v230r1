package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.suministropapel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TDP022C_SUMINISTROPAPEL")
public class SuministroPapelDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1002566278375333635L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long idSuministroPapel;

	@Column(name = "VALOR")
	private Integer valor;

	@Column(name = "NOMBRE")
	private String nombre;

	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
	}

	/**
	 * @return the idSuministroPapel
	 */
	public Long getIdSuministroPapel() {
		return idSuministroPapel;
	}

	/**
	 * @param idSuministroPapel the idSuministroPapel to set
	 */
	public void setIdSuministroPapel(Long idSuministroPapel) {
		this.idSuministroPapel = idSuministroPapel;
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