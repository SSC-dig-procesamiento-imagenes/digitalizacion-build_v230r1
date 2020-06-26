package mx.com.teclo.digitalizacion.service.configuracionescaner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.configuracionescaner.ConfiguracionEscanerDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.configuracionescaner.ConfiguracionEscanerDTO;
import mx.com.teclo.digitalizacion.persistencia.vo.configuracionescaner.ConfiguracionEscanerVO;

@Service
public class ConfiguracionEscanerServiceImp implements ConfigracionEscanerService {

	@Autowired
	private ConfiguracionEscanerDAO configuracionEscanerDAO;

	@Transactional(readOnly=true)
	public ConfiguracionEscanerVO buscarConfigScanerPorId(Long idconfigscanner) {
		ConfiguracionEscanerDTO configuracionEscanerDTO = null;
		ConfiguracionEscanerVO configuracionEscanerVO = new ConfiguracionEscanerVO();
		
		configuracionEscanerDTO = configuracionEscanerDAO.findOne(idconfigscanner);
		
		configuracionEscanerVO = ResponseConverter.copiarPropiedadesFull(configuracionEscanerDTO, ConfiguracionEscanerVO.class );
		
		return configuracionEscanerVO;
	}
	
	@Transactional(readOnly=true)
	public List<ConfiguracionEscanerVO> buscarConfiguracionesEscaners(){
		List<ConfiguracionEscanerDTO> listaConfiguracionEscanerDTO = null;
		List<ConfiguracionEscanerVO> listaConfiguracionEscanerVO = new ArrayList<>();
		
		listaConfiguracionEscanerDTO = configuracionEscanerDAO.findAll();
		
		listaConfiguracionEscanerVO = ResponseConverter.converterLista(new ArrayList<>(), listaConfiguracionEscanerDTO, ConfiguracionEscanerVO.class);
		
		return listaConfiguracionEscanerVO;
	}
}
