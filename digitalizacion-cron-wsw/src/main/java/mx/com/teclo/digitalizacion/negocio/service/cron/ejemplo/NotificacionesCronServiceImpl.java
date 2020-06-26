package mx.com.teclo.digitalizacion.negocio.service.cron.ejemplo;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificacionesCronServiceImpl implements NotificacionesCronService {
	private static final Logger logger = Logger.getLogger(NotificacionesCronServiceImpl.class);

	@Override
	//@Scheduled(cron = "1 * * * * ?")
	public void ejemploCron() {
		logger.info("Cron cada minuto");
		
		System.out.println("Soy un cron...");

	}

}
