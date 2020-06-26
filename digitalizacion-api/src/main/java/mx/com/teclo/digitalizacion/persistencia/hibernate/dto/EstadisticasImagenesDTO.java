package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TDP037D_ESTAD_IMG")
public class EstadisticasImagenesDTO implements Serializable {

	@Id
	@SequenceGenerator(name = "sTDP037", sequenceName="SQDP_REG_TDP037", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sTDP037")
	@Column(name = "ID_ESTAD_IMG")
	private Long idEstadisticas;
	
	@JoinColumn(name = "ID_IMAGEN", referencedColumnName = "ID_IMAGEN")
    @ManyToOne(optional = false)
	private ImagenesDTO idImagen;
	
	@JoinColumn(name = "ID_VALIDADOR", referencedColumnName = "ID_VALIDADOR")
    @ManyToOne(optional = false)
	private ValidadoresDTO idValidador;
	
	@Column(name = "ST_VALIDADA")
	private Short validacion;
	
	@JoinColumn(name = "ID_LOTE", referencedColumnName = "ID_LOTE")
    @ManyToOne(optional = false)
	private LotesDTO idLote;
	
	@Column(name = "FH_VALIDACION")
	private Date fechaValidacion;
	
	/*Métodos*/
	public EstadisticasImagenesDTO() {
	}

	public Long getIdEstadisticas() {
		return idEstadisticas;
	}

	public void setIdEstadisticas(Long idEstadisticas) {
		this.idEstadisticas = idEstadisticas;
	}

	public ImagenesDTO getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(ImagenesDTO idImagen) {
		this.idImagen = idImagen;
	}

	public ValidadoresDTO getIdValidador() {
		return idValidador;
	}

	public void setIdValidador(ValidadoresDTO idValidador) {
		this.idValidador = idValidador;
	}

	public Short getValidacion() {
		return validacion;
	}

	public void setValidacion(Short validacion) {
		this.validacion = validacion;
	}

	public LotesDTO getIdLote() {
		return idLote;
	}

	public void setIdLote(LotesDTO idLote) {
		this.idLote = idLote;
	}

	public Date getFechaValidacion() {
		return fechaValidacion;
	}

	public void setFechaValidacion(Date fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}
	
	
}
