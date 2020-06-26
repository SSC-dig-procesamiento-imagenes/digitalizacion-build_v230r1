package mx.com.teclo.digitalizacion.negocio.service.cron.liberacionimagenes;

public interface LiberacionImagenesCronService {

	public void liberaLotesDisponibles() throws Exception;
	public void liberaUnLoteDisponible() throws Exception;
}
