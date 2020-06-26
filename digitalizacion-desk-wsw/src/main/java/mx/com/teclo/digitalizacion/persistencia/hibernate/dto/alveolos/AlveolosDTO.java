package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.alveolos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.plantillas.PlantillasDTO;

@Entity
@Table(name = "TDP008D_ALVEOLOS")
public class AlveolosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long idAlveolo;
    @Basic(optional = false)
    @Column(name = "DENSIDAD")
    private Integer densidad;
    @Basic(optional = false)
    @Column(name = "AREA")
    private Integer area;
    @JoinColumn(name = "PLANTILLAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlantillasDTO plantillas;
	/**
	 * @return the idAlveolo
	 */
	public Long getIdAlveolo() {
		return idAlveolo;
	}
	/**
	 * @param idAlveolo the idAlveolo to set
	 */
	public void setIdAlveolo(Long idAlveolo) {
		this.idAlveolo = idAlveolo;
	}
	/**
	 * @return the densidad
	 */
	public Integer getDensidad() {
		return densidad;
	}
	/**
	 * @param densidad the densidad to set
	 */
	public void setDensidad(Integer densidad) {
		this.densidad = densidad;
	}
	/**
	 * @return the area
	 */
	public Integer getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(Integer area) {
		this.area = area;
	}
	/**
	 * @return the plantillas
	 */
	public PlantillasDTO getPlantillas() {
		return plantillas;
	}
	/**
	 * @param plantillas the plantillas to set
	 */
	public void setPlantillas(PlantillasDTO plantillas) {
		this.plantillas = plantillas;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}