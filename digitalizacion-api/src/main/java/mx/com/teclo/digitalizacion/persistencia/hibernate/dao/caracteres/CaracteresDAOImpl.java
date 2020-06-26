package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.caracteres;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CaracteresDTO;
@Repository
public class CaracteresDAOImpl  extends BaseDaoHibernate<CaracteresDTO> implements CaracteresDAO{

	
	
	@SuppressWarnings("unchecked")
	@Override
	public  List <CaracteresDTO> getCaracterAll() {
		Criteria c = getCurrentSession().createCriteria(CaracteresDTO.class);
		c.addOrder(Order.desc("id"));
		return  c.list();
	}
	
	@Override
	public CaracteresDTO getCaracterByName(String caracter) {
		Criteria c = getCurrentSession().createCriteria(CaracteresDTO.class);
		c.add(Restrictions.eq("caracter", caracter));
		//c.addOrder(Order.desc("id"));
		return (CaracteresDTO) c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List <CaracteresDTO> getCaracterByEstatus(String estatus) {
		Integer stActivo=estatus.equals("ACTIVO")?1:0;
		Criteria c = getCurrentSession().createCriteria(CaracteresDTO.class);
		c.add(Restrictions.eq("stActivo", stActivo));
		c.addOrder(Order.desc("id"));
		return  c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List <CaracteresDTO> getCaracterByConsidencia(String valor) {
		Criteria c = getCurrentSession().createCriteria(CaracteresDTO.class);
		c.add(Restrictions.ilike("caracter", valor+"%"));
		c.addOrder(Order.desc("id"));
		return  c.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaracteresDTO> getCaracterByRespuesta(BigDecimal idRespuesta){
		
		String hql = "SELECT  pt "
				+ "FROM CaracteresDTO as pt, "
				+ "PlantillaRespuestaCaracteresDTO as ppt "
				+ "WHERE pt.id = ppt.caracteresId.id "
				+ "and ppt.plantillasrespuestasId.id=:idRespuesta "
				+ "and pt.stActivo=1"
				+ "and ppt.stActivo=1 ORDER BY ppt.orden ASC";
			
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idRespuesta", idRespuesta);
		return query.list();		
	}
		

}
