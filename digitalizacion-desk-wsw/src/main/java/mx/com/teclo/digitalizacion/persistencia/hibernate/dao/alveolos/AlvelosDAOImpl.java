package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.alveolos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.alveolos.AlveolosDTO;

@Repository
public class AlvelosDAOImpl extends BaseDaoHibernate<AlveolosDTO> implements AlveolosDAO {

		@SuppressWarnings("unchecked")
		public List<AlveolosDTO> buscarAlbelosPlantilla (Long idPlantilla){
			Criteria criteria = getCurrentSession().createCriteria(AlveolosDTO.class);
			
			criteria.createAlias("plantillas", "plantillas");
			
			criteria.add(Restrictions.eq("plantillas.idPlantilla", idPlantilla));
			
			return criteria.list();
		}
}
