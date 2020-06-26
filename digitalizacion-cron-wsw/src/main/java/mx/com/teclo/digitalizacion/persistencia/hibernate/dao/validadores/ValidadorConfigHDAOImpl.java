package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ConfigValidadorDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadorConfigDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;

@Repository
public class ValidadorConfigHDAOImpl extends BaseDaoHibernate<ValidadorConfigDTO> 
	implements ValidadorConfigHDAO {

	@Override
	public ConfigValidadorDTO getConfiguracionActiva(Long idValidador) {
		ConfigValidadorDTO configValidador = null;
		String queryJPA = "	SELECT v.idConfiguracion 						"
				+ "			FROM  ValidadorConfigDTO v  					"
				+ "			WHERE v.idValidador.idValidador = :idValidador  "
				+ "				AND v.stConfigActiva = 1 					";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idValidador", idValidador);
		configValidador = (ConfigValidadorDTO) query.list().get(0);
		
		return configValidador;
	}
	
	private ValidadorConfigDTO getValidadorConfigActiva(Long idValidador) {
		String queryJPA = "	SELECT validadorConfig 											"
				+ "			FROM ValidadorConfigDTO validadorConfig  						"
				+ "			WHERE validadorConfig.idValidador.idValidador = :idValidador 	"
				+ "				AND validadorConfig.stConfigActiva = 1						";

		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idValidador", idValidador);
		ValidadorConfigDTO validadorConfigDTO = (ValidadorConfigDTO) query.list().get(0);
		
		return validadorConfigDTO;
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.ValidadorConfigHDAO#getCantidadImagenesRestantesPorValidar(java.lang.Long)
	 */
	@Override
	public int getCantidadImagenesRestantesPorValidar(Long idValidador) {
		ValidadorConfigDTO validadorConfigDTO = getValidadorConfigActiva(idValidador);
		Long cantidadRetornar = validadorConfigDTO.getCantidadImagenesRestantes();

		return cantidadRetornar.intValue();
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.ValidadorConfigHDAO#setCantidadImagenesRestantesPorValidar(java.lang.Long)
	 */
	@Override
	public void setCantidadImagenesRestantesPorValidar(Long idValidador, Long cantidad) {
		ValidadorConfigDTO validadorConfigDTO = getValidadorConfigActiva(idValidador);
		String queryJPA = "	UPDATE ValidadorConfigDTO validadorConfigDTO 						"
				+ "			SET validadorConfigDTO.nuCantImgRest = :cantidad					"
				+ "			WHERE validadorConfigDTO.stConfigActiva = 1  						"
				+ "				AND validadorConfigDTO.idValidadorConfig = :idValidadorConfig	";
		
		getCurrentSession().createQuery(queryJPA)
		.setParameter("idValidadorConfig", validadorConfigDTO.getIdValidadorConfig())
		.setParameter("cantidad", cantidad)
		.executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.ValidadorConfigHDAO#incrementaCantidadImagenesRestantesPorValidar(java.lang.Long, int)
	 */
	@Override
	public void incrementaCantidadImagenesRestantesPorValidar(Long idValidador, int incremento) {
		operacionSobreCantidadPorValidar(idValidador, incremento, OperacionesCantidadPorValidar.INCREMENTO);
	}
	

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.ValidadorConfigHDAO#decrementaCantidadImagenesRestantesPorValidar(java.lang.Long, int)
	 */
	@Override
	public void decrementaCantidadImagenesRestantesPorValidar(Long idValidador, int decremento) {
		operacionSobreCantidadPorValidar(idValidador, decremento, OperacionesCantidadPorValidar.DECREMENTO);
	}

	private void operacionSobreCantidadPorValidar(Long idValidador, int monto, OperacionesCantidadPorValidar operacion) {
		ValidadorConfigDTO validadorConfigDTO = getValidadorConfigActiva(idValidador);
		Long cantidad = validadorConfigDTO.getCantidadImagenesRestantes();
		Long cantImgMax = validadorConfigDTO.getIdConfiguracion().getNuLimImgAsignacion();
		/*String queryJPA = "	UPDATE ValidadorConfigDTO validadorConfigDTO 						"
				+ "			SET validadorConfigDTO.nuCantImgRest = :cantidad					"
				+ "			WHERE validadorConfigDTO.stConfigActiva = 1  						"
				+ "				AND validadorConfigDTO.idValidadorConfig = :idValidadorConfig	";*/
		
		switch (operacion) {
			case INCREMENTO:{cantidad += monto;}
			case DECREMENTO:{cantidad -= monto;}
		}
		
		if(cantidad < 0 || cantidad > cantImgMax)
			return;//Si no está entre los parámetros permitidos no puede realizarse la operación
		
		validadorConfigDTO.setCantidadImagenesRestantes(cantidad);
		update(validadorConfigDTO);
		
		/*getCurrentSession().createQuery(queryJPA)
		.setParameter("idValidadorConfig", validadorConfigDTO.getIdValidadorConfig())
		.setParameter("cantidad", cantidad)
		.executeUpdate();*/
	}

	private enum OperacionesCantidadPorValidar{
		INCREMENTO, DECREMENTO
	}
	
	

	

}
