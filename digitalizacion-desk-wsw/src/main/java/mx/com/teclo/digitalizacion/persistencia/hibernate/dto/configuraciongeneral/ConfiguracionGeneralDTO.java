package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.configuraciongeneral;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.nomenclaturaimagen.NomenclaturaImagenDTO;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP034C_CONFIG_GENERAL")
public class ConfiguracionGeneralDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONFIG_GENERAL")
    private Long idConfigGeneral;
    
    @Column(name = "TX_NOMBRE_RUTA")
    private String txNombreRuta;
    
    @JoinColumn(name = "ID_NOMENCLATURA_IMG", referencedColumnName = "ID_NOMENCLATURA_IMG")
    @ManyToOne(optional = false)
    private NomenclaturaImagenDTO idNomenclaturaImg;

	/**
	 * @return the idConfigGeneral
	 */
	public Long getIdConfigGeneral() {
		return idConfigGeneral;
	}

	/**
	 * @param idConfigGeneral the idConfigGeneral to set
	 */
	public void setIdConfigGeneral(Long idConfigGeneral) {
		this.idConfigGeneral = idConfigGeneral;
	}

	/**
	 * @return the txNombreRuta
	 */
	public String getTxNombreRuta() {
		return txNombreRuta;
	}

	/**
	 * @param txNombreRuta the txNombreRuta to set
	 */
	public void setTxNombreRuta(String txNombreRuta) {
		this.txNombreRuta = txNombreRuta;
	}

	/**
	 * @return the idNomenclaturaImg
	 */
	public NomenclaturaImagenDTO getIdNomenclaturaImg() {
		return idNomenclaturaImg;
	}

	/**
	 * @param idNomenclaturaImg the idNomenclaturaImg to set
	 */
	public void setIdNomenclaturaImg(NomenclaturaImagenDTO idNomenclaturaImg) {
		this.idNomenclaturaImg = idNomenclaturaImg;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
