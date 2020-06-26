package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.imagenes.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lotes.LotesDTO;

@Repository
public class ImagenesDAOImpl extends BaseDaoHibernate<ImagenesDTO> implements ImagenesDAO {

	@Override
	public List<ImagenesDTO> getImagenesByLote(LotesDTO loteDTO) {
		String queryJPA = 
			"	SELECT img				"
		  + "	FROM ImagenesDTO img	"
		  + "	WHERE img.lote = :lote	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("lote", loteDTO);
		
		List<ImagenesDTO> retorno = query.list();
				
		return retorno;
	}
	
}
