package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CambiosLotesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;

public interface CambiosLotesHDAO extends BaseDao<CambiosLotesDTO> {

	void addCambiosLotes(LotesDTO lotesDTO, Long idUsuario);
}
