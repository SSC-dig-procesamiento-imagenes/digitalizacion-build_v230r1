package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.estatusproceso;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.estatusproceso.EstatusProcesoDTO;

@Repository
public class ProcesoEstatusDAOImpl extends BaseDaoHibernate<EstatusProcesoDTO> implements ProcesoEstatusDAO {

	@Override
	public List<EstatusProcesoDTO> getEstatusDigitalizacion() {
		String queryJPA ="SELECT estatus FROM EstatusProcesoDTO estatus WHERE estatus.idEstatusProceso=1 OR estatus.idEstatusProceso=2 OR estatus.idEstatusProceso=3 OR estatus.idEstatusProceso=7";
		
		Query query = getCurrentSession().createQuery(queryJPA);
		List<EstatusProcesoDTO> listaRetorno = query.list();
		
		return listaRetorno;
	}
}
