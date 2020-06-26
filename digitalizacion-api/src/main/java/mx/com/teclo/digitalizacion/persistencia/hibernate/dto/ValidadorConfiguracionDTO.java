/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP005D_VALIDADOR_CONFIG")
public class ValidadorConfiguracionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_VALIDADOR_CONFIG")
    private Long idValidadorConfig;
    @Basic(optional = false)
    @Column(name = "FH_VALIDADOR_CONFIG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhValidadorConfig;
    @Basic(optional = false)
    @Column(name = "ST_CONFIG_ACTIVA")
    private short stConfigActiva;
    @JoinColumn(name = "ID_VALIDADOR", referencedColumnName = "ID_VALIDADOR")
    @ManyToOne(optional = false)
    private ValidadoresDTO idValidador;
    @JoinColumn(name = "ID_CONFIGURACION", referencedColumnName = "ID_CONFIGURACION")
    @ManyToOne(optional = false)
    private ConfiguracionValidadorDTO idConfiguracion;

    public ValidadorConfiguracionDTO() {
    }

    public ValidadorConfiguracionDTO(Long idValidadorConfig) {
        this.idValidadorConfig = idValidadorConfig;
    }

    public ValidadorConfiguracionDTO(Long idValidadorConfig, Date fhValidadorConfig, short stConfigActiva) {
        this.idValidadorConfig = idValidadorConfig;
        this.fhValidadorConfig = fhValidadorConfig;
        this.stConfigActiva = stConfigActiva;
    }

    public Long getIdValidadorConfig() {
        return idValidadorConfig;
    }

    public void setIdValidadorConfig(Long idValidadorConfig) {
        this.idValidadorConfig = idValidadorConfig;
    }

    public Date getFhValidadorConfig() {
        return fhValidadorConfig;
    }

    public void setFhValidadorConfig(Date fhValidadorConfig) {
        this.fhValidadorConfig = fhValidadorConfig;
    }

    public short getStConfigActiva() {
        return stConfigActiva;
    }

    public void setStConfigActiva(short stConfigActiva) {
        this.stConfigActiva = stConfigActiva;
    }

    public ValidadoresDTO getIdValidador() {
        return idValidador;
    }

    public void setIdValidador(ValidadoresDTO idValidador) {
        this.idValidador = idValidador;
    }

    public ConfiguracionValidadorDTO getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(ConfiguracionValidadorDTO idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValidadorConfig != null ? idValidadorConfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof ValidadorConfiguracionDTO)) {
            return false;
        }
        ValidadorConfiguracionDTO other = (ValidadorConfiguracionDTO) object;
        if ((this.idValidadorConfig == null && other.idValidadorConfig != null) || (this.idValidadorConfig != null && !this.idValidadorConfig.equals(other.idValidadorConfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.ValidadorConfiguracionDTO[ idValidadorConfig=" + idValidadorConfig + " ]";
    }
    
}
