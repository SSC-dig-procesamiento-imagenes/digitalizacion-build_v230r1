package mx.com.teclo.digitalizacion.negocio.vo.validadores;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;

public class AsignValidacionVO {

    private Long idAsignValidador;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City")
    private Date fhAsignacion;
    private Short stActivo;
    private Long idUsrCreacion;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City")
    private Date fhCreacion;
    private Long idUsrModifica;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City")
    private Date fhModificacion;
    private ImagenesVO idImagen;
    private ValidadoresVO idValidador;

    public AsignValidacionVO() {
    }

    public AsignValidacionVO(Long idAsignValidador) {
        this.idAsignValidador = idAsignValidador;
    }

    public Long getIdAsignValidador() {
        return idAsignValidador;
    }

    public void setIdAsignValidador(Long idAsignValidador) {
        this.idAsignValidador = idAsignValidador;
    }

    public Date getFhAsignacion() {
        return fhAsignacion;
    }

    public void setFhAsignacion(Date fhAsignacion) {
        this.fhAsignacion = fhAsignacion;
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

    public ImagenesVO getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(ImagenesVO idImagen) {
        this.idImagen = idImagen;
    }

    public ValidadoresVO getIdValidador() {
        return idValidador;
    }

    public void setIdValidador(ValidadoresVO idValidador) {
        this.idValidador = idValidador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignValidador != null ? idAsignValidador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof AsignValidacionVO)) {
            return false;
        }
        AsignValidacionVO other = (AsignValidacionVO) object;
        if ((this.idAsignValidador == null && other.idAsignValidador != null) || (this.idAsignValidador != null && !this.idAsignValidador.equals(other.idAsignValidador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vo.AsignValidacionVO[ idAsignValidador=" + idAsignValidador + " ]";
    }


}
