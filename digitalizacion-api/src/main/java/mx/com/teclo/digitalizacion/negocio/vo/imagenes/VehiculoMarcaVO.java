package mx.com.teclo.digitalizacion.negocio.vo.imagenes;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;

public class VehiculoMarcaVO {

	private Long idMarca;
	private String codigoMarca;
	private String nombreMarca;
	
	
	
	public VehiculoMarcaVO(Long idMarca, String codigoMarca, String nombreMarca) {
		this.idMarca = idMarca;
		this.codigoMarca = codigoMarca;
		this.nombreMarca = nombreMarca;
	}
	public VehiculoMarcaVO() {
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
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof VehiculoMarcaVO)) {
			return false;
		}
		VehiculoMarcaVO other = (VehiculoMarcaVO) object;
		if ((this.codigoMarca == null && other.codigoMarca != null)
				|| (this.codigoMarca != null && !this.codigoMarca.equals(other.codigoMarca))) {
			return false;
		}
		return true;
	}
	
	
}
