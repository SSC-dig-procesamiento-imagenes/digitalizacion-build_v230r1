package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.alveolos;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.alveolos.AlveolosDTO;

public interface AlveolosDAO extends BaseDao<AlveolosDTO>{
	
	List<AlveolosDTO> buscarAlbelosPlantilla (Long idPlantilla);
}
