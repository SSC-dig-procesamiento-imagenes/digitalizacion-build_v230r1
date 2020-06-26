package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.imagenes.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lotes.LotesDTO;

public interface ImagenesDAO extends BaseDao<ImagenesDTO>{
	List<ImagenesDTO> getImagenesByLote(LotesDTO loteDTO);
}
