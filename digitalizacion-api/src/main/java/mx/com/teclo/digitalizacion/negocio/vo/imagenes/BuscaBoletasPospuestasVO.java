package mx.com.teclo.digitalizacion.negocio.vo.imagenes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BuscaBoletasPospuestasVO {
	
	private Long idValidador;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City" )
	private Date fechaInicial;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City" )
	private Date fechaFinal;
	
	public BuscaBoletasPospuestasVO() {
	}
	
	public BuscaBoletasPospuestasVO(Long idValidador, Date fechaInicial, Date fechaFinal) {
		this.idValidador = idValidador;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}

	public Long getIdValidador() {
		return idValidador;
	}
	public void setIdValidador(Long idValidador) {
		this.idValidador = idValidador;
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
	
	

}
