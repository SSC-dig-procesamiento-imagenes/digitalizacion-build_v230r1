package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TDP_BITACORA_CAMBIOS")
public class TdpBitacoraCambiosDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3768135596133421868L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CAMBIO_ID", unique = true, nullable = false)
	private Long cambioId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({	
		@JoinColumn(name = "CONCEPTO_ID",referencedColumnName="CONCEPTO_ID"),
		@JoinColumn(name = "COMPONENTE_ID",referencedColumnName ="COMPONENTE_ID")
	})
	private TdpBitacoraConceptosDTO bitacoraConceptosDTO;
	
	
	@Column(name = "VALOR_ORIGINAL")
	private String valorOriginal;
	
	@Column(name = "VALOR_FINAL")
	private String valorFinal;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	//@Column(name = "CREADO_POR")
    @JoinColumn(name = "CREADO_POR", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
	private UsuariosDTO creadoPor;
	
	@Column(name = "ID_REGISTRO")
	private String idRegistro;
	
	@Column(name = "ORIGEN")
	private String origen;
	
	@Column(name = "ID_REGISTRO_DESCRIPCION")
	private String idRegistroDescripcion;

	public Long getCambioId() {
		return cambioId;
	}

	public void setCambioId(Long cambioId) {
		this.cambioId = cambioId;
	}

	public TdpBitacoraConceptosDTO getBitacoraConceptosDTO() {
		return bitacoraConceptosDTO;
	}

	public void setBitacoraConceptosDTO(TdpBitacoraConceptosDTO bitacoraConceptosDTO) {
		this.bitacoraConceptosDTO = bitacoraConceptosDTO;
	}

	public String getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(String valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public String getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public UsuariosDTO getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(UsuariosDTO creadoPor) {
		this.creadoPor = creadoPor;
	}

	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getIdRegistroDescripcion() {
		return idRegistroDescripcion;
	}

	public void setIdRegistroDescripcion(String idRegistroDescripcion) {
		this.idRegistroDescripcion = idRegistroDescripcion;
	}
}
