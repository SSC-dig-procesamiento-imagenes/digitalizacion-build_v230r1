package mx.com.teclo.digitalizacion.persistencia.vo.lotes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PersistirLoteLoteVO {
	private Long idLote;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date fechaCracionLote;
	private String rutaAlmacenamiento;
	private Integer numeroTotalImagenes;
	private Integer numeroTotalRechazadas;
	private Integer numeroTotalAceptadas;
	private Long idEtatusProceso;
	private String nombreLote;
	private Long idConfiguracionEscaner;
	private Integer metDigitalizacion;
	private Long nuFolioInicial;
	private Long nuFolioFinal;
	private Long idUsuario;
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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

	public Date getFechaCracionLote() {
		return fechaCracionLote;
	}

	public void setFechaCracionLote(Date fechaCracionLote) {
		this.fechaCracionLote = fechaCracionLote;
	}

	public String getRutaAlmacenamiento() {
		return rutaAlmacenamiento;
	}

	public void setRutaAlmacenamiento(String rutaAlmacenamiento) {
		this.rutaAlmacenamiento = rutaAlmacenamiento;
	}

	public Integer getNumeroTotalImagenes() {
		return numeroTotalImagenes;
	}

	public void setNumeroTotalImagenes(Integer numeroTotalImagenes) {
		this.numeroTotalImagenes = numeroTotalImagenes;
	}

	public Integer getNumeroTotalRechazadas() {
		return numeroTotalRechazadas;
	}

	public void setNumeroTotalRechazadas(Integer numeroTotalRechazadas) {
		this.numeroTotalRechazadas = numeroTotalRechazadas;
	}

	public Integer getNumeroTotalAceptadas() {
		return numeroTotalAceptadas;
	}

	public void setNumeroTotalAceptadas(Integer numeroTotalAceptadas) {
		this.numeroTotalAceptadas = numeroTotalAceptadas;
	}

	public String getNombreLote() {
		return nombreLote;
	}

	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}
	/**
	 * @return the idEtatusProceso
	 */
	public Long getIdEtatusProceso() {
		return idEtatusProceso;
	}
	/**
	 * @param idEtatusProceso the idEtatusProceso to set
	 */
	public void setIdEtatusProceso(Long idEtatusProceso) {
		this.idEtatusProceso = idEtatusProceso;
	}
	/**
	 * @return the idConfiguracionEscaner
	 */
	public Long getIdConfiguracionEscaner() {
		return idConfiguracionEscaner;
	}
	/**
	 * @param idConfiguracionEscaner the idConfiguracionEscaner to set
	 */
	public void setIdConfiguracionEscaner(Long idConfiguracionEscaner) {
		this.idConfiguracionEscaner = idConfiguracionEscaner;
	}
	/**
	 * @return the metDigitalizacion
	 */
	public Integer getMetDigitalizacion() {
		return metDigitalizacion;
	}
	/**
	 * @param metDigitalizacion the metDigitalizacion to set
	 */
	public void setMetDigitalizacion(Integer metDigitalizacion) {
		this.metDigitalizacion = metDigitalizacion;
	}
	
}
