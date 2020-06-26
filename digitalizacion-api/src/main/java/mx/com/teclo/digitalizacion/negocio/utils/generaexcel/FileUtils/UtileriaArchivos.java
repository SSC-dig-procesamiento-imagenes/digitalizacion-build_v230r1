package mx.com.teclo.digitalizacion.negocio.utils.generaexcel.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author UnitisDes0416
 *
 */
public class UtileriaArchivos {
	/**
	 * @author UnitisDes0416 
	 * @param rutaArchvio
	 * @return Boolean
	 */
	public Boolean validaDirectoriosRutaArchivo(String rutaArchvio) {
		File f = new File(rutaArchvio);
		if (!f.exists()) {
			if (!f.mkdirs()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param format String
	 * @param fecha Date
	 * @return String
	 */
	public String getStringDateFromFormat(String format,Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(fecha);
	}
}
