package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.campos;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.campos.CamposDTO;

@Repository
public class CamposHDAOImpl extends BaseDaoHibernate<CamposDTO> implements CamposHDAO{

	@Override
	public List<CamposDTO> getListaCamposDTOporIdPlantilla(Long idPlantilla) {
		String queryJPA =
			  "	SELECT campo 										"
			+ "	FROM CamposDTO campo 								"
			+ "	WHERE campo.idPlantilla.idPlantilla = :idPlantilla 	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idPlantilla", idPlantilla);

		List<CamposDTO> listaRetorno = query.list();
		
		return listaRetorno;
	}

	
}
