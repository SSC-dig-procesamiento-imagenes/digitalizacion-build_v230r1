/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "TDP035C_NOMENCLATURA_IMG")
public class NomenclaturaImgenesDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_NOMENCLATURA_IMG")
    private Long idNomenclaturaImg;
    @Column(name = "CD_NOMENCLATURA_IMG")
    private String cdNomenclaturaImg;
    @Column(name = "TX_NUMENLCATURA_IMG")
    private String txNumenlcaturaImg;

    public NomenclaturaImgenesDTO() {
    }

    public NomenclaturaImgenesDTO(Long idNomenclaturaImg) {
        this.idNomenclaturaImg = idNomenclaturaImg;
    }

    public Long getIdNomenclaturaImg() {
        return idNomenclaturaImg;
    }

    public void setIdNomenclaturaImg(Long idNomenclaturaImg) {
        this.idNomenclaturaImg = idNomenclaturaImg;
    }

    public String getCdNomenclaturaImg() {
        return cdNomenclaturaImg;
    }

    public void setCdNomenclaturaImg(String cdNomenclaturaImg) {
        this.cdNomenclaturaImg = cdNomenclaturaImg;
    }

    public String getTxNumenlcaturaImg() {
        return txNumenlcaturaImg;
    }

    public void setTxNumenlcaturaImg(String txNumenlcaturaImg) {
        this.txNumenlcaturaImg = txNumenlcaturaImg;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNomenclaturaImg != null ? idNomenclaturaImg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomenclaturaImgenesDTO)) {
            return false;
        }
        NomenclaturaImgenesDTO other = (NomenclaturaImgenesDTO) object;
        if ((this.idNomenclaturaImg == null && other.idNomenclaturaImg != null) || (this.idNomenclaturaImg != null && !this.idNomenclaturaImg.equals(other.idNomenclaturaImg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.NomenclaturaImgenesDTO[ idNomenclaturaImg=" + idNomenclaturaImg + " ]";
    }
    
}
