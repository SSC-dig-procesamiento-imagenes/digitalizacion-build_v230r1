package mx.com.teclo.digitalizacion.negocio.vo.validadores;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonFormat;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ConfigValidadorDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.TiposValidacionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadorConfigDTO;

public class ConfigValidadorVO {
    private Long idConfiguracion;
    private long nuImgMax;
    private long nuImgMin;
    private Long nuLimImgAsignacion;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City")
    private Date fhInicio;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City")
    private Date fhFinal;
    private short stConfigActiva;
    private Short stActivo;
    private Long idUsrCreacion;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City")
    private Date fhCreacion;
    private Long idUsrModifica;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City")
    private Date fhModificacion;
    private TiposValidacionVO idTiposVal;

    public ConfigValidadorVO() {
    }

    public ConfigValidadorVO(Long idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public ConfigValidadorVO(Long idConfiguracion, long nuImgMax, long nuImgMin, short stConfigActiva) {
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


    public TiposValidacionVO getIdTiposVal() {
        return idTiposVal;
    }

    public void setIdTiposVal(TiposValidacionVO idTiposVal) {
        this.idTiposVal = idTiposVal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfiguracion != null ? idConfiguracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof ConfigValidadorVO)) {
            return false;
        }
        ConfigValidadorVO other = (ConfigValidadorVO) object;
        if ((this.idConfiguracion == null && other.idConfiguracion != null) || (this.idConfiguracion != null && !this.idConfiguracion.equals(other.idConfiguracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vo.ConfigValidadorVO[ idConfiguracion=" + idConfiguracion + " ]";
    }

	
}
