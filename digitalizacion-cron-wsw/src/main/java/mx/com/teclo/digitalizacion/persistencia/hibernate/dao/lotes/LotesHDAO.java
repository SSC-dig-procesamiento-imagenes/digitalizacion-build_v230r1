package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;

import java.util.Date;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresEstadosLotes;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstatusProcesoDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery;

public interface LotesHDAO extends BaseDao<LotesDTO>{
	boolean isCancelable(LotesDTO loteDTO);
	void incrementaCantidadImagenesRechazadas(LotesDTO loteDTO) throws Exception;
	void incrementaCantidadImagenesAceptadas(LotesDTO loteDTO) throws Exception;
	LotesDTO cancelarLote(LotesDTO loteDTO);
	List<LotesQuery> getQueryGeneral();
	List<LotesQuery> getQueryAvanzada(Date fechaInicial, Date fechaFinal, Long idEstatusLote);
	void setEstatus(LotesDTO loteDTO, ValoresEstadosLotes valorEstado);
	void setEstatus(LotesDTO loteDTO, EstatusProcesoDTO estatusProcesoDTO);
	void formarParaLiberacion(LotesDTO loteDTO);
	void actualizaEstatusLotesDespuesSeleccion(List<LotesDTO> lotesDTO);
	void ponerEnLiberacion(LotesDTO loteDTO) throws Exception;
	LotesDTO formarParaLiberarLote(LotesDTO loteDTO);
	List<LotesDTO> getLotesPorEstado(ValoresEstadosLotes valorEstado);
	         
	
}
