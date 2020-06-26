package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValHistDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO;

public interface AsignValHistHDAO extends BaseDao<AsignValHistDTO>{
	void addNuevaAsignacionHistorica(AsignValHistDTO asignValHistDTO);
	void addNuevaAsignacionHistoricaByAsignacion(AsignValidacionDTO asignValidacionDTO);

}
