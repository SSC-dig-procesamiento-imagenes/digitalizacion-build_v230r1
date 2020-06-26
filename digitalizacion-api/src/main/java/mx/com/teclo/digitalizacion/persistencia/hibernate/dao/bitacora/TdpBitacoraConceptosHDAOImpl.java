package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.TdpBitacoraConceptosDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.VehiculoMarcaDTO;

@Repository
public class TdpBitacoraConceptosHDAOImpl extends BaseDaoHibernate<TdpBitacoraConceptosDTO>  
											implements TdpBitacoraConceptosHDAO {

	@Override
	public List<TdpBitacoraConceptosDTO> getConceptosByIdComponente(Long componenteId) {
		List<TdpBitacoraConceptosDTO> listaRetorno = new ArrayList<>();
		String queryJPA = ""
				+ "SELECT concepto											"
				+ "FROM TdpBitacoraConceptosDTO concepto					"
				+ "WHERE concepto.componenteID.componenteId =:componenteId	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("componenteId", componenteId);
		
		listaRetorno = query.list();
		
		return listaRetorno;
	}

	
	
}
