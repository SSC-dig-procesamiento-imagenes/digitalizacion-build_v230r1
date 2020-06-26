package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;

import java.util.Date;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lotes.CambiosLotesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lotes.LotesDTO;


@Repository
public class CambiosLotesHDAOImpl extends BaseDaoHibernate<CambiosLotesDTO> implements CambiosLotesHDAO {

	@Override
	public void addCambiosLotes(LotesDTO lotesDTO, Long idUsuario) {
		CambiosLotesDTO cambiosLotes = new CambiosLotesDTO();
		if(lotesDTO.getNombreLote() != null) {
			cambiosLotes.setCdLote(lotesDTO.getNombreLote());
		}else {
			cambiosLotes.setCdLote("Lote sin nombre");
		}
		
		cambiosLotes.setFhCreacion(lotesDTO.getFechaCracionLote());
		cambiosLotes.setFhModificacion(new Date(System.currentTimeMillis()));
		cambiosLotes.setIdLote(lotesDTO);
		cambiosLotes.setIdStatProceso(lotesDTO.getEstatusProceso());
		cambiosLotes.setIdUsrCreacion(idUsuario);
		cambiosLotes.setIdUsrModifica(idUsuario);
		cambiosLotes.setNuTotImagenes(lotesDTO.getNumeroTotalImagenes());
		cambiosLotes.setNuTotImgAceptadas(Long.valueOf(lotesDTO.getNumeroTotalAceptadas()));
		cambiosLotes.setNuTotImgRechazadas(Long.valueOf(lotesDTO.getNumeroTotalRechazadas()));
		cambiosLotes.setStActivo((short) 1);
		
		getCurrentSession().save(cambiosLotes);
		
	}


}
