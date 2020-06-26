package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.gestionPlantillas.campos;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CamposDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CaracteresDTO;
@Repository
public class CamposDAOImpl  extends BaseDaoHibernate<CamposDTO>implements CamposDAO {

	@Override
	public CamposDTO getCampoByName(String name) {
		Criteria c = getCurrentSession().createCriteria(CamposDTO.class);
		c.add(Restrictions.eq("nombre", name));
		//c.addOrder(Order.desc("id"));
		return (CamposDTO) c.uniqueResult();
	}

	@Override
	public List<CamposDTO> getCampoByEstatus(String estatus) {
		Integer stActivo=estatus.equals("ACTIVO")?1:0;
		Criteria c = getCurrentSession().createCriteria(CamposDTO.class);
		c.add(Restrictions.eq("stActivo", stActivo));
		c.addOrder(Order.desc("id"));
		return  c.list();
	}

	@Override
	public List<CamposDTO> getCampoByConsidencia(String valor) {
		Criteria c = getCurrentSession().createCriteria(CamposDTO.class);
		c.add(Restrictions.ilike("nombre", valor+"%"));
		c.addOrder(Order.desc("id"));
		return  c.list();
	}

	@Override
	public List<CamposDTO> getCampoAll() {
		Criteria c = getCurrentSession().createCriteria(CamposDTO.class);
		c.addOrder(Order.desc("id"));
		return  c.list();
	}



}
