package mx.com.teclo.digitalizacion.persistencia.vo.Imagenes;

public class LBImagenesNuevaVO{

	private String nombreImagen;
	private byte[] lbImagen;
	private String codigoImagen;
	
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
