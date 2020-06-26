package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.tipoarchivo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TDP021C_TIPOARCHIVOS")
public class TipoArchivosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6456726920643922112L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long idTipoArchivos;

	@Column(name = "VALOR")
	private Integer valor;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "EXTENSION")
	private String extencion;

	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
	}

	/**
	 * @return the idTipoArchivos
	 */
	public Long getIdTipoArchivos() {
		return idTipoArchivos;
	}

	/**
	 * @param idTipoArchivos the idTipoArchivos to set
	 */
	public void setIdTipoArcchivos(Long idTipoArchivos) {
		this.idTipoArchivos = idTipoArchivos;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Integer valor) {
		this.valor = valor;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the extencion
	 */
	public String getExtencion() {
		return extencion;
	}

	/**
	 * @param extencion the extencion to set
	 */
	public void setExtencion(String extencion) {
		this.extencion = extencion;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}