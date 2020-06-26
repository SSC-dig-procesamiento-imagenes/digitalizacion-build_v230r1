package mx.com.teclo.digitalizacion.service.configuraciongeneral;

import mx.com.teclo.digitalizacion.persistencia.vo.configuraciongeneral.ConfiguracionGeneralVO;

public interface ConfigracionGeneralService {
	
	public ConfiguracionGeneralVO buscarConfigGeneralPorId(Long idCongiguraciongeneral);

}
