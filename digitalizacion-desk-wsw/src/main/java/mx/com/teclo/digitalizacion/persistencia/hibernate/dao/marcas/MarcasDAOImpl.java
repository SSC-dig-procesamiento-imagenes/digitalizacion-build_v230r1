package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.marcas;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.marcas.MarcasDTO;

@Repository
public class MarcasDAOImpl extends BaseDaoHibernate<MarcasDTO> implements MarcasDAO {

	@SuppressWarnings("unchecked")
	public List<MarcasDTO> buscarMarcasPorPlatilla (Long IdPlantilla){
		Criteria criteria = getCurrentSession().createCriteria(MarcasDTO.class);
		
		criteria.createAlias("plantillas", "plantillas");
		
		criteria.add(Restrictions.eq("plantillas.idPlantilla", IdPlantilla));
		
		return criteria.list();
	}
}
