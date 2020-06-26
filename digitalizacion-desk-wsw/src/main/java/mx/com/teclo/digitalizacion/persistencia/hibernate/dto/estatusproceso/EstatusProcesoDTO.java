package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.estatusproceso;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TDP032C_STAT_PROCESO")
public class EstatusProcesoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9069734612110393669L;

	@Id
	@Column(name = "ID_STAT_PROCESO", unique = true, nullable = false)
	private Long idEstatusProceso;

	@Column(name = "CD_ESTATUS_PROCESO")
	private String codigoEstatusProceso;

	@Column(name = "TX_DESCRIPCION")
	private String descripcion;
	
	@Column(name = "NB_ESTATUS")
	private String nombreEstatus;
	
	

	public String getNombreEstatus() {
		return nombreEstatus;
	}

	public void setNombreEstatus(String nombreEstatus) {
		this.nombreEstatus = nombreEstatus;
	}

	/**
	 * @return the idEstatusProceso
	 */
	public Long getIdEstatusProceso() {
		return idEstatusProceso;
	}

	/**
	 * @param idEstatusProceso the idEstatusProceso to set
	 */
	public void setIdEstatusProceso(Long idEstatusProceso) {
		this.idEstatusProceso = idEstatusProceso;
	}

	/**
	 * @return the codigoEstatusProceso
	 */
	public String getCodigoEstatusProceso() {
		return codigoEstatusProceso;
	}

	/**
	 * @param codigoEstatusProceso the codigoEstatusProceso to set
	 */
	public void setCodigoEstatusProceso(String codigoEstatusProceso) {
		this.codigoEstatusProceso = codigoEstatusProceso;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}