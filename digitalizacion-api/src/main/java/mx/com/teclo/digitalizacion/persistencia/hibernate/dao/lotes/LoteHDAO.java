package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LoteDTO;

public interface LoteHDAO extends BaseDao<LoteDTO>{

	List<LoteDTO> getLotes();
}
