/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP031C_TIPOS_VALIDACION")
public class TiposValidacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPOS_VAL")
    private Long idTiposVal;
    @Basic(optional = false)
    @Column(name = "CD_TIPOS_VAL")
    private String codigoTiposVal;
    @Basic(optional = false)
    @Column(name = "TX_DESCRIPCION")
    private String txDescripcion;

    public TiposValidacionDTO() {
    }

    public TiposValidacionDTO(Long idTiposVal) {
        this.idTiposVal = idTiposVal;
    }

    public TiposValidacionDTO(Long idTiposVal, String codigoTiposVal, String txDescripcion) {
        this.idTiposVal = idTiposVal;
        this.codigoTiposVal = codigoTiposVal;
        this.txDescripcion = txDescripcion;
    }

    public Long getIdTiposVal() {
        return idTiposVal;
    }

    public void setIdTiposVal(Long idTiposVal) {
        this.idTiposVal = idTiposVal;
    }

    public String getCdTiposVal() {
        return codigoTiposVal;
    }

    public void setCdTiposVal(String codigoTiposVal) {
        this.codigoTiposVal = codigoTiposVal;
    }

    public String getTxDescripcion() {
        return txDescripcion;
    }

    public void setTxDescripcion(String txDescripcion) {
        this.txDescripcion = txDescripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiposVal != null ? idTiposVal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposValidacionDTO)) {
            return false;
        }
        TiposValidacionDTO other = (TiposValidacionDTO) object;
        if ((this.idTiposVal == null && other.idTiposVal != null) || (this.idTiposVal != null && !this.idTiposVal.equals(other.idTiposVal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.TiposValidacionDTO[ idTiposVal=" + idTiposVal + " ]";
    }
    
}
