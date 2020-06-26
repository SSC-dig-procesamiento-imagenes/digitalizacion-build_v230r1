package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lotes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.configuracionescaner.ConfiguracionEscanerDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.estatusproceso.EstatusProcesoDTO;

@Entity
@Table(name = "TDP042B_LOTES")
public class LotesBDTO implements Serializable {

	private static final long serialVersionUID = 5997195097888669206L;

	@Id
	@Column(name = "ID_LOTE", unique = true, nullable = false)
	private Long idLote;
	
	@Column(name = "FH_CREACION_LOTE")
	private Date fechaCracionLote;

	@Column(name = "TX_RUTA_ALMACENAM")
	private String rutaAlmacenamiento;
	
	@Column(name = "NU_TOT_IMAGENES")
	private Integer numeroTotalImagenes;

	@Column(name = "NU_TOT_IMG_RECHAZADAS")
	private Integer numeroTotalRechazadas;

	@Column(name = "NU_TOT_IMG_ACEPTADAS")
	private Integer numeroTotalAceptadas;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_STAT_PROCESO", referencedColumnName="ID_STAT_PROCESO")
	private EstatusProcesoDTO estatusProceso;
	
	@Column(name = "NB_LOTE")
	private String nombreLote;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_CONF_SCANNER", referencedColumnName="ID")
	private ConfiguracionEscanerDTO configuracionEscaner;
	
	@Column(name = "CD_MET_DIGITALIZACION")
	private Integer metDigitalizacion;

	@Column(name = "NU_FOLIO_INICIAL")
	private Long nuFolioInicial;
	@Column(name = "NU_FOLIO_FINAL")
	private Long nuFolioFinal;
	
	@Column(name = "ID_USUARIO")
	private Long idUsuario;
	
	public Long getIdUsuario() {
		return idUsuario;
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

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LotesBDTO() {
		
	}
	/**
	 * @return the estatusProceso
	 */
	public EstatusProcesoDTO getEstatusProceso() {
		return estatusProceso;
	}

	/**
	 * @param estatusProceso the estatusProceso to set
	 */
	public void setEstatusProceso(EstatusProcesoDTO estatusProceso) {
		this.estatusProceso = estatusProceso;
	}

	/**
	 * @return the configuracionEscaner
	 */
	public ConfiguracionEscanerDTO getConfiguracionEscaner() {
		return configuracionEscaner;
	}

	/**
	 * @param configuracionEscaner the configuracionEscaner to set
	 */
	public void setConfiguracionEscaner(ConfiguracionEscanerDTO configuracionEscaner) {
		this.configuracionEscaner = configuracionEscaner;
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}