package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "VEHICULO_MARCA")
public class VehiculoMarcaDTO implements Serializable {

	private static final long serialVersionUID = 4709242644487546366L;
	@Id
	@Column(name = "VMAR_ID")
	private Long idMarca;
	@Column(name = "VMAR_COD")
	private String codigoMarca;
	@Column(name = "VMAR_NOMBRE")
	private String nombreMarca;
	@Column(name = "VMAR_STATUS")
	private String estatusMarca;
	@Column(name = "CREADO_POR")
	private Long creador;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	
	
	public VehiculoMarcaDTO() {
	}
	
	public VehiculoMarcaDTO(Long idMarca, String codigoMarca, String nombreMarca, String estatusMarca, Long creador,
			Date fechaCreacion, Long modificadoPor, Date ultimaModificacion) {
		this.idMarca = idMarca;
		this.codigoMarca = codigoMarca;
		this.nombreMarca = nombreMarca;
		this.estatusMarca = estatusMarca;
		this.creador = creador;
		this.fechaCreacion = fechaCreacion;
		this.modificadoPor = modificadoPor;
		this.ultimaModificacion = ultimaModificacion;
	}
	public Long getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}
	public String getCodigoMarca() {
		return codigoMarca;
	}
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	public String getNombreMarca() {
		return nombreMarca;
	}
	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}
	public String getEstatusMarca() {
		return estatusMarca;
	}
	public void setEstatusMarca(String estatusMarca) {
		this.estatusMarca = estatusMarca;
	}
	public Long getCreador() {
		return creador;
	}
	public void setCreador(Long creador) {
		this.creador = creador;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
	
	
	
	
	
	
	
}
