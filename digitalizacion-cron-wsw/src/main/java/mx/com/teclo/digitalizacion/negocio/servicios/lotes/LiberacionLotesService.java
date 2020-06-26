package mx.com.teclo.digitalizacion.negocio.servicios.lotes;

public interface LiberacionLotesService {

	void liberaLotesDisponibles() throws Exception;
	void liberaUnLoteDisponible() throws Exception;
	boolean setEstado();
}
