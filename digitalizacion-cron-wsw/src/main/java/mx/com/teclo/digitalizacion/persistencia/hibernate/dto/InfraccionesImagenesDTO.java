package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INFRACCIONES_IMAGENES")
public class InfraccionesImagenesDTO implements Serializable{


	private static final long serialVersionUID = 923093400013666743L;

	@Id
	@Column(name = "NOMBRE_ARCHIVO")
	private String nombreArchivo;
	@Column(name = "INFRAC_NUM")
	private String infracNum;
	@Column(name = "INFRAC_NUM_CTRL")
	private String infracNumCtrl;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "FOTO")
	private byte[] foto;
	@Column(name = "BIT_NUM_CICLO")
	private Integer bitNumCiclo;
	@Column(name = "BIT_CICLO_ESTATUS")
	private Integer bitCicloEstatus;
	
	public InfraccionesImagenesDTO() {
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getInfracNum() {
		return infracNum;
	}

	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}

	public String getInfracNumCtrl() {
		return infracNumCtrl;
	}

	public void setInfracNumCtrl(String infracNumCtrl) {
		this.infracNumCtrl = infracNumCtrl;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Integer getBitNumCiclo() {
		return bitNumCiclo;
	}
	/**
	 * @param bitNumCiclo the bitNumCiclo to set
	 */
	public void setBitNumCiclo(Integer bitNumCiclo) {
		this.bitNumCiclo = bitNumCiclo;
	}
	/**
	 * @return the bitCicloEstatus
	 */
	public Integer getBitCicloEstatus() {
		return bitCicloEstatus;
	}
	/**
	 * @param bitCicloEstatus the bitCicloEstatus to set
	 */
	public void setBitCicloEstatus(Integer bitCicloEstatus) {
		this.bitCicloEstatus = bitCicloEstatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
