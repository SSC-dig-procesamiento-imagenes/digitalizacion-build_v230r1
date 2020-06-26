package mx.com.teclo.digitalizacion.service.plantillas;

import mx.com.teclo.digitalizacion.persistencia.vo.plantillas.PlantillaCompletaVO;
import mx.com.teclo.digitalizacion.persistencia.vo.plantillasolicitada.PlantillaSolicitadaVO;

public interface PlantillasService {
	
	PlantillaCompletaVO obtenerPlantillaPorIdentificador(Long platillaId);
	PlantillaSolicitadaVO obtenerPlantillaSolicitadaVOPorId(Long platillaId);
}
