package mx.com.teclo.digitalizacion.persistencia.vo.Imagenes;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ImagenesGuardarVO {

	private Long idImagen;
	private String placa;
	private String numeroLicencia;
	private String tipoLicencia;
	private String licenciaExpedidaEn;
	private String placaExpedidaEn;
	private Long numeroArticuloInfraccion;
	private Long numeroFraccion;
	private String numeroInciso;
	private Long numeroParrafo;
	private String placaOficial;
	private String utDelegacion;
	private Long numeroFolio;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date fechaInfraccion;
	private String nombreImagen;
	
	
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
	private Long idlote;
	private List<LBImagenesNuevaVO> lbImagenes;

	/**
	 * @return the idImagen
	 */
	public Long getIdImagen() {
		return idImagen;
	}
	/**
	 * @return the lbImagenes
	 */
	public List<LBImagenesNuevaVO> getLbImagenes() {
		return lbImagenes;
	}
	/**
	 * @param lbImagenes the lbImagenes to set
	 */
	public void setLbImagenes(List<LBImagenesNuevaVO> lbImagenes) {
		this.lbImagenes = lbImagenes;
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
	 * @return the idlote
	 */
	public Long getIdlote() {
		return idlote;
	}
	/**
	 * @param idlote the idlote to set
	 */
	public void setIdlote(Long idlote) {
		this.idlote = idlote;
	}	
}