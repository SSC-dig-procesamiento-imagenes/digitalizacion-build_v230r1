package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PermisoPospuestasDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadorConfigDTO;

@Repository
public class PermisoPospuestasHDAOImpl extends BaseDaoHibernate<PermisoPospuestasDTO>  implements PermisoPospuestasHDAO {

	@Override
	public Boolean puedeEvaluarPospuestas(String cdPerfil) {
		String queryJPA = ""
				+ "	SELECT pp	"
				+ "	FROM PermisoPospuestasDTO pp "
				+ "	WHERE pp.idPerfil.cdPerfil = :cdPerfil";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("cdPerfil", cdPerfil);
		
		Boolean retorno = query.list().size() >= 0 ? true : false;
		
		return retorno;
	}

	

}
