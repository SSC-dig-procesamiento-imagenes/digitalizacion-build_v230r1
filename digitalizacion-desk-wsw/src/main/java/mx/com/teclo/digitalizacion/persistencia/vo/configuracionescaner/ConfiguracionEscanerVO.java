package mx.com.teclo.digitalizacion.persistencia.vo.configuracionescaner;

import mx.com.teclo.digitalizacion.persistencia.vo.escaner.EscanerVO;
import mx.com.teclo.digitalizacion.persistencia.vo.resolucion.ResolucionVO;
import mx.com.teclo.digitalizacion.persistencia.vo.sumistropapel.SuministroPapelVO;
import mx.com.teclo.digitalizacion.persistencia.vo.tamaniopapel.TamanioPapaelVO;
import mx.com.teclo.digitalizacion.persistencia.vo.tipoarchivos.TipoArchivosVO;
import mx.com.teclo.digitalizacion.persistencia.vo.tipopixel.TipoPixelVO;

public class ConfiguracionEscanerVO{
	
	private Long idConfiguracionEscaner;

	private String nombreConfiguracionEscaner;

	private String nombreNomenclatura;

	private Integer contraste;
	
	private Integer brillo;
	
	private EscanerVO escaner;
	
	private TamanioPapaelVO tamanioPapael;
	
	private ResolucionVO resolucion;
	
	private TipoPixelVO tipoPixel;
	
	private TipoArchivosVO tipoArchivos;
	
	private SuministroPapelVO suministroPapel;

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
	public EscanerVO getEscaner() {
		return escaner;
	}

	/**
	 * @param escaner the escaner to set
	 */
	public void setEscaner(EscanerVO escaner) {
		this.escaner = escaner;
	}

	/**
	 * @return the tamanioPapael
	 */
	public TamanioPapaelVO getTamanioPapael() {
		return tamanioPapael;
	}

	/**
	 * @param tamanioPapael the tamanioPapael to set
	 */
	public void setTamanioPapael(TamanioPapaelVO tamanioPapael) {
		this.tamanioPapael = tamanioPapael;
	}

	/**
	 * @return the resolucion
	 */
	public ResolucionVO getResolucion() {
		return resolucion;
	}

	/**
	 * @param resolucion the resolucion to set
	 */
	public void setResolucion(ResolucionVO resolucion) {
		this.resolucion = resolucion;
	}

	/**
	 * @return the tipoPixel
	 */
	public TipoPixelVO getTipoPixel() {
		return tipoPixel;
	}

	/**
	 * @param tipoPixel the tipoPixel to set
	 */
	public void setTipoPixel(TipoPixelVO tipoPixel) {
		this.tipoPixel = tipoPixel;
	}

	/**
	 * @return the tipoArchivos
	 */
	public TipoArchivosVO getTipoArchivos() {
		return tipoArchivos;
	}

	/**
	 * @param tipoArchivos the tipoArchivos to set
	 */
	public void setTipoArchivos(TipoArchivosVO tipoArchivos) {
		this.tipoArchivos = tipoArchivos;
	}

	/**
	 * @return the suministroPapel
	 */
	public SuministroPapelVO getSuministroPapel() {
		return suministroPapel;
	}

	/**
	 * @param suministroPapel the suministroPapel to set
	 */
	public void setSuministroPapel(SuministroPapelVO suministroPapel) {
		this.suministroPapel = suministroPapel;
	}
}