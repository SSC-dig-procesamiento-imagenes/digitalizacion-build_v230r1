package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.configuracionescaner;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.escaner.EscanerDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.resolucion.ResolucionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.suministropapel.SuministroPapelDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.tamaniopapel.TamanioPapaelDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.tipoarchivo.TipoArchivosDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.tipopixcel.TipoPixelDTO;

@Entity
@Table(name = "TDP029C_CONFSCANNER")
public class ConfiguracionEscanerDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1023678406321137643L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long idConfiguracionEscaner;

	@Column(name = "NOMBRE")
	private String nombreConfiguracionEscaner;

	@Column(name = "NOMENCLATURA")
	private String nombreNomenclatura;

	@Column(name = "CONTRASTE")
	private Integer contraste;
	
	@Column(name = "BRILLO")
	private Integer brillo;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SCANNERS_ID", referencedColumnName = "ID")
	private EscanerDTO escaner;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PAPERSIZE_ID", referencedColumnName = "ID")
	private TamanioPapaelDTO tamanioPapael;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "RESOLUCION_ID", referencedColumnName = "ID")
	private ResolucionDTO resolucion;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TIPOPIXEL_ID", referencedColumnName = "ID")
	private TipoPixelDTO tipoPixel;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TIPOARCHIVOS_ID", referencedColumnName = "ID")
	private TipoArchivosDTO tipoArchivos;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SUMINISTROPAPEL_ID", referencedColumnName = "ID")
	private SuministroPapelDTO suministroPapel;

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
	 * @return the nombreConfiguracionEscaner
	 */
	public String getNombreConfiguracionEscaner() {
		return nombreConfiguracionEscaner;
	}

	/**
	 * @param nombreConfiguracionEscaner the nombreConfiguracionEscaner to set
	 */
	public void setNombreConfiguracionEscaner(String nombreConfiguracionEscaner) {
		this.nombreConfiguracionEscaner = nombreConfiguracionEscaner;
	}

	/**
	 * @return the nombreNomenclatura
	 */
	public String getNombreNomenclatura() {
		return nombreNomenclatura;
	}

	/**
	 * @param nombreNomenclatura the nombreNomenclatura to set
	 */
	public void setNombreNomenclatura(String nombreNomenclatura) {
		this.nombreNomenclatura = nombreNomenclatura;
	}

	/**
	 * @return the contraste
	 */
	public Integer getContraste() {
		return contraste;
	}

	/**
	 * @param contraste the contraste to set
	 */
	public void setContraste(Integer contraste) {
		this.contraste = contraste;
	}

	/**
	 * @return the brillo
	 */
	public Integer getBrillo() {
		return brillo;
	}

	/**
	 * @param brillo the brillo to set
	 */
	public void setBrillo(Integer brillo) {
		this.brillo = brillo;
	}

	/**
	 * @return the escaner
	 */
	public EscanerDTO getEscaner() {
		return escaner;
	}

	/**
	 * @param escaner the escaner to set
	 */
	public void setEscaner(EscanerDTO escaner) {
		this.escaner = escaner;
	}

	/**
	 * @return the tamanioPapael
	 */
	public TamanioPapaelDTO getTamanioPapael() {
		return tamanioPapael;
	}

	/**
	 * @param tamanioPapael the tamanioPapael to set
	 */
	public void setTamanioPapael(TamanioPapaelDTO tamanioPapael) {
		this.tamanioPapael = tamanioPapael;
	}

	/**
	 * @return the resolucion
	 */
	public ResolucionDTO getResolucion() {
		return resolucion;
	}

	/**
	 * @param resolucion the resolucion to set
	 */
	public void setResolucion(ResolucionDTO resolucion) {
		this.resolucion = resolucion;
	}

	/**
	 * @return the tipoPixel
	 */
	public TipoPixelDTO getTipoPixel() {
		return tipoPixel;
	}

	/**
	 * @param tipoPixel the tipoPixel to set
	 */
	public void setTipoPixel(TipoPixelDTO tipoPixel) {
		this.tipoPixel = tipoPixel;
	}

	/**
	 * @return the tipoArchivos
	 */
	public TipoArchivosDTO getTipoArchivos() {
		return tipoArchivos;
	}

	/**
	 * @param tipoArchivos the tipoArchivos to set
	 */
	public void setTipoArchivos(TipoArchivosDTO tipoArchivos) {
		this.tipoArchivos = tipoArchivos;
	}

	/**
	 * @return the suministroPapel
	 */
	public SuministroPapelDTO getSuministroPapel() {
		return suministroPapel;
	}

	/**
	 * @param suministroPapel the suministroPapel to set
	 */
	public void setSuministroPapel(SuministroPapelDTO suministroPapel) {
		this.suministroPapel = suministroPapel;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}