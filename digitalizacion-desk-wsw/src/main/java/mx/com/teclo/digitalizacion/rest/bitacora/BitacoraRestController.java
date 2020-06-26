package mx.com.teclo.digitalizacion.rest.bitacora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.digitalizacion.persistencia.vo.bitacora.BitacoraVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.LoteVO;
import mx.com.teclo.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@RestController
@RequestMapping("/bitacora")
public class BitacoraRestController {

	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	
	@RequestMapping(value = "/guardarbitacora", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody int guardarLote(@RequestBody BitacoraVO bitacoraVO) {
		LoteVO loteVO = null;
		System.out.println("Tabla Bitacora: "+bitacoraVO.getTabla());
		System.out.println("origen: "+bitacoraVO.getOrigen()                        );
		//loteVO = lotesService.guadarLote(persistirLoteLoteVO);
		
		int result=bitacoraCambiosService.guardarBitacoraCambiosParametros(
				bitacoraVO.getTabla()==null?"":bitacoraVO.getTabla(), // Nombre de la tabla
				bitacoraVO.getComponenteId()==null?1L:bitacoraVO.getComponenteId(), // Componente
				bitacoraVO.getConceptoId()==null?1L:bitacoraVO.getConceptoId(), // Concepto
				bitacoraVO.getValorOriginal()==null?"":bitacoraVO.getValorOriginal(), // Valor Original del registro.
				bitacoraVO.getValorFinal()==null?"":bitacoraVO.getValorFinal(), // Valor final del registro. 
				bitacoraVO.getModificadoPor()==null?1L:bitacoraVO.getModificadoPor(), // Id del usuario que  realiza el cambio 
				bitacoraVO.getIdRegistro()==null?"":bitacoraVO.getIdRegistro(), // IdRegistro   
				bitacoraVO.getRegistroDescripcion()==null?"":bitacoraVO.getRegistroDescripcion(),	// Descripcion del registro modificado.
				bitacoraVO.getOrigen()==null?"":bitacoraVO.getOrigen()); // Origen W / H
		
		return result;
	}
}
