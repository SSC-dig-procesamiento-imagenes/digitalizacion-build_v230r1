package mx.com.teclo.digitalizacion.persistencia.vo.Imagenes;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.imagenes.ImagenesDTO;

public class LBImagenesVO{

	private Long idLbImagen;
    private ImagenesDTO imagen;
	private String nombreImagen;
	private byte[] lbImagen;
	private String codigoImagen;
	
	public Long getIdLbImagen() {
		return idLbImagen;
	}
	public void setIdLbImagen(Long idLbImagen) {
		this.idLbImagen = idLbImagen;
	}
	public ImagenesDTO getImagen() {
		return imagen;
	}
	public void setImagen(ImagenesDTO imagen) {
		this.imagen = imagen;
	}
	public String getNombreImagen() {
		return nombreImagen;
	}
	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}
	public byte[] getLbImagen() {
		return lbImagen;
	}
	public void setLbImagen(byte[] lbImagen) {
		this.lbImagen = lbImagen;
	}
	public String getCodigoImagen() {
		return codigoImagen;
	}
	public void setCodigoImagen(String codigoImagen) {
		this.codigoImagen = codigoImagen;
	}	
}
