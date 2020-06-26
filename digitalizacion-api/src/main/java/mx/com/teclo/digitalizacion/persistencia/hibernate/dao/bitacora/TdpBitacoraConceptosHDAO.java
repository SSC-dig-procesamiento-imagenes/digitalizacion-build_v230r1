package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.TdpBitacoraConceptosDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.VehiculoMarcaDTO;

public interface TdpBitacoraConceptosHDAO extends BaseDao<TdpBitacoraConceptosDTO>{

	List<TdpBitacoraConceptosDTO> getConceptosByIdComponente(Long componenteId);

}
