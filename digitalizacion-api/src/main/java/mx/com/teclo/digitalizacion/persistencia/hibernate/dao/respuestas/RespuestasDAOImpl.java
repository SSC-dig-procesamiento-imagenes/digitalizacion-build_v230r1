package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.respuestas;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PlantillaRespuestaCaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PlantillasRespuestasDTO;
@Repository
public class RespuestasDAOImpl  extends BaseDaoHibernate<PlantillasRespuestasDTO> implements RespuestasDAO{

	@Override
	public Long contador(BigDecimal id) {
		  	Criteria criteria=getCurrentSession().createCriteria(PlantillaRespuestaCaracteresDTO.class);
		  	criteria.createAlias("plantillasrespuestasId", "respuesta");
		  	criteria.add(Restrictions.eq("respuesta.id", id));
	        criteria.setProjection(Projections.rowCount());
	        Long resultCount = (Long)criteria.uniqueResult();
		// TODO Auto-generated method stub
		return resultCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlantillasRespuestasDTO> getRespuestaAndContador(){
		
		String hql = "SELECT   TDP014.id,TDP014.NOMBRE,TDP014.DESCRIPCION,"+
				" count(TDP016.CARACTERES_ID) as tamanio "
				+ "FROM PlantillasRespuestasDTO as TDP014, "
				+ "PlantillaRespuestaCaracteresDTO as TDP016 "
				+ "WHERE TDP016.id = TDP014.caracteresId.id "
				+ "GROUP by  TDP014.id,TDP014.NOMBRE,TDP014.DESCRIPCION";
			
		Query query = getCurrentSession().createQuery(hql);
		return query.list();		
	}
	
	@Override
	public PlantillasRespuestasDTO getRespuestaByName(String nombre) {
		Criteria c = getCurrentSession().createCriteria(PlantillasRespuestasDTO.class);
		c.add(Restrictions.eq("nombre", nombre));
		return (PlantillasRespuestasDTO) c.uniqueResult();
	}
	
	
		@Override
	@SuppressWarnings("unchecked")
	public List<PlantillasRespuestasDTO> getListRespuestaByName(String nombre) {
		Criteria c = getCurrentSession().createCriteria(PlantillasRespuestasDTO.class);
		c.add(Restrictions.eq("nombre", nombre));
		c.addOrder(Order.desc("id"));
		return  c.list();
	}
		
		@SuppressWarnings("unchecked")
		@Override
	public List<PlantillasRespuestasDTO> getRespuestaByEstatus(String estatus) {
			Integer stActivo=estatus.equals("ACTIVO")?1:0;
			Criteria c = getCurrentSession().createCriteria(PlantillasRespuestasDTO.class);
			c.add(Restrictions.eq("stActivo", stActivo));
			c.addOrder(Order.desc("id"));
			return  c.list();
		}
		
		
		@SuppressWarnings("unchecked")
		@Override
	public List<PlantillasRespuestasDTO> getAllRespuestas() {
			Criteria c = getCurrentSession().createCriteria(PlantillasRespuestasDTO.class);
			c.addOrder(Order.desc("id"));
			return  c.list();
		}
	
	
	
	
//    public List ListRespuestas() {
//        Criteria l = getSession().createCriteria(PlantillaRespuestaCaracteresDTO.class, "asig")
//                .createAlias("asig.plantillasrespuestasId", "respuesta")
////                .add(Restrictions.eq("respuesta.stActivo",1))
//                .setProjection(Projections.projectionList()
//                        .add(Projections.groupProperty("usuario").add(Projections.groupProperty("")))
//                        .add(Projections.count("respuesta").as("contador"))
//                        .add(Projections.sum("nombre").as("nombre"))
//                        .add(Projections.sum("respuesta.montoF").as("facturados"))
//                        .add(Projections.sum("respuesta.descuentoF").as("descuentos")));
//        return l.list();
//    }
	
	
}
