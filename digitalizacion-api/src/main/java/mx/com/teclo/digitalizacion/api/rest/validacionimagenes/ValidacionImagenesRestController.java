package mx.com.teclo.digitalizacion.api.rest.validacionimagenes;

import mx.com.teclo.digitalizacion.negocio.servicios.imagenes.ImagenesService;
import mx.com.teclo.digitalizacion.negocio.servicios.lotes.LotesService;
import mx.com.teclo.digitalizacion.negocio.servicios.validadores.ValidadoresService;
import mx.com.teclo.digitalizacion.negocio.utils.Prueba;
import mx.com.teclo.digitalizacion.negocio.utils.ValueResource;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.BoletaPosponerVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.BuscaBoletasPospuestasVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.CancelarImagenVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSimplesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.LBImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.VehiculoMarcaVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesVO;
import mx.com.teclo.digitalizacion.negocio.vo.validadores.ValidadorBusquedaVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.ValidadoresHDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;

@RestController
@RequestMapping("/validacionImagenes")
public class ValidacionImagenesRestController {

	@Autowired
	private ImagenesService imagenesService;

	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@Autowired
	private LotesService lotesService;
	
	@Autowired
	private ValidadoresService validadoresService;
	
	/*Borrar*/
	@Autowired
	private ValidadoresHDAO validadoresHDAO;
	@Autowired
	private AsignValidacionHDAO asignValidacionHDAO;
	/*Borrar*/

