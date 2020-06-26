package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.bitacora.BitacoraComponentesEnum;
import mx.com.teclo.digitalizacion.bitacora.BitacoraConceptosEnum;
import mx.com.teclo.digitalizacion.bitacora.ParametrosBitacoraEnum;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;

@Repository
public class AsignValidacionHDAOImpl extends BaseDaoHibernate<AsignValidacionDTO>
		implements AsignValidacionHDAO {
	
	@Autowired
	private BitacoraCambiosService  bitacoraCambiosService;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@SuppressWarnings("unchecked")
	@Override
	public List<AsignValidacionDTO> getTodasAsignacionesActivas(ValidadoresDTO validador) {
		List<AsignValidacionDTO> listaAsignaciones = new ArrayList<>();
		String queryJPA = 
				"	SELECT asign 							"
				+"	FROM AsignValidacionDTO asign 			"
				+"	WHERE asign.idValidador = :validador 	"
				+"		AND asign.stActivo = 1 				"
				+"		AND asign.stPospuesta IS NULL		";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("validador", validador);
		listaAsignaciones = query.list();
		
		return listaAsignaciones;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenesDTO> getTodasImagenesAsignadasActivas(ValidadoresDTO validador) {
		List<ImagenesDTO> listaImagenes = new ArrayList<>();
		String queryJPA = "	SELECT asign.idImagen 					"
				+ "			FROM AsignValidacionDTO asign 			"
				+ "			WHERE  asign.idValidador = :validador 	"
				+ "				AND asign.stActivo = 1 				"
				+ "				AND asign.stPospuesta IS NULL	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("validador", validador);
		listaImagenes =  query.list();

		return listaImagenes;
	}

	@Override
	public Long asignarImagenValidador(ImagenesDTO imagenAdicionar, ValidadoresDTO validador) {
		AsignValidacionDTO asignacion = new AsignValidacionDTO();
		Long idRetornado;
		
		asignacion.setIdImagen(imagenAdicionar);
		asignacion.setIdValidador(validador);
		asignacion.setFhAsignacion(new Date(System.currentTimeMillis()));
		asignacion.setStActivo((short) 1);
		idRetornado = (Long) getCurrentSession().save(asignacion);
	
		return idRetornado;
	}

	@Override
	public void asignarImagenesValidador(List<ImagenesDTO> imagenesAdicionar, ValidadoresDTO validador) {
		
		for(ImagenesDTO imagen: imagenesAdicionar) {
			asignarImagenValidador(imagen,validador);
		}
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#deleteAsignacion(mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO)
	 */
	@Override
	public void deleteAsignacion(AsignValidacionDTO asign) {
		String queryJPA = " DELETE FROM AsignValidacionDTO asign				"
				+ "			WHERE asign.idAsignValidador = :idAsignValidador 	"
				+ "					AND asign.stPospuesta IS NULL				";
		
		getCurrentSession().createQuery(queryJPA)
		.setParameter("idAsignValidador", asign.getIdAsignValidador())
		.executeUpdate();

	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#getAsignacionActivaByImagen(mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO)
	 */
	@Override
	public AsignValidacionDTO getAsignacionActivaByImagen(ImagenesDTO imagenDTO) {
		String queryJPA = " SELECT asign 						"
				+ "			FROM AsignValidacionDTO asign  		"
				+ "			WHERE  asign.idImagen = :imagenDTO	"
				+ "				AND asign.stActivo = 1			";
		
		Query query = getCurrentSession().createQuery(queryJPA)
						.setParameter("imagenDTO", imagenDTO);
		AsignValidacionDTO asignValidacionDTO = (AsignValidacionDTO) query.list().get(0);
		
		return asignValidacionDTO;
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#getTodasImagenesEnUI(mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenesDTO> getTodasImagenesEnUI(ValidadoresDTO validador) {
		String queryJPA = "	SELECT asign.idImagen					"
				+ "			FROM AsignValidacionDTO asign 			"
				+ "			WHERE asign.idValidador = :validador 	"
				+ "				AND asign.stActivo = 1 				"
				+ "				AND asign.imagenEnUI = 1 			"
				+ "				AND asign.stPospuesta IS NULL		";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("validador", validador);
		List<ImagenesDTO> imagenesEnUI = query.list();
		
		
		return imagenesEnUI;
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#setImagenesEnUI(java.util.List, mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO)
	 */
	@Override
	public void setImagenesEnUI(List<ImagenesDTO> imagenes, ValidadoresDTO validador) {
		for(ImagenesDTO imagen :imagenes) {
			setImagenEnUI(imagen, validador);
		}
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#setImagenEnUI(mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO, mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO)
	 */
	@Override
	public void setImagenEnUI(ImagenesDTO imagen, ValidadoresDTO validador) {
		String queryJPA = "	UPDATE AsignValidacionDTO asign				"
				+ "			SET asign.imagenEnUI = 1 					"
				+ "				WHERE asign.idImagen = :imagen 			"
				+ "					AND asign.idValidador = :validador	"
				+ "					AND asign.stActivo = 1 				"
				+ "					AND asign.stPospuesta IS NULL		";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("imagen", imagen)
				.setParameter("validador", validador);
		query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#isAsignadaImagenAValidador(mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO, mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO)
	 */
	@Override
	public boolean isAsignadaImagenAValidadorEnUI(ImagenesDTO imagen, ValidadoresDTO validador) {
		String queryJPA = " SELECT asign							"
				+ "			FROM AsignValidacionDTO asign  			"
				+ "			WHERE asign.idImagen = :imagen 			"
				+ "				AND asign.idValidador = :validador 	"
				+ "				AND asign.stActivo = 1				"
				+ "				AND asign.imagenEnUI = 1 			"
				+ "				AND asign.stPospuesta IS NULL		";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("imagen", imagen)
				.setParameter("validador", validador);
		boolean isAsignada = query.list().size() > 0;
		
		return isAsignada;
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#setImagenesEnUIById(java.util.List, mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO)
	 */
	@Override
	public void setImagenesEnUIById(List<Long> idImagenes, ValidadoresDTO validador) {
		for (Long idImagen : idImagenes) {
			setImagenEnUIById(idImagen, validador);
		}
		
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#setImagenEnUIById(java.lang.Long, mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO)
	 */
	@Override
	public void setImagenEnUIById(Long idImagen, ValidadoresDTO validador) {
		String queryJPA = "	UPDATE AsignValidacionDTO asign					"
				+ "			SET asign.imagenEnUI = 1 						"
				+ "				WHERE asign.idImagen.idImagen = :idImagen	"
				+ "					AND asign.idValidador = :validador 		"
				+ "					AND asign.stActivo = 1 					"
				+ "					AND asign.stPospuesta IS NULL			";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idImagen", idImagen)
				.setParameter("validador", validador);
		query.executeUpdate();
		
	}

	@Override
	public List<Object[]> getAsignacionesActivasData() {
		String querySQL = 
			"	SELECT 	validador.ID_VALIDADOR as idValidador, 																\n" +
			"			usuario.CD_USUARIO as nickName, 																		\n" +
			"			usuario.NB_USUARIO AS nombre,																			\n" +
			"			usuario.NB_APATERNO AS apellidoP,																		\n" +
			"			usuario.NB_EMAIL AS email,																				\n" +
			"			count(imagen.ID_IMAGEN) as cantImagenesAsignadas,																\n" +
			"			count(asignacion.ST_IMG_EN_UI) as cantImagenesValidandose,														\n" +
			"			validador.FH_ULTIMA_OPER	as fechaUltimaOperacion																\n" +
			"	FROM 	TAQ025C_SE_USUARIOS usuario 																				\n" +
			"			RIGHT OUTER JOIN TDP002D_VALIDADORES validador ON (usuario.ID_USUARIO = validador.ID_USUARIO)				\n" +
			"			RIGHT OUTER JOIN TDP006D_ASIGN_VALIDACION asignacion ON (asignacion.ID_VALIDADOR = validador.ID_VALIDADOR) 	\n" +
			"			LEFT OUTER JOIN TDP001D_IMAGENES imagen ON (imagen.ID_IMAGEN = asignacion.ID_IMAGEN)						\n" +
			"	WHERE asignacion.ST_ACTIVO = 1																						\n" +

			"	GROUP BY 	validador.ID_VALIDADOR, 																				\n" +
			"				usuario.CD_USUARIO, 																					\n" +
			"				usuario.NB_USUARIO,																						\n" +
			"				usuario.NB_APATERNO,																					\n" +
			"				usuario.NB_EMAIL,																						\n" +
			"				validador.FH_ULTIMA_OPER																						\n"; 
		Query query = getCurrentSession().createSQLQuery(querySQL).addScalar("idValidador", LongType.INSTANCE)
				.addScalar("nickName", StringType.INSTANCE)
				.addScalar("nombre", StringType.INSTANCE)
				.addScalar("apellidoP", StringType.INSTANCE)
				.addScalar("email", StringType.INSTANCE)
				.addScalar("cantImagenesAsignadas", LongType.INSTANCE)
				.addScalar("cantImagenesValidandose", LongType.INSTANCE)
				.addScalar("fechaUltimaOperacion",  DateType.INSTANCE);
		List<Object[]> listaObjetos = query.list();

		return listaObjetos;
		
	}

	/*
	 * Borra las asignaciones activas del validador
	 * */
	@Override
	public void deleteAsignacionesActivasPorValidador(Long idValidador) {
		guardarBitacoraDeleteAsignacionesActivas(idValidador);
		deleteAsignacionesPorValidador(idValidador,(short) 1);
		
	}
	
	private void guardarBitacoraDeleteAsignacionesActivas(Long idValidador) {
		String queryJPA =
				"SELECT	asign											"
			  + "FROM	AsignValidacionDTO asign						"
			  + "WHERE	asign.idValidador.idValidador = :idValidador	"
			  + "		AND asign.stActivo = 1							"
			  + "		AND asign.stPospuesta IS NULL					";
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idValidador", idValidador);
		@SuppressWarnings("unchecked")
		List<AsignValidacionDTO> listaAsignaciones = query.list();
		
		if(listaAsignaciones != null && !listaAsignaciones.isEmpty()) {
			for(AsignValidacionDTO asignacion : listaAsignaciones) {
				bitacoraCambiosService.guardarBitacoraCambiosParametros(
						ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
						BitacoraComponentesEnum.GESTION_DE_ASIGNACIONES.getValor(), 
						BitacoraConceptosEnum.CANCELACION_DE_ASIGNACIONES.getValor(), 
						asignacion.getIdImagen()==null?"":asignacion.getIdImagen().getIdImagen().toString(),//--
						"",
						usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
						asignacion.getIdValidador()==null?"":asignacion.getIdValidador().toString(),//--
						"ID del usuario: "+asignacion.getIdValidador()==null?"":asignacion.getIdValidador().getIdUsuario().toString(), 
						ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			}
		}
		
	}
	
	@Override
	public void deleteAsignacionesNoActivasPorValidador(Long idValidador) {
		deleteAsignacionesPorValidador(idValidador, (short) 0);
		
	}

	private void deleteAsignacionesPorValidador(Long idValidador, short estado) {
		String queryJPA = 
				""
				+ "	DELETE FROM AsignValidacionDTO asign  					"
				+ "	WHERE asign.idValidador.idValidador = :idValidador	"
				+ "			AND asign.stActivo = :estado  				"
				+ "			AND asign.stPospuesta IS NULL				";
			Query query = getCurrentSession().createQuery(queryJPA)
					.setParameter("idValidador", idValidador)
					.setParameter("estado", estado);
			query.executeUpdate();
	}

	/*Devuelve todas las asignaciones activas y no pospuestas de un validador*/
	@SuppressWarnings("unchecked")
	@Override
	public List<AsignValidacionDTO> getTodasAsignaciones(Long idValidador) {
		String queryJPA = 
			"	SELECT asign 										"
			+ "	FROM AsignValidacionDTO asign 						"
			+ "	WHERE  asign.idValidador.idValidador = :idValidador	"
			+ "	AND asign.stPospuesta IS NULL						";
		List<AsignValidacionDTO> listaRetornar = new ArrayList<>();
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idValidador", idValidador);
		listaRetornar = query.list();
		
		return listaRetornar;
	}

	@Override
	@Transactional
	public Long getCantidadBoletasPospuestasByValidador(Long idValidador) {
		Long cantidadRetorno = Long.valueOf(0);
		String queryJPA = 
				"	SELECT COUNT(asign) 								"
				+ "	FROM AsignValidacionDTO asign 						"
				+ "	WHERE asign.idValidador.idValidador = :idValidador	"
				+ "	AND asign.stPospuesta = 1							";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idValidador", idValidador);
		
		
		  List<Object[]> lista = query.list(); 
		  Object val = lista.get(0);
		  
		  cantidadRetorno = (Long)val;
	
		return cantidadRetorno;

	}

	@Override
	public AsignValidacionDTO getAsignacionByIdValidadorAndIdImagen(Long idValidador, Long idImagen) {
		AsignValidacionDTO asignacionRetorno = null;
		String queryJPA = ""
				+ "	SELECT asign 										"
				+ "	FROM AsignValidacionDTO asign						"
				+ "	WHERE  asign.idValidador.idValidador = :idValidador	"
				+ "		AND asign.stPospuesta IS NULL					"
				+ "		AND asign.stActivo = 1							"
				+ "		AND asign.idImagen.idImagen = :idImagen			";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idValidador", idValidador)
				.setParameter("idImagen", idImagen);
		
		asignacionRetorno = (AsignValidacionDTO) query.list().get(0);
		
		return asignacionRetorno;
	}

	@Override
	public List<ImagenesDTO> getTodasImagenesPospuestasByValidador(Long idValidador) {
		String queryJPA = ""
				+ "	SELECT asign.idImagen 								"
				+ "	FROM AsignValidacionDTO asign						"
				+ "	WHERE  asign.idValidador.idValidador = :idValidador	"
				+ "		AND asign.stPospuesta IS NOT NULL				"
				+ "		AND asign.stActivo = 1							"
				+ "	ORDER BY asign.idValidador.idValidador				";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idValidador", idValidador);

		List<ImagenesDTO> listaRetorno = query.list();

		return listaRetorno;

	}

	@Override
	public Long getCantImagenesPospuestasByValidador(Long idValidador) {
		String queryJPA = ""
				+ "	SELECT count(asign.idImagen) 								"
				+ "	FROM AsignValidacionDTO asign						"
				+ "	WHERE  asign.idValidador.idValidador = :idValidador	"
				+ "		AND asign.stPospuesta IS NOT NULL				"
				+ "		AND asign.stActivo = 1							";

		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idValidador", idValidador);

		Long cantidad = (Long) query.uniqueResult();

		return cantidad;

	}
	
	
	@Override
	public Boolean existenAsignacionesPospuestas() {
		String queryJPA = ""
				+ "	SELECT count(asign.idImagen) 								"
				+ "	FROM AsignValidacionDTO asign						"
				+ "	WHERE asign.stPospuesta IS NOT NULL	"
				+ "		AND asign.stActivo = 1							";

		
		Query query = getCurrentSession().createQuery(queryJPA);

		Long cantidad = (Long) query.uniqueResult();

		return cantidad > 0 ? true : false;
	}

	@Override
	public List<ImagenesDTO> getTodasImagenesPospuestas() {
		String queryJPA = ""
				+ "	SELECT asign.idImagen					"
				+ "	FROM AsignValidacionDTO asign			"
				+ "	WHERE asign.stPospuesta IS NOT NULL		"
				+ "		AND asign.stActivo = 1				"
				+ "	ORDER BY asign.idValidador.idValidador	";

		Query query = getCurrentSession().createQuery(queryJPA);
		List<ImagenesDTO> listaRetorno = query.list();

		return listaRetorno;
	}

	@Override
	public List<ImagenesVO> getTodasImagenesPospuestasSinBlobByValidador(Long idValidador) {
		String queryJPA0 = "	SELECT NEW mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO(asign.idImagen.idImagen)	"
				+  "FROM AsignValidacionDTO asign			"
		+ "	WHERE  asign.idValidador.idValidador = :idValidador																						"
		+ "		AND asign.stPospuesta IS NOT NULL																									"
		+ "		AND asign.stActivo = 1																												";

		String queryJPA = ""
				+ "	SELECT NEW mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO(asign.idImagen.idImagen, asign.idImagen.codigoPlaca,				"
				+ "			asign.idImagen.numeroLicencia, asign.idImagen.codigoTipo, asign.idImagen.nbLicExpEn, asign.idImagen.nbPlacaExpEn,				"
				+ "			asign.idImagen.nuArtInfrac, asign.idImagen.nuFraccion, asign.idImagen.nuInciso, asign.idImagen.nuParrafo,						"
				+ "			asign.idImagen.codigoPlacaOficial, asign.idImagen.nbUtDelegacion, asign.idImagen.nuNumeroFolio, asign.idImagen.fhInfraccion,	"
				+ "			asign.idImagen.stLiberada, asign.idImagen.stValidada, asign.idImagen.idLote.idLote, asign.idImagen.nombreImagen,				"
				+ "			asign.idImagen.fechaLiberacion, asign.idImagen.stDuplicada,	asign.idImagen.txPospuesta, asign.idImagen.txRechazada,				"
				+ "			asign.idImagen.idValidador.usuario.cdUsuario, asign.idImagen.idValidadorPosp.usuario.cdUsuario,									"
				+ "			asign.idImagen.vehiculoMarca.idMarca, asign.idImagen.vehiculoMarca.codigoMarca, asign.idImagen.vehiculoMarca.nombreMarca)		"
				+ "	FROM AsignValidacionDTO asign																											"
				+ "	WHERE  asign.idValidador.idValidador = :idValidador																						"
				+ "		AND asign.stPospuesta IS NOT NULL																									"
				+ "		AND asign.stActivo = 1																												";
				//+ "	ORDER BY asign.idValidador.idValidador																									";
		
		Query query = getCurrentSession().createQuery(queryJPA0)
				.setParameter("idValidador", idValidador);

		List<ImagenesVO> listaRetorno = query.list();

		return listaRetorno;
	}

	@Override
	public List<ImagenesDTO> getImagenesPospuestasParam(Long idValidador, Date fechaInicial, Date fechaFinal) {
		String queryJPA = ""
				+ "	SELECT asign.idImagen 										"
				+ "	FROM AsignValidacionDTO asign 								"
				+"	WHERE asign.stPospuesta IS NOT NULL AND	asign.stActivo = 1	";
		String qIdValidador = ""
				+ "	asign.idValidador.idValidador = :idValidador				";
		String qAnd = " AND 													";
		String qFechas = ""
				+ "	asign.fhAsignacion BETWEEN :fechaInicial AND :fechaFinal	";
		Boolean tieneId = false;
		Boolean tieneFechas = false;

		
		if(idValidador != null ) {
			queryJPA += qAnd;
			queryJPA += qIdValidador;
			tieneId = true;
		}
		
		if(fechaInicial != null && fechaFinal != null) {
			tieneFechas = true;
			queryJPA += qAnd;
			queryJPA += qFechas;

		}
		
		Query query = getCurrentSession().createQuery(queryJPA);
		if(tieneId) {
			query.setParameter("idValidador", idValidador);
		}
		
		if(tieneFechas) {
			query.setParameter("fechaInicial", fechaInicial)
			.setParameter("fechaFinal", fechaFinal);
		}
		
		List<ImagenesDTO> listaRetorno = query.list();
		
		return listaRetorno;
	}

	@Override
	public Long getCantidadImagenesAsignadas(ValidadoresDTO validador) {
		String queryJPA = "	SELECT COUNT(asign.idImagen)			"
				+ "			FROM AsignValidacionDTO asign 			"
				+ "			WHERE  asign.idValidador = :validador 	"
				+ "				AND asign.stActivo = 1 				"
				+ "				AND asign.stPospuesta IS NULL		";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("validador", validador);
		Long cantidad = (Long) query.uniqueResult();

		return cantidad;
	}
	
	
	
	
	
}
