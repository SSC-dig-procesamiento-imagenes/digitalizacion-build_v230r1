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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author cassio
 */
@Entity
@Table(name = "TDP006D_ASIGN_VALIDACION")
public class AsignValidacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ASIGN_VALIDADOR", unique = true, nullable = false)
	@SequenceGenerator(name = "sTDP006", sequenceName="SQDP_REG_TDP006", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sTDP006")
    private Long idAsignValidador;
    @Column(name = "FH_ASIGNACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhAsignacion;
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
    @JoinColumn(name = "ID_IMAGEN", referencedColumnName = "ID_IMAGEN")
    @ManyToOne(optional = false)
    private ImagenesDTO idImagen;
    @JoinColumn(name = "ID_VALIDADOR", referencedColumnName = "ID_VALIDADOR")
    @ManyToOne(optional = false)
    private ValidadoresDTO idValidador;
    @Column(name = "ST_IMG_EN_UI")
    private Short imagenEnUI;
    @Column(name = "ST_POSPUESTA")
    private Short stPospuesta;
    
    public AsignValidacionDTO() {
    }

    public AsignValidacionDTO(Long idAsignValidador) {
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

       
	public Short getImagenEnUI() {
		return imagenEnUI;
	}

	public void setImagenEnUI(Short imagenEnUI) {
		this.imagenEnUI = imagenEnUI;
	}
	
	

	public Short getStPospuesta() {
		return stPospuesta;
	}

	public void setStPospuesta(Short stPospuesta) {
		this.stPospuesta = stPospuesta;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignValidador != null ? idAsignValidador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof AsignValidacionDTO)) {
            return false;
        }
        AsignValidacionDTO other = (AsignValidacionDTO) object;
        if ((this.idAsignValidador == null && other.idAsignValidador != null) || (this.idAsignValidador != null && !this.idAsignValidador.equals(other.idAsignValidador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.AsignValidacionDTO[ idAsignValidador=" + idAsignValidador + " ]";
    }
    
}
