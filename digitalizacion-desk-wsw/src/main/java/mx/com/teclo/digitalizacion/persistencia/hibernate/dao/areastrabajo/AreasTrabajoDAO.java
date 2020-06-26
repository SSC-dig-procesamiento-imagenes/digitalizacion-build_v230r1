package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.areastrabajo;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.areastrabajo.AreasTrabajoDTO;

public interface AreasTrabajoDAO extends BaseDao<AreasTrabajoDTO>{
	
	List<AreasTrabajoDTO> obtnerListaAreasTrabajo (Long idPlantilla);
}
