package mx.com.teclo.digitalizacion.negocio.servicios.lotes;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import mx.com.teclo.digitalizacion.negocio.utils.ValoresFicheroExcel;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSinBlobVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.*;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;

public interface LotesService {
	List<LotesResultVO> getInformacionLotes();
	List<LotesResultVO> getInformacionLotes(Date fechaInicial, Date fechaFinal, Long idEstatus);
	LotesResultVO cancelarLote(Long idLote);
	LotesResultVO formarParaLiberarLote(Long idLote);
	List<EstatusProcesoVO> getTodosEstatus();
	LotesResultVO enValidandoInformacion(Long idLote);
	List<ImagenesSinBlobVO> getImagenesPorLote(Long idLoteSeleccionado);
	List<LotesQuery> getLotesPorIds(List<Long> idsLista, ValoresFicheroExcel valorFicheroExcel);
	ByteArrayOutputStream getFicheroExcel(List<Long> listaIds, String nombreReporte, ValoresFicheroExcel lotesb); 	
	void addCambiosLotes(LotesDTO lotesDTO, Long idUsuario);
	void addCambiosLotes(Long idLote, Long idUsuario);
}
