package mx.com.teclo.digitalizacion.api.rest.gestionLotes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.teclo.digitalizacion.negocio.servicios.imagenes.ImagenesService;
import mx.com.teclo.digitalizacion.negocio.servicios.lotes.LotesService;
import mx.com.teclo.digitalizacion.negocio.utils.DatosLotesFromUI;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.AsignacionesDatosVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSinBlobVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.LBImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.EstatusProcesoVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesResultVO;

@RestController
@RequestMapping("/gestionLotes")

public class GestionLotesRestController {
	
	@Autowired
	private LotesService lotesService;
	@Autowired
	private ImagenesService imagenesService;
	

	@RequestMapping(value = "/imagenesPorLote", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('GESTION_LOTES')")
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
	@PreAuthorize("hasAnyAuthority('GESTION_LOTES')")
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



}