	@RequestMapping(value = "/todasMarcasVehiculos", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<List<VehiculoMarcaVO>> getVehiculosMarcasVO() {
		List<VehiculoMarcaVO> listaRetornar = new ArrayList<VehiculoMarcaVO>();
		HttpStatus estado = HttpStatus.OK;

		try {
			listaRetornar = imagenesService.getTodasMarcasVehiculos();
		} catch (Exception ex) {
			System.out.println(
					"Ocurrió un error al intentar devolver las marcas de vehículos para la UI: " + ex.getMessage());
			listaRetornar = new ArrayList<VehiculoMarcaVO>();
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<List<VehiculoMarcaVO>> result = new ResponseEntity<List<VehiculoMarcaVO>>(listaRetornar, estado);

		return result;
	}

	@RequestMapping(value = "/asignacionInicialVO", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	public synchronized @ResponseBody ResponseEntity<List<ImagenesVO>> getImagenesInicialesVO() {
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		List<ImagenesVO> listaImagenesVO = null;
		HttpStatus estado = HttpStatus.OK;
		
		//System.out.println("\n");
		//System.out.println(">> Entrada al método asignacionInicialVO. Usuario: "+idUsuario+" Hilo:"+Thread.currentThread().getId());

		try {
			listaImagenesVO = imagenesService.getImagenesInicialesVO(idUsuario);
		} catch (Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las imágenes para la UI: " + ex.getMessage());
			listaImagenesVO = new ArrayList<ImagenesVO>();
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<List<ImagenesVO>> result = new ResponseEntity<List<ImagenesVO>>(listaImagenesVO, estado);

		//System.out.println("<< Salida del método asignacionInicialVO. Usuario: "+idUsuario+" Hilo:"+Thread.currentThread().getId());
		
		return result;
	}


	@RequestMapping(value = "/marcarImagenAceptada", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<Boolean> marcarImagenAceptada(@RequestBody ImagenesVO imagenMarcar) {
		Boolean resp = true;
		HttpStatus estado = HttpStatus.ACCEPTED;
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		boolean generaCambioEstadoLote = false;
		try {
			if (imagenesService.isImagenAceptable(imagenMarcar)) {
				generaCambioEstadoLote = imagenesService.marcarImagenVOComoAceptada(imagenMarcar, idUsuario);
				if (generaCambioEstadoLote) {
					lotesService.addCambiosLotes(imagenMarcar.getIdLote(), idUsuario);
				}
			} else {
				resp = false;
			}
		} catch (Exception ex) {
			resp = false;
			estado = HttpStatus.CONFLICT;
			System.out.println("Ocurrió un error al marcar como aceptada la imagen: " + ex.getMessage());
		}

		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(resp, estado);

		return result;
	}

	@RequestMapping(value = "/isImagenAceptable", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<Boolean> isImagenAceptable(@RequestBody ImagenesVO imagenMarcar) {
		Boolean resp = true;
		HttpStatus estado = HttpStatus.ACCEPTED;

		try {
			if (imagenesService.isImagenAceptable(imagenMarcar)) {
				resp = true;
			} else {
				resp = false;
			}
		} catch (Exception ex) {
			resp = false;
			estado = HttpStatus.CONFLICT;
			System.out.println("Ocurrió un error al revisar la imagen: " + ex.getMessage());
		}

		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(resp, estado);

		return result;
	}


	@RequestMapping(value = "/marcarImagenRechazadaPorId", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<Boolean> marcarImagenRechazadaPorId(@RequestBody CancelarImagenVO cancelarImagenVO) {
		Boolean resp = true;
		HttpStatus estado = HttpStatus.ACCEPTED;
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		boolean generaCambioEstadoLote = false;

		try {
			generaCambioEstadoLote = imagenesService.marcarImagenComoRechazada(cancelarImagenVO.getIdImagenSeleccionada(), idUsuario, cancelarImagenVO.getCausa());
			if (generaCambioEstadoLote) {
				Long idLote = imagenesService.getIdLoteByIdImagen(cancelarImagenVO.getIdImagenSeleccionada());
				lotesService.addCambiosLotes(idLote, idUsuario);
			}
		} catch (Exception ex) {
			System.out.println("Ocurrió un error al marcar como rechazada la imagen: " + ex.getMessage());
			resp = false;
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(resp, estado);

		return result;
	}

	@RequestMapping(value = "/marcarLBImagenAnverso", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<Boolean> marcarLBImagenAnverso(@RequestBody Long idLBImagen) {
		HttpStatus estado = HttpStatus.OK;
		Boolean resp = true;

		try {
			imagenesService.marcarLBImagenComoAnverso(idLBImagen);
		} catch (Exception ex) {
			System.out.println("Ocurrió un error al marcar la imagen como anverso: " + ex.getMessage());
			resp = false;
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(resp, estado);

		return result;
	}

	@RequestMapping(value = "/marcarLBImagenReverso", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<Boolean> marcarLBImagenReverso(@RequestBody Long idLBImagen) {
		HttpStatus estado = HttpStatus.OK;
		Boolean resp = true;

		try {
			imagenesService.marcarLBImagenComoReverso(idLBImagen);
		} catch (Exception ex) {
			System.out.println("Ocurrió un error al marcar la imagen como reverso: " + ex.getMessage());
			resp = false;
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(resp, estado);

		return result;
	}

	@RequestMapping(value = "/imagenRealPorIdentificador", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = true)
	public @ResponseBody ResponseEntity<ImagenesVO> getImagen(
			@RequestParam(value = "idImagenSeleccionada") Long idImagenSimpleVO) {
		ImagenesVO imagenVO = null;
		HttpStatus estado = HttpStatus.OK;

		try {
			imagenVO = imagenesService.getImagenVOPorId(idImagenSimpleVO);
		} catch (Exception ex) {
			imagenVO = null;
			System.out.println("Ocurrió un error al devolver la imagen: " + ex.getMessage());
			estado = HttpStatus.CONFLICT;
		}
		ResponseEntity<ImagenesVO> result = new ResponseEntity<ImagenesVO>(imagenVO, estado);

		return result;
	}

	@RequestMapping(value = "/cambiarPosicionImagen", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<List<LBImagenesVO>> cambiarPosicionImagen(@RequestBody Long idLBImagen) {
		HttpStatus estado = HttpStatus.OK;
		List<LBImagenesVO> resp = new ArrayList<LBImagenesVO>();

		try {
			resp = imagenesService.cambiarPosicionImagen(idLBImagen);
		} catch (Exception ex) {
			System.out.println("Ocurrió un error al cambiar de posición la imagen: " + idLBImagen + " Excepcion: "
					+ ex.getMessage());
			resp = new ArrayList<LBImagenesVO>();
			;
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<List<LBImagenesVO>> result = new ResponseEntity<List<LBImagenesVO>>(resp, estado);

		return result;
	}

	@RequestMapping(value = "/tieneAsignaciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = true)
	public @ResponseBody ResponseEntity<Boolean> tieneAsignaciones() {
		Boolean resp = true;
		HttpStatus estado = HttpStatus.ACCEPTED;
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		try {
			resp = imagenesService.tieneAsignacionesElUsuario(idUsuario);
		} catch (Exception ex) {
			resp = false;
			estado = HttpStatus.CONFLICT;
			System.out.println("Ocurrió un error al buscar las asignaciones del validador: " + ex.getMessage());
		}

		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(resp, estado);

		return result;
	}
	
	@RequestMapping(value = "/existenAsignacionesPospuestas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = true)
	public @ResponseBody ResponseEntity<Boolean> existenAsignacionesPospuestas() {
		Boolean resp = true;
		HttpStatus estado = HttpStatus.ACCEPTED;

		try {
			resp = imagenesService.existenAsignacionesPospuestas();
		} catch (Exception ex) {
			resp = false;
			estado = HttpStatus.CONFLICT;
			System.out.println("Ocurrió un error al buscar las asignaciones del validador: " + ex.getMessage());
		}

		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(resp, estado);

		return result;
	}

	@RequestMapping(value = "/tieneAsignacionesPospuestas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = true)
	public @ResponseBody ResponseEntity<Boolean> tieneAsignacionesPospuestas() {
		Boolean resp = true;
		HttpStatus estado = HttpStatus.ACCEPTED;
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		try {
			resp = imagenesService.tieneAsignacionesPospuestasElUsuario(idUsuario);
		} catch (Exception ex) {
			resp = false;
			estado = HttpStatus.CONFLICT;
			System.out.println("Ocurrió un error al buscar las asignaciones del validador: " + ex.getMessage());
		}

		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(resp, estado);

		return result;
	}

	
	@RequestMapping(value = "/tieneAsignaciones2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = true)
	public @ResponseBody ResponseEntity<Boolean> tieneAsignaciones2() {
		Boolean resp = true;
		HttpStatus estado = HttpStatus.ACCEPTED;
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		try {
			resp = imagenesService.tieneAsignacionesElUsuario(idUsuario);
		} catch (Exception ex) {
			resp = false;
			estado = HttpStatus.CONFLICT;
			System.out.println("Ocurrió un error al buscar las asignaciones del validador: " + ex.getMessage());
		}

		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(resp, estado);

		return result;
	}
	
	@RequestMapping(value = "/posponerBoleta", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<Boolean> posponerBoleta(@RequestBody BoletaPosponerVO boletaPosponerVO) {
		Boolean resp = false;
		HttpStatus estado = HttpStatus.OK;
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		
		try {
			resp = imagenesService.posponerBoleta(boletaPosponerVO.getIdImagen(), boletaPosponerVO.getCausa(), idUsuario);
			
		} catch (Exception ex) {
			resp = false;
			estado = HttpStatus.CONFLICT;
			System.out.println("Ocurrió un error al posponer la boleta: " + ex.getMessage());
		}

		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(resp, estado);

		return result;
	}
	
	@RequestMapping(value = "/posponerBoleta2", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<Boolean> posponerBoleta2(@RequestParam("causa") String causa, @RequestParam("idImagen") Long idImagen) {
		Boolean resp = false;
		HttpStatus estado = HttpStatus.OK;
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();

		try {
			resp = imagenesService.posponerBoleta(idImagen, causa, idUsuario);
			
		} catch (Exception ex) {
			resp = false;
			estado = HttpStatus.CONFLICT;
			System.out.println("Ocurrió un error al posponer la boleta: " + ex.getMessage());
		}

		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(resp, estado);

		return result;
	}
	
	@RequestMapping(value = "/todasImagenesPospuestas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<List<ImagenesVO>> getTodasImagenesPospuestasVO() {
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		List<ImagenesVO> listaImagenesVO = null;
		HttpStatus estado = HttpStatus.OK;

		try {
			listaImagenesVO = imagenesService.getTodasImagenesPospuestas();
		} catch (Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las imágenes para la UI: " + ex.getMessage());
			listaImagenesVO = new ArrayList<ImagenesVO>();
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<List<ImagenesVO>> result = new ResponseEntity<List<ImagenesVO>>(listaImagenesVO, estado);

		return result;
	}


	@RequestMapping(value = "/imagenesPospuestasValidadorActivo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<List<ImagenesVO>> getImagenesPospuestasValidadorActivoVO() {
		List<ImagenesVO> listaImagenesVO = null;
		HttpStatus estado = HttpStatus.OK;

		try {
			listaImagenesVO = imagenesService.getTodasImagenesPospuestasByidUsuario(usuarioFirmadoService.getUsuarioFirmadoVO());
		} catch (Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las imágenes para la UI: " + ex.getMessage());
			listaImagenesVO = new ArrayList<ImagenesVO>();
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<List<ImagenesVO>> result = new ResponseEntity<List<ImagenesVO>>(listaImagenesVO, estado);

		return result;
	}
	
	@RequestMapping(value = "/blobsByIdImagen", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<List<LBImagenesVO>> getImagenesBlbByIdImagen(
			@RequestParam(value = "idImagenSeleccionada") Long idImagenSeleccionada) {
		
		List<LBImagenesVO> listaLBImagenesVO = null;
		HttpStatus estado = HttpStatus.OK;

		try {
			listaLBImagenesVO = imagenesService.getLBImagenesPorIdImagenDTO(idImagenSeleccionada);
		} catch (Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las imágenes para la UI: " + ex.getMessage());
			listaLBImagenesVO = new ArrayList<LBImagenesVO>();
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<List<LBImagenesVO>> result = new ResponseEntity<List<LBImagenesVO>>(listaLBImagenesVO, estado);

		return result;
	}
	
	@RequestMapping(value = "/todosValidadores", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<List<ValidadorBusquedaVO>> getodosValidadores() {
		List<ValidadorBusquedaVO> listaRetornar = new ArrayList<ValidadorBusquedaVO>();
		HttpStatus estado = HttpStatus.OK;

		try {
			listaRetornar = validadoresService.getAllValidadores();
		} catch (Exception ex) {
			System.out.println(
					"Ocurrió un error al intentar devolver las marcas de vehículos para la UI: " + ex.getMessage());
			listaRetornar = new ArrayList<ValidadorBusquedaVO>();
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<List<ValidadorBusquedaVO>> result = new ResponseEntity<List<ValidadorBusquedaVO>>(listaRetornar, estado);

		return result;
	}
	
	@RequestMapping(value = "/imagenesPospuestasParam", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDACION_SERVICE')")
	@Transactional(readOnly = false)
	public @ResponseBody ResponseEntity<List<ImagenesVO>> getImagenesPospuestasParam(@RequestParam(value = "idValidador") Long idValidador,
			@RequestParam(value = "fechaInicial") @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") Date fechaInicial,
			@RequestParam(value = "fechaFinal") @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") Date fechaFinal) {
		List<ImagenesVO> listaImagenesVO = null;
		HttpStatus estado = HttpStatus.OK;

		try {
			listaImagenesVO = imagenesService.getImagenesPospuestasParam(idValidador, fechaInicial,fechaFinal);
		} catch (Exception ex) {
			System.out.println("Ocurrió un error al intentar devolver las imágenes pospuestas para la UI: " + ex.getMessage());
			listaImagenesVO = new ArrayList<ImagenesVO>();
			estado = HttpStatus.CONFLICT;
		}

		ResponseEntity<List<ImagenesVO>> result = new ResponseEntity<List<ImagenesVO>>(listaImagenesVO, estado);

		return result;
	}
}
