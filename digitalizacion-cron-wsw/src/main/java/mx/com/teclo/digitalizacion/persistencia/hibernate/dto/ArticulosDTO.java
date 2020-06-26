package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ARTICULOS")
public class ArticulosDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ART_ID")
	private Long artId;
	@Column(name = "ART_MOTIVACION")
	private String artMotivacion;
	
	public ArticulosDTO() {
	}

	public Long getArtId() {
		return artId;
	}

	public void setArtId(Long artId) {
		this.artId = artId;
	}

	public String getArtMotivacion() {
		return artMotivacion;
	}

	public void setArtMotivacion(String artMotivacion) {
		this.artMotivacion = artMotivacion;
	}
	
	

}
