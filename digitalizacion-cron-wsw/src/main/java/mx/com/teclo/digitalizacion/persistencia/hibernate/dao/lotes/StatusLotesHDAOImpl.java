package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresEstadosLotes;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstatusProcesoDTO;

@Repository
public class StatusLotesHDAOImpl extends BaseDaoHibernate<EstatusProcesoDTO> implements StatusLotesHDAO {

	public EstatusProcesoDTO getEstatus(ValoresEstadosLotes valorEstado) {
		String queryJPA = "	SELECT estatusDTO 								"
				+ "			FROM EstatusProcesoDTO estatusDTO					"
				+ "			WHERE estatusDTO.cdEstatusProceso = :valorEstado ";
		
		Query query = getCurrentSession().createQuery(queryJPA)
		.setParameter("valorEstado", valorEstado.getValor());
		
		EstatusProcesoDTO estadoRetorno = (EstatusProcesoDTO) query.list().get(0);
				
		return estadoRetorno;
	}
}
