package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores;

import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValHistDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO;

@Repository
public class AsignValHistHDAOImpl extends BaseDaoHibernate<AsignValHistDTO> implements AsignValHistHDAO {

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValHistHDAO#addNuevaAsignacion(mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValHistDTO)
	 */
	@Override
	public void addNuevaAsignacionHistorica(AsignValHistDTO asignValHistDTO) {
		getCurrentSession().save(asignValHistDTO);
		
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValHistHDAO#addNuevaAsignacionHistoricaByAsignacion(mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO)
	 */
	@Override
	public void addNuevaAsignacionHistoricaByAsignacion(AsignValidacionDTO asignValidacionDTO) {
		AsignValHistDTO nuevoHistorico = new AsignValHistDTO();
		
		nuevoHistorico.setFhAsignacion(asignValidacionDTO.getFhAsignacion());
		nuevoHistorico.setIdAsignValidador(asignValidacionDTO.getIdAsignValidador());
		nuevoHistorico.setIdImagen(asignValidacionDTO.getIdImagen());
		nuevoHistorico.setIdValidador(asignValidacionDTO.getIdValidador());
		nuevoHistorico.setStActivo((short) 0);
		
		
		getCurrentSession().save(nuevoHistorico);
	}

	
}
