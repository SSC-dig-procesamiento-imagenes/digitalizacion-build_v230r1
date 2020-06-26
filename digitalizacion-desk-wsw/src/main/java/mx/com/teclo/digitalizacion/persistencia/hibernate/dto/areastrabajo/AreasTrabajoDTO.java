package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.areastrabajo;

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
@Table(name = "TDP009D_AREASTRABAJO")
public class AreasTrabajoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long idAreaTrabajo;
    @Basic(optional = false)
    @Column(name = "COORDENADAIY")
    private Integer coordenadaiY;
    @Basic(optional = false)
    @Column(name = "COORDENADAIX")
    private Integer coordenadaiX;
    @Basic(optional = false)
    @Column(name = "COORDENADAFY")
    private Integer coordenadafY;
    @Basic(optional = false)
    @Column(name = "COORDENADAFX")
    private Integer coordenadafX;
    @Column(name = "NUMERO_COLUMNAS")
    private String numeroColumnas;
    @Basic(optional = false)
    @Column(name = "NUMERO_FILAS")
    private Integer numeroFilas;
    @JoinColumn(name = "PLANTILLAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlantillasDTO plantillas;
	/**
	 * @return the idAreaTrabajo
	 */
	public Long getIdAreaTrabajo() {
		return idAreaTrabajo;
	}
	/**
	 * @param idAreaTrabajo the idAreaTrabajo to set
	 */
	public void setIdAreaTrabajo(Long idAreaTrabajo) {
		this.idAreaTrabajo = idAreaTrabajo;
	}
	/**
	 * @return the coordenadaiY
	 */
	public Integer getCoordenadaiY() {
		return coordenadaiY;
	}
	/**
	 * @param coordenadaiY the coordenadaiY to set
	 */
	public void setCoordenadaiY(Integer coordenadaiY) {
		this.coordenadaiY = coordenadaiY;
	}
	/**
	 * @return the coordenadaiX
	 */
	public Integer getCoordenadaiX() {
		return coordenadaiX;
	}
	/**
	 * @param coordenadaiX the coordenadaiX to set
	 */
	public void setCoordenadaiX(Integer coordenadaiX) {
		this.coordenadaiX = coordenadaiX;
	}
	/**
	 * @return the coordenadafY
	 */
	public Integer getCoordenadafY() {
		return coordenadafY;
	}
	/**
	 * @param coordenadafY the coordenadafY to set
	 */
	public void setCoordenadafY(Integer coordenadafY) {
		this.coordenadafY = coordenadafY;
	}
	/**
	 * @return the coordenadafX
	 */
	public Integer getCoordenadafX() {
		return coordenadafX;
	}
	/**
	 * @param coordenadafX the coordenadafX to set
	 */
	public void setCoordenadafX(Integer coordenadafX) {
		this.coordenadafX = coordenadafX;
	}
	/**
	 * @return the numeroColumnas
	 */
	public String getNumeroColumnas() {
		return numeroColumnas;
	}
	/**
	 * @param numeroColumnas the numeroColumnas to set
	 */
	public void setNumeroColumnas(String numeroColumnas) {
		this.numeroColumnas = numeroColumnas;
	}
	/**
	 * @return the numeroFilas
	 */
	public Integer getNumeroFilas() {
		return numeroFilas;
	}
	/**
	 * @param numeroFilas the numeroFilas to set
	 */
	public void setNumeroFilas(Integer numeroFilas) {
		this.numeroFilas = numeroFilas;
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
