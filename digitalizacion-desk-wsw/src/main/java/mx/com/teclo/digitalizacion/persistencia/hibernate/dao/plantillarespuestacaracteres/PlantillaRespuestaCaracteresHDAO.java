package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.plantillarespuestacaracteres;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.caracteres.CaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.plantillasrespuestacaracteres.PlantillaRespuestaCaracteresDTO;

public interface PlantillaRespuestaCaracteresHDAO extends BaseDao<PlantillaRespuestaCaracteresDTO>{

	List<CaracteresDTO> getCaracteresPorIdPlantillaRespuesta(Long idPlantillaRespuesta);
}
