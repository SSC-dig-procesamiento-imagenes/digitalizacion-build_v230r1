package mx.com.teclo.digitalizacion.persistencia.vo.plantillarespuestacaracteres;

import java.math.BigDecimal;
import java.math.BigInteger;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.caracteres.CaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.plantillasrespuesta.PlantillasRespuestasDTO;

public class PlantillaRespuestaCaracteresVO {

    private BigDecimal id;
    private BigInteger orden;
    private PlantillasRespuestasDTO plantillasrespuestasId;
    private CaracteresDTO caracteresId;
	
    public PlantillaRespuestaCaracteresVO() {
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigInteger getOrden() {
		return orden;
	}

	public void setOrden(BigInteger orden) {
		this.orden = orden;
	}

	public PlantillasRespuestasDTO getPlantillasrespuestasId() {
		return plantillasrespuestasId;
	}

	public void setPlantillasrespuestasId(PlantillasRespuestasDTO plantillasrespuestasId) {
		this.plantillasrespuestasId = plantillasrespuestasId;
	}

	public CaracteresDTO getCaracteresId() {
		return caracteresId;
	}

	public void setCaracteresId(CaracteresDTO caracteresId) {
		this.caracteresId = caracteresId;
	}
    
    
}
