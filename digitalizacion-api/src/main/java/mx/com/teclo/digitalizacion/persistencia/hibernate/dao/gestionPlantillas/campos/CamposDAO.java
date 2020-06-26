package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.gestionPlantillas.campos;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CamposDTO;


public interface CamposDAO extends BaseDao<CamposDTO>{
	
	public CamposDTO getCampoByName(String caracter);

	public List<CamposDTO> getCampoByEstatus(String estatus);

	public List<CamposDTO> getCampoByConsidencia(String valor);

	public List <CamposDTO>  getCampoAll();

}
