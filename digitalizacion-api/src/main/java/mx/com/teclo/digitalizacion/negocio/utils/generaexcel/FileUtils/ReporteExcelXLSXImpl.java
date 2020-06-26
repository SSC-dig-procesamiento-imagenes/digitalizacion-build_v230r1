package mx.com.teclo.digitalizacion.negocio.utils.generaexcel.FileUtils;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PeticionReporteVO;
import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PropiedadMultilenguajeVO;

/**
 * 
 * @author UnitisDes0416
 *
 */
public class ReporteExcelXLSXImpl {
	public static int LIMITE_HOJA = 1048556;
	/**
	 * @author UnitisDes0416
	 * @param peticioReporteVO PeticioReporteVO
	 * @return XSSFWorkbook
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public XSSFWorkbook generaReporteXLSX(PeticionReporteVO peticioReporteVO,PropiedadMultilenguajeVO propsMultilenguaje ){
		UtileriaArchivos utileriaArchivos = new UtileriaArchivos();
		CSSExcel cssExcel = new CSSExcel();
		XSSFWorkbook libro = new XSSFWorkbook();
		Sheet hoja = null;
		int numRegistros = 0;
		int numeroHojas = 0;
		List<Object> listaRegistros;
		int indiceRegistros;
		List<String> listaEncabezados;
		
		while(numRegistros < peticioReporteVO.getContenido().size()){
			listaRegistros = (List<Object>) peticioReporteVO.getContenido().get(numRegistros);
			indiceRegistros = 0;
			listaEncabezados = (List<String>) peticioReporteVO.getEncabezado().get(numRegistros);
			if((listaRegistros.size()%LIMITE_HOJA) == 0){
				numeroHojas = (listaRegistros.size()/LIMITE_HOJA) != 0 ? (listaRegistros.size()/LIMITE_HOJA) :1;
			}else{
				numeroHojas = (listaRegistros.size()/LIMITE_HOJA) != 0 ? (listaRegistros.size()/LIMITE_HOJA)+1 :1;
			}
			
			int hojas = 0;
			int numRegistro = 0;
			int numCotenido=0;
			int registrosPorHoja = 0;
			while(hojas < numeroHojas){
				hoja = libro.createSheet();
		        Integer numeroFila = 1;
		        
		        XSSFFont FONT_BOLDWEIGHT_BOLD_16 = cssExcel.FONT_BOLDWEIGHT_BOLD_16(libro);
		        XSSFFont FONT_BOLDWEIGHT_BOLD = cssExcel.FONT_BOLDWEIGHT_BOLD(libro);
		        
		        XSSFCellStyle styleTitulo = cssExcel.CELLSTYLE_ALIGN_CENTER(libro, FONT_BOLDWEIGHT_BOLD_16);
		        XSSFCellStyle styleSubTitulo = cssExcel.CELL_STYLE(libro, FONT_BOLDWEIGHT_BOLD);
		        XSSFCellStyle styleEncabezado = cssExcel.CELL_STYLE_SOLID_FOREGROUND_GREY_40(libro);
		        
		        Row filaTitulo = hoja.createRow(numeroFila);
		        filaTitulo.setHeightInPoints(18);
		                
		    	Cell celdaTitulo = filaTitulo.createCell(0);
			    celdaTitulo.setCellValue(peticioReporteVO.getPropiedadesReporte().getTituloExcel());
			    celdaTitulo.setCellStyle(styleTitulo);
		    	
		        hoja = cssExcel.MERGED_REGION(hoja, numeroFila, 0,listaEncabezados.size());
		        
		        if(StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getFechaI()) ||
		        		StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getFechaF())){
			        numeroFila = numeroFila + 2;
			        
			       
			    	//crea Fecha para reporte
			        Row filaFecha = hoja.createRow(numeroFila);
			        filaFecha.setHeightInPoints(16);
			        
			        Cell celdaFecha = filaFecha.createCell(0);
			        
			        if(StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getFechaI()) &&
			        		StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getFechaF())){
			        	celdaFecha.setCellValue(propsMultilenguaje.getTituloPeriodo()+ peticioReporteVO.getPropiedadesReporte().getFechaI() + 
			        			propsMultilenguaje.getTituloPeriodo() + peticioReporteVO.getPropiedadesReporte().getFechaF());
			        }else{
			        	celdaFecha.setCellValue(propsMultilenguaje.getTituloGenerico() + utileriaArchivos.getStringDateFromFormat("dd/MM/yyyy", new Date()));
			        }
			        celdaFecha.setCellStyle(styleSubTitulo);
			        
			        hoja = cssExcel.MERGED_REGION(hoja, numeroFila, 0, 10);
			        
			        numeroFila = numeroFila + 2;
		        }else{
		        	numeroFila = numeroFila + 2;
		        }
		        
		        if(StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getPersonaReporte())){
		        	//crea titulo con su estilo
		            Row filaNombrePersona = hoja.createRow(numeroFila);
		            filaNombrePersona.setHeightInPoints(16);
		            
		            Cell celdaNombrePersona = filaNombrePersona.createCell(0);
		            
		            celdaNombrePersona.setCellValue(peticioReporteVO.getPropiedadesReporte().getPersonaReporte());
		            celdaNombrePersona.setCellStyle(styleSubTitulo);
		            
		            hoja = cssExcel.MERGED_REGION(hoja, numeroFila, 0, 10);
		            
		            numeroFila = numeroFila + 2;
		        }
		        
		        if(peticioReporteVO.getPropiedadesReporte().getSubtitulos() != null){
			        if(!peticioReporteVO.getPropiedadesReporte().getSubtitulos().isEmpty()){
			        	for(String subtitulo : peticioReporteVO.getPropiedadesReporte().getSubtitulos()){
			        		Row filaSubtitulo = hoja.createRow(numeroFila);
			        		filaSubtitulo.setHeightInPoints(16);
			        		
			        		Cell celdaSubtitulo = filaSubtitulo.createCell(0);
			        		celdaSubtitulo.setCellValue(subtitulo);
			        		celdaSubtitulo.setCellStyle(styleSubTitulo);
			        		
			        		hoja = cssExcel.MERGED_REGION(hoja, numeroFila, 0, 10);
			        		
			        		numeroFila = numeroFila + 1;
			        	}
			        	
			        	numeroFila = numeroFila + 1;
			        }
		        }
		        
		        Row filaNumRegistros = hoja.createRow(numeroFila);
		        Cell celdasubtitulo1 = filaNumRegistros.createCell(0);
		        celdasubtitulo1.setCellValue(propsMultilenguaje.getTotalRegistros() + listaRegistros.size());
		        celdasubtitulo1.setCellStyle(styleSubTitulo);
		        numeroFila = numeroFila + 2;
		        
		        //sub titulos
		        Row filaEncabezados = hoja.createRow(numeroFila);
		        
		        int numEmcabezado = 0;
		        int celda = 0 ;
		        while(numEmcabezado < listaEncabezados.size()){
		        	Cell celdaEncazado = filaEncabezados.createCell(celda);
					celdaEncazado.setCellValue(listaEncabezados.get(numEmcabezado));
					celdaEncazado.setCellStyle(styleEncabezado);
					celda ++;
					numEmcabezado ++;
		        }
		        numeroFila++;
		        
		        Row filaRegistros;
		        int numeroCelda = 0;
		        List<String> listaRegistro;
		        while(numCotenido < listaRegistros.size() && registrosPorHoja < LIMITE_HOJA){
		        	listaRegistro = (List<String>) listaRegistros.get(numCotenido);
		        	filaRegistros = hoja.createRow(numeroFila);
		        	for(String registro : listaRegistro){
		        		filaRegistros.createCell(numeroCelda).setCellValue(registro);
		        		numeroCelda++;
		        	}
		        	numCotenido++;
		        	numeroCelda = 0;
		        	indiceRegistros++;
		        	numeroFila++;
		        	registrosPorHoja++;
		        }
		        registrosPorHoja = 0;
		        
		        numeroCelda = 0;
				for(String registro : (List<String>)listaRegistros.get(0)){
					hoja.autoSizeColumn(numeroCelda);
					numeroCelda++;
				}
				hojas++;
			}
			numRegistros++;
		}
		return libro;
	}
}
