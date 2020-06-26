/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.plantillasrespuestacaracteres;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.caracteres.CaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.plantillasrespuesta.PlantillasRespuestasDTO;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP016D_PLANTRESP_CARACTERES")
public class PlantillaRespuestaCaracteresDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "ORDEN")
    private BigInteger orden;
    @JoinColumn(name = "PLANTILLASRESPUESTAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlantillasRespuestasDTO plantillasrespuestasId;
    @JoinColumn(name = "CARACTERES_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CaracteresDTO caracteresId;

    public PlantillaRespuestaCaracteresDTO() {
    }

    public PlantillaRespuestaCaracteresDTO(BigDecimal id) {
        this.id = id;
    }

    public PlantillaRespuestaCaracteresDTO(BigDecimal id, BigInteger orden) {
        this.id = id;
        this.orden = orden;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getOrden() {
        return orden;
    }

    public void setOrden(BigInteger orden) {
        this.orden = orden;
    }

    public PlantillasRespuestasDTO getPlantillasrespuestasId() {
        return plantillasrespuestasId;
    }

    public void setPlantillasrespuestasId(PlantillasRespuestasDTO plantillasrespuestasId) {
        this.plantillasrespuestasId = plantillasrespuestasId;
    }

    public CaracteresDTO getCaracteresId() {
        return caracteresId;
    }

    public void setCaracteresId(CaracteresDTO caracteresId) {
        this.caracteresId = caracteresId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof PlantillaRespuestaCaracteresDTO)) {
            return false;
        }
        PlantillaRespuestaCaracteresDTO other = (PlantillaRespuestaCaracteresDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.PlantillaRespuestaCaracteresDTO[ id=" + id + " ]";
    }
    
}
