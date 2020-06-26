package mx.com.teclo.digitalizacion.negocio.utils.generaexcel.reporteexcel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PeticionReporteVO;


/**
 * 
 * @author UnitisDes0416
 *
 */
public interface PeticionReporteBO {
	
	/**
	 *@author UnitisDes0416 
	 * @param peticioReporteVO PeticioReporteVO
	 * @return ByteArrayOutputStream
	 * @throws IOException
	 */
	public ByteArrayOutputStream generaReporteExcel(PeticionReporteVO peticioReporteVO) throws IOException;
}
