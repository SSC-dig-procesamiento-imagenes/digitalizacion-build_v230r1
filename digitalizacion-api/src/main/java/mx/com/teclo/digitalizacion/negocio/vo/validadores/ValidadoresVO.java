package mx.com.teclo.digitalizacion.negocio.vo.validadores;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonFormat;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValHistDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadorConfigDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;

public class ValidadoresVO {
    private Long idValidador;
    private short stValidadorActivo;
    private long idUsuario;
    private Short stActivo;
    private Long idUsrCreacion;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City")
    private Date fhCreacion;
    private Long idUsrModifica;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City")
    private Date fhModificacion;

    public ValidadoresVO() {
    }

    public ValidadoresVO(Long idValidador) {
        this.idValidador = idValidador;
    }

    public ValidadoresVO(Long idValidador, short stValidadorActivo, long idUsuario) {
        this.idValidador = idValidador;
        this.stValidadorActivo = stValidadorActivo;
        this.idUsuario = idUsuario;
    }

    public Long getIdValidador() {
        return idValidador;
    }

    public void setIdValidador(Long idValidador) {
        this.idValidador = idValidador;
    }

    public short getStValidadorActivo() {
        return stValidadorActivo;
    }

    public void setStValidadorActivo(short stValidadorActivo) {
        this.stValidadorActivo = stValidadorActivo;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValidador != null ? idValidador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof ValidadoresVO)) {
            return false;
        }
        ValidadoresVO other = (ValidadoresVO) object;
        if ((this.idValidador == null && other.idValidador != null) || (this.idValidador != null && !this.idValidador.equals(other.idValidador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vo.ValidadoresVO[ idValidador=" + idValidador + " ]";
    }


}
