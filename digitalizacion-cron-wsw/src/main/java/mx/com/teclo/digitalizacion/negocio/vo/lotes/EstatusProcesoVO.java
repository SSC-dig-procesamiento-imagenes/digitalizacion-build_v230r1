package mx.com.teclo.digitalizacion.negocio.vo.lotes;

public class EstatusProcesoVO {

    private Long idEstatus;
    private String codigoEstatus;
    private String descripcionStatus;
    private String nombreEstatus;

    public EstatusProcesoVO(){
    	
    }

	public Long getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Long idEstatus) {
		this.idEstatus = idEstatus;
	}

	public String getCodigoEstatus() {
		return codigoEstatus;
	}

	public void setCodigoEstatus(String codigoEstatus) {
		this.codigoEstatus = codigoEstatus;
	}

	public String getDescripcionStatus() {
		return descripcionStatus;
	}

	public void setDescripcionStatus(String descripcionStatus) {
		this.descripcionStatus = descripcionStatus;
	}

	public String getNombreEstatus() {
		return nombreEstatus;
	}

	public void setNombreEstatus(String nombreEstatus) {
		this.nombreEstatus = nombreEstatus;
	}

	
    
}
