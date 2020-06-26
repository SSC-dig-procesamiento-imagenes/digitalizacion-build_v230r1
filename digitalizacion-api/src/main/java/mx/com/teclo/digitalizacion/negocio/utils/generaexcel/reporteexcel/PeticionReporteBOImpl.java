package mx.com.teclo.digitalizacion.negocio.utils.generaexcel.reporteexcel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.FileUtils.ReporteExcelGenerico;
import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.util.enums.Mensajes;
import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PeticionReporteVO;
import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PropiedadMultilenguajeVO;


/**
 * 
 * @author UnitisDes0416
 *
 */
public class PeticionReporteBOImpl{
	
	/**
	 * @author UnitisDes0416
	 * @param peticioReporteVO PeticioReporteVO
	 * @return ByteArrayOutputStream
	 * @throws IOException
	 */
	public ByteArrayOutputStream generaReporteExcel(PeticionReporteVO peticioReporteVO) throws IOException{
		ReporteExcelGenerico reporteExcelGenerico = new ReporteExcelGenerico();
		PropiedadMultilenguajeVO propsMultilenguaje= new PropiedadMultilenguajeVO(Mensajes.TOTAL_REGISTROS.getMensaje(),Mensajes.TITULO_GENERICO.getMensaje(),Mensajes.PREPOSICION_PERIODO.getMensaje(),Mensajes.TITULO_PERIODO.getMensaje());
		return reporteExcelGenerico.generaReporte(peticioReporteVO,propsMultilenguaje);
	}
	
	public ByteArrayOutputStream plopezGeneraReporteExcel(PeticionReporteVO peticioReporteVO, List<PeticionReporteVO> hojas) throws IOException{
		ReporteExcelGenerico reporteExcelGenerico = new ReporteExcelGenerico();
		PropiedadMultilenguajeVO propsMultilenguaje= new PropiedadMultilenguajeVO(Mensajes.TOTAL_REGISTROS.getMensaje(),Mensajes.TITULO_GENERICO.getMensaje(),Mensajes.PREPOSICION_PERIODO.getMensaje(),Mensajes.TITULO_PERIODO.getMensaje());
		return reporteExcelGenerico.plopezGeneraReporte(peticioReporteVO, hojas ,propsMultilenguaje);
	}
	
	/**
	 * @author Fjmb
	 * @param peticioReporteVO PeticioReporteVO
	 * @param propiedadMultilenguajeVO PropsMultilenguaje 
	 * @return ByteArrayOutputStream
	 * @throws IOException
	 */
	public ByteArrayOutputStream generaReporteExcelMultilenguaje(PeticionReporteVO peticioReporteVO, PropiedadMultilenguajeVO propsMultilenguaje ) throws IOException{
		ReporteExcelGenerico reporteExcelGenerico = new ReporteExcelGenerico();
		return reporteExcelGenerico.generaReporte(peticioReporteVO ,propsMultilenguaje);
	}
}
