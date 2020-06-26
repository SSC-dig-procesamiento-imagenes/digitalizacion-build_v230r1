package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.caracteres;

import java.math.BigDecimal;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CaracteresDTO;

public interface CaracteresDAO extends BaseDao<CaracteresDTO> {

	public CaracteresDTO getCaracterByName(String caracter);

	public List<CaracteresDTO> getCaracterByEstatus(String estatus);

	public List<CaracteresDTO> getCaracterByConsidencia(String valor);

	public   List <CaracteresDTO>  getCaracterAll();
	
	public List<CaracteresDTO> getCaracterByRespuesta(BigDecimal idRespuesta);
	
}
