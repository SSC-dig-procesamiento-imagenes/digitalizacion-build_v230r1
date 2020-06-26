package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TDP_BITACORA_CONCEPTOS")
public class TdpBitacoraConceptosDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6906740824122126389L;



	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "conceptoId", column = @Column(name = "CONCEPTO_ID")),
		@AttributeOverride(name = "componenteId", column = @Column(name = "COMPONENTE_ID"))})
	private PkTdpBitacoraConceptosDTO pkTcaBitacoraConceptosDTO;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPONENTE_ID", insertable = false, updatable = false)
//    @JoinColumn(name = "ID_LOTE", referencedColumnName = "ID_LOTE")
//    @ManyToOne(optional = false)
	private TdpBitacoraComponentesDTO componenteID;
	
	
	@Column(name = "CONCEPTO_ID", insertable = false, updatable = false)
	private Long conceptoId;
	
	@Column(name = "CONCEPTO_NOMBRE")
	private String conceptoNombre;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "VISIBLE_REPORTE")
	private Boolean visibleReporte;

	public PkTdpBitacoraConceptosDTO getPkTcaBitacoraConceptosDTO() {
		return pkTcaBitacoraConceptosDTO;
	}

	public void setPkTcaBitacoraConceptosDTO(PkTdpBitacoraConceptosDTO pkTcaBitacoraConceptosDTO) {
		this.pkTcaBitacoraConceptosDTO = pkTcaBitacoraConceptosDTO;
	}

	public String getConceptoNombre() {
		return conceptoNombre;
	}

	public void setConceptoNombre(String conceptoNombre) {
		this.conceptoNombre = conceptoNombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Boolean getVisibleReporte() {
		return visibleReporte;
	}

	public void setVisibleReporte(Boolean visibleReporte) {
		this.visibleReporte = visibleReporte;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TdpBitacoraComponentesDTO getBitacoraConponentesDTO() {
		return componenteID;
	}

	public void setBitacoraConponentesDTO(TdpBitacoraComponentesDTO bitacoraConponentesDTO) {
		this.componenteID = bitacoraConponentesDTO;
	}

}
