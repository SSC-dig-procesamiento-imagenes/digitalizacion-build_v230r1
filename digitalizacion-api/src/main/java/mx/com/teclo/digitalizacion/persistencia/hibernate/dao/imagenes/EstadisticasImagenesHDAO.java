package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.negocio.vo.estadisticas.FechasConsultasVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstadisticasImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;

public interface EstadisticasImagenesHDAO extends BaseDao<EstadisticasImagenesDTO>{
	List<Object[]> getCantidadEvaluacionesPorUsuario(String fechaInicialStr, String fechaFinalStr);
	
	List<Object[]> getCantidadEvaluacionesPorUsuarioOld(String fechaInicialStr, String fechaFinalStr);

	List<Object[]> getCantidadEvaluacionesPorUsuario();
	
}
