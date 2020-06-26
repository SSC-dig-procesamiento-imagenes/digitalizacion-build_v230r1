package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;

import java.util.List;

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

	@Override
	public List<EstatusProcesoDTO> getEstatusPertinentesLiberacion() {
		String queryJPA =
				"	SELECT ep  																						"
			  + "	FROM EstatusProcesoDTO ep 																		"
			  + "	WHERE ep.cdEstatusProceso IN ('PROCESADO', 'LIBERADO', 'VALINFORMACION',						"
			  + "									'FORMLIBERACION','ENLIBERACION','CANCELADO', 'INFOVALIDADA'	)	";
		Query query = getCurrentSession().createQuery(queryJPA);
		List<EstatusProcesoDTO> listaRetornar = query.list();
		
		return listaRetornar;
	}
	
	@Override
	public List<EstatusProcesoDTO> getEstatus() {
		String queryJPA =
				"	SELECT ep  																						"
			  + "	FROM EstatusProcesoDTO ep 																		";
		Query query = getCurrentSession().createQuery(queryJPA);
		List<EstatusProcesoDTO> listaRetornar = query.list();
		
		return listaRetornar;
	}
}
