package mx.com.teclo.digitalizacion.negocio.servicios.gestionPlantillas.campos;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CamposDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.CaracterVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.plantillas.gestionPlantillas.CamposVO;

public interface CamposService {
	public List<CamposVO> getCampos(String tipoBusqueda,String valor);
	Boolean evaluasaveOrUpdate(CamposVO camposVO);
	CamposDTO saveOrUpdate(CamposDTO camposDTO ,CamposVO camposVO);
	ByteArrayOutputStream getCaracterExcel(String tipoBusqueda,String valor);

}
