package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.internal.util.privilegedactions.GetDeclaredConstructor;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CambiosLotesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;


@Repository
public class CambiosLotesHDAOImpl extends BaseDaoHibernate<CambiosLotesDTO> implements CambiosLotesHDAO {

	@Override
	public void addCambiosLotes(LotesDTO lotesDTO, Long idUsuario) {
		CambiosLotesDTO cambiosLotes = new CambiosLotesDTO();
		if(lotesDTO.getNbLote() != null) {
			cambiosLotes.setCdLote(lotesDTO.getNbLote());
		}else {
			cambiosLotes.setCdLote("Lote sin nombre");
		}
		
		cambiosLotes.setFhCreacion(lotesDTO.getFhCreacionLote());
		cambiosLotes.setFhModificacion(new Date(System.currentTimeMillis()));
		cambiosLotes.setIdLote(lotesDTO);
		cambiosLotes.setIdStatProceso(lotesDTO.getIdStatProceso());
		cambiosLotes.setIdUsrCreacion(idUsuario);
		cambiosLotes.setIdUsrModifica(idUsuario);
		cambiosLotes.setNuTotImagenes(lotesDTO.getNuTotImagenes());
		cambiosLotes.setNuTotImgAceptadas(lotesDTO.getNuTotImgAceptadas());
		cambiosLotes.setNuTotImgRechazadas(lotesDTO.getNuTotImgRechazadas());
		cambiosLotes.setStActivo((short) 1);
		
		getCurrentSession().save(cambiosLotes);
		
	}


}
