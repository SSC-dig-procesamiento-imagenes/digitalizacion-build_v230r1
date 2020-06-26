package mx.com.teclo.digitalizacion.negocio.servicios.gestionPlantillas.campos;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.gestionPlantillas.campos.CamposDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.gestionPlantillas.orientaciones.OrientacionesDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CamposDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.plantillas.gestionPlantillas.CamposVO;

@Service
public class CamposServiceImpl implements CamposService {

	@Autowired
	private CamposDAO camposDAO;
	@Autowired
	private OrientacionesDAO orientacionesDAO;

	@Override
	@Transactional(readOnly = true)
	public List<CamposVO> getCampos(String tipoBusqueda, String valor) {
		List<CamposDTO> listcamposDTO = new ArrayList<CamposDTO>();
		List<CamposVO> listCampoVO = new ArrayList<CamposVO>();
		/// List<Pl antillasRespuestasDTO> lisRespuestasVO=new
		/// ArrayList<PlantillasRespuestasDTO>();
		// lisRespuestasVO=respuestasDAO.getRespuestaAndContador();

		switch (tipoBusqueda) {
		case "TODOS":
			listcamposDTO = camposDAO.findAll();
			listCampoVO = ResponseConverter.converterLista(new ArrayList<>(), listcamposDTO, CamposVO.class);
			break;
		case "NOMBRE":
			listcamposDTO = camposDAO.getCampoByConsidencia(valor);
			listCampoVO = ResponseConverter.converterLista(new ArrayList<>(), listcamposDTO, CamposVO.class);

			break;
		case "ESTATUS":
			if (valor.equals("*")) {
				listcamposDTO = camposDAO.getCampoAll();
				listCampoVO = ResponseConverter.converterLista(new ArrayList<>(), listcamposDTO, CamposVO.class);

			} else
				listcamposDTO = camposDAO.getCampoByEstatus(valor);
			listCampoVO = ResponseConverter.converterLista(new ArrayList<>(), listcamposDTO, CamposVO.class);

			break;
		default:
			break;
		}

		return listCampoVO;
	}


	@Override
	public CamposDTO saveOrUpdate(CamposDTO camposDTO, CamposVO camposVO) {
		// TODO Auto-generated method stub
		if (camposVO.getId()!=null) {
			camposDTO =camposDAO.findOne(camposVO.getId());
		}
		camposDTO.setCoordenadafx(camposVO.getCoordenadafx());
		camposDTO.setCoordenadafy(camposVO.getCoordenadafy());
		camposDTO.setCoordenadaix(camposVO.getCoordenadaix());
		camposDTO.setCoordenadaiy(camposVO.getCoordenadaiy());
		camposDTO.setNombre(camposVO.getNombre());
		camposDTO.setOrientacionesId(orientacionesDAO.findOne(camposVO.getOrientacionesId().getId()));
		camposDTO.setPlantillasId(null);
		return null;
	}

	@Override
	public ByteArrayOutputStream getCaracterExcel(String tipoBusqueda, String valor) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public Boolean evaluasaveOrUpdate(CamposVO camposVO) {
		Boolean operacion=false;
		CamposDTO valdarCampo=camposDAO.getCampoByName(camposVO.getNombre());
				CamposDTO campoOpe=new CamposDTO();
		if (valdarCampo==null) {	
			CamposDTO campoSave =new CamposDTO();
			campoOpe=saveOrUpdate(campoSave,camposVO);
			operacion=true;
		}else if(valdarCampo!=null && valdarCampo.getId().equals(camposVO.getId())){	
			campoOpe=saveOrUpdate(valdarCampo,camposVO);
			operacion=true;
		}
		return operacion;
	}

}
