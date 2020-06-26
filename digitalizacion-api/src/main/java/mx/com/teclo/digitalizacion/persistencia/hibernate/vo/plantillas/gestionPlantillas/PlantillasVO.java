package mx.com.teclo.digitalizacion.persistencia.hibernate.vo.plantillas.gestionPlantillas;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PlantillasVO {

	private BigDecimal id;
	private String nombre;
	private String descripcion;
	private BigInteger algoritmoAjuste;
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigInteger getAlgoritmoAjuste() {
		return algoritmoAjuste;
	}
	public void setAlgoritmoAjuste(BigInteger algoritmoAjuste) {
		this.algoritmoAjuste = algoritmoAjuste;
	}
	
}
