/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "TDP032C_STAT_PROCESO")
public class EstatusProcesoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_STAT_PROCESO")
    private Long idStatProceso;
    @Basic(optional = false)
    @Column(name = "CD_ESTATUS_PROCESO")
    private String cdEstatusProceso;
    @Column(name = "TX_DESCRIPCION")
    private String descripcionStatus;
    @Column(name = "NB_ESTATUS")
    private String nombreEstatus;

    public EstatusProcesoDTO() {
    }

    public EstatusProcesoDTO(Long idStatProceso) {
        this.idStatProceso = idStatProceso;
    }

    public EstatusProcesoDTO(Long idStatProceso, String cdEstatusProceso) {
        this.idStatProceso = idStatProceso;
        this.cdEstatusProceso = cdEstatusProceso;
    }

    public Long getIdStatProceso() {
        return idStatProceso;
    }

    public void setIdStatProceso(Long idStatProceso) {
        this.idStatProceso = idStatProceso;
    }

    public String getCdEstatusProceso() {
        return cdEstatusProceso;
    }

    public void setCdEstatusProceso(String cdEstatusProceso) {
        this.cdEstatusProceso = cdEstatusProceso;
    }

    public String getDescripcionStatus() {
        return descripcionStatus;
    }

    public void setDescripcionStatus(String descripcionStatus) {
        this.descripcionStatus = descripcionStatus;
    }


    public String getNombreEstatus() {
		return nombreEstatus;
	}

	public void setNombreEstatus(String nombreEstatus) {
		this.nombreEstatus = nombreEstatus;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatProceso != null ? idStatProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatusProcesoDTO)) {
            return false;
        }
        EstatusProcesoDTO other = (EstatusProcesoDTO) object;
        if ((this.idStatProceso == null && other.idStatProceso != null) || (this.idStatProceso != null && !this.idStatProceso.equals(other.idStatProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.EstatusProcesoDTO[ idStatProceso=" + idStatProceso + " ]";
    }
    
}
