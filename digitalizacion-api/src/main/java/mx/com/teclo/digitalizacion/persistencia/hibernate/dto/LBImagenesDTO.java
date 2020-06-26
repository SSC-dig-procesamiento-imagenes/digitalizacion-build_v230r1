package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TDP036D_LB_IMAGENES")
public class LBImagenesDTO implements Serializable {

	@Id
	@Basic(optional = false)
	@Column(name = "ID_LB_IMAGEN")
	private Long idLbImagen;
    @JoinColumn(name = "ID_IMAGEN", referencedColumnName = "ID_IMAGEN")
    @ManyToOne(optional = false)
    private ImagenesDTO idImagen;
	@Column(name = "NB_IMAGEN")
	@Basic(optional = false)
	private String nombreImagen;
	@Column(name = "LB_IMAGEN")
	@Basic(optional = false)
	private byte[] lbImagen;
	@Column(name = "CD_IMAGEN")
	@Basic(optional = false)
	private String codigoImagen;
	
	public LBImagenesDTO() {
		
	}
	
	public Long getIdLbImagen() {
		return idLbImagen;
	}
	public void setIdLbImagen(Long idLbImagen) {
		this.idLbImagen = idLbImagen;
	}
	public ImagenesDTO getIdImagen() {
		return idImagen;
	}
	public void setIdImagen(ImagenesDTO idImagen) {
		this.idImagen = idImagen;
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


	

}
