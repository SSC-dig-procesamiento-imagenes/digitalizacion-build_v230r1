package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LoteDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;


@Repository
public class LoteHDAOImpl extends BaseDaoHibernate<LoteDTO> implements LoteHDAO{

	public List<LoteDTO> getLotes(){
		
		Criteria criteria = getCurrentSession().createCriteria(LotesDTO.class);
//		getCurrentSession().createQuery("esta es la consulta hpql");
				
		return criteria.list();
	}
	
}
