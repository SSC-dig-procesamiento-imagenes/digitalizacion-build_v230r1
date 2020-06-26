package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ArticulosInfracFinanzasDTO;

@Repository
public class ArticulosInfracFinanzasHDAOImpl extends BaseDaoHibernate<ArticulosInfracFinanzasDTO> implements ArticulosInfracFinanzasHDAO {

	@Override
	public Long getArticuloPorDatos(Date fecha, Long articulo, Long fraccion, String inciso,
			Long parrafo) throws Exception {
		// TODO Los parámetros inciso y párrafo pueden ser nulos
		
		String querySQL =
			"	SELECT aif.ART_ID, aif.ARTICULO 							"
			+ "	FROM ARTICULOS_INFRAC_FINANZAS aif							"
			+ "	WHERE :fecha BETWEEN aif.FECHA_INICIO and aif.FECHA_TERMINO	"
			+ "		AND aif.ARTICULO = :articulo							"
			+ "		AND aif.FRACCION = :fraccion 							";
		String andInciso = " AND aif.INCISO = :inciso						";
		String andParrafo = "AND aif.PARRAFO = :parrafo						";
		Boolean tieneInciso = false;
		Boolean tieneParrafo = false;
		
		if (inciso != null && !inciso.trim().equals("")) {
			tieneInciso = true;
			querySQL += andInciso;
		}
		if(parrafo != null) {
			tieneParrafo = true;
			querySQL += andParrafo;
		}
		
		Query query = getCurrentSession().createSQLQuery(querySQL)
				.setParameter("fecha", fecha)
				.setParameter("articulo", articulo)
				.setParameter("fraccion", fraccion);
		
		if(tieneInciso) {
			query.setParameter("inciso", inciso);
		}
		if(tieneParrafo) {
			query.setParameter("parrafo", parrafo);
		}
		
		try {
			List<Object[]> listaRetornar = query.list();
			Object[] val =  listaRetornar.get(0);//Aquí explota
		
			return ((BigDecimal)val[0]).longValue();
		}catch(Exception ex) {
			throw new Exception("Los parámetros no produjeron ningún resultado. fecha: "+fecha+" artículo: "+articulo+" fracción:"+fraccion+
					" inciso: "+inciso+" párrafo: "+parrafo);
		}
		
		//return (long) 0;
	}

}
