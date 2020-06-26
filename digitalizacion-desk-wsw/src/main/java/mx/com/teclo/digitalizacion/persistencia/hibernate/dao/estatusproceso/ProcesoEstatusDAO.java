package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.estatusproceso;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.estatusproceso.EstatusProcesoDTO;

public interface ProcesoEstatusDAO extends BaseDao<EstatusProcesoDTO>{
	List<EstatusProcesoDTO> getEstatusDigitalizacion();
}
