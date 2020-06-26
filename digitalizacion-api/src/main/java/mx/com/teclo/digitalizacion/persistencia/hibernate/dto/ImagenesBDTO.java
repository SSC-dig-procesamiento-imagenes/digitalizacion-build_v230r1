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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP041B_IMAGENES")
public class ImagenesBDTO implements Serializable {

	private static final long serialVersionUID = -5731607651916430642L;
	/*
	 * @Id
	 * @SequenceGenerator(name = "sTDP001B", sequenceName="SQDP_REG_TDP001B", allocationSize=1)
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sTDP001B")
	 * @Column(name = "ID_BIMAGEN", unique = true, nullable = false) 
	 * private Long idBImagen;
	 */
	@Id
	@Column(name = "ID_IMAGEN", unique = true, nullable = false)
	private Long idImagen;
	@Column(name = "CD_PLACA")
	private String codigoPlaca;
	@Column(name = "CD_NUM_LICENCIA")
	private String numeroLicencia;
	@Column(name = "CD_TIPO")
	private String codigoTipo;
	@Column(name = "NB_LIC_EXP_EN")
	private String nbLicExpEn;
	@Column(name = "NB_PLACA_EXP_EN")
	private String nbPlacaExpEn;
	@Column(name = "NU_ART_INFRAC")
	private Long nuArtInfrac;
	@Column(name = "NU_FRACCION")
	private Long nuFraccion;
	@Column(name = "NU_INCISO")
	private String nuInciso;
	@Column(name = "NU_PARRAFO")
	private Long nuParrafo;
	@Column(name = "CD_PLACA_OFICIAL")
	private String codigoPlacaOficial;
	@Column(name = "NB_UT_DELEGACION")
	private String nbUtDelegacion;
	@Column(name = "NU_NUMERO_FOLIO")
	private Long nuNumeroFolio;
	@Column(name = "FH_INFRACCION")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City")
	private Date fhInfraccion;
	@Column(name = "ST_LIBERADA")
	@Basic(optional = false)
	private Short stLiberada;
	@Column(name = "ST_VALIDADA")
	@Basic(optional = false)
	private Short stValidada;
    @JoinColumn(name = "ID_LOTE", referencedColumnName = "ID_LOTE")
    @ManyToOne(optional = false)
    private LotesDTO idLote;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idImagen")
    private Collection<LBImagenesDTO> lbImagenesCollection;
	@Column(name = "NB_IMAGEN")
	@Basic(optional = false)
	private String nombreImagen;
	@Column(name = "FH_LIBERACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaLiberacion;
	
	@JoinColumn(name = "ID_MARCA", referencedColumnName = "VMAR_ID")
    @ManyToOne(optional = true)
	private VehiculoMarcaDTO vehiculoMarca;
	@Column(name = "ST_DUPLICADA")
	private Short stDuplicada;


	public VehiculoMarcaDTO getVehiculoMarca() {
		return vehiculoMarca;
	}

	public void setVehiculoMarca(VehiculoMarcaDTO vehiculoMarca) {
		this.vehiculoMarca = vehiculoMarca;
	}

	public Short getStDuplicada() {
		return stDuplicada;
	}

	public void setStDuplicada(Short stDuplicada) {
		this.stDuplicada = stDuplicada;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public ImagenesBDTO() {
	}

	public ImagenesBDTO(Long idImagen) {
		this.idImagen = idImagen;
	}


	public Long getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(Long idImagen) {
		this.idImagen = idImagen;
	}

	public String getCodigoPlaca() {
		return codigoPlaca;
	}

	public void setCodigoPlaca(String codigoPlaca) {
		this.codigoPlaca = codigoPlaca;
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}

	public String getCodigoTipo() {
		return codigoTipo;
	}

	public void setCodigoTipo(String codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	public String getNbLicExpEn() {
		return nbLicExpEn;
	}

	public void setNbLicExpEn(String nbLicExpEn) {
		this.nbLicExpEn = nbLicExpEn;
	}

	public String getNbPlacaExpEn() {
		return nbPlacaExpEn;
	}

	public void setNbPlacaExpEn(String nbPlacaExpEn) {
		this.nbPlacaExpEn = nbPlacaExpEn;
	}

	public Long getNuArtInfrac() {
		return nuArtInfrac;
	}

	public void setNuArtInfrac(Long nuArtInfrac) {
		this.nuArtInfrac = nuArtInfrac;
	}

	public Long getNuFraccion() {
		return nuFraccion;
	}

	public void setNuFraccion(Long nuFraccion) {
		this.nuFraccion = nuFraccion;
	}

	public String getNuInciso() {
		return nuInciso;
	}

	public void setNuInciso(String nuInciso) {
		this.nuInciso = nuInciso;
	}

	public Long getNuParrafo() {
		return nuParrafo;
	}

	public void setNuParrafo(Long nuParrafo) {
		this.nuParrafo = nuParrafo;
	}

	public String getCodigoPlacaOficial() {
		return codigoPlacaOficial;
	}

	public void setCodigoPlacaOficial(String codigoPlacaOficial) {
		this.codigoPlacaOficial = codigoPlacaOficial;
	}

	public String getNbUtDelegacion() {
		return nbUtDelegacion;
	}

	public void setNbUtDelegacion(String nbUtDelegacion) {
		this.nbUtDelegacion = nbUtDelegacion;
	}

	public Long getNuNumeroFolio() {
		return nuNumeroFolio;
	}

	public void setNuNumeroFolio(Long nuNumeroFolio) {
		this.nuNumeroFolio = nuNumeroFolio;
	}

	public Date getFhInfraccion() {
		return fhInfraccion;
	}

	public void setFhInfraccion(Date fhInfraccion) {
		this.fhInfraccion = fhInfraccion;
	}

	public Short getStLiberada() {
		return stLiberada;
	}

	public void setStLiberada(Short stLiberada) {
		this.stLiberada = stLiberada;
	}

	public Short getStValidada() {
		return stValidada;
	}

	public void setStValidada(Short stValidada) {
		this.stValidada = stValidada;
	}

	public LotesDTO getIdLote() {
		return idLote;
	}

	public void setIdLote(LotesDTO idLote) {
		this.idLote = idLote;
	}

	public Collection<LBImagenesDTO> getLbImagenesCollection() {
		return lbImagenesCollection;
	}

	public void setLbImagenesCollection(Collection<LBImagenesDTO> lbImagenesCollection) {
		this.lbImagenesCollection = lbImagenesCollection;
	}
	
	

	public Date getFechaLiberacion() {
		return fechaLiberacion;
	}

	public void setFechaLiberacion(Date fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idImagen != null ? idImagen.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		 
		if (!(object instanceof ImagenesBDTO)) {
			return false;
		}
		ImagenesBDTO other = (ImagenesBDTO) object;
		if ((this.idImagen == null && other.idImagen != null)
				|| (this.idImagen != null && !this.idImagen.equals(other.idImagen))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "dtos.ImagenesDTO[ idImagen=" + idImagen + " ]";
	}

}
