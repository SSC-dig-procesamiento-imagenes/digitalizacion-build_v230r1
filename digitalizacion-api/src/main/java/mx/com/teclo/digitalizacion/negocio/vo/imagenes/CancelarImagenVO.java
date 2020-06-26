package mx.com.teclo.digitalizacion.negocio.vo.imagenes;

public class CancelarImagenVO {

	Long idImagenSeleccionada;
	String causa;
	public CancelarImagenVO() {
	}
	public Long getIdImagenSeleccionada() {
		return idImagenSeleccionada;
	}
	public void setIdImagenSeleccionada(Long idImagenSeleccionada) {
		this.idImagenSeleccionada = idImagenSeleccionada;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	
	
}
