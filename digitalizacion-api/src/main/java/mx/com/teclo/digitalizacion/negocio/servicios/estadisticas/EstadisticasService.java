package mx.com.teclo.digitalizacion.negocio.servicios.estadisticas;

import java.util.List;

import mx.com.teclo.digitalizacion.negocio.vo.estadisticas.*;

public interface EstadisticasService {
	List<ConsultaGeneralVO> getConsultaGeneral(String fechaInicialStr, String fechaFinalStr);

	List<ConsultaGeneralVO> getConsultaGeneral();
	
}
