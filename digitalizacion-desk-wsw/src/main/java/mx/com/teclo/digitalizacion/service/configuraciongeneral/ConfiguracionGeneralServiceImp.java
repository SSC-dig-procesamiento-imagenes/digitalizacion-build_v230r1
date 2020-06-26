package mx.com.teclo.digitalizacion.service.configuraciongeneral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.configuraciongeneral.ConfiguracionGeneralDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.configuraciongeneral.ConfiguracionGeneralDTO;
import mx.com.teclo.digitalizacion.persistencia.vo.configuraciongeneral.ConfiguracionGeneralVO;

@Service
public class ConfiguracionGeneralServiceImp implements ConfigracionGeneralService {

	@Autowired
	private ConfiguracionGeneralDAO configuracionGeneralDAO;

	@Transactional(readOnly=true)
	public ConfiguracionGeneralVO buscarConfigGeneralPorId(Long idCongiguraciongeneral) {
		ConfiguracionGeneralDTO configuracionGeneralDTO = null;
		ConfiguracionGeneralVO configuracionGeneralVO = new ConfiguracionGeneralVO();
		
		configuracionGeneralDTO = configuracionGeneralDAO.findOne(idCongiguraciongeneral);
		
		configuracionGeneralVO = ResponseConverter.copiarPropiedadesFull(configuracionGeneralDTO, ConfiguracionGeneralVO.class );
		
		return configuracionGeneralVO;
	}
}
