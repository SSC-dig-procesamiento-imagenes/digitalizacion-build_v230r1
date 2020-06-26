package mx.com.teclo.digitalizacion.negocio.servicios.usuarios;
import java.util.Date;
import java.util.List;

import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSinBlobVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.*;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.UsuariosDTO;

public interface UsuariosService {

	String cambiarContrasenia(Long idUsuario, String nueva, String antiguaContrasenia, boolean matchPattern);
	
}
