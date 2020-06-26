/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



//import dtos.TiposValidacionDTO;

/**
 *
 * @author cassio
 */
@Entity
@Table(name = "TDP003C_CONFIG_VALIDADOR")
public class ConfigValidadorDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONFIGURACION")
    private Long idConfiguracion;
    @Basic(optional = false)
    @Column(name = "NU_IMG_MAX")
    private long nuImgMax;
    @Basic(optional = false)
    @Column(name = "NU_IMG_MIN")
    private long nuImgMin;
    @Column(name = "NU_LIM_IMG_ASIGNACION")
    private Long nuLimImgAsignacion;
    @Column(name = "FH_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhInicio;
    @Column(name = "FH_FINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhFinal;
    @Basic(optional = false)
    @Column(name = "ST_CONFIG_ACTIVA")
    private short stConfigActiva;
    @Column(name = "ST_ACTIVO")
    private Short stActivo;
    @Column(name = "ID_USR_CREACION")
    private Long idUsrCreacion;
    @Column(name = "FH_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhCreacion;
    @Column(name = "ID_USR_MODIFICA")
    private Long idUsrModifica;
    @Column(name = "FH_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhModificacion;
    @JoinColumn(name = "ID_TIPOS_VAL", referencedColumnName = "ID_TIPOS_VAL")
    @ManyToOne(optional = false)
    private TiposValidacionDTO idTiposVal;
    @Column(name = "NU_MAX_POSPUESTAS")
    private Long nuMaxPospuestas;

    public ConfigValidadorDTO() {
    }

    public ConfigValidadorDTO(Long idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public ConfigValidadorDTO(Long idConfiguracion, long nuImgMax, long nuImgMin, short stConfigActiva) {
        this.idConfiguracion = idConfiguracion;
        this.nuImgMax = nuImgMax;
        this.nuImgMin = nuImgMin;
        this.stConfigActiva = stConfigActiva;
    }

    public Long getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(Long idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public long getNuImgMax() {
        return nuImgMax;
    }

    public void setNuImgMax(long nuImgMax) {
        this.nuImgMax = nuImgMax;
    }

    public long getNuImgMin() {
        return nuImgMin;
    }

    public void setNuImgMin(long nuImgMin) {
        this.nuImgMin = nuImgMin;
    }

    public Long getNuLimImgAsignacion() {
        return nuLimImgAsignacion;
    }

    public void setNuLimImgAsignacion(Long nuLimImgAsignacion) {
        this.nuLimImgAsignacion = nuLimImgAsignacion;
    }

    public Date getFhInicio() {
        return fhInicio;
    }

    public void setFhInicio(Date fhInicio) {
        this.fhInicio = fhInicio;
    }

    public Date getFhFinal() {
        return fhFinal;
    }

    public void setFhFinal(Date fhFinal) {
        this.fhFinal = fhFinal;
    }

    public short getStConfigActiva() {
        return stConfigActiva;
    }

    public void setStConfigActiva(short stConfigActiva) {
        this.stConfigActiva = stConfigActiva;
    }

    public Short getStActivo() {
        return stActivo;
    }

    public void setStActivo(Short stActivo) {
        this.stActivo = stActivo;
    }

    public Long getIdUsrCreacion() {
        return idUsrCreacion;
    }

    public void setIdUsrCreacion(Long idUsrCreacion) {
        this.idUsrCreacion = idUsrCreacion;
    }

    public Date getFhCreacion() {
        return fhCreacion;
    }

    public void setFhCreacion(Date fhCreacion) {
        this.fhCreacion = fhCreacion;
    }

    public Long getIdUsrModifica() {
        return idUsrModifica;
    }

    public void setIdUsrModifica(Long idUsrModifica) {
        this.idUsrModifica = idUsrModifica;
    }

    public Date getFhModificacion() {
        return fhModificacion;
    }

    public void setFhModificacion(Date fhModificacion) {
        this.fhModificacion = fhModificacion;
    }

    public TiposValidacionDTO getIdTiposVal() {
        return idTiposVal;
    }

    public void setIdTiposVal(TiposValidacionDTO idTiposVal) {
        this.idTiposVal = idTiposVal;
    }
    
    

    public Long getNuMaxPospuestas() {
		return nuMaxPospuestas;
	}

	public void setNuMaxPospuestas(Long nuMaxPospuestas) {
		this.nuMaxPospuestas = nuMaxPospuestas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfiguracion != null ? idConfiguracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof ConfigValidadorDTO)) {
            return false;
        }
        ConfigValidadorDTO other = (ConfigValidadorDTO) object;
        if ((this.idConfiguracion == null && other.idConfiguracion != null) || (this.idConfiguracion != null && !this.idConfiguracion.equals(other.idConfiguracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.ConfigValidadorDTO[ idConfiguracion=" + idConfiguracion + " ]";
    }
    
}
