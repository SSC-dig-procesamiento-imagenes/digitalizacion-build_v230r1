package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.*;

@Repository
public class ImagenesBHDAOImpl extends BaseDaoHibernate<ImagenesBDTO> implements ImagenesBHDAO {

	@Override
	public List<ImagenesBDTO> getImagenesBDTOByLote(Long idLote) {
		List<ImagenesBDTO> listaRetornar = new ArrayList<>();
		String queryJPA = "	SELECT img 					"
				+ "			FROM ImagenesBDTO img 		"
				+ "			WHERE img.idLote.idLote = :idLote  	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idLote", idLote);
		
		listaRetornar = query.list();
		
		return listaRetornar;
	}


	
	
}
