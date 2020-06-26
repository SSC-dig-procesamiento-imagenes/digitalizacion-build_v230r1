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
 * @author cassio
 */
@Entity
@Table(name = "TDP005D_VALIDADOR_CONFIG")
public class ValidadorConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_VALIDADOR_CONFIG")
    private Long idValidadorConfig;
    @Basic(optional = false)
    @Column(name = "FH_VALIDADOR_CONFIG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhValidadorConfig;
    @Column(name = "ST_CONFIG_ACTIVA")
    private Short stConfigActiva;
    @JoinColumn(name = "ID_VALIDADOR", referencedColumnName = "ID_VALIDADOR")
    @ManyToOne(optional = false)
    private ValidadoresDTO idValidador;
    @JoinColumn(name = "ID_CONFIGURACION", referencedColumnName = "ID_CONFIGURACION")
    @ManyToOne(optional = false)
    private ConfigValidadorDTO idConfiguracion;
    @Column(name="NU_CANT_IMG_REST")
    private Long nuCantImgRest;

    public ValidadorConfigDTO() {
    }

    public ValidadorConfigDTO(Long idValidadorConfig) {
        this.idValidadorConfig = idValidadorConfig;
    }

    public ValidadorConfigDTO(Long idValidadorConfig, Date fhValidadorConfig) {
        this.idValidadorConfig = idValidadorConfig;
        this.fhValidadorConfig = fhValidadorConfig;
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

    public Short getStConfigActiva() {
        return stConfigActiva;
    }

    public void setStConfigActiva(Short stConfigActiva) {
        this.stConfigActiva = stConfigActiva;
    }

    public ValidadoresDTO getIdValidador() {
        return idValidador;
    }

    public void setIdValidador(ValidadoresDTO idValidador) {
        this.idValidador = idValidador;
    }

    public ConfigValidadorDTO getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(ConfigValidadorDTO idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }
    
    
    public Long getCantidadImagenesRestantes() {
		return nuCantImgRest;
	}

	public void setCantidadImagenesRestantes(Long cantidadImagenesRestantes) {
		this.nuCantImgRest = cantidadImagenesRestantes;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idValidadorConfig != null ? idValidadorConfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValidadorConfigDTO)) {
            return false;
        }
        ValidadorConfigDTO other = (ValidadorConfigDTO) object;
        if ((this.idValidadorConfig == null && other.idValidadorConfig != null) || (this.idValidadorConfig != null && !this.idValidadorConfig.equals(other.idValidadorConfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.ValidadorConfigDTO[ idValidadorConfig=" + idValidadorConfig + " ]";
    }
    
}
