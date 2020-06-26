package mx.com.teclo.digitalizacion.negocio.utils.generaexcel.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PeticionReporteVO;
import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PropiedadMultilenguajeVO;

/**
 * 
 * @author UnitisDes0416
 *
 */
public class ReporteExcelGenerico {
	
	/**
	 * @author UnitisDes0416
	 * @param peticioReporteVO PeticioReporteVO
	 * @return ByteArrayOutputStream
	 * @throws IOException
	 */
	public ByteArrayOutputStream generaReporte(PeticionReporteVO peticioReporteVO, PropiedadMultilenguajeVO propsMultilenguaje) throws IOException{
		UtileriaArchivos utileriaArchivos = new UtileriaArchivos();
		ReporteExcelXLSImpl reporteExcelXLSImpl = new ReporteExcelXLSImpl();
		ReporteExcelXLSXImpl ReporteExcelXLSXImpl = new ReporteExcelXLSXImpl();
		ByteArrayOutputStream response = new ByteArrayOutputStream();
		File archivoExcel = null;
		FileOutputStream archivoSave = null;
		XSSFWorkbook reporteXLSX = null;
		HSSFWorkbook reporteXLS = null;
		String dirArchivo = null;
		
		
		if(StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getRutaArchivo())){
			utileriaArchivos.validaDirectoriosRutaArchivo(peticioReporteVO.getPropiedadesReporte().getRutaArchivo());
			dirArchivo = peticioReporteVO.getPropiedadesReporte().getRutaArchivo()+peticioReporteVO.getPropiedadesReporte().getNombreReporte()+peticioReporteVO.getPropiedadesReporte().getExtencionArchvio();
			archivoExcel = new File(dirArchivo);
			archivoExcel.createNewFile();
			archivoSave = new FileOutputStream(archivoExcel);
		}
		
		if(peticioReporteVO.getPropiedadesReporte().getExtencionArchvio().equalsIgnoreCase(".xls")){
			
			reporteXLS = reporteExcelXLSImpl.generaReporteXLS(peticioReporteVO, propsMultilenguaje);
		}else if(peticioReporteVO.getPropiedadesReporte().getExtencionArchvio().equalsIgnoreCase(".xlsx")){
			reporteXLSX = ReporteExcelXLSXImpl.generaReporteXLSX(peticioReporteVO, propsMultilenguaje);
		}
		
		if(reporteXLS != null && StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getRutaArchivo())){
			reporteXLS.write(archivoSave);
			reporteXLS.write(response);
			archivoSave.close();
		}else if(reporteXLSX != null && StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getRutaArchivo())){
			reporteXLSX.write(archivoSave);
			reporteXLSX.write(response);
			archivoSave.close();
		}else if(reporteXLS != null && StringUtils.isBlank(peticioReporteVO.getPropiedadesReporte().getRutaArchivo())){
			reporteXLS.write(response);
		}else if(reporteXLSX != null && StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getRutaArchivo())){
			reporteXLSX.write(response);
		}
		
		return response;
	}

	public ByteArrayOutputStream plopezGeneraReporte(PeticionReporteVO peticioReporteVO, List<PeticionReporteVO> hojas,
			PropiedadMultilenguajeVO propsMultilenguaje) throws IOException {
		UtileriaArchivos utileriaArchivos = new UtileriaArchivos();
		ReporteExcelXLSImpl reporteExcelXLSImpl = new ReporteExcelXLSImpl();
		ReporteExcelXLSXImpl ReporteExcelXLSXImpl = new ReporteExcelXLSXImpl();
		ByteArrayOutputStream response = new ByteArrayOutputStream();
		File archivoExcel = null;
		FileOutputStream archivoSave = null;
		XSSFWorkbook reporteXLSX = null;
		HSSFWorkbook reporteXLS = null;
		String dirArchivo = null;
		
		
		if(StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getRutaArchivo())){
			utileriaArchivos.validaDirectoriosRutaArchivo(peticioReporteVO.getPropiedadesReporte().getRutaArchivo());
			dirArchivo = peticioReporteVO.getPropiedadesReporte().getRutaArchivo()+peticioReporteVO.getPropiedadesReporte().getNombreReporte()+peticioReporteVO.getPropiedadesReporte().getExtencionArchvio();
			archivoExcel = new File(dirArchivo);
			archivoExcel.createNewFile();
			archivoSave = new FileOutputStream(archivoExcel);
		}
		
		if(peticioReporteVO.getPropiedadesReporte().getExtencionArchvio().equalsIgnoreCase(".xls")){
			
			reporteXLS = reporteExcelXLSImpl.plopezGeneraReporteXLS(peticioReporteVO, hojas, propsMultilenguaje);
		}else if(peticioReporteVO.getPropiedadesReporte().getExtencionArchvio().equalsIgnoreCase(".xlsx")){
			reporteXLSX = ReporteExcelXLSXImpl.generaReporteXLSX(peticioReporteVO, propsMultilenguaje);
		}
		
		if(reporteXLS != null && StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getRutaArchivo())){
			reporteXLS.write(archivoSave);
			reporteXLS.write(response);
			archivoSave.close();
		}else if(reporteXLSX != null && StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getRutaArchivo())){
			reporteXLSX.write(archivoSave);
			reporteXLSX.write(response);
			archivoSave.close();
		}else if(reporteXLS != null && StringUtils.isBlank(peticioReporteVO.getPropiedadesReporte().getRutaArchivo())){
			reporteXLS.write(response);
		}else if(reporteXLSX != null && StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getRutaArchivo())){
			reporteXLSX.write(response);
		}
		
		return response;

	}
}
