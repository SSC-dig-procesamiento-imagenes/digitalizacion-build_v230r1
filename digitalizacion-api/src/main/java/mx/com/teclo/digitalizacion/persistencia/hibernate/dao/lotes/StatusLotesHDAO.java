package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresEstadosLotes;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstatusProcesoDTO;

public interface StatusLotesHDAO extends BaseDao<EstatusProcesoDTO>{
	EstatusProcesoDTO getEstatus(ValoresEstadosLotes valorEstado);
	List<EstatusProcesoDTO> getEstatusPertinentesLiberacion();
	List<EstatusProcesoDTO> getEstatus();
}
