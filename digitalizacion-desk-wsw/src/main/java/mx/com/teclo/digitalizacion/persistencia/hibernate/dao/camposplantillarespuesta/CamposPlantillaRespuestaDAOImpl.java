package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.camposplantillarespuesta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.camposplantillarespuesta.CamposPlantillaRespuestaDTO;

@Repository
public class CamposPlantillaRespuestaDAOImpl extends BaseDaoHibernate<CamposPlantillaRespuestaDTO> implements CamposPlantillaRespuestaDAO {
	
	@SuppressWarnings("unchecked")
	public List<CamposPlantillaRespuestaDTO> buscaTodosLosCampos(Long idPlantilla){
		Criteria criteria = getCurrentSession().createCriteria(CamposPlantillaRespuestaDTO.class);
		
		criteria.createAlias("plantillaRespuesta", "plantillaRespuesta");
		
		criteria.add(Restrictions.eq("plantillaRespuesta.idPlantillaRespuesta", idPlantilla));
		
		return criteria.list();
	}

	@Override
	public List<CamposPlantillaRespuestaDTO> getTodosCamposPlantillasRespuestaDTOporIdCampo(Long idCampo) {
		String queryJPA =
				"	SELECT cp								"
				+ "	FROM  CamposPlantillaRespuestaDTO cp	"
				+ "	WHERE cp.campos.idCampos = :idCampo 	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idCampo", idCampo);
		List<CamposPlantillaRespuestaDTO> listaRetornar = query.list();
		
		return listaRetornar;
	}
	
	
}
