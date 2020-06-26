/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private BigDecimal id;
    @JoinColumn(name = "CAMPOS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CamposDTO camposId;
    @JoinColumn(name = "PLANTRESP_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlantillasRespuestasDTO plantrespId;

    public CamposPlantillaRespuestaDTO() {
    }

    public CamposPlantillaRespuestaDTO(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public CamposDTO getCamposId() {
        return camposId;
    }

    public void setCamposId(CamposDTO camposId) {
        this.camposId = camposId;
    }

    public PlantillasRespuestasDTO getPlantrespId() {
        return plantrespId;
    }

    public void setPlantrespId(PlantillasRespuestasDTO plantrespId) {
        this.plantrespId = plantrespId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof CamposPlantillaRespuestaDTO)) {
            return false;
        }
        CamposPlantillaRespuestaDTO other = (CamposPlantillaRespuestaDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.CamposPlantillaRespuestaDTO[ id=" + id + " ]";
    }
    
}
