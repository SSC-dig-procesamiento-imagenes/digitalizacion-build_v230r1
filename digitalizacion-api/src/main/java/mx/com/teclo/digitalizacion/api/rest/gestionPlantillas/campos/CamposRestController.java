package mx.com.teclo.digitalizacion.api.rest.gestionPlantillas.campos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.digitalizacion.negocio.servicios.gestionPlantillas.campos.CamposService;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.plantillas.gestionPlantillas.CamposVO;

@RestController
@RequestMapping("/campos")
public class CamposRestController {
	@Autowired CamposService  camposService;
	
	@RequestMapping(value = "/getCampos", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyAuthority('CARACTERES')")
	public ResponseEntity<List<CamposVO>> getCampos(@RequestParam(value = "tipoBusqueda") String tipoBusqueda,@RequestParam(value = "valor") String valor) throws NotFoundException {
		List<CamposVO> listaConsulta = camposService.getCampos(tipoBusqueda, valor);
		if (listaConsulta.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<CamposVO>>(listaConsulta, HttpStatus.OK);
	}

}
