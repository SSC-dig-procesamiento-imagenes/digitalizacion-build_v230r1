package mx.com.teclo.digitalizacion.negocio.servicios.reportesgenerales;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.digitalizacion.negocio.utils.ValoresFicheroExcel;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ComboComponentesVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ControlCatalogosVO;

public interface ReportesGeneralesService {

	public List<ControlCatalogosVO> getReportesConfigurados();

}
