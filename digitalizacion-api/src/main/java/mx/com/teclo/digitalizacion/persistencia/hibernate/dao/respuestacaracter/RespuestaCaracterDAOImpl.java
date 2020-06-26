package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.respuestacaracter;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PlantillaRespuestaCaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PlantillasRespuestasDTO;
@Repository
public class RespuestaCaracterDAOImpl extends BaseDaoHibernate<PlantillaRespuestaCaracteresDTO> implements RespuestaCaracterDAO {

	@SuppressWarnings("unchecked")
	@Override
	public PlantillaRespuestaCaracteresDTO getCaracterByRespuesta(BigDecimal idRespuesta, BigDecimal idCaracter) {
		Criteria c = getCurrentSession().createCriteria(PlantillaRespuestaCaracteresDTO.class);
		c.createAlias("plantillasrespuestasId", "respuesta");
		c.createAlias("caracteresId", "caracter");
	  	c.add(Restrictions.eq("respuesta.id", idRespuesta));
	  	c.add(Restrictions.eq("respuesta.stActivo",1));
		c.add(Restrictions.eq("caracter.id", idCaracter));
		c.add(Restrictions.eq("caracter.stActivo",1));
		c.add(Restrictions.eq("stActivo", 1));
		return  (PlantillaRespuestaCaracteresDTO) c.uniqueResult();
	}
	
	
}
