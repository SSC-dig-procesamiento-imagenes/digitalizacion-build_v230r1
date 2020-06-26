package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ARTICULOS_INFRAC_FINANZAS")
public class ArticulosInfracFinanzasDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3556949473928516320L;

	@Id
    private Long id;
	
	@Column(name = "ARTICULO")
	private Long articulo;
	@Column(name = "FRACCION")
	private Long fraccion;
	@Column(name = "INCISO")
	private String inciso;
	@Column(name = "PARRAFO")
	private String parrafo;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "ART_ID")
	private Long artId;
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	@Column(name = "FECHA_TERMINO")
	private Date fechaTermino;
	
	public ArticulosInfracFinanzasDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getArticulo() {
		return articulo;
	}

	public void setArticulo(Long articulo) {
		this.articulo = articulo;
	}

	public Long getFraccion() {
		return fraccion;
	}

	public void setFraccion(Long fraccion) {
		this.fraccion = fraccion;
	}

	public String getInciso() {
		return inciso;
	}

	public void setInciso(String inciso) {
		this.inciso = inciso;
	}

	public String getParrafo() {
		return parrafo;
	}

	public void setParrafo(String parrafo) {
		this.parrafo = parrafo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getArtId() {
		return artId;
	}

	public void setArtId(Long artId) {
		this.artId = artId;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	
	
	
}
