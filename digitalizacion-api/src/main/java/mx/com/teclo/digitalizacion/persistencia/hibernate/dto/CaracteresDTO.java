/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP015D_CARACTERES")
public class CaracteresDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Id
	@SequenceGenerator(name = "seqCaracter", sequenceName="SEQDP015D_CARACTERES", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCaracter")
	@Column(name = "ID")
	private BigDecimal id;

	@Basic(optional = false)
	@Column(name = "CARACTER")
	private String caracter;

	@Basic(optional = true)
	@Column(name = "TX_OBSERVACION")
	private String txObservacion;

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

	public CaracteresDTO() {
	}

	public String getTxObservacion() {
		return txObservacion;
	}

	public void setTxObservacion(String txObservacion) {
		this.txObservacion = txObservacion;
	}


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

	public CaracteresDTO(BigDecimal id) {
		this.id = id;
	}

	public CaracteresDTO(BigDecimal id, String caracter) {
		this.id = id;
		this.caracter = caracter;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof CaracteresDTO)) {
			return false;
		}
		CaracteresDTO other = (CaracteresDTO) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "dtos.CaracteresDTO[ id=" + id + " ]";
	}

}
