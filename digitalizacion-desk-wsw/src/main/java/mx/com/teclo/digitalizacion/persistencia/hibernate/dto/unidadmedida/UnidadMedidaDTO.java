package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.unidadmedida;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TDP017C_UNITSIZE")
public class UnidadMedidaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6606561875717661581L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long idUnidadMedida;

	@Column(name = "VALOR")
	private Integer valor;

	@Column(name = "NOMBRE")
	private String nombre;

	/**
	 * @return the idUnidadMedida
	 */
	public Long getIdUnidadMedida() {
		return idUnidadMedida;
	}

	/**
	 * @param idUnidadMedida the idUnidadMedida to set
	 */
	public void setIdUnidadMedida(Long idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
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