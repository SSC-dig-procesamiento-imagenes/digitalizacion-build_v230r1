package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.*;


public interface ImagenesBHDAO extends BaseDao<ImagenesBDTO>{

	List<ImagenesBDTO> getImagenesBDTOByLote(Long idLote);

	
}
