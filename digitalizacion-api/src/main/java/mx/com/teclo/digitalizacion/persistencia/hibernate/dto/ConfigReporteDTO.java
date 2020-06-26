package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TDP040D_CONFIG_REPORTE")
public class ConfigReporteDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 454085831998000314L;

	@Id
	@Column(name = "ID_CONFIG_REPORTE", unique = true, nullable = false)
	private Long idConfigReporte;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERMISO_ACCESO")
	private PermisoAccesoUsuarioDTO idPermisoAccesoUsuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CTRL_REPORTE")
	private CtrlReportesDTO idCtrlReportes;

	@Column(name = "ST_ACTIVO")
	private Long stActivo;

	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION")
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA")
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION")
	private Date fhModificacion;

	public Long getIdConfigRep() {
		return idConfigReporte;
	}

	public void setIdConfigRep(Long idConfigRep) {
		this.idConfigReporte = idConfigRep;
	}

	public PermisoAccesoUsuarioDTO getIdPermisoAccesoUsuario() {
		return idPermisoAccesoUsuario;
	}

	public void setIdPermisoAccesoUsuario(PermisoAccesoUsuarioDTO idPermisoAccesoUsuario) {
		this.idPermisoAccesoUsuario = idPermisoAccesoUsuario;
	}

	public CtrlReportesDTO getIdCtrlReportes() {
		return idCtrlReportes;
	}

	public void setIdCtrlReportes(CtrlReportesDTO idCtrlReportes) {
		this.idCtrlReportes = idCtrlReportes;
	}

	public Long getStActivo() {
		return stActivo;
	}

	public void setStActivo(Long stActivo) {
		this.stActivo = stActivo;
	}

	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Long getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
