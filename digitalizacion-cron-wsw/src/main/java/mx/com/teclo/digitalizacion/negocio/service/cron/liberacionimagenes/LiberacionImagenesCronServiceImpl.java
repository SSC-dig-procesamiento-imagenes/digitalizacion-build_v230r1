package mx.com.teclo.digitalizacion.negocio.service.cron.liberacionimagenes;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.digitalizacion.negocio.service.cron.ejemplo.NotificacionesCronServiceImpl;
import mx.com.teclo.digitalizacion.negocio.servicios.lotes.LiberacionLotesService;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresEstadosLotes;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.ImagenesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.LotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;

@Service
public class LiberacionImagenesCronServiceImpl implements LiberacionImagenesCronService {
	private static final Logger logger = Logger.getLogger(LiberacionImagenesCronServiceImpl.class);


	@Autowired
	private LiberacionLotesService liberacionLotesService;
	
	//TODO:perhaps, some day...
	//@Scheduled(cron = "1 * * * * ?")
	public void liberaLotesDisponibles() throws Exception {
		liberacionLotesService.liberaLotesDisponibles();
				
	}
	
	//TODO:perhaps, some day...
	@Scheduled(cron = "1 * * * * ?")
	public void liberaUnLoteDisponible() throws Exception {
		boolean result = liberacionLotesService.setEstado();
		if(result) {
			liberacionLotesService.liberaUnLoteDisponible();
		}
				
	}
	

	
	
}
