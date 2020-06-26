package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.tamaniopapel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.unidadmedida.UnidadMedidaDTO;

@Entity
@Table(name = "TDP018C_PAPERSIZE")
public class TamanioPapaelDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6606561875717661581L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long idTamanioPapel;

	@Column(name = "VALOR")
	private Integer valor;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "WIDTH")
	private String ancho;
	
	@Column(name = "HEIGHT")
	private String alto;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "UNITSIZE_ID")
	private UnidadMedidaDTO UnidadMedida;

	/**
	 * @return the idTamanioPapel
	 */
	public Long getIdTamanioPapel() {
		return idTamanioPapel;
	}

	/**
	 * @return the unidadMedida
	 */
	public UnidadMedidaDTO getUnidadMedida() {
		return UnidadMedida;
	}

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(UnidadMedidaDTO unidadMedida) {
		UnidadMedida = unidadMedida;
	}

	/**
	 * @param idTamanioPapel the idTamanioPapel to set
	 */
	public void setIdTamanioPapel(Long idTamanioPapel) {
		this.idTamanioPapel = idTamanioPapel;
	}

	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
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
	 * @return the ancho
	 */
	public String getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(String ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the alto
	 */
	public String getAlto() {
		return alto;
	}

	/**
	 * @param alto the alto to set
	 */
	public void setAlto(String alto) {
		this.alto = alto;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}