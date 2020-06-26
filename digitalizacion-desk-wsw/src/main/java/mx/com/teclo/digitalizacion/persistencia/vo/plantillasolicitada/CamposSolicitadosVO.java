package mx.com.teclo.digitalizacion.persistencia.vo.plantillasolicitada;

import java.util.List;

public class CamposSolicitadosVO {
	private Long id;
	private String nombre;
	private Integer coordenadaiy;
	private Integer coordenadaix;
	private Integer coordenadafy;
	private Integer coordenadafx;
	private Long orientaciones_id;
	private Long plantillas_id;
	private List<PlantillaRespuestaSolicitadaVO> plantillarespuesta;
	
	public CamposSolicitadosVO() {
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

	public Integer getCoordenadaiy() {
		return coordenadaiy;
	}

	public void setCoordenadaiy(Integer coordenadaiy) {
		this.coordenadaiy = coordenadaiy;
	}

	public Integer getCoordenadaix() {
		return coordenadaix;
	}

	public void setCoordenadaix(Integer coordenadaix) {
		this.coordenadaix = coordenadaix;
	}

	public Integer getCoordenadafy() {
		return coordenadafy;
	}

	public void setCoordenadafy(Integer coordenadafy) {
		this.coordenadafy = coordenadafy;
	}

	public Integer getCoordenadafx() {
		return coordenadafx;
	}

	public void setCoordenadafx(Integer coordenadafx) {
		this.coordenadafx = coordenadafx;
	}

	public Long getOrientaciones_id() {
		return orientaciones_id;
	}

	public void setOrientaciones_id(Long orientaciones_id) {
		this.orientaciones_id = orientaciones_id;
	}

	public Long getPlantillas_id() {
		return plantillas_id;
	}

	public void setPlantillas_id(Long plantillas_id) {
		this.plantillas_id = plantillas_id;
	}

	public List<PlantillaRespuestaSolicitadaVO> getPlantillarespuesta() {
		return plantillarespuesta;
	}

	public void setPlantillarespuesta(List<PlantillaRespuestaSolicitadaVO> plantillarespuesta) {
		this.plantillarespuesta = plantillarespuesta;
	}
	
	
	
}
