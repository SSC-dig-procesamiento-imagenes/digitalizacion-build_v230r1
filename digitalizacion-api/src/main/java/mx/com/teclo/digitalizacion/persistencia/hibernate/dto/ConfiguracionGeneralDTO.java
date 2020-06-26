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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TDP034C_CONFIG_GENERAL")
public class ConfiguracionGeneralDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONFIG_GENERAL")
    private Long idConfigGeneral;
    @Column(name = "TX_NOMBRE_RUTA")
    private String txNombreRuta;
    @JoinColumn(name = "ID_NOMENCLATURA_IMG", referencedColumnName = "ID_NOMENCLATURA_IMG")
    @ManyToOne
    private NomenclaturaImgenesDTO idNomenclaturaImg;

    public ConfiguracionGeneralDTO() {
    }

    public ConfiguracionGeneralDTO(Long idConfigGeneral) {
        this.idConfigGeneral = idConfigGeneral;
    }

    public Long getIdConfigGeneral() {
        return idConfigGeneral;
    }

    public void setIdConfigGeneral(Long idConfigGeneral) {
        this.idConfigGeneral = idConfigGeneral;
    }

    public String getTxNombreRuta() {
        return txNombreRuta;
    }

    public void setTxNombreRuta(String txNombreRuta) {
        this.txNombreRuta = txNombreRuta;
    }

    public NomenclaturaImgenesDTO getIdNomenclaturaImg() {
        return idNomenclaturaImg;
    }

    public void setIdNomenclaturaImg(NomenclaturaImgenesDTO idNomenclaturaImg) {
        this.idNomenclaturaImg = idNomenclaturaImg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfigGeneral != null ? idConfigGeneral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof ConfiguracionGeneralDTO)) {
            return false;
        }
        ConfiguracionGeneralDTO other = (ConfiguracionGeneralDTO) object;
        if ((this.idConfigGeneral == null && other.idConfigGeneral != null) || (this.idConfigGeneral != null && !this.idConfigGeneral.equals(other.idConfigGeneral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.ConfiguracionGeneralDTO[ idConfigGeneral=" + idConfigGeneral + " ]";
    }
    
}
