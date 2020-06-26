package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.campos;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.campos.CamposDTO;

public interface CamposHDAO extends BaseDao<CamposDTO>{

	List<CamposDTO> getListaCamposDTOporIdPlantilla(Long idPlantilla);
}
