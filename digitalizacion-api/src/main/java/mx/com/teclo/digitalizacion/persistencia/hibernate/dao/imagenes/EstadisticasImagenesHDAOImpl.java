package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.negocio.vo.estadisticas.FechasConsultasVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstadisticasImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;

@Repository
public class EstadisticasImagenesHDAOImpl 	extends BaseDaoHibernate<EstadisticasImagenesDTO> 
											implements EstadisticasImagenesHDAO {
	
	@Autowired
	private EntityManager em;
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Object[]> getCantidadEvaluacionesPorUsuario(String fechaInicialStr, String fechaFinalStr) {

		fechaInicialStr += " 00:00:00";
		fechaFinalStr += " 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date fechaInicial = new Date();
		Date fechaFinal = new Date();
		try {
			fechaInicial = sdf.parse(fechaInicialStr);
			fechaFinal = sdf.parse(fechaFinalStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Object[]> listaRetornar = null;
		String queryJPA =
				"	SELECT	stat.idValidador.usuario.idUsuario,																		\n" +
				"			stat.idValidador.idValidador,																			\n" +
				"			stat.idValidador.usuario.cdUsuario,																		\n" +
				"			stat.idValidador.usuario.email,																			\n" +
				"			stat.idValidador.usuario.nombreUsuario,																	\n" +
				"			stat.idValidador.usuario.apellidoPaterno,																\n" +
				"			stat.idValidador.usuario.apellidoMaterno,																\n" +
				"			CASE 																									\n" +
				"				WHEN stat.validacion = 0 THEN																		\n" +
				"					'RECHAZADAS'																					\n" +
				"				WHEN stat.validacion = 1 THEN																		\n" +
				"					'ACEPTADAS'																					\n" +
				"				ELSE																								\n" +
				"					'POSPUESTAS'																						\n" +
				"			END AS EVALUACION,																						\n" +
				"			count(stat.idImagen) AS CANTIDAD																		\n" +
				"	FROM	EstadisticasImagenesDTO stat 																			\n" +
				"	WHERE 	stat.fechaValidacion 	BETWEEN  :fechaInicial															\n"+
				"										AND	 :fechaFinal															\n"+
				"	GROUP BY	stat.idValidador.usuario.idUsuario,																						\n" +
				"				stat.idValidador.idValidador,																					\n" +
				"				stat.idValidador.usuario.cdUsuario,																						\n" +
				"				stat.idValidador.usuario.email,																						\n" +
				"				stat.idValidador.usuario.nombreUsuario, 																					\n" +
				"				stat.idValidador.usuario.apellidoPaterno,																					\n" +
				"				stat.idValidador.usuario.apellidoMaterno,																					\n" +
				"				stat.validacion																					\n" +
				"	ORDER BY 	stat.idValidador.usuario.nombreUsuario																						";
		Query query;
		query = getCurrentSession().createQuery(queryJPA)
				.setParameter("fechaInicial", fechaInicial)
				.setParameter("fechaFinal", fechaFinal);

		listaRetornar = query.list();
		
		return listaRetornar;
	}

	
	@Override
	public List<Object[]> getCantidadEvaluacionesPorUsuarioOld(String fechaInicialStr, String fechaFinalStr) {
		
		List<Object[]> listaRetornar = null;
		String querySQL =
				"	SELECT	usu.ID_USUARIO,																							\n" +
				"			val.ID_VALIDADOR,																						\n" +
				"			usu.CD_USUARIO,																							\n" +
				"			usu.NB_EMAIL,																							\n" +
				"			usu.NB_USUARIO,																							\n" +
				"			usu.NB_APATERNO,																						\n" +
				"			usu.NB_AMATERNO,																						\n" +
				"			CASE 																									\n" +
				"				WHEN stat.ST_VALIDADA = 0 THEN																		\n" +
				"					'RECHAZADAS'																					\n" +
				"				WHEN stat.ST_VALIDADA = 1 THEN																		\n" +
				"					'ACEPTADAS'																					\n" +
				"				ELSE																								\n" +
				"					'POSPUESTAS'																						\n" +
				"			END AS EVALUACION,																						\n" +
				"			count(stat.ID_IMAGEN) as \"CANTIDAD\"																	\n" +
				"	FROM	TDP037D_ESTAD_IMG stat 																					\n" +
				"			LEFT JOIN TDP002D_VALIDADORES val ON (val.ID_VALIDADOR = stat.ID_VALIDADOR)								\n" +
				"			LEFT JOIN TAQ025C_SE_USUARIOS usu ON (usu.ID_USUARIO = val.ID_USUARIO)									\n" +
				"	WHERE 	stat.FH_VALIDACION 	BETWEEN  to_date('" + fechaInicialStr + " 00:00:00','DD-MM-YYYY hh24:mi:ss')		\n"+
				"								AND 	 to_date('" + fechaFinalStr   + " 23:59:59','DD-MM-YYYY hh24:mi:ss')		\n"+
				"	GROUP BY	usu.ID_USUARIO,																						\n" +
				"				val.ID_VALIDADOR,																					\n" +
				"				usu.CD_USUARIO,																						\n" +
				"				usu.NB_EMAIL,																						\n" +
				"				usu.NB_USUARIO, 																					\n" +
				"				usu.NB_APATERNO,																					\n" +
				"				usu.NB_AMATERNO,																					\n" +
				"				stat.ST_VALIDADA																					\n" +
				"	ORDER BY 	usu.NB_USUARIO																						";
		Query query;
		query = getCurrentSession().createSQLQuery(querySQL);

		listaRetornar = query.list();
		
		return listaRetornar;
	}

	//@Override
	public List<Object[]> getCantidadEvaluacionesPorUsuarioOld2() {
		List<Object[]> listaRetornar = null;
		String querySQL =
				"	SELECT	usu.ID_USUARIO,																		\n" +
				"			val.ID_VALIDADOR,																	\n" +
				"			usu.CD_USUARIO,																		\n" +
				"			usu.NB_EMAIL,																		\n" +
				"			usu.NB_USUARIO,																		\n" +
				"			usu.NB_APATERNO,																	\n" +
				"			usu.NB_AMATERNO,																	\n" +
				"			CASE 																				\n" +
				"				WHEN stat.ST_VALIDADA = 0 THEN													\n" +
				"					'RECHAZADAS'																\n" +
				"				WHEN stat.ST_VALIDADA = 1 THEN													\n" +
				"					'ACEPTADAS'																	\n" +
				"				ELSE																			\n" +
				"					'POSPUESTAS'																\n" +
				"			END AS EVALUACION,																	\n" +
				"			count(stat.ID_IMAGEN) as \"CANTIDAD\"												\n" +
				"	FROM	TDP037D_ESTAD_IMG stat 																\n" +
				"			LEFT JOIN TDP002D_VALIDADORES val ON (val.ID_VALIDADOR = stat.ID_VALIDADOR)			\n" +
				"			LEFT JOIN TAQ025C_SE_USUARIOS usu ON (usu.ID_USUARIO = val.ID_USUARIO)				\n" +
				"	GROUP BY	usu.ID_USUARIO,																	\n" +
				"				val.ID_VALIDADOR,																\n" +
				"				usu.CD_USUARIO,																	\n" +
				"				usu.NB_EMAIL,																	\n" +
				"				usu.NB_USUARIO, 																\n" +
				"				usu.NB_APATERNO,																\n" +
				"				usu.NB_AMATERNO,																\n" +
				"				stat.ST_VALIDADA																\n" +
				"	ORDER BY 	usu.NB_USUARIO																	";
		Query query;
		query = getCurrentSession().createSQLQuery(querySQL);

		listaRetornar = query.list();
		
		return listaRetornar;
	}

        	@Override
	public List<Object[]> getCantidadEvaluacionesPorUsuario() {
	
		List<Object[]> listaRetornar = null;
		String queryJPA =
				"	SELECT	stat.idValidador.usuario.idUsuario,																		\n" +
				"			stat.idValidador.idValidador,																			\n" +
				"			stat.idValidador.usuario.cdUsuario,																		\n" +
				"			stat.idValidador.usuario.email,																			\n" +
				"			stat.idValidador.usuario.nombreUsuario,																	\n" +
				"			stat.idValidador.usuario.apellidoPaterno,																\n" +
				"			stat.idValidador.usuario.apellidoMaterno,																\n" +
				"			CASE 																									\n" +
				"				WHEN stat.validacion = 0 THEN																		\n" +
				"					'RECHAZADAS'																					"
				+ "				WHEN stat.validacion = 1 THEN																		"
				+ "					'ACEPTADAS'																						\n" +
				"				ELSE																								\n" +
				"					'POSPUESTAS'																					\n" +
				"			END AS EVALUACION,																						\n" +
				"			count(stat.idImagen) AS CANTIDAD																		\n" +
				"	FROM	EstadisticasImagenesDTO stat 																			\n" +
				"	GROUP BY	stat.idValidador.usuario.idUsuario,																	\n" +
				"				stat.idValidador.idValidador,																		\n" +
				"				stat.idValidador.usuario.cdUsuario,																	\n" +
				"				stat.idValidador.usuario.email,																		\n" +
				"				stat.idValidador.usuario.nombreUsuario, 															\n" +
				"				stat.idValidador.usuario.apellidoPaterno,															\n" +
				"				stat.idValidador.usuario.apellidoMaterno,															\n" +
				"				stat.validacion																						\n" +
				"	ORDER BY 	stat.idValidador.usuario.nombreUsuario																	";
		Query query;
		query = getCurrentSession().createQuery(queryJPA);

		listaRetornar = query.list();
		
		//testingEM();

		return listaRetornar;
	}
	
	private void testingEM() {
		String queryJPA = ""
				+ "SELECT img "
				+ "FROM ImagenesDTO img ";
		
		TypedQuery<ImagenesDTO> tq = em.createQuery(queryJPA, ImagenesDTO.class);
		List<ImagenesDTO> imgs = tq.getResultList();
		int cantidad = tq.getMaxResults();
	}


}
