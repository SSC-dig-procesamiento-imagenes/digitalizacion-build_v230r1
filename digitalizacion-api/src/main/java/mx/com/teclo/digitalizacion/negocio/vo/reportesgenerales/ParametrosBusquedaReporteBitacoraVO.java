package mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ParametrosBusquedaReporteBitacoraVO {

	private Long componenteId;
	private List<String> conceptosId;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss",timezone="America/Mexico_City" )
	private Date fechaInicio;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss",timezone="America/Mexico_City" )
	private Date fechaFin;
	
	public ParametrosBusquedaReporteBitacoraVO() {
	}

	public Long getComponenteId() {
		return componenteId;
	}

	public void setComponenteId(Long componenteId) {
		this.componenteId = componenteId;
	}

	public List<String> getConceptosId() {
		return conceptosId;
	}

	public void setConceptosId(List<String> conceptosId) {
		this.conceptosId = conceptosId;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
