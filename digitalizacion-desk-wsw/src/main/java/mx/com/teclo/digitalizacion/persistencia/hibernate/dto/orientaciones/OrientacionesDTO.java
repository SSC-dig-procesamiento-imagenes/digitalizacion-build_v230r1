/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.orientaciones;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TDP013C_ORIENTACIONES")
public class OrientacionesDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long idOrientaciones;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombreOrientacion;
    
	/**
	 * @return the idOrientaciones
	 */
	public Long getIdOrientaciones() {
		return idOrientaciones;
	}
	/**
	 * @param idOrientaciones the idOrientaciones to set
	 */
	public void setIdOrientaciones(Long idOrientaciones) {
		this.idOrientaciones = idOrientaciones;
	}
	/**
	 * @return the nombreOrientacion
	 */
	public String getNombreOrientacion() {
		return nombreOrientacion;
	}
	/**
	 * @param nombreOrientacion the nombreOrientacion to set
	 */
	public void setNombreOrientacion(String nombreOrientacion) {
		this.nombreOrientacion = nombreOrientacion;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}    
}
