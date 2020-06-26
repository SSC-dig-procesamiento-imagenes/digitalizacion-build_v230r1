package mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BitacoraCambiosVO {

	private Long idCambio;
	private String nombreComponente;
	private String nombreConcepto;
	private String valorOriginal;
	private String valorFinal;
	private String registroAlterado;
	private String idRegistro;
	private String modificadoPor;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy  HH:mm:ss",timezone="America/Mexico_City")
	private Date fechaModificacion;
	

	public BitacoraCambiosVO(Long idCambio, String nombreComponente, String nombreConcepto, String valorOriginal,
			String valorFinal, String registroAlterado, String idRegistro, String modificadoPor, String apellidosP,
			Date fechaModificacion) {
		super();
		this.idCambio = idCambio;
		this.nombreComponente = nombreComponente;
		this.nombreConcepto = nombreConcepto;
		this.valorOriginal = valorOriginal;
		this.valorFinal = valorFinal;
		this.registroAlterado = registroAlterado;
		this.idRegistro = idRegistro;
		this.modificadoPor = apellidosP + " " + modificadoPor;
		this.fechaModificacion = fechaModificacion;
	}

	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

	public BitacoraCambiosVO() {

	}

	public Long getIdCambio() {
		return idCambio;
	}

	public void setIdCambio(Long idCambio) {
		this.idCambio = idCambio;
	}

	public String getNombreComponente() {
		return nombreComponente;
	}

	public void setNombreComponente(String nombreComponente) {
		this.nombreComponente = nombreComponente;
	}

	public String getNombreConcepto() {
		return nombreConcepto;
	}

	public void setNombreConcepto(String nombreConcepto) {
		this.nombreConcepto = nombreConcepto;
	}

	public String getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(String valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public String getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}

	public String getRegistroAlterado() {
		return registroAlterado;
	}

	public void setRegistroAlterado(String registroAlterado) {
		this.registroAlterado = registroAlterado;
	}

	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}


}
