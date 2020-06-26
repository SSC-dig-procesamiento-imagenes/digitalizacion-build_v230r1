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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cassio
 */
@Entity
@Table(name = "TDP030D_ASIGN_VAL_HIST")
public class AsignValHistDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ASIGN_VALIDADOR")//, unique = true, nullable = false)
//	@SequenceGenerator(name = "sTDP030", sequenceName="SQDP_REG_TDP030", allocationSize=1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sTDP030")
    private Long idAsignValidador;
    @Column(name = "FH_ASIGNACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhAsignacion;
    @Column(name = "ST_ACTIVO")
    private Short stActivo;
    @JoinColumn(name = "ID_IMAGEN", referencedColumnName = "ID_IMAGEN")
    @ManyToOne(optional = false)
    private ImagenesDTO idImagen;
    @JoinColumn(name = "ID_VALIDADOR", referencedColumnName = "ID_VALIDADOR")
    @ManyToOne(optional = false)
    private ValidadoresDTO idValidador;

    public AsignValHistDTO() {
    }

    public AsignValHistDTO(Long idAsignValidador) {
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

    public ImagenesDTO getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(ImagenesDTO idImagen) {
        this.idImagen = idImagen;
    }

    public ValidadoresDTO getIdValidador() {
        return idValidador;
    }

    public void setIdValidador(ValidadoresDTO idValidador) {
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
         
        if (!(object instanceof AsignValHistDTO)) {
            return false;
        }
        AsignValHistDTO other = (AsignValHistDTO) object;
        if ((this.idAsignValidador == null && other.idAsignValidador != null) || (this.idAsignValidador != null && !this.idAsignValidador.equals(other.idAsignValidador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.AsignValHistDTO[ idAsignValidador=" + idAsignValidador + " ]";
    }
    
}
