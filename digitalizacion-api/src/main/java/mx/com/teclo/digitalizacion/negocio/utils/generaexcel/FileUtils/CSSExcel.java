package mx.com.teclo.digitalizacion.negocio.utils.generaexcel.FileUtils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author UnitisDes0416
 *
 */
public class CSSExcel {
	
	/**
	 * @author UnitisDes0416
	 * @param libro XSSFWorkbook
	 * @return XSSFFont
	 */
	public XSSFFont FONT_BOLDWEIGHT_BOLD_16(XSSFWorkbook libro){
		XSSFFont fontTitulo = (XSSFFont) libro.createFont();
        fontTitulo.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        fontTitulo.setFontHeightInPoints((short)16);
        return fontTitulo;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param libro XSSFWorkbook
	 * @return XSSFFont
	 */
	public XSSFFont FONT_BOLDWEIGHT_BOLD(XSSFWorkbook libro){
		XSSFFont fontSubTitulo = (XSSFFont) libro.createFont();
	    fontSubTitulo.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	    return fontSubTitulo;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param libro HSSFWorkbook
	 * @return HSSFFont
	 */
	public HSSFFont FONT_BOLDWEIGHT_BOLD_16(HSSFWorkbook libro){
		HSSFFont fontTitulo = (HSSFFont) libro.createFont();
        fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontTitulo.setFontHeightInPoints((short)16);
        return fontTitulo;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param libro HSSFWorkbook
	 * @return HSSFFont
	 */
	public HSSFFont FONT_BOLDWEIGHT_BOLD(HSSFWorkbook libro){
		HSSFFont fontSubTitulo = (HSSFFont) libro.createFont();
	    fontSubTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    return fontSubTitulo;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param libro XSSFWorkbook
	 * @param fontTitulo XSSFFont
	 * @return XSSFCellStyle
	 */
	public XSSFCellStyle CELLSTYLE_ALIGN_CENTER(XSSFWorkbook libro, XSSFFont fontTitulo){
		XSSFCellStyle styleTitulo = (XSSFCellStyle) libro.createCellStyle();
        styleTitulo.setFont(fontTitulo);
        styleTitulo.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        return styleTitulo;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param libro XSSFWorkbook
	 * @param fontSubTitulo XSSFFont
	 * @return XSSFCellStyle
	 */
	public XSSFCellStyle CELL_STYLE(XSSFWorkbook libro, XSSFFont fontSubTitulo){
		XSSFCellStyle styleSubTitulo = (XSSFCellStyle) libro.createCellStyle();
	    styleSubTitulo.setFont(fontSubTitulo);
	    return styleSubTitulo;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param libro HSSFWorkbook
	 * @param fontTitulo HSSFFont
	 * @return HSSFCellStyle
	 */
	public HSSFCellStyle CELLSTYLE_ALIGN_CENTER(HSSFWorkbook libro, HSSFFont fontTitulo){
		HSSFCellStyle styleTitulo = (HSSFCellStyle) libro.createCellStyle();
        styleTitulo.setFont(fontTitulo);
        styleTitulo.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        return styleTitulo;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param libro HSSFWorkbook
	 * @param fontSubTitulo HSSFFont
	 * @return HSSFCellStyle
	 */
	public HSSFCellStyle CELL_STYLE(HSSFWorkbook libro, HSSFFont fontSubTitulo){
		HSSFCellStyle styleSubTitulo = (HSSFCellStyle) libro.createCellStyle();
	    styleSubTitulo.setFont(fontSubTitulo);
	    return styleSubTitulo;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param libro HSSFWorkbook
	 * @return HSSFCellStyle
	 */
	public HSSFCellStyle CELL_STYLE_SOLID_FOREGROUND_GREY_40(HSSFWorkbook libro){
		HSSFCellStyle styleEncabezado = (HSSFCellStyle) libro.createCellStyle();  
	    styleEncabezado.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	    styleEncabezado.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
	    return styleEncabezado;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param libro HSSFWorkbook
	 * @return XSSFCellStyle
	 */
	public XSSFCellStyle CELL_STYLE_SOLID_FOREGROUND_GREY_40(XSSFWorkbook libro){
		XSSFCellStyle styleEncabezado = (XSSFCellStyle) libro.createCellStyle();  
	    styleEncabezado.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	    styleEncabezado.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
	    return styleEncabezado;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param hoja Sheet
	 * @param numeroFila int
	 * @param celdaI int
	 * @param celdaF int
	 * @return Sheet
	 */
	public Sheet MERGED_REGION(Sheet hoja, int numeroFila, int celdaI, int celdaF) {
		hoja.addMergedRegion(new CellRangeAddress(numeroFila, numeroFila, celdaI, celdaF));
		return hoja;
	}
}
