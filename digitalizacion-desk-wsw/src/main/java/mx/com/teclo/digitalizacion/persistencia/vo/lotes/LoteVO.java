package mx.com.teclo.digitalizacion.persistencia.vo.lotes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import mx.com.teclo.digitalizacion.persistencia.vo.configuracionescaner.ConfiguracionEscanerVO;
import mx.com.teclo.digitalizacion.persistencia.vo.estatusproceso.EstatusProcesoVO;

public class LoteVO {
	private Long idLote;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date fechaCracionLote;
	private String rutaAlmacenamiento;
	private Integer numeroTotalImagenes;
	private Integer numeroTotalRechazadas;
	private Integer numeroTotalAceptadas;
	private EstatusProcesoVO estatusProceso;
	private String nombreLote;
	private ConfiguracionEscanerVO configuracionEscaner;
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
	/**
	 * @return the idLote
	 */
	public Long getIdLote() {
		return idLote;
	}
	/**
	 * @param idLote the idLote to set
	 */
	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}
	/**
	 * @return the fechaCracionLote
	 */
	public Date getFechaCracionLote() {
		return fechaCracionLote;
	}
	/**
	 * @param fechaCracionLote the fechaCracionLote to set
	 */
	public void setFechaCracionLote(Date fechaCracionLote) {
		this.fechaCracionLote = fechaCracionLote;
	}
	/**
	 * @return the rutaAlmacenamiento
	 */
	public String getRutaAlmacenamiento() {
		return rutaAlmacenamiento;
	}
	/**
	 * @param rutaAlmacenamiento the rutaAlmacenamiento to set
	 */
	public void setRutaAlmacenamiento(String rutaAlmacenamiento) {
		this.rutaAlmacenamiento = rutaAlmacenamiento;
	}
	/**
	 * @return the numeroTotalImagenes
	 */
	public Integer getNumeroTotalImagenes() {
		return numeroTotalImagenes;
	}
	/**
	 * @param numeroTotalImagenes the numeroTotalImagenes to set
	 */
	public void setNumeroTotalImagenes(Integer numeroTotalImagenes) {
		this.numeroTotalImagenes = numeroTotalImagenes;
	}
	/**
	 * @return the numeroTotalRechazadas
	 */
	public Integer getNumeroTotalRechazadas() {
		return numeroTotalRechazadas;
	}
	/**
	 * @param numeroTotalRechazadas the numeroTotalRechazadas to set
	 */
	public void setNumeroTotalRechazadas(Integer numeroTotalRechazadas) {
		this.numeroTotalRechazadas = numeroTotalRechazadas;
	}
	/**
	 * @return the numeroTotalAceptadas
	 */
	public Integer getNumeroTotalAceptadas() {
		return numeroTotalAceptadas;
	}
	/**
	 * @param numeroTotalAceptadas the numeroTotalAceptadas to set
	 */
	public void setNumeroTotalAceptadas(Integer numeroTotalAceptadas) {
		this.numeroTotalAceptadas = numeroTotalAceptadas;
	}
	/**
	 * @return the estatusProceso
	 */
	public EstatusProcesoVO getEstatusProceso() {
		return estatusProceso;
	}
	/**
	 * @param estatusProceso the estatusProceso to set
	 */
	public void setEstatusProceso(EstatusProcesoVO estatusProceso) {
		this.estatusProceso = estatusProceso;
	}
	/**
	 * @return the nombreLote
	 */
	public String getNombreLote() {
		return nombreLote;
	}
	/**
	 * @param nombreLote the nombreLote to set
	 */
	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}
	/**
	 * @return the configuracionEscaner
	 */
	public ConfiguracionEscanerVO getConfiguracionEscaner() {
		return configuracionEscaner;
	}
	/**
	 * @param configuracionEscaner the configuracionEscaner to set
	 */
	public void setConfiguracionEscaner(ConfiguracionEscanerVO configuracionEscaner) {
		this.configuracionEscaner = configuracionEscaner;
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
