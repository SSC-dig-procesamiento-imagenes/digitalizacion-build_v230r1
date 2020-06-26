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

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP002D_VALIDADORES")
public class ValidadoresDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_VALIDADOR")
    private Long idValidador;
    @Basic(optional = false)
    @Column(name = "ST_VALIDADOR_ACTIVO")
    private short stValidadorActivo;
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
    @Column(name="ID_USUARIO")
    private Long idUsuario;
    @Column(name="FH_ULTIMA_OPER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimaOperacion;
    
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", insertable=false, updatable=false)
    @ManyToOne(optional=false)
	private UsuariosDTO usuario;
    
    
    public ValidadoresDTO() {
    }

    public ValidadoresDTO(Long idValidador) {
        this.idValidador = idValidador;
    }

    public ValidadoresDTO(Long idValidador, short stValidadorActivo) {
        this.idValidador = idValidador;
        this.stValidadorActivo = stValidadorActivo;
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValidador != null ? idValidador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof ValidadoresDTO)) {
            return false;
        }
        ValidadoresDTO other = (ValidadoresDTO) object;
        if ((this.idValidador == null && other.idValidador != null) || (this.idValidador != null && !this.idValidador.equals(other.idValidador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.ValidadoresDTO[ idValidador=" + idValidador + " ]";
    }

	public Date getFechaUltimaOperacion() {
		return fechaUltimaOperacion;
	}

	public void setFechaUltimaOperacion(Date fechaUltimaOperacion) {
		this.fechaUltimaOperacion = fechaUltimaOperacion;
	}

	public UsuariosDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuariosDTO usuario) {
		this.usuario = usuario;
	}
    
    
}
