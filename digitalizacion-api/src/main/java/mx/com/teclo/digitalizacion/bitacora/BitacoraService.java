package mx.com.teclo.digitalizacion.bitacora;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import mx.com.teclo.digitalizacion.negocio.utils.ValoresFicheroExcel;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.BitacoraCambiosVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ComboComponentesVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ComboConceptosVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ParametrosBusquedaReporteBitacoraVO;

public interface BitacoraService {

	public List<ComboComponentesVO> getComponentesBitacora();

	public List<ComboConceptosVO> getConceptosBitacora(Long componenteId);

	public List<BitacoraCambiosVO> getBitacoraCambios(ParametrosBusquedaReporteBitacoraVO parametrosBusquedaVO); 
	
	ByteArrayOutputStream getFicheroExcel(List<BitacoraCambiosVO> listaCambios, String nombreReporte, Date fechaInicio,
			Date fechaFin); 
	
}
