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
@Table(name = "TDP027C_TIPOARCHIVOS_SCANNERS")
public class TipoArchivosEscanersDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @JoinColumn(name = "TIPOARCHIVOS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoArchivosDTO tipoarchivosId;
    @JoinColumn(name = "SCANNERS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EscanersDTO scannersId;

    public TipoArchivosEscanersDTO() {
    }

    public TipoArchivosEscanersDTO(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public TipoArchivosDTO getTipoarchivosId() {
        return tipoarchivosId;
    }

    public void setTipoarchivosId(TipoArchivosDTO tipoarchivosId) {
        this.tipoarchivosId = tipoarchivosId;
    }

    public EscanersDTO getScannersId() {
        return scannersId;
    }

    public void setScannersId(EscanersDTO scannersId) {
        this.scannersId = scannersId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof TipoArchivosEscanersDTO)) {
            return false;
        }
        TipoArchivosEscanersDTO other = (TipoArchivosEscanersDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.TipoArchivosEscanersDTO[ id=" + id + " ]";
    }
    
}
