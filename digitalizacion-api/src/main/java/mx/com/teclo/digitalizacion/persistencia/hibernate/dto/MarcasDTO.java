/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP010D_MARCAS")
public class MarcasDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "COORDENADAIY")
    private BigInteger coordenadaiy;
    @Basic(optional = false)
    @Column(name = "COORDENADAIX")
    private BigInteger coordenadaix;
    @Basic(optional = false)
    @Column(name = "COORDENADAFY")
    private BigInteger coordenadafy;
    @Basic(optional = false)
    @Column(name = "COORDENADAFX")
    private BigInteger coordenadafx;
    @Column(name = "ORIENTACION")
    private String orientacion;
    @Basic(optional = false)
    @Column(name = "NUMERO_OBJETOS")
    private BigInteger numeroObjetos;
    @JoinColumn(name = "PLANTILLAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlantillasDTO plantillasId;

    public MarcasDTO() {
    }

    public MarcasDTO(BigDecimal id) {
        this.id = id;
    }

    public MarcasDTO(BigDecimal id, BigInteger coordenadaiy, BigInteger coordenadaix, BigInteger coordenadafy, BigInteger coordenadafx, BigInteger numeroObjetos) {
        this.id = id;
        this.coordenadaiy = coordenadaiy;
        this.coordenadaix = coordenadaix;
        this.coordenadafy = coordenadafy;
        this.coordenadafx = coordenadafx;
        this.numeroObjetos = numeroObjetos;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getCoordenadaiy() {
        return coordenadaiy;
    }

    public void setCoordenadaiy(BigInteger coordenadaiy) {
        this.coordenadaiy = coordenadaiy;
    }

    public BigInteger getCoordenadaix() {
        return coordenadaix;
    }

    public void setCoordenadaix(BigInteger coordenadaix) {
        this.coordenadaix = coordenadaix;
    }

    public BigInteger getCoordenadafy() {
        return coordenadafy;
    }

    public void setCoordenadafy(BigInteger coordenadafy) {
        this.coordenadafy = coordenadafy;
    }

    public BigInteger getCoordenadafx() {
        return coordenadafx;
    }

    public void setCoordenadafx(BigInteger coordenadafx) {
        this.coordenadafx = coordenadafx;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public BigInteger getNumeroObjetos() {
        return numeroObjetos;
    }

    public void setNumeroObjetos(BigInteger numeroObjetos) {
        this.numeroObjetos = numeroObjetos;
    }

    public PlantillasDTO getPlantillasId() {
        return plantillasId;
    }

    public void setPlantillasId(PlantillasDTO plantillasId) {
        this.plantillasId = plantillasId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof MarcasDTO)) {
            return false;
        }
        MarcasDTO other = (MarcasDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.MarcasDTO[ id=" + id + " ]";
    }
    
}
