package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.respuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PlantillasRespuestasDTO;
public interface RespuestasDAO extends BaseDao<PlantillasRespuestasDTO> {
	public Long contador(BigDecimal id);

	PlantillasRespuestasDTO getRespuestaByName(String caracter);

	List<PlantillasRespuestasDTO> getRespuestaAndContador();

	List<PlantillasRespuestasDTO> getListRespuestaByName(String nombre);

	List<PlantillasRespuestasDTO> getRespuestaByEstatus(String estatus);

	List<PlantillasRespuestasDTO> getAllRespuestas();

}
