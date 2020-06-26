package mx.com.teclo.digitalizacion.negocio.utils.generaexcel.FileUtils;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PeticionReporteVO;
import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PropiedadMultilenguajeVO;


/**
 * 
 * @author UnitisDes0416
 *
 */
public class ReporteExcelXLSImpl {
	public static int LIMITE_HOJA = 64980;
	/**
	 * @author UnitisDes0416
	 * @param peticioReporteVO PeticioReporteVO
	 * @return HSSFWorkbook
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public HSSFWorkbook generaReporteXLS(PeticionReporteVO peticioReporteVO, PropiedadMultilenguajeVO propsMultilenguaje){
		UtileriaArchivos utileriaArchivos = new UtileriaArchivos();
		CSSExcel cssExcel = new CSSExcel();
		HSSFWorkbook libro = new HSSFWorkbook();
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
		        
		        HSSFFont FONT_BOLDWEIGHT_BOLD_16 = cssExcel.FONT_BOLDWEIGHT_BOLD_16(libro);
		        HSSFFont FONT_BOLDWEIGHT_BOLD = cssExcel.FONT_BOLDWEIGHT_BOLD(libro);
		        
		        HSSFCellStyle styleTitulo = cssExcel.CELLSTYLE_ALIGN_CENTER(libro, FONT_BOLDWEIGHT_BOLD_16);
		        HSSFCellStyle styleSubTitulo = cssExcel.CELL_STYLE(libro, FONT_BOLDWEIGHT_BOLD);
		        HSSFCellStyle styleEncabezado = cssExcel.CELL_STYLE_SOLID_FOREGROUND_GREY_40(libro);
		        
		        Row filaTitulo = hoja.createRow(numeroFila);
		        filaTitulo.setHeightInPoints(18);
		        
		    	Cell celdaTitulo = filaTitulo.createCell(0);
			    celdaTitulo.setCellValue(peticioReporteVO.getPropiedadesReporte().getTituloExcel());
			    celdaTitulo.setCellStyle(styleTitulo);
		    	
		        hoja = cssExcel.MERGED_REGION(hoja, numeroFila, 0, listaEncabezados.size());
		        
		        if(StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getFechaI()) ||
		        		StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getFechaF())){
			        numeroFila = numeroFila + 2;
			        
			       
			    	//crea Fecha para reporte
			        Row filaFecha = hoja.createRow(numeroFila);
			        filaFecha.setHeightInPoints(16);
			        
			        Cell celdaFecha = filaFecha.createCell(0);
			        
			        if(StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getFechaI()) &&
			        		StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getFechaF())){
			        	celdaFecha.setCellValue(propsMultilenguaje.getTituloPeriodo() + peticioReporteVO.getPropiedadesReporte().getFechaI() + 
			        			propsMultilenguaje.getTituloPreposicionPeriodo() + peticioReporteVO.getPropiedadesReporte().getFechaF());
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
		        celdasubtitulo1.setCellValue(propsMultilenguaje.getTotalRegistros()+ listaRegistros.size());
		        celdasubtitulo1.setCellStyle(styleSubTitulo);
		        numeroFila = numeroFila + 2;
		        
		        //sub titulos
		        Row filaEncabezados = hoja.createRow(numeroFila);
		        
		        int numEmcabezado = 0;
		        int celda = 0 ;
		        while(numEmcabezado < listaEncabezados.size() ){
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
	
	
	@SuppressWarnings("unchecked")
	public HSSFWorkbook plopezGeneraReporteXLS(PeticionReporteVO peticioReporteVO, List<PeticionReporteVO> demasHojas,
			PropiedadMultilenguajeVO propsMultilenguaje) {
		
		UtileriaArchivos utileriaArchivos = new UtileriaArchivos();
		CSSExcel cssExcel = new CSSExcel();
		HSSFWorkbook libro = new HSSFWorkbook();
		Sheet hoja = null;
		Sheet hojaDetalles = null;
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
				hoja = libro.createSheet("LOTES");
			
		        Integer numeroFila = 1;
		        
		        HSSFFont FONT_BOLDWEIGHT_BOLD_16 = cssExcel.FONT_BOLDWEIGHT_BOLD_16(libro);
		        HSSFFont FONT_BOLDWEIGHT_BOLD = cssExcel.FONT_BOLDWEIGHT_BOLD(libro);
		        
		        HSSFCellStyle styleTitulo = cssExcel.CELLSTYLE_ALIGN_CENTER(libro, FONT_BOLDWEIGHT_BOLD_16);
		        HSSFCellStyle styleSubTitulo = cssExcel.CELL_STYLE(libro, FONT_BOLDWEIGHT_BOLD);
		        HSSFCellStyle styleEncabezado = cssExcel.CELL_STYLE_SOLID_FOREGROUND_GREY_40(libro);
		        
		        Row filaTitulo = hoja.createRow(numeroFila);
		        filaTitulo.setHeightInPoints(18);
		        
		    	Cell celdaTitulo = filaTitulo.createCell(0);
			    celdaTitulo.setCellValue(peticioReporteVO.getPropiedadesReporte().getTituloExcel());
			    celdaTitulo.setCellStyle(styleTitulo);
		    	
		        hoja = cssExcel.MERGED_REGION(hoja, numeroFila, 0, listaEncabezados.size());
		        
		        if(StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getFechaI()) ||
		        		StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getFechaF())){
			        numeroFila = numeroFila + 2;
			        
			       
			    	//crea Fecha para reporte
			        Row filaFecha = hoja.createRow(numeroFila);
			        filaFecha.setHeightInPoints(16);
			        
			        Cell celdaFecha = filaFecha.createCell(0);
			        
			        if(StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getFechaI()) &&
			        		StringUtils.isNotBlank(peticioReporteVO.getPropiedadesReporte().getFechaF())){
			        	celdaFecha.setCellValue(propsMultilenguaje.getTituloPeriodo() + peticioReporteVO.getPropiedadesReporte().getFechaI() + 
			        			propsMultilenguaje.getTituloPreposicionPeriodo() + peticioReporteVO.getPropiedadesReporte().getFechaF());
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
		        celdasubtitulo1.setCellValue(propsMultilenguaje.getTotalRegistros()+ listaRegistros.size());
		        celdasubtitulo1.setCellStyle(styleSubTitulo);
		        numeroFila = numeroFila + 2;
		        
		        //sub titulos
		        Row filaEncabezados = hoja.createRow(numeroFila);
		        
		        int numEmcabezado = 0;
		        int celda = 0 ;
		        while(numEmcabezado < listaEncabezados.size() ){
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
		numRegistros = 0;
		
		for(PeticionReporteVO hojaDetalle : demasHojas) {
			numRegistros = 0;
			while(numRegistros < hojaDetalle.getContenido().size()){
				listaRegistros = (List<Object>) hojaDetalle.getContenido().get(numRegistros);
				indiceRegistros = 0;
				listaEncabezados = (List<String>) hojaDetalle.getEncabezado().get(numRegistros);
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
					String nombreHoja = "LOTE "+ hojaDetalle.getPropiedadesReporte().getTituloExcel().replaceAll("\\D+","");
					
					hoja = libro.createSheet(nombreHoja);
			        Integer numeroFila = 1;
			        
			        HSSFFont FONT_BOLDWEIGHT_BOLD_16 = cssExcel.FONT_BOLDWEIGHT_BOLD_16(libro);
			        HSSFFont FONT_BOLDWEIGHT_BOLD = cssExcel.FONT_BOLDWEIGHT_BOLD(libro);
			        
			        HSSFCellStyle styleTitulo = cssExcel.CELLSTYLE_ALIGN_CENTER(libro, FONT_BOLDWEIGHT_BOLD_16);
			        HSSFCellStyle styleSubTitulo = cssExcel.CELL_STYLE(libro, FONT_BOLDWEIGHT_BOLD);
			        HSSFCellStyle styleEncabezado = cssExcel.CELL_STYLE_SOLID_FOREGROUND_GREY_40(libro);
			        
			        Row filaTitulo = hoja.createRow(numeroFila);
			        filaTitulo.setHeightInPoints(18);
			        
			    	Cell celdaTitulo = filaTitulo.createCell(0);
				    celdaTitulo.setCellValue(hojaDetalle.getPropiedadesReporte().getTituloExcel());
				    celdaTitulo.setCellStyle(styleTitulo);
			    	
			        hoja = cssExcel.MERGED_REGION(hoja, numeroFila, 0, listaEncabezados.size());
			        
			        if(StringUtils.isNotBlank(hojaDetalle.getPropiedadesReporte().getFechaI()) ||
			        		StringUtils.isNotBlank(hojaDetalle.getPropiedadesReporte().getFechaF())){
				        numeroFila = numeroFila + 2;
				        
				       
				    	//crea Fecha para reporte
				        Row filaFecha = hoja.createRow(numeroFila);
				        filaFecha.setHeightInPoints(16);
				        
				        Cell celdaFecha = filaFecha.createCell(0);
				        
				        if(StringUtils.isNotBlank(hojaDetalle.getPropiedadesReporte().getFechaI()) &&
				        		StringUtils.isNotBlank(hojaDetalle.getPropiedadesReporte().getFechaF())){
				        	celdaFecha.setCellValue(propsMultilenguaje.getTituloPeriodo() + hojaDetalle.getPropiedadesReporte().getFechaI() + 
				        			propsMultilenguaje.getTituloPreposicionPeriodo() + hojaDetalle.getPropiedadesReporte().getFechaF());
				        }else{
				        	celdaFecha.setCellValue(propsMultilenguaje.getTituloGenerico() + utileriaArchivos.getStringDateFromFormat("dd/MM/yyyy", new Date()));
				        }
				        celdaFecha.setCellStyle(styleSubTitulo);
				        
				        hoja = cssExcel.MERGED_REGION(hoja, numeroFila, 0, 10);
				        
				        numeroFila = numeroFila + 2;
			        }else{
			        	numeroFila = numeroFila + 2;
			        }
			        
			        if(StringUtils.isNotBlank(hojaDetalle.getPropiedadesReporte().getPersonaReporte())){
			        	//crea titulo con su estilo
			            Row filaNombrePersona = hoja.createRow(numeroFila);
			            filaNombrePersona.setHeightInPoints(16);
			            
			            Cell celdaNombrePersona = filaNombrePersona.createCell(0);
			            
			            celdaNombrePersona.setCellValue(hojaDetalle.getPropiedadesReporte().getPersonaReporte());
			            celdaNombrePersona.setCellStyle(styleSubTitulo);
			            
			            hoja = cssExcel.MERGED_REGION(hoja, numeroFila, 0, 10);
			            
			            numeroFila = numeroFila + 2;
			        }
			        
			        if(hojaDetalle.getPropiedadesReporte().getSubtitulos() != null){
				        if(!hojaDetalle.getPropiedadesReporte().getSubtitulos().isEmpty()){
				        	for(String subtitulo : hojaDetalle.getPropiedadesReporte().getSubtitulos()){
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
			        celdasubtitulo1.setCellValue(propsMultilenguaje.getTotalRegistros()+ listaRegistros.size());
			        celdasubtitulo1.setCellStyle(styleSubTitulo);
			        numeroFila = numeroFila + 2;
			        
			        //sub titulos
			        Row filaEncabezados = hoja.createRow(numeroFila);
			        
			        int numEmcabezado = 0;
			        int celda = 0 ;
			        while(numEmcabezado < listaEncabezados.size() ){
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
		}
		
		return libro;
	}
}
