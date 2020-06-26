package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;


import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DIRECTORIOS_DIGITALIZACION_DIA")
public class DirDigitalizacionDTO implements Serializable{
	
	private static final long serialVersionUID = -1210940558835477031L;
	

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "folio", column = @Column(name = "FOLIO")),
		@AttributeOverride(name = "anvrev", column = @Column(name = "ANV_REV"))})
    private DigitalizacionIdDTO idCopuesto;
	
	@Column(name = "RUTA_ARCHIVO")
	private String rutaArchivo;
	@Column(name = "BIT_CICLO_ESTATUS")
	private Integer stBitCiclo;
	@Column(name = "IMAGEN")
	private byte[] foto;
	@Column(name = "RUTA_ARCHIVO_BAK")
	private String rutaArchivoBak;
	public DigitalizacionIdDTO getIdCopuesto() {
		return idCopuesto;
	}
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	public Integer getStBitCiclo() {
		return stBitCiclo;
	}
	public byte[] getFoto() {
		return foto;
	}
	public String getRutaArchivoBak() {
		return rutaArchivoBak;
	}
	public void setIdCopuesto(DigitalizacionIdDTO idCopuesto) {
		this.idCopuesto = idCopuesto;
	}
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	public void setStBitCiclo(Integer stBitCiclo) {
		this.stBitCiclo = stBitCiclo;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public void setRutaArchivoBak(String rutaArchivoBak) {
		this.rutaArchivoBak = rutaArchivoBak;
	}
	
	
	
	
	
}
