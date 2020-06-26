package mx.com.teclo.digitalizacion.api.rest.reportes;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.digitalizacion.bitacora.BitacoraService;
import mx.com.teclo.digitalizacion.negocio.servicios.reportesgenerales.ReportesGeneralesService;
import mx.com.teclo.digitalizacion.negocio.vo.estadisticas.ConsultaGeneralVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.BitacoraCambiosVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ComboComponentesVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ComboConceptosVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ConfiguracionAccesoVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ControlCatalogosVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ParametrosBusquedaReporteBitacoraVO;


@RestController
@RequestMapping("/reportesGenerales")
public class ReportesGeneralesRestController {

	
	@Autowired
	private ReportesGeneralesService reportesGeneralesService;
	@Autowired
	private BitacoraService bitacoraService;
	
	/**
	 * 
	 * @return la lista de catálogos para ser mostrados en la ventana de reportes generales
	 */
	@RequestMapping(value = "/reportesConfigurados", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('REPORTES_GENERALES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<ControlCatalogosVO>> reportesConfigurados() {
		
		List<ControlCatalogosVO> listaRetorno = new ArrayList<>();
		HttpStatus estado = HttpStatus.OK;
		
		try {
			listaRetorno = reportesGeneralesService.getReportesConfigurados();
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar enviar los reportes para la UI: "+ex.getMessage());
			listaRetorno = new ArrayList<ControlCatalogosVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<ControlCatalogosVO>> result =
				new ResponseEntity<List<ControlCatalogosVO>>(listaRetorno,estado);
		
		return result;
		
	}
	/**
	 * 
	 * @return lista de todos los componentes de la bitácora
	 */
	@RequestMapping(value = "/consultaComponentesBitacora", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('REPORTES_GENERALES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<ComboComponentesVO>> consultaComponentesBitacora() {
		
		List<ComboComponentesVO> listaRetorno = new ArrayList<>();
		HttpStatus estado = HttpStatus.OK;
		
		try {
			listaRetorno = bitacoraService.getComponentesBitacora();
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar enviar los componentes para la UI: "+ex.getMessage());
			listaRetorno = new ArrayList<ComboComponentesVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<ComboComponentesVO>> result =
				new ResponseEntity<List<ComboComponentesVO>>(listaRetorno,estado);
		
		return result;
		
	}
	
	/**
	 * 
	 * @param componenteId
	 * @return lista de conceptos correspondientes al componente pasado como parámetro
	 */
	@RequestMapping(value = "/consultaConceptosBitacora", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('REPORTES_GENERALES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<ComboConceptosVO>> consultaConceptosBitacora(@RequestParam(value = "componenteId" ) 
	Long componenteId) {
		
		List<ComboConceptosVO> listaRetorno = new ArrayList<>();
		HttpStatus estado = HttpStatus.OK;
		
		try {
			listaRetorno = bitacoraService.getConceptosBitacora(componenteId);
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar enviar los conceptos para la UI: " + ex.getMessage());
			listaRetorno = new ArrayList<ComboConceptosVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<ComboConceptosVO>> result =
				new ResponseEntity<List<ComboConceptosVO>>(listaRetorno,estado);
		
		return result;
	}

	/**
	 * 
	 * @param parametrosBusquedaVO parámetros de búsqueda en la bitácora
	 * @return lista de cambios de la bitácora dados los parámetros de búsqueda
	 */
	@RequestMapping(value = "/consultaBitacoraCambios", method = RequestMethod.POST, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('REPORTES_GENERALES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<BitacoraCambiosVO>> consultaBitacoraCambios(@RequestBody ParametrosBusquedaReporteBitacoraVO 
			parametrosBusquedaVO) {
		
		List<BitacoraCambiosVO> listaRetorno = new ArrayList<>();
		HttpStatus estado = HttpStatus.OK;
		
		try {
			listaRetorno = bitacoraService.getBitacoraCambios(parametrosBusquedaVO);
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar enviar los conceptos para la UI: " + ex.getMessage());
			listaRetorno = new ArrayList<BitacoraCambiosVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<BitacoraCambiosVO>> result =
				new ResponseEntity<List<BitacoraCambiosVO>>(listaRetorno,estado);
		
		return result;
	}

	@RequestMapping(value = "/generarReporteBitacora", method = RequestMethod.POST, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('REPORTES_GENERALES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<byte[]> generarReporteBitacora(@RequestBody ParametrosBusquedaReporteBitacoraVO 
			parametrosBusquedaVO) {
		
		List<BitacoraCambiosVO> listaCambios = new ArrayList<>();
		HttpStatus estado = HttpStatus.OK;
		byte[] bytes = null;
		HttpHeaders headers = new HttpHeaders();
		
		try {
			listaCambios = bitacoraService.getBitacoraCambios(parametrosBusquedaVO);
			if(listaCambios.isEmpty()) {
				System.out.println("Reporte vacío");
				return null;
			}
			
			String nombreReporte = "Reporte_Bitácora_Cambios";
			
			ByteArrayOutputStream reporteExcel = bitacoraService.getFicheroExcel(listaCambios, nombreReporte,
					parametrosBusquedaVO.getFechaInicio(),parametrosBusquedaVO.getFechaFin());

			bytes = reporteExcel.toByteArray();

			
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
			headers.add("Content-Disposition", "attachmnt; filename =" + nombreReporte +".xls");
			headers.add("filename", nombreReporte +".xls");
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setContentLength(bytes.length);
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar obtener el fichero excel del reporte: " + ex.getMessage());
			bytes = null;
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<byte[]> result =	new ResponseEntity<byte[]>(bytes,headers,estado);
		
		return result;
	}

}
