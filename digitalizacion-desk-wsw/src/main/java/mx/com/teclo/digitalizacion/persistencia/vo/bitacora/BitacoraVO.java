package mx.com.teclo.digitalizacion.persistencia.vo.bitacora;

public class BitacoraVO {

	private String tabla;
	private Long componenteId;
	private Long conceptoId;
	private String valorOriginal;
	private String valorFinal;
	private Long modificadoPor;
	private String idRegistro;
	private String registroDescripcion;
	private String origen;
	
	
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public Long getComponenteId() {
		return componenteId;
	}
	public void setComponenteId(Long componenteId) {
		this.componenteId = componenteId;
	}
	public Long getConceptoId() {
		return conceptoId;
	}
	public void setConceptoId(Long conceptoId) {
		this.conceptoId = conceptoId;
	}
	public String getValorOriginal() {
		return valorOriginal;
	}
	public void setValorOriginal(String valorOriginal) {
		this.valorOriginal = valorOriginal;
	}
	public String getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}
	public Long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public String getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}
	public String getRegistroDescripcion() {
		return registroDescripcion;
	}
	public void setRegistroDescripcion(String registroDescripcion) {
		this.registroDescripcion = registroDescripcion;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	
	
	
	
}
