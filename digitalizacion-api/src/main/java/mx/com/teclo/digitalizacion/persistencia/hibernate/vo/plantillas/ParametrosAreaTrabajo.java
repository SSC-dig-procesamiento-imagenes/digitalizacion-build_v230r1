package mx.com.teclo.digitalizacion.persistencia.hibernate.vo.plantillas;

import java.io.Serializable;
import java.util.List;

public class ParametrosAreaTrabajo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8888535645754194115L;
	private int filas;
	private int columnas;
	private byte[] boleta;
	private String extencion;
	private String nombre;
	private int calidad;
	//private List<MarcasPlantillaVO> listMarcas;
	
	private MarcasPlantillaVO marcasVO;

	public MarcasPlantillaVO getMarcasVO() {
		return marcasVO;
	}
	public void setMarcasVO(MarcasPlantillaVO marcasVO) {
		this.marcasVO = marcasVO;
	}
	public int getFilas() {
		return filas;
	}
	public void setFilas(int filas) {
		this.filas = filas;
	}
	public int getColumnas() {
		return columnas;
	}
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	public byte[] getBoleta() {
		return boleta;
	}
	public void setBoleta(byte[] boleta) {
		this.boleta = boleta;
	}
	public String getExtencion() {
		return extencion;
	}
	public void setExtencion(String extencion) {
		this.extencion = extencion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCalidad() {
		return calidad;
	}
	public void setCalidad(int calidad) {
		this.calidad = calidad;
	}
	
		
}
