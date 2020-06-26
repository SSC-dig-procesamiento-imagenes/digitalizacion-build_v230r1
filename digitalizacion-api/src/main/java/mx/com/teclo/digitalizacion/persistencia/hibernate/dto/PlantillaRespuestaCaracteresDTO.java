/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP016D_PLANTRESP_CARACTERES")
public class PlantillaRespuestaCaracteresDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Id
	@SequenceGenerator(name = "seqRespuestaCaracter", sequenceName="SEQDP016D_PLANTRESP_CARACTERES", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRespuestaCaracter")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "ORDEN")
    private BigInteger orden;
    @JoinColumn(name = "PLANTILLASRESPUESTAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlantillasRespuestasDTO plantillasrespuestasId;
    @JoinColumn(name = "CARACTERES_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CaracteresDTO caracteresId;
    
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

	public PlantillaRespuestaCaracteresDTO() {
    }

    public PlantillaRespuestaCaracteresDTO(BigDecimal id) {
        this.id = id;
    }

    public PlantillaRespuestaCaracteresDTO(BigDecimal id, BigInteger orden) {
        this.id = id;
        this.orden = orden;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getOrden() {
        return orden;
    }

    public void setOrden(BigInteger orden) {
        this.orden = orden;
    }

    public PlantillasRespuestasDTO getPlantillasrespuestasId() {
        return plantillasrespuestasId;
    }

    public void setPlantillasrespuestasId(PlantillasRespuestasDTO plantillasrespuestasId) {
        this.plantillasrespuestasId = plantillasrespuestasId;
    }

    public CaracteresDTO getCaracteresId() {
        return caracteresId;
    }

    public void setCaracteresId(CaracteresDTO caracteresId) {
        this.caracteresId = caracteresId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof PlantillaRespuestaCaracteresDTO)) {
            return false;
        }
        PlantillaRespuestaCaracteresDTO other = (PlantillaRespuestaCaracteresDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.PlantillaRespuestaCaracteresDTO[ id=" + id + " ]";
    }
    
}
