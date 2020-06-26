package mx.com.teclo.digitalizacion.rest.imagenes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.digitalizacion.persistencia.vo.Imagenes.ImagenesGuardarVO;
import mx.com.teclo.digitalizacion.service.imagenes.ImagenesService;


@RestController
@RequestMapping("/imagenes")
public class ImagenesRestController {
	
	@Autowired
	private ImagenesService imagenesService;
	
	@RequestMapping(value = "/informacionImagen", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Boolean> guardarInformacionImagen(@RequestBody ImagenesGuardarVO imagenesVO) {
		Boolean respuesta = imagenesService.guardarInformacionImagen(imagenesVO);
		
		return new ResponseEntity<Boolean>(respuesta ,HttpStatus.OK);
	}
}
