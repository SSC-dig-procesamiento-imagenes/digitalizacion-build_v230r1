package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ConfigValidadorDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadorConfigDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;

public interface ValidadorConfigHDAO extends BaseDao<ValidadorConfigDTO>{

	ConfigValidadorDTO getConfiguracionActiva(Long idValidador);
	int getCantidadImagenesRestantesPorValidar(Long idValidador);
	void setCantidadImagenesRestantesPorValidar(Long idValidador, Long cantidad);
	void incrementaCantidadImagenesRestantesPorValidar(Long idValidador, int incremento);
	void decrementaCantidadImagenesRestantesPorValidar(Long idValidador, int decremento);
}
