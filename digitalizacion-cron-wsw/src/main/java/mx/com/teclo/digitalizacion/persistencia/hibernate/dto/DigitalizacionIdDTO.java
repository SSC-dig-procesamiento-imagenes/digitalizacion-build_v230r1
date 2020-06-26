package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
public class DigitalizacionIdDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3648622328325882301L;
	@Basic(optional = false)
	@Column(name = "FOLIO")
    private String folio;
	@Basic(optional = false)
    @Column(name = "ANV_REV")
	private String anvrev;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFolio() {
		return folio;
	}
	public String getAnvrev() {
		return anvrev;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public void setAnvrev(String anvrev) {
		this.anvrev = anvrev;
	}
	
	




}
