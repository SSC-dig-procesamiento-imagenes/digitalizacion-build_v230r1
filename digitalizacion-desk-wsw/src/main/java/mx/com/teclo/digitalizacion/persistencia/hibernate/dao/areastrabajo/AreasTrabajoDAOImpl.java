package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.areastrabajo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.areastrabajo.AreasTrabajoDTO;

@Repository
public class AreasTrabajoDAOImpl extends BaseDaoHibernate<AreasTrabajoDTO> implements AreasTrabajoDAO {
	
	@SuppressWarnings("unchecked")
	public List<AreasTrabajoDTO> obtnerListaAreasTrabajo (Long idPlantilla){
		Criteria criteria = getCurrentSession().createCriteria(AreasTrabajoDTO.class);
		
		criteria.createAlias("plantillas", "plantillas");
		
		criteria.add(Restrictions.eqOrIsNull("plantillas.idPlantilla", idPlantilla));
		
		return criteria.list();
	}
}
