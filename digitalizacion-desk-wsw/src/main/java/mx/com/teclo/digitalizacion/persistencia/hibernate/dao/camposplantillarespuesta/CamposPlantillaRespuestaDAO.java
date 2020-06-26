package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.camposplantillarespuesta;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.camposplantillarespuesta.CamposPlantillaRespuestaDTO;

public interface CamposPlantillaRespuestaDAO extends BaseDao<CamposPlantillaRespuestaDTO>{
	
	List<CamposPlantillaRespuestaDTO> buscaTodosLosCampos(Long idPlantilla);
	List<CamposPlantillaRespuestaDTO> getTodosCamposPlantillasRespuestaDTOporIdCampo(Long idCampo);
}
