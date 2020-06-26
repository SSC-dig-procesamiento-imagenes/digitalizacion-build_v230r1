package mx.com.teclo.digitalizacion.api.rest.respuestas;

import java.io.ByteArrayOutputStream;
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
import mx.com.teclo.digitalizacion.negocio.servicios.respuestas.RespuestasService;
import mx.com.teclo.digitalizacion.negocio.utils.RutinasTiempoImpl;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PlantillasRespuestasDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.RespuestaCaracterVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.RespuestaVO;

@RestController
@RequestMapping("/respuestas")
public class RespuestasRestController {
	@Autowired
	RespuestasService respuestasservice;
	
	@RequestMapping(value="/getRespuestas",method=RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('CARACTERES')")		
	public ResponseEntity<List<RespuestaVO>> buscarRespuestas(@RequestParam(value = "tipoBusqueda") String tipoBusqueda,@RequestParam(value = "valor")String valor)
			throws NotFoundException {
		List<RespuestaVO> listaConsulta = respuestasservice.getRespuestas(tipoBusqueda,valor);
		if (listaConsulta.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<RespuestaVO>>(listaConsulta, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveRespuesta", method = RequestMethod.POST)
	// @PreAuthorize("hasAnyAuthority('CARACTERES')")
	public ResponseEntity<PlantillasRespuestasDTO> saveOrUpdate(@RequestBody RespuestaCaracterVO caracterVO) throws NotFoundException {
		PlantillasRespuestasDTO realizarOperacion = respuestasservice.saveRespuesta(caracterVO);
		if (realizarOperacion.equals(new PlantillasRespuestasDTO())) {
			throw new NotFoundException("El nombre de respuesta ya existe");
		}
		return new ResponseEntity<PlantillasRespuestasDTO>(realizarOperacion, HttpStatus.OK);
	}

	@RequestMapping(value = "/descargaExcel", method = RequestMethod.GET, produces = "application/json")
	///@PreAuthorize("hasAnyAuthority('XX')")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<byte[]>getficheroExcelBusqueda(@RequestParam(value = "tipoBusqueda") String tipoBusqueda,@RequestParam(value = "valor") String valor) {
		RutinasTiempoImpl f =new RutinasTiempoImpl();
		String fechaImpresion=f.getFecha_ddMMYYYY_hhmmss(new Date());
		String nombreReporte = "REPORTE_GENERAL_RESPUESTA_"+tipoBusqueda+"_"+fechaImpresion;
		ByteArrayOutputStream ficheroExcelBytes = respuestasservice.getRespuestaExcel(tipoBusqueda,valor);
		
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
