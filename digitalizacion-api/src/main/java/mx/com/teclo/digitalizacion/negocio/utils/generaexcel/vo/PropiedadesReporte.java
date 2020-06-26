package mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo;

import java.io.InputStream;
import java.util.List;

/**
 * 
 * @author UnitisDes0416
 *
 */
public class PropiedadesReporte {
	
	private String rutaArchivo;
	private String extencionArchvio;
	private String nombreReporte;
	private String tituloExcel;
	private String fechaI;
	private String fechaF;
	private InputStream imagenReporte;
	private int numeroHojas;
	private String personaReporte;
	private List<String> subtitulos;
	
	/**
	 * @return the rutaArchivo
	 */
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	/**
	 * @return the personaReporte
	 */
	public String getPersonaReporte() {
		return personaReporte;
	}
	/**
	 * @param personaReporte the personaReporte to set
	 */
	public void setPersonaReporte(String personaReporte) {
		this.personaReporte = personaReporte;
	}
	/**
	 * @return the numeroHojas
	 */
	public int getNumeroHojas() {
		return numeroHojas;
	}
	/**
	 * @param numeroHojas the numeroHojas to set
	 */
	public void setNumeroHojas(int numeroHojas) {
		this.numeroHojas = numeroHojas;
	}
	/**
	 * @param rutaArchivo the rutaArchivo to set
	 */
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	/**
	 * @return the extencionArchvio
	 */
	public String getExtencionArchvio() {
		return extencionArchvio;
	}
	/**
	 * @param extencionArchvio the extencionArchvio to set
	 */
	public void setExtencionArchvio(String extencionArchvio) {
		this.extencionArchvio = extencionArchvio;
	}
	/**
	 * @return the nombreReporte
	 */
	public String getNombreReporte() {
		return nombreReporte;
	}
	/**
	 * @param nombreReporte the nombreReporte to set
	 */
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}
	/**
	 * @return the tituloExcel
	 */
	public String getTituloExcel() {
		return tituloExcel;
	}
	/**
	 * @param tituloExcel the tituloExcel to set
	 */
	public void setTituloExcel(String tituloExcel) {
		this.tituloExcel = tituloExcel;
	}
	/**
	 * @return the imagenReporte
	 */
	public InputStream getImagenReporte() {
		return imagenReporte;
	}
	/**
	 * @return the fechaI
	 */
	public String getFechaI() {
		return fechaI;
	}
	/**
	 * @param fechaI the fechaI to set
	 */
	public void setFechaI(String fechaI) {
		this.fechaI = fechaI;
	}
	/**
	 * @return the fechaF
	 */
	public String getFechaF() {
		return fechaF;
	}
	/**
	 * @param fechaF the fechaF to set
	 */
	public void setFechaF(String fechaF) {
		this.fechaF = fechaF;
	}
	/**
	 * @param imagenReporte the imagenReporte to set
	 */
	public void setImagenReporte(InputStream imagenReporte) {
		this.imagenReporte = imagenReporte;
	}
	/**
	 * @return the subtitulos
	 */
	public List<String> getSubtitulos() {
		return subtitulos;
	}
	/**
	 * @param subtitulos the subtitulos to set
	 */
	public void setSubtitulos(List<String> subtitulos) {
		this.subtitulos = subtitulos;
	}
}
