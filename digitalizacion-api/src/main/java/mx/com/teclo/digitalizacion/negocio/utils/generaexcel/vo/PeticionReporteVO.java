package mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo;

import java.util.List;

/**
 * 
 * @author UnitisDes0416
 *
 */
public class PeticionReporteVO {
	
	private PropiedadesReporte propiedadesReporte;
	private List<Object> encabezado;
	private List<Object> contenido;
	
	/**
	 * @return the propiedadesReporte
	 */
	public PropiedadesReporte getPropiedadesReporte() {
		return propiedadesReporte;
	}
	/**
	 * @param propiedadesReporte the propiedadesReporte to set
	 */
	public void setPropiedadesReporte(PropiedadesReporte propiedadesReporte) {
		this.propiedadesReporte = propiedadesReporte;
	}
	/**
	 * @return the encabezado
	 */
	public List<Object> getEncabezado() {
		return encabezado;
	}
	/**
	 * @param encabezado the encabezado to set
	 */
	public void setEncabezado(List<Object> encabezado) {
		this.encabezado = encabezado;
	}
	/**
	 * @return the contenido
	 */
	public List<Object> getContenido() {
		return contenido;
	}
	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(List<Object> contenido) {
		this.contenido = contenido;
	}
}
