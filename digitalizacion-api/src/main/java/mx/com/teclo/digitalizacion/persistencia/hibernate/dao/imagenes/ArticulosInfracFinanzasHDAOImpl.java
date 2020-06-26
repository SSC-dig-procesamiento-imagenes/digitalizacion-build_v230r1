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
	public boolean getArticuloPorDatos(Date fecha, Long articulo, Long fraccion, String inciso,
			Long parrafo) throws Exception {
		//Los parámetros inciso y párrafo pueden ser nulos
		
		String querySQL =
			"	SELECT aif.ART_ID, aif.ARTICULO 							"
			+ "	FROM ARTICULOS_INFRAC_FINANZAS aif							"
			+ "	WHERE :fecha BETWEEN aif.FECHA_INICIO and aif.FECHA_TERMINO	"
			+ "		AND aif.ARTICULO = :articulo							";
		String andFraccion = 	" AND aif.FRACCION = :fraccion				";
		String andInciso = " AND aif.INCISO = :inciso						";
		String andParrafo = " AND aif.PARRAFO = :parrafo					";
		Boolean tieneFraccion = false;
		Boolean tieneInciso = false;
		Boolean tieneParrafo = false;
		
		if (fraccion != null) {
			tieneFraccion = true;
			querySQL += andFraccion;
		}
		
		if (inciso != null) {
			tieneInciso = true;
			querySQL += andInciso;
		}
		if(parrafo != null) {
			tieneParrafo = true;
			querySQL += andParrafo;
		}
		
		Query query = getCurrentSession().createSQLQuery(querySQL)
				.setParameter("fecha", fecha)
				.setParameter("articulo", articulo);
		
		if(tieneFraccion) {
			query.setParameter("fraccion", fraccion);
		}
		if(tieneInciso) {
			query.setParameter("inciso", inciso);
		}
		if(tieneParrafo) {
			query.setParameter("parrafo", parrafo);
		}
		
		List<Object[]> listaRetornar = query.list();
		boolean retorno = listaRetornar.size() == 1 ? true : false; 
		
		return retorno;
	}

}
