package mx.com.teclo.digitalizacion.persistencia.vo.lotes;

public class ActualizarFoliosVO {

	private Long modificadoPor;
	private Long nuFolioInicial;
	private Long nuFolioFinal;
	private Long idLote;
	
	public ActualizarFoliosVO() {
	}

	public Long getNuFolioInicial() {
		return nuFolioInicial;
	}

	public void setNuFolioInicial(Long nuFolioInicial) {
		this.nuFolioInicial = nuFolioInicial;
	}

	public Long getNuFolioFinal() {
		return nuFolioFinal;
	}

	public void setNuFolioFinal(Long nuFolioFinal) {
		this.nuFolioFinal = nuFolioFinal;
	}

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	
	
}
