package mx.com.teclo.digitalizacion.negocio.servicios.plantillas;

import mx.com.teclo.algorithms.vo.MarcasWebVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.plantillas.ParametrosAreaTrabajo;

public interface PlantillaService {
 public MarcasWebVO obtenerMarcasValidasPx(ParametrosAreaTrabajo parametrosAreaTrabajo) throws Exception;
}
		