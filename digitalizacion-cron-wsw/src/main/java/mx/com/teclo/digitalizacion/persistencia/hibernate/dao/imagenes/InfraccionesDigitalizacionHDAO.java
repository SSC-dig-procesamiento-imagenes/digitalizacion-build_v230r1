package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ArticulosInfracFinanzasDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.InfraccionesDigitalizacionDTO;

public interface InfraccionesDigitalizacionHDAO extends BaseDao< InfraccionesDigitalizacionDTO>{

	Boolean existeBoleta(Long nuNumeroFolio);
	

}
