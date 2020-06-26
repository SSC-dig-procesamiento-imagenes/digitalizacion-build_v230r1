package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.escaner;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TDP023C_SCANNERS")
public class EscanerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9069734612110393669L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long idEscaner;

	@Column(name = "MARCA")
	private String marca;

	@Column(name = "MODELO")
	private String modelo;

	/**
	 * @return the idEscaner
	 */
	public Long getIdEscaner() {
		return idEscaner;
	}

	/**
	 * @param idEscaner the idEscaner to set
	 */
	public void setIdEscaner(Long idEscaner) {
		this.idEscaner = idEscaner;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}