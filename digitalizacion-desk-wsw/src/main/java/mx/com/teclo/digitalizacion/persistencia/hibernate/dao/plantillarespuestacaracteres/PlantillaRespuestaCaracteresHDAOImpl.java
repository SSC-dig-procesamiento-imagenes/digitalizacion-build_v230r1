package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.plantillarespuestacaracteres;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.caracteres.CaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.plantillasrespuestacaracteres.PlantillaRespuestaCaracteresDTO;

@Repository
public class PlantillaRespuestaCaracteresHDAOImpl extends BaseDaoHibernate<PlantillaRespuestaCaracteresDTO> implements PlantillaRespuestaCaracteresHDAO{

	@Override
	public List<CaracteresDTO> getCaracteresPorIdPlantillaRespuesta(Long idPlantillaRespuesta) {
		String queryJPA=
				" SELECT pr.caracteresId														"
			  + " FROM PlantillaRespuestaCaracteresDTO pr										"
			  + " WHERE pr.plantillasrespuestasId.idPlantillaRespuesta = :idPlantillaRespuesta	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idPlantillaRespuesta", idPlantillaRespuesta);
		
		List<CaracteresDTO> listaRetorno = query.list();

		return listaRetorno;
	}

	
	
}
