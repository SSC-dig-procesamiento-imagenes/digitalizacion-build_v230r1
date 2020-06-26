package mx.com.teclo.digitalizacion.negocio.servicios.usuarios;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.digitalizacion.bitacora.BitacoraComponentesEnum;
import mx.com.teclo.digitalizacion.bitacora.BitacoraConceptosEnum;
import mx.com.teclo.digitalizacion.bitacora.ParametrosBitacoraEnum;
import mx.com.teclo.digitalizacion.negocio.utils.Utils;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresImagenValidada;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSinBlobVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.EstatusProcesoVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesResultVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.ImagenesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.LotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.StatusLotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.usuarios.UsuariosHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LoteDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.UsuariosDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstatusProcesoDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;

@Service
public class UsuariosServiceImpl implements UsuariosService {
	
	@Autowired
	private BitacoraCambiosService  bitacoraCambiosService;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@Autowired
	private UsuariosHDAO usuariosHDAO;

	@Override
	@Transactional(readOnly = false)
	public String cambiarContrasenia(Long idUsuario,  String nueva, String antiguaContrasenia, boolean matchPattern ) {
		UsuariosDTO usuario = usuariosHDAO.findOne(idUsuario);
		Pattern patron = Pattern.compile(
				"(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[a-zA-Z0-9$@$!%*?&](?=.*[!#$%&()*+,-./:;<=>?@"
						+ "\"[" + "\\" + "\"]" + "_|]){8,}");
		if(nueva == null || antiguaContrasenia == null || nueva.length() == 0 || antiguaContrasenia.length() == 0) {
			return "Las contraseñas no pueden estar vacías";
		}
		
		Matcher muestra = patron.matcher(nueva);
		
		if(matchPattern) {
			if(!muestra.find() || muestra.group(0) == null) {
				return "La contraseña introducida no cumple con los requisitos de seguridad mínimos.";
			}
		}
		
		
		String antiguaEcriptada = usuariosHDAO.encriptarCampo(antiguaContrasenia);
		String antiguaNoEncriptada = usuariosHDAO.desencriptarCampo(usuariosHDAO.findOne(idUsuario).getContrasenia());
		
		if(!antiguaNoEncriptada.equals(antiguaContrasenia)) {
			return "La contraseña que introdujo para el usuario " + usuario.getCdUsuario()+" es incorrecta.";
		}
		
		String nuevaEncriptada = usuariosHDAO.encriptarCampo(nueva);
		if(nueva.equals(antiguaNoEncriptada)) {
			return "La contraseña introducida no puede ser igual a la actual.";
		}
		
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
				BitacoraComponentesEnum.CAMBIO_DE_CONTRASENA.getValor(), 
				BitacoraConceptosEnum.CAMBIO_DE_CONTRASENA.getValor(), 
				"****",
				"****",
				usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
				usuario.getIdUsuario()==null?"":usuario.getIdUsuario().toString(),//--------
				usuario.getNombreUsuario()==null?"":usuario.getNombreUsuario(), 
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		
		usuario.setContrasenia(nuevaEncriptada);
		usuario.setFechaModificacionContrasenia(new Date());
		usuariosHDAO.update(usuario);
		
		return "La contraseña se cambió correctamente.";
	}
	
	

}
