package mx.com.teclo.digitalizacion.service.lote;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.digitalizacion.persistencia.vo.estatusproceso.EstatusProcesoVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.ActualizarFoliosVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.ActualizarLoteVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.LoteVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.PersistirLoteLoteVO;

public interface LotesService {
	
	public List<LoteVO> getLotesAll();
	
	public List<LoteVO> getLotesPorParametros(Long idstat,String fechaIni,String fechaFin);
	
	LoteVO guadarLote(PersistirLoteLoteVO persistirLoteLoteVO, Long idUsuario);
	
	void actualizarLote(ActualizarLoteVO actualizarLoteVO, Long idUsuario);
	
	List<EstatusProcesoVO> getTodosEstatus();
	List<EstatusProcesoVO> getTodosEstatusDigitalizacion();

	public ByteArrayOutputStream getBoletasPorLoteExcel(Long idLote, String nombreReporte);

	public void actualizarFolios(ActualizarFoliosVO actualizarFoliosVO, Long modificadoPor);

}
