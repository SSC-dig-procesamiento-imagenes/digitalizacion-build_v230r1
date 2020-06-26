package mx.com.teclo.digitalizacion.negocio.servicios.respuestas;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PlantillasRespuestasDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.CaracterVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.RespuestaCaracterVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.RespuestaVO;

public interface RespuestasService {
	List<RespuestaVO> getRespuestas(String tipoBusqueda,String valor);
	PlantillasRespuestasDTO saveRespuesta(RespuestaCaracterVO caracterVO);
	ByteArrayOutputStream getRespuestaExcel(String tipoBusqueda, String valor);
}
