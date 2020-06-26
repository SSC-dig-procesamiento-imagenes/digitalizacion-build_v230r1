package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ArticulosInfracFinanzasDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.InfraccionesDigitalizacionDTO;

@Repository
public class InfraccionesDigitalizacionHDAOImpl extends BaseDaoHibernate<InfraccionesDigitalizacionDTO> implements InfraccionesDigitalizacionHDAO{

	@Override
	public Boolean existeBoleta(Long nuNumeroFolio) {
		String queryJPA =
				"	SELECT idi 								"
				+ "	FROM  InfraccionesDigitalizacionDTO idi	"
				+ "	WHERE idi.infracNum = :nuNumeroFolio	";
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("nuNumeroFolio", nuNumeroFolio.toString());
		
		
		return query.list().size() == 0 ? false : true;
	}

}
