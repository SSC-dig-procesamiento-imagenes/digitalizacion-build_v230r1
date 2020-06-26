package mx.com.teclo.digitalizacion.negocio.vo.imagenes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AsignacionesDatosVO {
	
	private Long idValidador;
	private String nickName;
	private String nombre;
	private String apellidoP;
	private String email;
	private Long cantImagenesAsignadas;
	private Long cantImagenesValidandose;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City")
	private Date fechaUltimaOperacion;
	
	
	public AsignacionesDatosVO() {

	}
	
	public AsignacionesDatosVO(Long idValidador, String nickName, String nombre, String apellidoP, String email,
			Long cantImagenesAsignadas, Long cantImagenesValidandose, Date fechaUltimaOperacion) {
		this.idValidador = idValidador;
		this.nickName = nickName;
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.email = email;
		this.cantImagenesAsignadas = cantImagenesAsignadas;
		this.cantImagenesValidandose = cantImagenesValidandose;
		this.fechaUltimaOperacion = fechaUltimaOperacion;
	}

	
	public Long getIdValidador() {
		return idValidador;
	}

	public void setIdValidador(Long idValidador) {
		this.idValidador = idValidador;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoP() {
		return apellidoP;
	}

	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCantImagenesAsignadas() {
		return cantImagenesAsignadas;
	}

	public void setCantImagenesAsignadas(Long cantImagenesAsignadas) {
		this.cantImagenesAsignadas = cantImagenesAsignadas;
	}

	public Long getCantImagenesValidandose() {
		return cantImagenesValidandose;
	}

	public void setCantImagenesValidandose(Long cantImagenesValidandose) {
		this.cantImagenesValidandose = cantImagenesValidandose;
	}

	public Date getFechaUltimaOperacion() {
		return fechaUltimaOperacion;
	}

	public void setFechaUltimaOperacion(Date fechaUltimaOperacion) {
		this.fechaUltimaOperacion = fechaUltimaOperacion;
	}

	
}
