package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TAQ025C_SE_USUARIOS")
public class UsuariosDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6907093618618026821L;
	@Id
	@Column(name = "ID_USUARIO")
	private Long idUsuario;
	@Column(name = "CD_USUARIO")
	private String cdUsuario;
	@Column(name = "NB_CONTRASENIA")
	private String contrasenia; 
	@Column(name = "NB_USUARIO")
	private String nombreUsuario;
	@Column(name = "NB_APATERNO")
	private String apellidoPaterno;
	@Column(name = "NB_AMATERNO")
	private String apellidoMaterno;
	@Column(name = "NB_EMAIL")
	private String email;
	@Column(name = "NU_TELEFONO")
	private Long telefono;
	@Column(name = "ST_CONTRASENIA")
	private Short estadoContrasenia;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FH_MODIF_CONTRASENIA")
	private Date fechaModificacionContrasenia;
	@Column(name = "ST_ACTIVO")
	private Short activo;
	@Column(name = "ID_USR_CREACION")
	private Long idUsuarioCreador;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FH_CREACION")
	private Date fechaCreacion;
	@Column(name = "ID_USR_MODIFICA")
	private Long idUsuarioModifador;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FH_MODIFICACION")
	private Date fechaModificacion;
	
	
	public UsuariosDTO() {
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Short getEstadoContrasenia() {
		return estadoContrasenia;
	}

	public void setEstadoContrasenia(Short estadoContrasenia) {
		this.estadoContrasenia = estadoContrasenia;
	}

	public Date getFechaModificacionContrasenia() {
		return fechaModificacionContrasenia;
	}

	public void setFechaModificacionContrasenia(Date fechaModificacionContrasenia) {
		this.fechaModificacionContrasenia = fechaModificacionContrasenia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public Short getActivo() {
		return activo;
	}

	public void setActivo(Short activo) {
		this.activo = activo;
	}

	public Long getIdUsuarioCreador() {
		return idUsuarioCreador;
	}

	public void setIdUsuarioCreador(Long idUsuarioCreador) {
		this.idUsuarioCreador = idUsuarioCreador;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getIdUsuarioModifador() {
		return idUsuarioModifador;
	}

	public void setIdUsuarioModifador(Long idUsuarioModifador) {
		this.idUsuarioModifador = idUsuarioModifador;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	
}
