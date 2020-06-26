package mx.com.teclo.digitalizacion.rest.configuraciongeneral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.digitalizacion.persistencia.vo.configuraciongeneral.ConfiguracionGeneralVO;
import mx.com.teclo.digitalizacion.service.configuraciongeneral.ConfigracionGeneralService;


@RestController
@RequestMapping("/ConfigGral")
public class ConfiguracionGeneralController {
	
	@Autowired
	private ConfigracionGeneralService configracionGeneralService;
	
	@RequestMapping(value = "/buscarConfigGeneralPorId", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ConfiguracionGeneralVO> buscarConfigGeneralPorId(@RequestParam(value = "idCongiguraciongeneral") Long idCongiguraciongeneral) {
		ConfiguracionGeneralVO configuracionGeneralVO= configracionGeneralService.buscarConfigGeneralPorId(idCongiguraciongeneral);
		
		return new ResponseEntity<ConfiguracionGeneralVO>(configuracionGeneralVO,HttpStatus.OK);
	}
}
