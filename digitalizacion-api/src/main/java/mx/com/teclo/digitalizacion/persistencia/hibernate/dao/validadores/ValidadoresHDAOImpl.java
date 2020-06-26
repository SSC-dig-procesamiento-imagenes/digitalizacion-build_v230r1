package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;

@Repository
public class ValidadoresHDAOImpl extends BaseDaoHibernate<ValidadoresDTO>
	implements ValidadoresHDAO {

	@Override
	public Long getIdUsuario(Long idValidador) {
		ValidadoresDTO validador = getCurrentSession().get(ValidadoresDTO.class, idValidador);
		
		return validador.getIdUsuario();
	}

	@Override
	public ValidadoresDTO getValidadorByIdUsuario(Long idUsuario) {
		ValidadoresDTO validador;
		String queryJPA = "	SELECT v 						"
				+ "			FROM ValidadoresDTO v 			"
				+ "			WHERE v.idUsuario = :idUsuario	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idUsuario", idUsuario);
		validador = (ValidadoresDTO)query.list().get(0);
		
		return validador;
	}

	@Override
	public ValidadoresDTO getValidadorByIdValidador(Long idValidador) {
		ValidadoresDTO validador = getCurrentSession().get(ValidadoresDTO.class, idValidador);
		
		return validador;
	}


}
