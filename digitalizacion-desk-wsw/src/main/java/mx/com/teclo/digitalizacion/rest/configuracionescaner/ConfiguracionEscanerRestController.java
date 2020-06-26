package mx.com.teclo.digitalizacion.rest.configuracionescaner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.digitalizacion.persistencia.vo.configuracionescaner.ConfiguracionEscanerVO;
import mx.com.teclo.digitalizacion.service.configuracionescaner.ConfigracionEscanerService;


@RestController
@RequestMapping("/configuracionescaner")
public class ConfiguracionEscanerRestController {
	
	@Autowired
	private ConfigracionEscanerService configracionEscanerService;
	
	@RequestMapping(value = "/buscarConfiguracionPorScaner", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ConfiguracionEscanerVO> buscarConfiguracionPorScaner(@RequestParam(value = "idconfigscanner") Long idconfigscanner) {
		ConfiguracionEscanerVO configscan = configracionEscanerService.buscarConfigScanerPorId(idconfigscanner);
		
		return new ResponseEntity<ConfiguracionEscanerVO>(configscan, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscarConfigEscaners", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ConfiguracionEscanerVO>> buscarConfiguracionesEscaners() {
		List<ConfiguracionEscanerVO> ListaConfiguraciones = configracionEscanerService.buscarConfiguracionesEscaners();
		
		return new ResponseEntity<List<ConfiguracionEscanerVO>>(ListaConfiguraciones, HttpStatus.OK);
	}
}
