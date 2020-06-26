/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.*;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP011D_CAMPOS")
public class CamposDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    
    @Id
	@SequenceGenerator(name = "seqCampos", sequenceName="SEQDP011D_CAMPOS", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCampos")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "COORDENADAIY")
    private BigInteger coordenadaiy;
    @Basic(optional = false)
    @Column(name = "COORDENADAIX")
    private BigInteger coordenadaix;
    @Basic(optional = false)
    @Column(name = "COORDENADAFY")
    private BigInteger coordenadafy;
    @Basic(optional = false)
    @Column(name = "COORDENADAFX")
    private BigInteger coordenadafx;
    @JoinColumn(name = "PLANTILLAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlantillasDTO plantillasId;
    @JoinColumn(name = "ORIENTACIONES_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private OrientacionesDTO orientacionesId;
    
    @Column(name="ST_ACTIVO")
    private Integer stActivo;

    
    
    public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public CamposDTO() {
    }

    public CamposDTO(BigDecimal id) {
        this.id = id;
    }

    public CamposDTO(BigDecimal id, String nombre, BigInteger coordenadaiy, BigInteger coordenadaix, BigInteger coordenadafy, BigInteger coordenadafx) {
        this.id = id;
        this.nombre = nombre;
        this.coordenadaiy = coordenadaiy;
        this.coordenadaix = coordenadaix;
        this.coordenadafy = coordenadafy;
        this.coordenadafx = coordenadafx;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getCoordenadaiy() {
        return coordenadaiy;
    }

    public void setCoordenadaiy(BigInteger coordenadaiy) {
        this.coordenadaiy = coordenadaiy;
    }

    public BigInteger getCoordenadaix() {
        return coordenadaix;
    }

    public void setCoordenadaix(BigInteger coordenadaix) {
        this.coordenadaix = coordenadaix;
    }

    public BigInteger getCoordenadafy() {
        return coordenadafy;
    }

    public void setCoordenadafy(BigInteger coordenadafy) {
        this.coordenadafy = coordenadafy;
    }

    public BigInteger getCoordenadafx() {
        return coordenadafx;
    }

    public void setCoordenadafx(BigInteger coordenadafx) {
        this.coordenadafx = coordenadafx;
    }

    public PlantillasDTO getPlantillasId() {
        return plantillasId;
    }

    public void setPlantillasId(PlantillasDTO plantillasId) {
        this.plantillasId = plantillasId;
    }

    public OrientacionesDTO getOrientacionesId() {
        return orientacionesId;
    }

    public void setOrientacionesId(OrientacionesDTO orientacionesId) {
        this.orientacionesId = orientacionesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof CamposDTO)) {
            return false;
        }
        CamposDTO other = (CamposDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.CamposDTO[ id=" + id + " ]";
    }
    
}
