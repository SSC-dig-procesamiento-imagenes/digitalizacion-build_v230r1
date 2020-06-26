package mx.com.teclo.digitalizacion.persistencia.hibernate.vo.plantillas;

import java.util.List;

public class AreaTrabajoPlantilla {
	
	List <int[][]> cordenadas;
	Long alto;
	Long ancho;
	Long areaInicioTrabajo;
	Long areaFinTrabajo;
	
	public List<int[][]> getCordenadas() {
		return cordenadas;
	}
	public void setCordenadas(List<int[][]> cordenadas) {
		this.cordenadas = cordenadas;
	}
	public Long getAlto() {
		return alto;
	}
	public void setAlto(Long alto) {
		this.alto = alto;
	}
	public Long getAncho() {
		return ancho;
	}
	public void setAncho(Long ancho) {
		this.ancho = ancho;
	}
	public Long getAreaInicioTrabajo() {
		return areaInicioTrabajo;
	}
	public void setAreaInicioTrabajo(Long areaInicioTrabajo) {
		this.areaInicioTrabajo = areaInicioTrabajo;
	}
	public Long getAreaFinTrabajo() {
		return areaFinTrabajo;
	}
	public void setAreaFinTrabajo(Long areaFinTrabajo) {
		this.areaFinTrabajo = areaFinTrabajo;
	}
	
	
}
