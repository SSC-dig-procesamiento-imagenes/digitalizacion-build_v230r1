package mx.com.teclo.digitalizacion.api.rest.caracteres;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.digitalizacion.negocio.servicios.caracteres.CaracteresService;
import mx.com.teclo.digitalizacion.negocio.utils.RutinasTiempoImpl;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.CaracterVO;

@RestController
@RequestMapping("/caracteres")
public class CaracteresRestController {
	@Autowired
	private CaracteresService caracteresService;

	@RequestMapping(value = "/getCaracteres", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyAuthority('CARACTERES')")
	public ResponseEntity<List<CaracterVO>> buscarCaracteres(@RequestParam(value = "tipoBusqueda") String tipoBusqueda,@RequestParam(value = "valor") String valor) throws NotFoundException {
		List<CaracterVO> listaConsulta = caracteresService.getCaracter(tipoBusqueda,valor);
		if (listaConsulta.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<CaracterVO>>(listaConsulta, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCaracteresByRespuesta", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyAuthority('CARACTERES')")
	public ResponseEntity<List<CaracterVO>> buscarCaracteresByRespuesta(@RequestParam(value = "idRespuesta") BigDecimal idRespuesta) throws NotFoundException {
		List<CaracterVO> listaConsulta = caracteresService.getCaracteresByRespuesta(idRespuesta);
		if (listaConsulta.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<CaracterVO>>(listaConsulta, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	// @PreAuthorize("hasAnyAuthority('CARACTERES')")
	public ResponseEntity<Boolean> saveOrUpdate(@RequestBody CaracterVO caracterVO) throws NotFoundException {
		Boolean realizarOperacion = caracteresService.evaluasaveOrUpdate(caracterVO);
		if (realizarOperacion == false) {
			throw new NotFoundException("El nombre de caracter ya existe");
		}
		return new ResponseEntity<Boolean>(realizarOperacion, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/descargaExcel", method = RequestMethod.GET, produces = "application/json")
	///@PreAuthorize("hasAnyAuthority('XX')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<byte[]>getficheroExcelBusqueda(@RequestParam(value = "tipoBusqueda") String tipoBusqueda,@RequestParam(value = "valor") String valor) {
		
		RutinasTiempoImpl f =new RutinasTiempoImpl();
		String fechaImpresion=f.getFecha_ddMMYYYY_hhmmss(new Date());
		String nombreReporte = "REPORTE_GENERAL_CARACTERES_"+tipoBusqueda+"_"+fechaImpresion;
		ByteArrayOutputStream ficheroExcelBytes = caracteresService.getCaracterExcel(tipoBusqueda,valor);
		
		byte[] bytes = ficheroExcelBytes.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
		headers.add("Content-Disposition", "attachmnt; filename =" + nombreReporte +".xls");
		headers.add("filename", nombreReporte +".xls");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK );

		return response;
		
	}
	
	
}
