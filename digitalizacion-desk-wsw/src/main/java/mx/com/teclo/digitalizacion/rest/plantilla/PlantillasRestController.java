package mx.com.teclo.digitalizacion.rest.plantilla;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.digitalizacion.persistencia.vo.plantillas.PlantillaCompletaVO;
import mx.com.teclo.digitalizacion.persistencia.vo.plantillasolicitada.PlantillaSolicitadaVO;
import mx.com.teclo.digitalizacion.service.plantillas.PlantillasService;


@RestController
@RequestMapping("/paltillas")
public class PlantillasRestController {
	
	@Autowired
	private PlantillasService plantillasService;
	
	/*@RequestMapping(value = "/PlatillaPorId", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<PlantillaCompletaVO> getLotesAll(@RequestParam(value = "plantillaI") Long platillaId) {
		PlantillaCompletaVO PlatillaCompleta = plantillasService.obtenerPlantillaPorIdentificador(platillaId);
		return new ResponseEntity<PlantillaCompletaVO>(PlatillaCompleta,HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/PlatillaPorId", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<PlantillaSolicitadaVO> getLotesAll(@RequestParam(value = "plantillaI") Long platillaId) {
		PlantillaSolicitadaVO plantillaSolicitadaVO = plantillasService.obtenerPlantillaSolicitadaVOPorId(platillaId);
				
		return new ResponseEntity<PlantillaSolicitadaVO>(plantillaSolicitadaVO,HttpStatus.OK);
	}
	
}