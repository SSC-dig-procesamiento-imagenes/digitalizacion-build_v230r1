package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TDP_BITACORA_COMPONENTES")
public class TdpBitacoraComponentesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2937545984918642447L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "COMPONENTE_ID", unique = true, nullable = false)
	private Long componenteId;
	
	@Column(name = "COMPONENTE_NOMBRE")
	private String componenteNombre;
	
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
	
	public Long getComponenteId() {
		return componenteId;
	}
	public void setComponenteId(Long componenteId) {
		this.componenteId = componenteId;
	}
	public String getComponenteNombre() {
		return componenteNombre;
	}
	public void setComponenteNombre(String componenteNombre) {
		this.componenteNombre = componenteNombre;
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
}
