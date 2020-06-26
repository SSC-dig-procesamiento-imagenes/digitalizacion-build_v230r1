/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.plantillasrespuesta;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP014D_PLANTILLASRESPUESTAS")
public class PlantillasRespuestasDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long idPlantillaRespuesta;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombrePlantillaRespuesta;
    @Column(name = "DESCRIPCION")
    private String descripcionPlantillaRespuesta;
    
	/**
	 * @return the idPlantillaRespuesta
	 */
	public Long getIdPlantillaRespuesta() {
		return idPlantillaRespuesta;
	}
	/**
	 * @param idPlantillaRespuesta the idPlantillaRespuesta to set
	 */
	public void setIdPlantillaRespuesta(Long idPlantillaRespuesta) {
		this.idPlantillaRespuesta = idPlantillaRespuesta;
	}
	/**
	 * @return the nombrePlantillaRespuesta
	 */
	public String getNombrePlantillaRespuesta() {
		return nombrePlantillaRespuesta;
	}
	/**
	 * @param nombrePlantillaRespuesta the nombrePlantillaRespuesta to set
	 */
	public void setNombrePlantillaRespuesta(String nombrePlantillaRespuesta) {
		this.nombrePlantillaRespuesta = nombrePlantillaRespuesta;
	}
	/**
	 * @return the descripcionPlantillaRespuesta
	 */
	public String getDescripcionPlantillaRespuesta() {
		return descripcionPlantillaRespuesta;
	}
	/**
	 * @param descripcionPlantillaRespuesta the descripcionPlantillaRespuesta to set
	 */
	public void setDescripcionPlantillaRespuesta(String descripcionPlantillaRespuesta) {
		this.descripcionPlantillaRespuesta = descripcionPlantillaRespuesta;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
