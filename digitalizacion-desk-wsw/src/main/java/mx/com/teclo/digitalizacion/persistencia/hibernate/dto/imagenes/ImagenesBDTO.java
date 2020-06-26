package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.imagenes;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lotes.LotesDTO;

@Entity
@Table(name = "TDP041B_IMAGENES")
public class ImagenesBDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2398923416217871311L;

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
	private String placa;
	@Column(name = "CD_NUM_LICENCIA")
	private String numeroLicencia;
	@Column(name = "CD_TIPO")
	private String tipoLicencia;
	@Column(name = "NB_LIC_EXP_EN")
	private String licenciaExpedidaEn;
	@Column(name = "NB_PLACA_EXP_EN")
	private String placaExpedidaEn;
	@Column(name = "NU_ART_INFRAC")
	private Long numeroArticuloInfraccion;
	@Column(name = "NU_FRACCION")
	private Long numeroFraccion;
	@Column(name = "NU_INCISO")
	private String numeroInciso;
	@Column(name = "NU_PARRAFO")
	private Long numeroParrafo;
	@Column(name = "CD_PLACA_OFICIAL")
	private String placaOficial;
	@Column(name = "NB_UT_DELEGACION")
	private String utDelegacion;
	@Column(name = "NU_NUMERO_FOLIO")
	private Long numeroFolio;
	@Column(name = "FH_INFRACCION")
	private Date fechaInfraccion;
	@Column(name = "ST_LIBERADA")
	@Basic(optional = false)
	private Short estatusLiberada;
	@Column(name = "ST_VALIDADA")
	@Basic(optional = false)
	private Short estatusValidada;
    @JoinColumn(name = "ID_LOTE", referencedColumnName = "ID_LOTE")
    @ManyToOne(optional = false)
    private LotesDTO lote;
	@Column(name = "NB_IMAGEN")
	@Basic(optional = false)
	private String nombreImagen;
	@Column(name = "FH_LIBERACION")
	private Date fechaLiberacion;
	
	/**
	 * @return the idImagen
	 */
	public Long getIdImagen() {
		return idImagen;
	}
	/**
	 * @param idImagen the idImagen to set
	 */
	public void setIdImagen(Long idImagen) {
		this.idImagen = idImagen;
	}
	/**
	 * @return the codigoPlaca
	 */
	public String getPlaca() {
		return placa;
	}
	/**
	 * @param codigoPlaca the codigoPlaca to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	/**
	 * @return the numeroLicencia
	 */
	public String getNumeroLicencia() {
		return numeroLicencia;
	}
	/**
	 * @param numeroLicencia the numeroLicencia to set
	 */
	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}
	/**
	 * @return the tipoLicencia
	 */
	public String getTipoLicencia() {
		return tipoLicencia;
	}
	/**
	 * @param tipoLicencia the tipoLicencia to set
	 */
	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}
	/**
	 * @return the licenciaExpedidaEn
	 */
	public String getLicenciaExpedidaEn() {
		return licenciaExpedidaEn;
	}
	/**
	 * @param licenciaExpedidaEn the licenciaExpedidaEn to set
	 */
	public void setLicenciaExpedidaEn(String licenciaExpedidaEn) {
		this.licenciaExpedidaEn = licenciaExpedidaEn;
	}
	/**
	 * @return the placaExpedidaEn
	 */
	public String getPlacaExpedidaEn() {
		return placaExpedidaEn;
	}
	/**
	 * @param placaExpedidaEn the placaExpedidaEn to set
	 */
	public void setPlacaExpedidaEn(String placaExpedidaEn) {
		this.placaExpedidaEn = placaExpedidaEn;
	}
	/**
	 * @return the numeroArticuloInfraccion
	 */
	public Long getNumeroArticuloInfraccion() {
		return numeroArticuloInfraccion;
	}
	/**
	 * @param numeroArticuloInfraccion the numeroArticuloInfraccion to set
	 */
	public void setNumeroArticuloInfraccion(Long numeroArticuloInfraccion) {
		this.numeroArticuloInfraccion = numeroArticuloInfraccion;
	}
	/**
	 * @return the numeroFraccion
	 */
	public Long getNumeroFraccion() {
		return numeroFraccion;
	}
	/**
	 * @param numeroFraccion the numeroFraccion to set
	 */
	public void setNumeroFraccion(Long numeroFraccion) {
		this.numeroFraccion = numeroFraccion;
	}
	/**
	 * @return the numeroInciso
	 */
	public String getNumeroInciso() {
		return numeroInciso;
	}
	/**
	 * @param numeroInciso the numeroInciso to set
	 */
	public void setNumeroInciso(String numeroInciso) {
		this.numeroInciso = numeroInciso;
	}
	/**
	 * @return the numeroParrafo
	 */
	public Long getNumeroParrafo() {
		return numeroParrafo;
	}
	/**
	 * @param numeroParrafo the numeroParrafo to set
	 */
	public void setNumeroParrafo(Long numeroParrafo) {
		this.numeroParrafo = numeroParrafo;
	}
	/**
	 * @return the placaOficial
	 */
	public String getPlacaOficial() {
		return placaOficial;
	}
	/**
	 * @param placaOficial the placaOficial to set
	 */
	public void setPlacaOficial(String placaOficial) {
		this.placaOficial = placaOficial;
	}
	/**
	 * @return the utDelegacion
	 */
	public String getUtDelegacion() {
		return utDelegacion;
	}
	/**
	 * @param utDelegacion the utDelegacion to set
	 */
	public void setUtDelegacion(String utDelegacion) {
		this.utDelegacion = utDelegacion;
	}
	/**
	 * @return the numeroFolio
	 */
	public Long getNumeroFolio() {
		return numeroFolio;
	}
	/**
	 * @param numeroFolio the numeroFolio to set
	 */
	public void setNumeroFolio(Long numeroFolio) {
		this.numeroFolio = numeroFolio;
	}
	/**
	 * @return the fechaInfraccion
	 */
	public Date getFechaInfraccion() {
		return fechaInfraccion;
	}
	/**
	 * @param fechaInfraccion the fechaInfraccion to set
	 */
	public void setFechaInfraccion(Date fechaInfraccion) {
		this.fechaInfraccion = fechaInfraccion;
	}
	/**
	 * @return the estatusLiberada
	 */
	public Short getEstatusLiberada() {
		return estatusLiberada;
	}
	/**
	 * @param estatusLiberada the estatusLiberada to set
	 */
	public void setEstatusLiberada(Short estatusLiberada) {
		this.estatusLiberada = estatusLiberada;
	}
	/**
	 * @return the estatusValidada
	 */
	public Short getEstatusValidada() {
		return estatusValidada;
	}
	/**
	 * @param estatusValidada the estatusValidada to set
	 */
	public void setEstatusValidada(Short estatusValidada) {
		this.estatusValidada = estatusValidada;
	}
	/**
	 * @return the lote
	 */
	public LotesDTO getLote() {
		return lote;
	}
	/**
	 * @param lote the lote to set
	 */
	public void setLote(LotesDTO lote) {
		this.lote = lote;
	}
	/**
	 * @return the nombreImagen
	 */
	public String getNombreImagen() {
		return nombreImagen;
	}
	/**
	 * @param nombreImagen the nombreImagen to set
	 */
	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}
	/**
	 * @return the fechaLiberacion
	 */
	public Date getFechaLiberacion() {
		return fechaLiberacion;
	}
	/**
	 * @param fechaLiberacion the fechaLiberacion to set
	 */
	public void setFechaLiberacion(Date fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
