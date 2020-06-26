package mx.com.teclo.digitalizacion.persistencia.hibernate.vo.plantillas.gestionPlantillas;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class CamposVO {

	private BigDecimal id;
	private String nombre;
	private BigInteger coordenadaiy;
	private BigInteger coordenadaix;
	private BigInteger coordenadafy;
	private BigInteger coordenadafx;
	private PlantillasVO plantillasId;
	private OrientacionesVO orientacionesId;
	private Integer stActivo;
	
	
	public Integer getStActivo() {
		return stActivo;
	}
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}
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
	public BigInteger getCoordenadaiy() {
		return coordenadaiy;
	}
	public void setCoordenadaiy(BigInteger coordenadaiy) {
		this.coordenadaiy = coordenadaiy;
	}
	public BigInteger getCoordenadaix() {
		return coordenadaix;
	}
	public void setCoordenadaix(BigInteger coordenadaix) {
		this.coordenadaix = coordenadaix;
	}
	public BigInteger getCoordenadafy() {
		return coordenadafy;
	}
	public void setCoordenadafy(BigInteger coordenadafy) {
		this.coordenadafy = coordenadafy;
	}
	public BigInteger getCoordenadafx() {
		return coordenadafx;
	}
	public void setCoordenadafx(BigInteger coordenadafx) {
		this.coordenadafx = coordenadafx;
	}
	public PlantillasVO getPlantillasId() {
		return plantillasId;
	}
	public void setPlantillasId(PlantillasVO plantillasId) {
		this.plantillasId = plantillasId;
	}
	public OrientacionesVO getOrientacionesId() {
		return orientacionesId;
	}
	public void setOrientacionesId(OrientacionesVO orientacionesId) {
		this.orientacionesId = orientacionesId;
	}

}
