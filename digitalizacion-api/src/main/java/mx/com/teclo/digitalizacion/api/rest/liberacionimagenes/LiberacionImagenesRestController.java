package mx.com.teclo.digitalizacion.api.rest.liberacionimagenes;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.digitalizacion.negocio.servicios.imagenes.ImagenesService;
import mx.com.teclo.digitalizacion.negocio.servicios.lotes.LotesService;
import mx.com.teclo.digitalizacion.negocio.utils.DatosLotesFromUI;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresFicheroExcel;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.AsignacionesDatosVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSinBlobVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.LBImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.EstatusProcesoVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesResultVO;

@RestController
@RequestMapping("/liberacionImagenes")

public class LiberacionImagenesRestController {
	
	@Autowired
	private LotesService lotesService;
	@Autowired
	private ImagenesService imagenesService;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@RequestMapping(value = "/estadosLotes", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<EstatusProcesoVO>> getEstadosLotes() {
		List<EstatusProcesoVO> listaRetorno = null;
		HttpStatus estado = HttpStatus.OK;
		
		try {
			listaRetorno = lotesService.getTodosEstatus();
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver los estatus de lotes para la UI: "+ex.getMessage());
			listaRetorno = new ArrayList<EstatusProcesoVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<EstatusProcesoVO>> result =
				new ResponseEntity<List<EstatusProcesoVO>>(listaRetorno,estado);
		
		return result;
	}

	
	@RequestMapping(value = "/lotes", method = RequestMethod.GET,produces = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<LotesResultVO>> getLotesResultVO() {
		List<LotesResultVO> listaRetorno = null;
		HttpStatus estado = HttpStatus.OK;
		
		try {
			listaRetorno = lotesService.getInformacionLotes();
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver los lotes para la UI: "+ex.getMessage());
			listaRetorno = new ArrayList<LotesResultVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<LotesResultVO>> result =
				new ResponseEntity<List<LotesResultVO>>(listaRetorno,estado);
		
		return result;
	}

	@RequestMapping(value = "/lotesParametrizados", method = RequestMethod.GET, 
			produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<LotesResultVO>> getLotesResultVO(@RequestParam(value = "datos") DatosLotesFromUI datos) {
		List<LotesResultVO> listaRetorno = null;
		HttpStatus estado = HttpStatus.OK;
		
		try {
			listaRetorno = lotesService.getInformacionLotes(datos.getFechaInicial(),datos.getFechaFinal(),datos.getIdEstatus());
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver los lotes para la UI: "+ex.getMessage());
			listaRetorno = new ArrayList<LotesResultVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<LotesResultVO>> result =
				new ResponseEntity<List<LotesResultVO>>(listaRetorno,estado);
		
		return result;
	}

	@RequestMapping(value = "/lotesParametrizadosPost", method = RequestMethod.POST, 
			produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<LotesResultVO>> getLotesResultVOPost(@RequestBody DatosLotesFromUI datos) {
		List<LotesResultVO> listaRetorno = null;
		HttpStatus estado = HttpStatus.OK;
		
		if((datos.getFechaInicial() != null && datos.getFechaFinal() == null)||
				(datos.getFechaInicial() == null && datos.getFechaFinal() != null)) {
			
			listaRetorno = new ArrayList<LotesResultVO>();
			estado = HttpStatus.NOT_ACCEPTABLE;
			
		}else {
			try {
				if(datos.getIdEstatus() == null && datos.getFechaFinal() == null && datos.getFechaInicial() == null ) {
					listaRetorno = lotesService.getInformacionLotes();
				}else {
					listaRetorno = lotesService.getInformacionLotes(datos.getFechaInicial(),datos.getFechaFinal(),datos.getIdEstatus());
				}
				
			}catch(Exception ex) {
				System.out.println("Ocurrió un error al intentar devolver los lotes para la UI: "+ex.getMessage());
				listaRetorno = new ArrayList<LotesResultVO>();
				estado = HttpStatus.CONFLICT;
			}
		}
		
		ResponseEntity<List<LotesResultVO>> result =
				new ResponseEntity<List<LotesResultVO>>(listaRetorno,estado);
		
		return result;
	}
	
	@RequestMapping(value = "/cancelarLote", method = RequestMethod.POST, consumes = "application/json", 
			produces = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=false)
	public @ResponseBody ResponseEntity<LotesResultVO> cancelarLote(@RequestBody Long idLote) {
		LotesResultVO retorno = new LotesResultVO();
		HttpStatus estado = HttpStatus.OK;
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		
		try {
			retorno = lotesService.cancelarLote(idLote);
			lotesService.addCambiosLotes(idLote, idUsuario);
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar cancelar el lote: "+ex.getMessage());
			retorno = new LotesResultVO();
			estado = HttpStatus.METHOD_NOT_ALLOWED;
		}
		
		ResponseEntity<LotesResultVO> result =
				new ResponseEntity<LotesResultVO>(retorno,estado);
		
		return result;
	}
	
	@RequestMapping(value = "/formarParaLiberarLote", method = RequestMethod.POST, consumes = "application/json", 
			produces = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=false)
	public @ResponseBody ResponseEntity<LotesResultVO> formarParaLiberarLote(@RequestBody Long idLote) {
		LotesResultVO retorno = new LotesResultVO();
		HttpStatus estado = HttpStatus.OK;
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		
		try {
			retorno = lotesService.formarParaLiberarLote(idLote);
			lotesService.addCambiosLotes(idLote, idUsuario);
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar cancelar el lote: "+ex.getMessage());
			retorno = new LotesResultVO();
			estado = HttpStatus.METHOD_NOT_ALLOWED;
		}
		
		ResponseEntity<LotesResultVO> result =
				new ResponseEntity<LotesResultVO>(retorno,estado);
		
		return result;
	}

	@RequestMapping(value = "/enValidandoInformacion", method = RequestMethod.POST, consumes = "application/json", 
			produces = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=false)
	public @ResponseBody ResponseEntity<LotesResultVO> ponerEnValidandoInformacion(@RequestBody Long idLote) {
		LotesResultVO retorno = new LotesResultVO();
		HttpStatus estado = HttpStatus.OK;
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		
		try {
			retorno = lotesService.enValidandoInformacion(idLote);
			lotesService.addCambiosLotes(idLote, idUsuario);
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar cambiar a validando información el lote: "+ex.getMessage());
			retorno = new LotesResultVO();
			estado = HttpStatus.METHOD_NOT_ALLOWED;
		}
		
		ResponseEntity<LotesResultVO> result =
				new ResponseEntity<LotesResultVO>(retorno,estado);
		
		return result;
	}


	@RequestMapping(value = "/asignacionesActivas", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<AsignacionesDatosVO>> getAsignacionesActivas() {
		List<AsignacionesDatosVO> listaRetorno = new ArrayList<>();
		HttpStatus estado = HttpStatus.OK;
		
		try {
			
			listaRetorno =  imagenesService.getAsignacionesActivasData();
		
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las imágenes asignadas a la UI: "+ex.getMessage());
			listaRetorno = new ArrayList<AsignacionesDatosVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<AsignacionesDatosVO>> result =
				new ResponseEntity<List<AsignacionesDatosVO>>(listaRetorno,estado);
		
		return result;
		
	}

	@RequestMapping(value = "/asignacionesActivasValidador", method = RequestMethod.DELETE, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<AsignacionesDatosVO>> deleteAsignacionesActivasPorValidador(@RequestParam(value = "idValidadorSeleccionado") 
																								Long idValidadorSeleccionado) {
		List<AsignacionesDatosVO> listaRetorno = new ArrayList<>();
		HttpStatus estado = HttpStatus.OK;
		
		try {
			imagenesService.deleteAsignacionesActivasPorValidador(idValidadorSeleccionado);
			listaRetorno =  imagenesService.getAsignacionesActivasData();
		
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las imágenes asignadas a la UI: "+ex.getMessage());
			listaRetorno = new ArrayList<AsignacionesDatosVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<AsignacionesDatosVO>> result =
				new ResponseEntity<List<AsignacionesDatosVO>>(listaRetorno,estado);
		
		return result;
		
	}
	
	@RequestMapping(value = "/imagenesPorLote", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<ImagenesSinBlobVO>> getImagenesPorLote(@RequestParam(value = "idLoteSeleccionado") 
																									Long idLoteSeleccionado) {
		List<ImagenesSinBlobVO> listaRetorno = new ArrayList<>();
		HttpStatus estado = HttpStatus.OK;
		
		try {
			
			listaRetorno = lotesService.getImagenesPorLote(idLoteSeleccionado);
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver los estatus de lotes para la UI: "+ex.getMessage());
			listaRetorno = new ArrayList<ImagenesSinBlobVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<ImagenesSinBlobVO>> result =
				new ResponseEntity<List<ImagenesSinBlobVO>>(listaRetorno,estado);
		
		return result;
		
	}

	@RequestMapping(value = "/imagenesBlob", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<LBImagenesVO>> getLBImagenesPorIdImagen(@RequestParam(value = "idImagenSeleccionada") 
																									Long idImagenSeleccionada) {
		List<LBImagenesVO> listaRetorno = new ArrayList<>();
		HttpStatus estado = HttpStatus.OK;
		
		try {
			
			listaRetorno = imagenesService.getLBImagenesPorIdImagenDTO(idImagenSeleccionada);
			if(listaRetorno.size() != 2)
				throw new Exception("Cada boleta debe tener dos imágenes asociadas: " + listaRetorno.toString());
		
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver los estatus de lotes para la UI: "+ex.getMessage());
			listaRetorno = new ArrayList<LBImagenesVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<LBImagenesVO>> result =
				new ResponseEntity<List<LBImagenesVO>>(listaRetorno,estado);
		
		return result;
		
	}

	/*
	 * Genera fichero excel de los valores que el sistema ha cambiado o modificado de los lotes e imágenes
	 * 
	*/
	@RequestMapping(value = "/ficheroExcelBusqueda", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<byte[]>getficheroExcelBusqueda(@RequestParam(value = "idLotes") String idLotes) {
		HttpStatus estado = HttpStatus.OK;
		List<Long> listaIds = crearListaIdLotes(idLotes);
		String nombreReporte = "Reporte_Lotes";
		
		
		ByteArrayOutputStream ficheroExcelBytes = lotesService.getFicheroExcel(listaIds, nombreReporte,ValoresFicheroExcel.LOTES);
		
		byte[] bytes = ficheroExcelBytes.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
		headers.add("Content-Disposition", "attachmnt; filename =" + nombreReporte +".xls");
		headers.add("filename", nombreReporte +".xls");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, estado);

		return response;
		
	}

	/*
	 * Genera fichero excel de los valores originales de los lotes e imágenes
	 * */
	@RequestMapping(value = "/ficheroExcelBusquedaB", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('LIBERACION_IMAGENES')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<byte[]>getficheroExcelBusquedaB(@RequestParam(value = "idLotes") String idLotes) {
		HttpStatus estado = HttpStatus.OK;
		List<Long> listaIds = crearListaIdLotes(idLotes);
		String nombreReporte = "Reporte_de_Lotes";
		
		ByteArrayOutputStream ficheroExcelBytes = lotesService.getFicheroExcel(listaIds, nombreReporte,ValoresFicheroExcel.LOTESB);
		
		byte[] bytes = ficheroExcelBytes.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
		headers.add("Content-Disposition", "attachmnt; filename =" + nombreReporte +".xls");
		headers.add("filename", nombreReporte +".xls");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, estado);

		return response;
		
	}


	private List<Long> crearListaIdLotes(String jsonIdLista){
		List<Long> listaIds = new ArrayList<>();
        String[] arregloIds = jsonIdLista.split(",");
        
        for(String valor : arregloIds) {
        	listaIds.add(Long.valueOf(valor));
        }

        
        return listaIds;
    }

}
