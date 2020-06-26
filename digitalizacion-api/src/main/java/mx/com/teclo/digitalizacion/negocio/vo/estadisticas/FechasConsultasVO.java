package mx.com.teclo.digitalizacion.negocio.vo.estadisticas;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FechasConsultasVO {
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd",timezone="America/Mexico_City")
	private Date fechaInicial;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd",timezone="America/Mexico_City")
	private Date fechaFinal;
	
	public FechasConsultasVO() {
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
