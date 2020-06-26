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
@Table(name = "TDP029C_CONFSCANNER")
public class ConfiguracionEscanerDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "NOMENCLATURA")
    private String nomenclatura;
    @Basic(optional = false)
    @Column(name = "CONTRASTE")
    private BigInteger contraste;
    @Basic(optional = false)
    @Column(name = "BRILLO")
    private BigInteger brillo;
    @JoinColumn(name = "PAPERSIZE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TamanioPapelDTO papersizeId;
    @JoinColumn(name = "RESOLUCION_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ResolucionDTO resolucionId;
    @JoinColumn(name = "TIPOPIXEL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoPixelDTO tipopixelId;
    @JoinColumn(name = "TIPOARCHIVOS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoArchivosDTO tipoarchivosId;
    @JoinColumn(name = "SUMINISTROPAPEL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SuministroPapelDTO suministropapelId;
    @JoinColumn(name = "SCANNERS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EscanersDTO scannersId;

    public ConfiguracionEscanerDTO() {
    }

    public ConfiguracionEscanerDTO(BigDecimal id) {
        this.id = id;
    }

    public ConfiguracionEscanerDTO(BigDecimal id, BigInteger contraste, BigInteger brillo) {
        this.id = id;
        this.contraste = contraste;
        this.brillo = brillo;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public BigInteger getContraste() {
        return contraste;
    }

    public void setContraste(BigInteger contraste) {
        this.contraste = contraste;
    }

    public BigInteger getBrillo() {
        return brillo;
    }

    public void setBrillo(BigInteger brillo) {
        this.brillo = brillo;
    }

    public TamanioPapelDTO getPapersizeId() {
        return papersizeId;
    }

    public void setPapersizeId(TamanioPapelDTO papersizeId) {
        this.papersizeId = papersizeId;
    }

    public ResolucionDTO getResolucionId() {
        return resolucionId;
    }

    public void setResolucionId(ResolucionDTO resolucionId) {
        this.resolucionId = resolucionId;
    }

    public TipoPixelDTO getTipopixelId() {
        return tipopixelId;
    }

    public void setTipopixelId(TipoPixelDTO tipopixelId) {
        this.tipopixelId = tipopixelId;
    }

    public TipoArchivosDTO getTipoarchivosId() {
        return tipoarchivosId;
    }

    public void setTipoarchivosId(TipoArchivosDTO tipoarchivosId) {
        this.tipoarchivosId = tipoarchivosId;
    }

    public SuministroPapelDTO getSuministropapelId() {
        return suministropapelId;
    }

    public void setSuministropapelId(SuministroPapelDTO suministropapelId) {
        this.suministropapelId = suministropapelId;
    }

    public EscanersDTO getScannersId() {
        return scannersId;
    }

    public void setScannersId(EscanersDTO scannersId) {
        this.scannersId = scannersId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfiguracionEscanerDTO)) {
            return false;
        }
        ConfiguracionEscanerDTO other = (ConfiguracionEscanerDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.ConfiguracionEscanerDTO[ id=" + id + " ]";
    }
    
}
