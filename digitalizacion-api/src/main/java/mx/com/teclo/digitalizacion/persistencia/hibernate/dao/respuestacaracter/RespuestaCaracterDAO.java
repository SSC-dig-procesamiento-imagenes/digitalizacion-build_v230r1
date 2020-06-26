package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.respuestacaracter;

import java.math.BigDecimal;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PlantillaRespuestaCaracteresDTO;

public interface RespuestaCaracterDAO extends BaseDao<PlantillaRespuestaCaracteresDTO> {
	public PlantillaRespuestaCaracteresDTO getCaracterByRespuesta(BigDecimal idRespuesta,BigDecimal idCaracter);

}
