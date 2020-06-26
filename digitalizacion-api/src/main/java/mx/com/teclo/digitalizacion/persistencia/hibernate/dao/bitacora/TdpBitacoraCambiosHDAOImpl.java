package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.BitacoraCambiosVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ParametrosBusquedaReporteBitacoraVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.TdpBitacoraCambiosDTO;

@Repository
public class TdpBitacoraCambiosHDAOImpl extends BaseDaoHibernate<TdpBitacoraCambiosDTO>  implements TdpBitacoraCambiosHDAO {

	@Override
	public List<BitacoraCambiosVO> getCambiosByParametros(ParametrosBusquedaReporteBitacoraVO parametrosBusquedaVO) {
		List<BitacoraCambiosVO> listaRetorno = new ArrayList<>();
		String listaIdConceptos = "";
		int sizeList = parametrosBusquedaVO.getConceptosId().size();
		for (int i = 0; i < sizeList; i++) {
			String cad = parametrosBusquedaVO.getConceptosId().get(i);
			if(i == sizeList - 1) {
				listaIdConceptos += cad ;
			}
			else {
				listaIdConceptos += cad +",";
			}
		}
		String queryJPA = ""
				+ "SELECT new mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.BitacoraCambiosVO(	"
				+ "		cambio.cambioId, cambio.bitacoraConceptosDTO.componenteID.componenteNombre, 		"
				+ "		cambio.bitacoraConceptosDTO.conceptoNombre,											"
				+ "		cambio.valorOriginal, cambio.valorFinal, cambio.idRegistroDescripcion,				"
				+ "		cambio.idRegistro, "
				+ "		cambio.creadoPor.nombreUsuario, cambio.creadoPor.apellidoPaterno, "
				+ "		cambio.fechaCreacion					"
				+ ")																						"
				+ "FROM TdpBitacoraCambiosDTO cambio														"
				+ "WHERE  cambio.bitacoraConceptosDTO.conceptoId IN ("+ listaIdConceptos +") 				"
						+ "AND cambio.bitacoraConceptosDTO.componenteID.componenteId = :componenteId		"
						+ "AND (cambio.fechaCreacion BETWEEN :fechaInicio AND :fechaFin )";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("componenteId", parametrosBusquedaVO.getComponenteId())
				.setParameter("fechaInicio", parametrosBusquedaVO.getFechaInicio())
				.setParameter("fechaFin", parametrosBusquedaVO.getFechaFin());
		
						
		listaRetorno = query.list();
		
		return listaRetorno;
	}

}
