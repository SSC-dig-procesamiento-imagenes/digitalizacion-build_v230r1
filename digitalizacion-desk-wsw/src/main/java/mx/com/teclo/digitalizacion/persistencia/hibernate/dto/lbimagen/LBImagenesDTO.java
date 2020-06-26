package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lbimagen;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.imagenes.ImagenesDTO;

@Entity
@Table(name = "TDP036D_LB_IMAGENES")
public class LBImagenesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1192740388166115976L;
	
	@Id
	@SequenceGenerator(name = "sTDP036", sequenceName="SQDP_REG_TDP036", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sTDP036")
	@Column(name = "ID_LB_IMAGEN", unique = true, nullable = false)
	private Long idLbImagen;
    @JoinColumn(name = "ID_IMAGEN", referencedColumnName = "ID_IMAGEN")
    @ManyToOne(optional = false)
    private ImagenesDTO imagen;
	@Column(name = "NB_IMAGEN")
	@Basic(optional = false)
	private String nombreImagen;
	@Column(name = "LB_IMAGEN")
	@Basic(optional = false)
	private byte[] lbImagen;
	@Column(name = "CD_IMAGEN")
	@Basic(optional = false)
	private String codigoImagen;
	
	public Long getIdLbImagen() {
		return idLbImagen;
	}
	public void setIdLbImagen(Long idLbImagen) {
		this.idLbImagen = idLbImagen;
	}
	public ImagenesDTO getImagen() {
		return imagen;
	}
	public void setImagen(ImagenesDTO imagen) {
		this.imagen = imagen;
	}
	public String getNombreImagen() {
		return nombreImagen;
	}
	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}
	public byte[] getLbImagen() {
		return lbImagen;
	}
	public void setLbImagen(byte[] lbImagen) {
		this.lbImagen = lbImagen;
	}
	public String getCodigoImagen() {
		return codigoImagen;
	}
	public void setCodigoImagen(String codigoImagen) {
		this.codigoImagen = codigoImagen;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
