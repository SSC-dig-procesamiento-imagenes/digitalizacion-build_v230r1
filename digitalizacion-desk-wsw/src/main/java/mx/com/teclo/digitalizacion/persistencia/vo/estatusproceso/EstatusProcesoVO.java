package mx.com.teclo.digitalizacion.persistencia.vo.estatusproceso;

public class EstatusProcesoVO {

	private Long idEstatusProceso;
	
	private String codigoEstatusProceso;
	
	private String descripcion;
	
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

}