package mx.com.teclo.digitalizacion.persistencia.vo.campos;

import mx.com.teclo.digitalizacion.persistencia.vo.orientaciones.OrientacionesVO;

public class CamposVO {

	private Long idCampos;
    
	private String nombreCampos;
    
	private Integer coordenadaiY;
    
	private Integer coordenadaiX;
    
	private Integer coordenadafY;
    
	private Integer coordenadafX;
    
	private OrientacionesVO Orientaciones;

	/**
	 * @return the idCampos
	 */
	public Long getIdCampos() {
		return idCampos;
	}

	/**
	 * @param idCampos the idCampos to set
	 */
	public void setIdCampos(Long idCampos) {
		this.idCampos = idCampos;
	}

	/**
	 * @return the nombreCampos
	 */
	public String getNombreCampos() {
		return nombreCampos;
	}

	/**
	 * @param nombreCampos the nombreCampos to set
	 */
	public void setNombreCampos(String nombreCampos) {
		this.nombreCampos = nombreCampos;
	}

	/**
	 * @return the coordenadaiY
	 */
	public Integer getCoordenadaiY() {
		return coordenadaiY;
	}

	/**
	 * @param coordenadaiY the coordenadaiY to set
	 */
	public void setCoordenadaiY(Integer coordenadaiY) {
		this.coordenadaiY = coordenadaiY;
	}

	/**
	 * @return the coordenadaiX
	 */
	public Integer getCoordenadaiX() {
		return coordenadaiX;
	}

	/**
	 * @param coordenadaiX the coordenadaiX to set
	 */
	public void setCoordenadaiX(Integer coordenadaiX) {
		this.coordenadaiX = coordenadaiX;
	}

	/**
	 * @return the coordenadafY
	 */
	public Integer getCoordenadafY() {
		return coordenadafY;
	}

	/**
	 * @param coordenadafY the coordenadafY to set
	 */
	public void setCoordenadafY(Integer coordenadafY) {
		this.coordenadafY = coordenadafY;
	}

	/**
	 * @return the coordenadafX
	 */
	public Integer getCoordenadafX() {
		return coordenadafX;
	}

	/**
	 * @param coordenadafX the coordenadafX to set
	 */
	public void setCoordenadafX(Integer coordenadafX) {
		this.coordenadafX = coordenadafX;
	}

	/**
	 * @return the orientaciones
	 */
	public OrientacionesVO getOrientaciones() {
		return Orientaciones;
	}

	/**
	 * @param orientaciones the orientaciones to set
	 */
	public void setOrientaciones(OrientacionesVO orientaciones) {
		Orientaciones = orientaciones;
	}	
}