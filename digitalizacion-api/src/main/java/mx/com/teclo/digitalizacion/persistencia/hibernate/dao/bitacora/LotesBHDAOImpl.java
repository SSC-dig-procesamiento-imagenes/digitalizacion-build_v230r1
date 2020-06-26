package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesBDTO;

@Repository
public class LotesBHDAOImpl extends BaseDaoHibernate<LotesBDTO> implements LotesBHDAO {

	@Override
	public List<LotesQuery> getLotesParaExcel(List<Long> idLotes) {
		List<LotesQuery> listaRetorno = new ArrayList<>();
		String queryJPA = "	SELECT NEW 	mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery( 							"
				+ " 					lote.idLote , lote.fhCreacionLote , lote.nuTotImagenes , lote.nuTotImgAceptadas ,	"
				+ "						lote.nuTotImgRechazadas , lote.idStatProceso.idStatProceso , 						"
				+ "						lote.idStatProceso.cdEstatusProceso, lote.idStatProceso.nombreEstatus,				"
				+ "						lote.idStatProceso.descripcionStatus, lote.nbLote, lote.nuFolioInicial,				"
				+ "						lote.nuFolioFinal )																	"
				+ "			FROM LotesBDTO lote 																			"
				+ "			WHERE lote.idLote  IN																			";
		String queryIds = " ( ";
		String queryOrderBy = "	ORDER BY lote.idLote																		";
		
		for(int i = 0; i < idLotes.size(); i++) {
			Long id = idLotes.get(i);
			if(i != idLotes.size() - 1) {
				queryIds = queryIds + id + " , ";
			}else {
				queryIds = queryIds + id + " ) ";
			}
		}
		
		queryJPA = queryJPA + queryIds + queryOrderBy;
		
		Query query = getCurrentSession().createQuery(queryJPA);
		listaRetorno = query.list();
		
		return listaRetorno;
	}

	
}
