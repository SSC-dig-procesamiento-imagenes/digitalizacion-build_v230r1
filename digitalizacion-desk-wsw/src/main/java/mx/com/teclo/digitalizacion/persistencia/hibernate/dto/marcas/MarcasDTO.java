package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.marcas;

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
@Table(name = "TDP010D_MARCAS")
public class MarcasDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long idMarcas;
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
    @JoinColumn(name = "ORIENTACION_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private  OrientacionesDTO orientaciones;
    @Basic(optional = false)
    @Column(name = "NUMERO_OBJETOS")
    private Integer numeroObjetos;
    @JoinColumn(name = "PLANTILLAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlantillasDTO plantillas;

    /**
	 * @return the idMarcas
	 */
	public Long getIdMarcas() {
		return idMarcas;
	}
	/**
	 * @param idMarcas the idMarcas to set
	 */
	public void setIdMarcas(Long idMarcas) {
		this.idMarcas = idMarcas;
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
		return orientaciones;
	}
	/**
	 * @param orientaciones the orientaciones to set
	 */
	public void setOrientaciones(OrientacionesDTO orientaciones) {
		this.orientaciones = orientaciones;
	}
	/**
	 * @return the numeroObjetos
	 */
	public Integer getNumeroObjetos() {
		return numeroObjetos;
	}
	/**
	 * @param numeroObjetos the numeroObjetos to set
	 */
	public void setNumeroObjetos(Integer numeroObjetos) {
		this.numeroObjetos = numeroObjetos;
	}
	/**
	 * @return the plantillas
	 */
	public PlantillasDTO getPlantillas() {
		return plantillas;
	}
	/**
	 * @param plantillas the plantillas to set
	 */
	public void setPlantillas(PlantillasDTO plantillas) {
		this.plantillas = plantillas;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}