/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.camposplantillarespuesta;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.campos.CamposDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.plantillasrespuesta.PlantillasRespuestasDTO;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP012D_CAMPOS_PLANTRESP")
public class CamposPlantillaRespuestaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "CAMPOS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CamposDTO campos;
    @JoinColumn(name = "PLANTRESP_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlantillasRespuestasDTO plantillaRespuesta;
    

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the campos
	 */
	public CamposDTO getCampos() {
		return campos;
	}
	/**
	 * @param campos the campos to set
	 */
	public void setCampos(CamposDTO campos) {
		this.campos = campos;
	}
	/**
	 * @return the plantillaRespuesta
	 */
	public PlantillasRespuestasDTO getPlantillaRespuesta() {
		return plantillaRespuesta;
	}
	/**
	 * @param plantillaRespuesta the plantillaRespuesta to set
	 */
	public void setPlantillaRespuesta(PlantillasRespuestasDTO plantillaRespuesta) {
		this.plantillaRespuesta = plantillaRespuesta;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
