package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValHistDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PermisoPospuestasDTO;

public interface PermisoPospuestasHDAO extends BaseDao<PermisoPospuestasDTO>{

	Boolean puedeEvaluarPospuestas(String cdPerfil);
	
}
