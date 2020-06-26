package mx.com.teclo.digitalizacion.negocio.vo.imagenes;

import java.io.Serializable;

public class BoletaPosponerVO{
	private Long idImagen;
	private String causa;
	
	public BoletaPosponerVO() {
		
	}
	
	public BoletaPosponerVO(Long idImagen, String causa) {
		super();
		this.idImagen = idImagen;
		this.causa = causa;
	}

	public Long getIdImagen() {
		return idImagen;
	}
	public void setIdImagen(Long idImagen) {
		this.idImagen = idImagen;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}

}
