package mx.com.teclo.digitalizacion.api.rest.estadisticas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.digitalizacion.negocio.servicios.estadisticas.EstadisticasService;
import mx.com.teclo.digitalizacion.negocio.vo.estadisticas.ConsultaGeneralVO;
import mx.com.teclo.digitalizacion.negocio.vo.estadisticas.FechasConsultasVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSinBlobVO;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticasImagenesRestController {

	@Autowired
	private EstadisticasService estadisticasService;
	
	/*El formato de la fecha tiene que ser 'DD-MM-YYYY' */
	@RequestMapping(value = "/validadoresImagenesParametrizado", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('ESTADISTICAS')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<ConsultaGeneralVO>> getImagenesPorValidadorParametrizado(
											@RequestParam(value = "fechaInicial") String fechaInicialStr,
											@RequestParam(value = "fechaFinal") String fechaFinalStr) {
		
		List<ConsultaGeneralVO> listaRetorno = new ArrayList<>();
		HttpStatus estado = HttpStatus.OK;
		
		try {
			
			listaRetorno = estadisticasService.getConsultaGeneral(fechaInicialStr, fechaFinalStr);
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar enviar los datos estadísticos para UI: "+ex.getMessage());
			listaRetorno = new ArrayList<ConsultaGeneralVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<ConsultaGeneralVO>> result =
				new ResponseEntity<List<ConsultaGeneralVO>>(listaRetorno,estado);
		
		return result;
		
	}

	@RequestMapping(value = "/validadoresImagenes", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('ESTADISTICAS')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<List<ConsultaGeneralVO>> getImagenesPorValidador() {
		
		List<ConsultaGeneralVO> listaRetorno = new ArrayList<>();
		HttpStatus estado = HttpStatus.OK;
		
		try {
			
			listaRetorno = estadisticasService.getConsultaGeneral();
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar enviar los datos estadísticos para UI: "+ex.getMessage());
			listaRetorno = new ArrayList<ConsultaGeneralVO>();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<List<ConsultaGeneralVO>> result =
				new ResponseEntity<List<ConsultaGeneralVO>>(listaRetorno,estado);
		
		return result;
		
	}
	
}
