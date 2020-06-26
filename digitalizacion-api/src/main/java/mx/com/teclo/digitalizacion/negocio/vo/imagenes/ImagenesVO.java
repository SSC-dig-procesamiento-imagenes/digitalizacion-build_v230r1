package mx.com.teclo.digitalizacion.negocio.vo.imagenes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ImagenesVO {

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
	
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	//@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss Z")
	//Para invierno: @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", locale="es-MX", timezone="GMT-6" )
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", locale="es-MX", timezone="GMT-5" )
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone="America/Mexico_City")
	private Date fhInfraccion;
	private Short stLiberada;
	private Short stValidada;
	private Long idLote;
	private LBImagenesVO lbImagenAnverso;
	private LBImagenesVO lbImagenReverso;
	private String nombreImagen;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone="America/Mexico_City")
	private Date fechaLiberacion;
	private Short stDuplicada;
	private VehiculoMarcaVO vehiculoMarca;
    private String txPospuesta;
    private String txRechazada;
    private String idValidadorEvaluo;
    private String idValidadorPospuso;
    /*Campos para construir en VehiculoMarcaVO en el constructor:*/
	private Long idMarca;
	private String codigoMarca;
	private String nombreMarca;
	
	public ImagenesVO(Long idImagen, String codigoPlaca, String numeroLicencia, String codigoTipo, String nbLicExpEn,
			String nbPlacaExpEn, Long nuArtInfrac, Long nuFraccion, String nuInciso, Long nuParrafo,
			String codigoPlacaOficial, String nbUtDelegacion, Long nuNumeroFolio, Date fhInfraccion, Short stLiberada,
			Short stValidada, Long idLote, String nombreImagen, Date fechaLiberacion, Short stDuplicada,
			String txPospuesta, String txRechazada, String idValidadorEvaluo, String idValidadorPospuso, Long idMarca,
			String codigoMarca, String nombreMarca) {
		this.idImagen = idImagen;
		this.codigoPlaca = codigoPlaca;
		this.numeroLicencia = numeroLicencia;
		this.codigoTipo = codigoTipo;
		this.nbLicExpEn = nbLicExpEn;
		this.nbPlacaExpEn = nbPlacaExpEn;
		this.nuArtInfrac = nuArtInfrac;
		this.nuFraccion = nuFraccion;
		this.nuInciso = nuInciso;
		this.nuParrafo = nuParrafo;
		this.codigoPlacaOficial = codigoPlacaOficial;
		this.nbUtDelegacion = nbUtDelegacion;
		this.nuNumeroFolio = nuNumeroFolio;
		this.fhInfraccion = fhInfraccion;
		this.stLiberada = stLiberada;
		this.stValidada = stValidada;
		this.idLote = idLote;
		this.nombreImagen = nombreImagen;
		this.fechaLiberacion = fechaLiberacion;
		this.stDuplicada = stDuplicada;
		this.txPospuesta = txPospuesta;
		this.txRechazada = txRechazada;
		this.idValidadorEvaluo = idValidadorEvaluo;
		this.idValidadorPospuso = idValidadorPospuso;
		this.idMarca = idMarca;
		this.codigoMarca = codigoMarca;
		this.nombreMarca = nombreMarca;
		
		this.vehiculoMarca = new VehiculoMarcaVO(idMarca, codigoMarca, nombreMarca);
		this.lbImagenAnverso = null;
		this.lbImagenReverso = null;
	}
	
	public ImagenesVO() {

	}

	public ImagenesVO(Long idImagen) {
		this.idImagen = idImagen;
	}

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}



	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}



	public String getNombreMarca() {
		return nombreMarca;
	}



	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
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



	public LBImagenesVO getLbImagenAnverso() {
		return lbImagenAnverso;
	}



	public void setLbImagenAnverso(LBImagenesVO lbImagenAnverso) {
		this.lbImagenAnverso = lbImagenAnverso;
	}



	public LBImagenesVO getLbImagenReverso() {
		return lbImagenReverso;
	}



	public void setLbImagenReverso(LBImagenesVO lbImagenReverso) {
		this.lbImagenReverso = lbImagenReverso;
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

	public String getTxPospuesta() {
		return txPospuesta;
	}

	public void setTxPospuesta(String txPospuesta) {
		this.txPospuesta = txPospuesta;
	}

	public String getTxRechazada() {
		return txRechazada;
	}

	public void setTxRechazada(String txRechazada) {
		this.txRechazada = txRechazada;
	}

	public String getIdValidadorEvaluo() {
		return idValidadorEvaluo;
	}

	public void setIdValidadorEvaluo(String idValidadorEvaluo) {
		this.idValidadorEvaluo = idValidadorEvaluo;
	}

	public String getIdValidadorPospuso() {
		return idValidadorPospuso;
	}

	public void setIdValidadorPospuso(String idValidadorPospuso) {
		this.idValidadorPospuso = idValidadorPospuso;
	}


	
	

}
