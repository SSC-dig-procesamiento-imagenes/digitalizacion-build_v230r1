package mx.com.teclo.digitalizacion.api.rest.usuarios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.digitalizacion.negocio.servicios.imagenes.ImagenesService;
import mx.com.teclo.digitalizacion.negocio.servicios.lotes.LotesService;
import mx.com.teclo.digitalizacion.negocio.servicios.usuarios.UsuariosService;
import mx.com.teclo.digitalizacion.negocio.utils.CambioContraseniaVO;
import mx.com.teclo.digitalizacion.negocio.utils.DatosLotesFromUI;
import mx.com.teclo.digitalizacion.negocio.utils.MensajeRetornoVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.AsignacionesDatosVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSinBlobVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.LBImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.EstatusProcesoVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesResultVO;

@RestController
@RequestMapping("/gestionUsuarios")

public class UsuariosRestController {
	
	@Autowired
	private UsuariosService usuariosService;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	

	@PostMapping(value = "/cambiarContrasenia", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasAnyAuthority('CAMBIO_CONTRASENIA')")
	@Transactional(readOnly=false)
	public @ResponseBody ResponseEntity<MensajeRetornoVO> cambioContrasenia(@RequestBody CambioContraseniaVO cambioContraseniaVO) {
		String retornoStr = "La contraseña se cambió correctamente";
		HttpStatus estado = HttpStatus.OK;
		
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		
		try {
			retornoStr = usuariosService.cambiarContrasenia(idUsuario,  cambioContraseniaVO.getNuevaContrasenia(), 
					cambioContraseniaVO.getAntiguaContrasenia(),true);
			
		}catch(Exception ex) {
			System.out.println("Ocurrió un error al intentar cambiar la contraseña: "+ex.getMessage());
			retornoStr = "Ocurrió un error al intentar cambiar la contraseña: "+ex.getMessage();
			estado = HttpStatus.CONFLICT;
		}
		
		ResponseEntity<MensajeRetornoVO> result =
				new ResponseEntity<MensajeRetornoVO>(new MensajeRetornoVO(retornoStr),estado);
		
		return result;
		
	}




}
