package mx.com.teclo.digitalizacion.persistencia.vo.plantillasolicitada;

import java.util.List;

import mx.com.teclo.digitalizacion.persistencia.vo.alvelos.AlveolosVO;

public class PlantillaSolicitadaVO {

	private Long id;
	private String nombre;
	private Integer algoritmo_ajuste;
	private AlveolosVO alveolo;
	private List<CamposSolicitadosVO> campos;
	
	public PlantillaSolicitadaVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getAlgoritmo_ajuste() {
		return algoritmo_ajuste;
	}

	public void setAlgoritmo_ajuste(Integer algoritmo_ajuste) {
		this.algoritmo_ajuste = algoritmo_ajuste;
	}

	public AlveolosVO getAlveolo() {
		return alveolo;
	}

	public void setAlveolo(AlveolosVO alveolo) {
		this.alveolo = alveolo;
	}

	public List<CamposSolicitadosVO> getCampos() {
		return campos;
	}

	public void setCampos(List<CamposSolicitadosVO> campos) {
		this.campos = campos;
	}
	
	
}
