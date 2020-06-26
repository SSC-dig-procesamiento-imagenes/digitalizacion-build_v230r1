package mx.com.teclo.digitalizacion.negocio.utils;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DatosLotesFromUI {
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss")
	private Date fechaInicial;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss")
	private Date fechaFinal;
	private Long idEstatus;
	
	public DatosLotesFromUI() {
		
	}
	
	
	public DatosLotesFromUI(Date fechaInicial, Date fechaFinal, Long idEstatus) {
		super();
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.idEstatus = idEstatus;
	}


	public Date getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public Long getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(Long idEstatus) {
		this.idEstatus = idEstatus;
	}
	
	
}
