/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lotes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.estatusproceso.EstatusProcesoDTO;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP033B_CAMBIOS_LOTES")
public class CambiosLotesDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@SequenceGenerator(name = "sTDP033B", sequenceName="SQDP_REG_TDP033B", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sTDP033B")
    @Basic(optional = false)
    @Column(name = "ID_CAMBIOS_LOTES")
    private Long idCambiosLotes;
    @Basic(optional = false)
    @Column(name = "CD_LOTE")
    private String cdLote;
    @Basic(optional = false)
    @Column(name = "NU_TOT_IMAGENES")
    private long nuTotImagenes;
    @Column(name = "NU_TOT_IMG_RECHAZADAS")
    private Long nuTotImgRechazadas;
    @Column(name = "NU_TOT_IMG_ACEPTADAS")
    private Long nuTotImgAceptadas;
    @Column(name = "ST_ACTIVO")
    private Short stActivo;
    @Column(name = "ID_USR_CREACION")
    private Long idUsrCreacion;
    @Column(name = "FH_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhCreacion;
    @Column(name = "ID_USR_MODIFICA")
    private Long idUsrModifica;
    @Column(name = "FH_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhModificacion;
    @JoinColumn(name = "ID_LOTE", referencedColumnName = "ID_LOTE")
    @ManyToOne(optional = false)
    private LotesDTO idLote;
    @JoinColumn(name = "ID_STAT_PROCESO", referencedColumnName = "ID_STAT_PROCESO")
    @ManyToOne(optional = false)
    private EstatusProcesoDTO idStatProceso;

    public CambiosLotesDTO() {
    }

    public CambiosLotesDTO(Long idCambiosLotes) {
        this.idCambiosLotes = idCambiosLotes;
    }

    public CambiosLotesDTO(Long idCambiosLotes, String cdLote, long nuTotImagenes) {
        this.idCambiosLotes = idCambiosLotes;
        this.cdLote = cdLote;
        this.nuTotImagenes = nuTotImagenes;
    }

    public Long getIdCambiosLotes() {
        return idCambiosLotes;
    }

    public void setIdCambiosLotes(Long idCambiosLotes) {
        this.idCambiosLotes = idCambiosLotes;
    }

    public String getCdLote() {
        return cdLote;
    }

    public void setCdLote(String cdLote) {
        this.cdLote = cdLote;
    }

    public long getNuTotImagenes() {
        return nuTotImagenes;
    }

    public void setNuTotImagenes(long nuTotImagenes) {
        this.nuTotImagenes = nuTotImagenes;
    }

    public Long getNuTotImgRechazadas() {
        return nuTotImgRechazadas;
    }

    public void setNuTotImgRechazadas(Long nuTotImgRechazadas) {
        this.nuTotImgRechazadas = nuTotImgRechazadas;
    }

    public Long getNuTotImgAceptadas() {
        return nuTotImgAceptadas;
    }

    public void setNuTotImgAceptadas(Long nuTotImgAceptadas) {
        this.nuTotImgAceptadas = nuTotImgAceptadas;
    }

    public Short getStActivo() {
        return stActivo;
    }

    public void setStActivo(Short stActivo) {
        this.stActivo = stActivo;
    }

    public Long getIdUsrCreacion() {
        return idUsrCreacion;
    }

    public void setIdUsrCreacion(Long idUsrCreacion) {
        this.idUsrCreacion = idUsrCreacion;
    }

    public Date getFhCreacion() {
        return fhCreacion;
    }

    public void setFhCreacion(Date fhCreacion) {
        this.fhCreacion = fhCreacion;
    }

    public Long getIdUsrModifica() {
        return idUsrModifica;
    }

    public void setIdUsrModifica(Long idUsrModifica) {
        this.idUsrModifica = idUsrModifica;
    }

    public Date getFhModificacion() {
        return fhModificacion;
    }

    public void setFhModificacion(Date fhModificacion) {
        this.fhModificacion = fhModificacion;
    }

    public LotesDTO getIdLote() {
        return idLote;
    }

    public void setIdLote(LotesDTO idLote) {
        this.idLote = idLote;
    }

    public EstatusProcesoDTO getIdStatProceso() {
        return idStatProceso;
    }

    public void setIdStatProceso(EstatusProcesoDTO idStatProceso) {
        this.idStatProceso = idStatProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCambiosLotes != null ? idCambiosLotes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof CambiosLotesDTO)) {
            return false;
        }
        CambiosLotesDTO other = (CambiosLotesDTO) object;
        if ((this.idCambiosLotes == null && other.idCambiosLotes != null) || (this.idCambiosLotes != null && !this.idCambiosLotes.equals(other.idCambiosLotes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.CambiosLotesDTO[ idCambiosLotes=" + idCambiosLotes + " ]";
    }
    
}
