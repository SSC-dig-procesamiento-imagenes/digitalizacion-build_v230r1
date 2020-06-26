package mx.com.teclo.digitalizacion.persistencia.vo.plantillasolicitada;

import java.util.List;

import mx.com.teclo.digitalizacion.persistencia.vo.caracteres.CaracteresVO;

public class PlantillaRespuestaSolicitadaVO {

	private Long id;
	private String nombre;
	private String descripcion;
	private List<CaracteresVO> caracteres;
	
	public PlantillaRespuestaSolicitadaVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<CaracteresVO> getCaracteres() {
		return caracteres;
	}

	public void setCaracteres(List<CaracteresVO> caracteres) {
		this.caracteres = caracteres;
	}
	
	
	
}
