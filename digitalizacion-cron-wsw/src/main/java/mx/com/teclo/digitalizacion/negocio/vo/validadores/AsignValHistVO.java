package mx.com.teclo.digitalizacion.negocio.vo.validadores;

import java.util.Date;

import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO;

public class AsignValHistVO {

    private Long idAsignValidador;
    private Date fhAsignacion;
    private Short stActivo;
    private ImagenesVO idImagen;
    private ValidadoresVO idValidador;

    public AsignValHistVO() {
    }

    public AsignValHistVO(Long idAsignValidador) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignValHistVO)) {
            return false;
        }
        AsignValHistVO other = (AsignValHistVO) object;
        if ((this.idAsignValidador == null && other.idAsignValidador != null) || (this.idAsignValidador != null && !this.idAsignValidador.equals(other.idAsignValidador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vo.AsignValHistVO[ idAsignValidador=" + idAsignValidador + " ]";
    }

}
