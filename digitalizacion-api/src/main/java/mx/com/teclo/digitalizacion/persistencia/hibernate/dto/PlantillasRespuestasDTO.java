/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP014D_PLANTILLASRESPUESTAS")
public class PlantillasRespuestasDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Id
	@SequenceGenerator(name = "seqRespuesta", sequenceName="SEQDP014D_PLANTILLASRESPUESTAS", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRespuesta")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    

	@Basic(optional = false)
	@Column(name = "ST_ACTIVO")
	private Integer stActivo;

	@Basic(optional = false)
	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion;

	@Basic(optional = false)
	@Column(name = "FH_CREACION")
	private Date fhCreacion;

	@Basic(optional = false)
	@Column(name = "ID_USR_MODIFICA")
	private Long idUsrModifica;

	@Basic(optional = false)
	@Column(name = "FH_MODIFICACION")
	private Date fhModificacion;
	
	

    public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
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

	public PlantillasRespuestasDTO() {
    }

    public PlantillasRespuestasDTO(BigDecimal id) {
        this.id = id;
    }

    public PlantillasRespuestasDTO(BigDecimal id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof PlantillasRespuestasDTO)) {
            return false;
        }
        PlantillasRespuestasDTO other = (PlantillasRespuestasDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.PlantillasRespuestasDTO[ id=" + id + " ]";
    }
    
}
