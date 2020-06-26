package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lotes.LotesDTO;

public interface LotesDAO extends BaseDao<LotesDTO>{
	List<LotesDTO> getLotesPorParametros(Long idstat, String fechaIni, String fechaFin);
}
