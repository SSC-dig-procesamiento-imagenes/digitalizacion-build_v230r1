package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author plopez
 *
 */
@Entity
@Table(name = "TDP038C_CTRL_REPORTE")
public class CtrlReportesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2302821165203057747L;

	@Id
	@Column(name = "ID_CTRL_REPORTE", unique = true, nullable = false)
	private Long idCtrlReporte;

	@Column(name = "NB_CATALOGO")
	private String nombre;

	@Column(name = "NB_RUTA")
	private String ruta;

	@Column(name = "ST_ACTIVO")
	private Boolean stActivo;

	@Column(name = "ID_PADRE")
	private Long idPadre;

	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion;

	@Column(name = "FH_CREACION")
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA")
	private Long idUsrModifica;

	@Column(name = "FH_MODIFICACION")
	private Date fhModificacion;

	public Long getIdCtrlReporte() {
		return idCtrlReporte;
	}

	public void setIdCtrlReporte(Long idCtrlReporte) {
		this.idCtrlReporte = idCtrlReporte;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Boolean getStActivo() {
		return stActivo;
	}

	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}

	public Long getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
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
