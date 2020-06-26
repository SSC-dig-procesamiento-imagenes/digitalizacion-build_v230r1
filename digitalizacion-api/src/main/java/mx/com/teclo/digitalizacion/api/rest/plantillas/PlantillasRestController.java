package mx.com.teclo.digitalizacion.api.rest.plantillas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.algorithms.vo.MarcasWebVO;
import mx.com.teclo.digitalizacion.negocio.servicios.plantillas.PlantillaService;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.plantillas.ParametrosAreaTrabajo;

@RestController
@RequestMapping("plantillas")
public class PlantillasRestController {
private @Autowired	PlantillaService plantillaService;

@RequestMapping("/obtenerAreaTrabajo")
public ResponseEntity<MarcasWebVO> obtenerAreaTrabajo(@RequestBody ParametrosAreaTrabajo parametros) throws Exception{
		MarcasWebVO areaTrabajoPlantilla=new MarcasWebVO();
		areaTrabajoPlantilla=plantillaService.obtenerMarcasValidasPx(parametros);
		return new ResponseEntity<MarcasWebVO>(areaTrabajoPlantilla,HttpStatus.OK);
	}
}
