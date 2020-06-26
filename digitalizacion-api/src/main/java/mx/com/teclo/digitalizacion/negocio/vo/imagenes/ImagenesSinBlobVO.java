package mx.com.teclo.digitalizacion.negocio.vo.imagenes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ImagenesSinBlobVO {
	private Long idImagen;
	private String codigoPlaca;
	private String numeroLicencia;
	private String codigoTipo;
	private String nbLicExpEn;
	private String nbPlacaExpEn;
	private Long nuArtInfrac;
	private Long nuFraccion;
	private String nuInciso;
	private Long nuParrafo;
	private String codigoPlacaOficial;
	private String nbUtDelegacion;
	private Long nuNumeroFolio;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone="America/Mexico_City")
	private Date fhInfraccion;
	private String stLiberada;
	private String stValidada;
	private Long idLote;
	private String nombreImagen;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="America/Mexico_City")
	private Date fechaLiberacion;
	private Short stDuplicada;
	private VehiculoMarcaVO vehiculoMarca;
	
	public ImagenesSinBlobVO() {

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


	public String getStLiberada() {
		return stLiberada;
	}


	public void setStLiberada(String stLiberada) {
		this.stLiberada = stLiberada;
	}


	public String getStValidada() {
		return stValidada;
	}


	public void setStValidada(String stValidada) {
		this.stValidada = stValidada;
	}


	public Long getIdLote() {
		return idLote;
	}


	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}


	public String getNombreImagen() {
		return nombreImagen;
	}


	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}


	public Date getFechaLiberacion() {
		return fechaLiberacion;
	}


	public void setFechaLiberacion(Date fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}


	public Short getStDuplicada() {
		return stDuplicada;
	}


	public void setStDuplicada(Short stDuplicada) {
		this.stDuplicada = stDuplicada;
	}


	public VehiculoMarcaVO getVehiculoMarca() {
		return vehiculoMarca;
	}


	public void setVehiculoMarca(VehiculoMarcaVO vehiculoMarca) {
		this.vehiculoMarca = vehiculoMarca;
	}
	
	
	
}
