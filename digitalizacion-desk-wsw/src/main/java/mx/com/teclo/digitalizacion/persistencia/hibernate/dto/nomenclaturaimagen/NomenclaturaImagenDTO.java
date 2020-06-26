package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.nomenclaturaimagen;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TDP035C_NOMENCLATURA_IMG")
public class NomenclaturaImagenDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1023678406321137643L;

	@Id
	@Column(name = "ID_NOMENCLATURA_IMG", unique = true, nullable = false)
	private Long idNomenclaturaImagen;

	@Column(name = "CD_NOMENCLATURA_IMG")
	private String codigoNomenclaturaImagen;

	@Column(name = "TX_NUMENLCATURA_IMG")
	private String descripcionNomenclaturaImegen;

	/**
	 * @return the idNomenclaturaImagen
	 */
	public Long getIdNomenclaturaImagen() {
		return idNomenclaturaImagen;
	}

	/**
	 * @param idNomenclaturaImagen the idNomenclaturaImagen to set
	 */
	public void setIdNomenclaturaImagen(Long idNomenclaturaImagen) {
		this.idNomenclaturaImagen = idNomenclaturaImagen;
	}

	/**
	 * @return the codigoNomenclaturaImagen
	 */
	public String getCodigoNomenclaturaImagen() {
		return codigoNomenclaturaImagen;
	}

	/**
	 * @param codigoNomenclaturaImagen the codigoNomenclaturaImagen to set
	 */
	public void setCodigoNomenclaturaImagen(String codigoNomenclaturaImagen) {
		this.codigoNomenclaturaImagen = codigoNomenclaturaImagen;
	}

	/**
	 * @return the descripcionNomenclaturaImegen
	 */
	public String getDescripcionNomenclaturaImegen() {
		return descripcionNomenclaturaImegen;
	}

	/**
	 * @param descripcionNomenclaturaImegen the descripcionNomenclaturaImegen to set
	 */
	public void setDescripcionNomenclaturaImegen(String descripcionNomenclaturaImegen) {
		this.descripcionNomenclaturaImegen = descripcionNomenclaturaImegen;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}