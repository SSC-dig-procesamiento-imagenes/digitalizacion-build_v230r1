package mx.com.teclo.digitalizacion.service.imagenes;

import mx.com.teclo.digitalizacion.persistencia.vo.Imagenes.ImagenesGuardarVO;

public interface ImagenesService {
	
	Boolean guardarInformacionImagen (ImagenesGuardarVO imagenesVO);
}
