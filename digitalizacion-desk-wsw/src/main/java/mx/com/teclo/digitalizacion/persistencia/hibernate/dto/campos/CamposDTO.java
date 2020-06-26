package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.campos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.orientaciones.OrientacionesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.plantillas.PlantillasDTO;

@Entity
@Table(name = "TDP011D_CAMPOS")
public class CamposDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long idCampos;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombreCampos;
    @Basic(optional = false)
    @Column(name = "COORDENADAIY")
    private Integer coordenadaiY;
    @Basic(optional = false)
    @Column(name = "COORDENADAIX")
    private Integer coordenadaiX;
    @Basic(optional = false)
    @Column(name = "COORDENADAFY")
    private Integer coordenadafY;
    @Basic(optional = false)
    @Column(name = "COORDENADAFX")
    private Integer coordenadafX;
    @JoinColumn(name = "ORIENTACIONES_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private OrientacionesDTO Orientaciones;
    @JoinColumn(name = "PLANTILLAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlantillasDTO idPlantilla;
    
    
    
	public PlantillasDTO getIdPlantilla() {
		return idPlantilla;
	}
	public void setIdPlantilla(PlantillasDTO idPlantilla) {
		this.idPlantilla = idPlantilla;
	}
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
	public OrientacionesDTO getOrientaciones() {
		return Orientaciones;
	}
	/**
	 * @param orientaciones the orientaciones to set
	 */
	public void setOrientaciones(OrientacionesDTO orientaciones) {
		Orientaciones = orientaciones;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}