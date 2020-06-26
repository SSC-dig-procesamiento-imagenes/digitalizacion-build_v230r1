package mx.com.teclo.digitalizacion.negocio.servicios.lotes;
import java.util.Date;
import java.util.List;

import mx.com.teclo.digitalizacion.negocio.vo.lotes.*;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;

public interface LotesService {
	List<LotesResultVO> getInformacionLotes();
	List<LotesResultVO> getInformacionLotes(Date fechaInicial, Date fechaFinal, Long idEstatus);
	LotesResultVO cancelarLote(Long idLote);
	LotesResultVO formarParaLiberarLote(Long idLote);
	List<EstatusProcesoVO> getTodosEstatus();
	List<LotesDTO> getTodosLotesFormadosParaLiberar();
}
