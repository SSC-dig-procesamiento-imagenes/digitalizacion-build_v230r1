package mx.com.teclo.digitalizacion.service.configuracionescaner;

import java.util.List;

import mx.com.teclo.digitalizacion.persistencia.vo.configuracionescaner.ConfiguracionEscanerVO;

public interface ConfigracionEscanerService {
	
	public ConfiguracionEscanerVO buscarConfigScanerPorId(Long idplantilla);
	
	public List<ConfiguracionEscanerVO> buscarConfiguracionesEscaners();

}
