package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.BitacoraCambiosVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ParametrosBusquedaReporteBitacoraVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.TdpBitacoraCambiosDTO;

public interface TdpBitacoraCambiosHDAO extends BaseDao<TdpBitacoraCambiosDTO>{

	List<BitacoraCambiosVO> getCambiosByParametros(ParametrosBusquedaReporteBitacoraVO parametrosBusquedaVO);

}
