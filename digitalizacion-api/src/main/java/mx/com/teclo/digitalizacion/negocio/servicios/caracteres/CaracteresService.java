package mx.com.teclo.digitalizacion.negocio.servicios.caracteres;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.CaracterVO;

public interface CaracteresService {
	List<CaracterVO> getCaracter(String tipoBusqueda,String valor);
	Boolean evaluasaveOrUpdate(CaracterVO caracterVO);
	CaracteresDTO saveOrUpdate(CaracteresDTO caracteresDTO ,CaracterVO caracterVO);
	ByteArrayOutputStream getCaracterExcel(String tipoBusqueda,String valor);
	
	List<CaracterVO> getCaracteresByRespuesta(BigDecimal idRespuesta);
}
