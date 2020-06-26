package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TDP039D_PERMISO_ACCESO_USUARIO")
public class PermisoAccesoUsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1674416877182939836L;

	@Id
	@Column(name = "ID_PERMISO_ACCESO", unique = true, nullable = false)
	private Long idPermisoAcceso;
	
	@ManyToOne
	@JoinColumn(name = "EMP_ID")
	private ValidadoresDTO idValidador;

	@Column(name = "TX_DESCRIPCION")
	private String txDescripcion;

	@Column(name = "ST_ACTIVO")
	private Long stActivo;

	@Column(name = "FH_CREACION")
	private Date fechaCreacion;

	@Column(name = "ID_USER_CREACION")
	private Long idUserCreacion;

	@Column(name = "FH_ULTIMA_MODIFICACION")
	private Date fechaUltimaModif;

	@Column(name = "ID_USER_MODIFICACION")
	private Long idUserModif;

	public Long getIdPermisoAcceso() {
		return idPermisoAcceso;
	}

	public void setIdPermisoAcceso(Long idPermisoAcceso) {
		this.idPermisoAcceso = idPermisoAcceso;
	}

	public ValidadoresDTO getIdValidador() {
		return idValidador;
	}

	public void setIdValidador(ValidadoresDTO idValidador) {
		this.idValidador = idValidador;
	}

	public String getTxDescripcion() {
		return txDescripcion;
	}

	public void setTxDescripcion(String txDescripcion) {
		this.txDescripcion = txDescripcion;
	}

	public Long getStActivo() {
		return stActivo;
	}

	public void setStActivo(Long stActivo) {
		this.stActivo = stActivo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getIdUserCreacion() {
		return idUserCreacion;
	}

	public void setIdUserCreacion(Long idUserCreacion) {
		this.idUserCreacion = idUserCreacion;
	}

	public Date getFechaUltimaModif() {
		return fechaUltimaModif;
	}

	public void setFechaUltimaModif(Date fechaUltimaModif) {
		this.fechaUltimaModif = fechaUltimaModif;
	}

	public Long getIdUserModif() {
		return idUserModif;
	}

	public void setIdUserModif(Long idUserModif) {
		this.idUserModif = idUserModif;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
