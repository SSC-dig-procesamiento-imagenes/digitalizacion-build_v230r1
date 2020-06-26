package mx.com.teclo.digitalizacion.negocio.vo.lotes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LotesResultVO {

	private Long numeroLote;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date fechaLote;
	private Long totalImagenes;
	private Long cantImagenesAceptadas;
	private Long cantImagenesRechazadas;
	private EstatusProcesoVO estatusProceso;
	
	public LotesResultVO() {
		
	}
	
	public LotesResultVO(Long numeroLote, Date fechaLote, Long totalImagenes, Long cantImagenesAceptadas,
			Long cantImagenesRechazadas, EstatusProcesoVO estatusProceso) {
		this.numeroLote = numeroLote;
		this.fechaLote = fechaLote;
		this.totalImagenes = totalImagenes;
		this.cantImagenesAceptadas = cantImagenesAceptadas;
		this.cantImagenesRechazadas = cantImagenesRechazadas;
		this.estatusProceso = estatusProceso;
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
	public EstatusProcesoVO getEstatusProceso() {
		return estatusProceso;
	}
	public void setEstatusProceso(EstatusProcesoVO estatusProceso) {
		this.estatusProceso = estatusProceso;
	}
	
	
}
