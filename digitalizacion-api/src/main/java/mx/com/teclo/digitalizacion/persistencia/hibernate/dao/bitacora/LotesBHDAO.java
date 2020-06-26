package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesBDTO;


public interface LotesBHDAO extends BaseDao<LotesBDTO>{

	List<LotesQuery> getLotesParaExcel(List<Long> idLotes);
	
}
