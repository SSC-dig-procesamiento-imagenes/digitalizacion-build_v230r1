package mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales;

public class ControlCatalogosVO {

	private Long idCtrlCatReporte;
	private String nombre;
	private String ruta;
	private Long idPadre;
	private ControlCatalogosVO object;
	
	public ControlCatalogosVO(Long idCtrlCatReporte, String nombre, String ruta, Long idPadre,
			ControlCatalogosVO object) {
		super();
		this.idCtrlCatReporte = idCtrlCatReporte;
		this.nombre = nombre;
		this.ruta = ruta;
		this.idPadre = idPadre;
		this.object = object;
	}

	public ControlCatalogosVO() {
		super();
	}

	public Long getIdCtrlCatReporte() {
		return idCtrlCatReporte;
	}

	public void setIdCtrlCatReporte(Long idCtrlCatReporte) {
		this.idCtrlCatReporte = idCtrlCatReporte;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Long getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}

	public ControlCatalogosVO getObject() {
		return object;
	}

	public void setObject(ControlCatalogosVO object) {
		this.object = object;
	}
	
	
	
}
