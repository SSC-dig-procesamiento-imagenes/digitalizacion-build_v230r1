package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;

import java.util.Date;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ArticulosInfracFinanzasDTO;

public interface ArticulosInfracFinanzasHDAO extends BaseDao<ArticulosInfracFinanzasDTO>{

	boolean getArticuloPorDatos(Date fecha, Long articulo, Long fraccion, String inciso, Long parrafo) throws Exception;
	
}
