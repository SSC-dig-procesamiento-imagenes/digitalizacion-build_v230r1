package mx.com.teclo.digitalizacion.negocio.vo.imagenes;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class LBImagenesVO {

	private Long idLbImagen;
    private Long idImagen;
	private String nombreImagen;
	private String lbImagen;
	private String codigoImagen;
	
	public LBImagenesVO() {
		
	}

	public Long getIdLbImagen() {
		return idLbImagen;
	}

	public void setIdLbImagen(Long idLbImagen) {
		this.idLbImagen = idLbImagen;
	}

	public Long getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(Long idImagen) {
		this.idImagen = idImagen;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public String getLbImagen() {
		return lbImagen;
	}

	public void setLbImagen(String lbImagen) {
		this.lbImagen = lbImagen;
	}

	public String getCodigoImagen() {
		return codigoImagen;
	}

	public void setCodigoImagen(String codigoImagen) {
		this.codigoImagen = codigoImagen;
	}
	
	

}
