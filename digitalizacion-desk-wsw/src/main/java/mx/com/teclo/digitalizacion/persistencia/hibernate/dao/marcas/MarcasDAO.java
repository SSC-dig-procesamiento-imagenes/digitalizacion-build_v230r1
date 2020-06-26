package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.marcas;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.marcas.MarcasDTO;

public interface MarcasDAO extends BaseDao<MarcasDTO>{
	
	List<MarcasDTO> buscarMarcasPorPlatilla (Long IdPlantilla);
}
