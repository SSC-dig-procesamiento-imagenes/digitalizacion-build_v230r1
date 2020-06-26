package mx.com.teclo.digitalizacion.negocio.vo.lotes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LotesQuery {

	private Long numeroLote;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private java.util.Date fechaLote;
	private Long totalImagenes;
	private Long cantImagenesAceptadas;
	private Long cantImagenesRechazadas;
	private Long idEstatus;
	private String codigoEstatus;
	private String nombreEstatus;
	private String descripcionStatus;
	
	public LotesQuery() {
		
	}
	
	public LotesQuery(Long numeroLote, java.util.Date fechaLote, Long totalImagenes, Long cantImagenesAceptadas,
			Long cantImagenesRechazadas, Long idEstatus, String codigoEstatus, String nombreEstatus, String descripcionStatus) {
		this.numeroLote = numeroLote;
		this.fechaLote = fechaLote;
		this.totalImagenes = totalImagenes;
		this.cantImagenesAceptadas = cantImagenesAceptadas;
		this.cantImagenesRechazadas = cantImagenesRechazadas;
		this.idEstatus = idEstatus;
		this.codigoEstatus = codigoEstatus;
		this.nombreEstatus = nombreEstatus;
		this.descripcionStatus = descripcionStatus;
	}



	public Long getNumeroLote() {
		return numeroLote;
	}
	public void setNumeroLote(Long numeroLote) {
		this.numeroLote = numeroLote;
	}
	public Date getFechaLote() {
		return fechaLote;
	}
	public void setFechaLote(Date fechaLote) {
		this.fechaLote = fechaLote;
	}
	public Long getTotalImagenes() {
		return totalImagenes;
	}
	public void setTotalImagenes(Long totalImagenes) {
		this.totalImagenes = totalImagenes;
	}
	public Long getCantImagenesAceptadas() {
		return cantImagenesAceptadas;
	}
	public void setCantImagenesAceptadas(Long cantImagenesAceptadas) {
		this.cantImagenesAceptadas = cantImagenesAceptadas;
	}
	public Long getCantImagenesRechazadas() {
		return cantImagenesRechazadas;
	}
	public void setCantImagenesRechazadas(Long cantImagenesRechazadas) {
		this.cantImagenesRechazadas = cantImagenesRechazadas;
	}
	public Long getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(Long idEstatus) {
		this.idEstatus = idEstatus;
	}
	public String getCodigoEstatus() {
		return codigoEstatus;
	}
	public void setCodigoEstatus(String codigoEstatus) {
		this.codigoEstatus = codigoEstatus;
	}

	public String getNombreEstatus() {
		return nombreEstatus;
	}

	public void setNombreEstatus(String nombreEstatus) {
		this.nombreEstatus = nombreEstatus;
	}

	public String getDescripcionStatus() {
		return descripcionStatus;
	}

	public void setDescripcionStatus(String descripcionStatus) {
		this.descripcionStatus = descripcionStatus;
	}
	
	
	
}
