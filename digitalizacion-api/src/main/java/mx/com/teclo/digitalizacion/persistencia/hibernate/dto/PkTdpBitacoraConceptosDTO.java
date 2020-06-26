package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PkTdpBitacoraConceptosDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2397379813168912370L;

	@Column(name="CONCEPTO_ID")
	private Long conceptoId;
	
	@Column(name="COMPONENTE_ID")
	private Long componenteId;

	public Long getConceptoId() {
		return conceptoId;
	}

	public void setConceptoId(Long conceptoId) {
		this.conceptoId = conceptoId;
	}

	public Long getComponenteId() {
		return componenteId;
	}

	public void setComponenteId(Long componenteId) {
		this.componenteId = componenteId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
}
