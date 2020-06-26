/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author unitis0521
 */
@Entity
@Table(name = "TDP042B_LOTES")
public class LotesBDTO implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID_LOTE", unique = true, nullable = false)
	private Long idLote;
    @Column(name = "FH_CREACION_LOTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhCreacionLote;
    @Column(name = "TX_RUTA_ALMACENAM")
    private String txRutaAlmacenam;
    @Column(name = "NU_TOT_IMAGENES")
    private Long nuTotImagenes;
    @Column(name = "NU_TOT_IMG_RECHAZADAS")
    private Long nuTotImgRechazadas;
    @Column(name = "NU_TOT_IMG_ACEPTADAS")
    private Long nuTotImgAceptadas;
    @Column(name = "NB_LOTE")
    private String nbLote;
    @Basic(optional = false)
    @Column(name = "ID_CONF_SCANNER")
    private long idConfScanner;
    @Basic(optional = false)
    @Column(name = "CD_MET_DIGITALIZACION")
    private short cdMetDigitalizacion;
    @JoinColumn(name = "ID_STAT_PROCESO", referencedColumnName = "ID_STAT_PROCESO")
    @ManyToOne(optional = false)
    private EstatusProcesoDTO idStatProceso;
    @JoinColumn(name = "ID_CONFIG_GENERAL", referencedColumnName = "ID_CONFIG_GENERAL")
    @ManyToOne
    private ConfiguracionGeneralDTO idConfigGeneral;
    @Column(name = "FH_CANCELACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCancelacion;
    @Column(name = "FH_LIBERACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLiberacion;

	@Column(name = "NU_FOLIO_INICIAL")
	private Long nuFolioInicial;
	
	@Column(name = "NU_FOLIO_FINAL")
	private Long nuFolioFinal;
	
	
    public LotesBDTO() {
    }

    public LotesBDTO(Long idLote) {
        this.idLote = idLote;
    }

    public LotesBDTO(Long idLote, long idConfScanner, short cdMetDigitalizacion) {
        this.idLote = idLote;
        this.idConfScanner = idConfScanner;
        this.cdMetDigitalizacion = cdMetDigitalizacion;
    }

    public Long getIdLote() {
        return idLote;
    }

    public void setIdLote(Long idLote) {
        this.idLote = idLote;
    }

    public Date getFhCreacionLote() {
        return fhCreacionLote;
    }

    public void setFhCreacionLote(Date fhCreacionLote) {
        this.fhCreacionLote = fhCreacionLote;
    }

    public String getTxRutaAlmacenam() {
        return txRutaAlmacenam;
    }

    public void setTxRutaAlmacenam(String txRutaAlmacenam) {
        this.txRutaAlmacenam = txRutaAlmacenam;
    }

    public Long getNuTotImagenes() {
        return nuTotImagenes;
    }

    public void setNuTotImagenes(Long nuTotImagenes) {
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

    public String getNbLote() {
        return nbLote;
    }

    public void setNbLote(String nbLote) {
        this.nbLote = nbLote;
    }

    public long getIdConfScanner() {
        return idConfScanner;
    }

    public void setIdConfScanner(long idConfScanner) {
        this.idConfScanner = idConfScanner;
    }

    public short getCdMetDigitalizacion() {
        return cdMetDigitalizacion;
    }

    public void setCdMetDigitalizacion(short cdMetDigitalizacion) {
        this.cdMetDigitalizacion = cdMetDigitalizacion;
    }

    public EstatusProcesoDTO getIdStatProceso() {
        return idStatProceso;
    }

    public void setIdStatProceso(EstatusProcesoDTO idStatProceso) {
        this.idStatProceso = idStatProceso;
    }

    public ConfiguracionGeneralDTO getIdConfigGeneral() {
        return idConfigGeneral;
    }

    public void setIdConfigGeneral(ConfiguracionGeneralDTO idConfigGeneral) {
        this.idConfigGeneral = idConfigGeneral;
    }
    
    public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public Date getFechaLiberacion() {
		return fechaLiberacion;
	}

	public void setFechaLiberacion(Date fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idLote != null ? idLote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
         
        if (!(object instanceof LotesBDTO)) {
            return false;
        }
        LotesBDTO other = (LotesBDTO) object;
        if ((this.idLote == null && other.idLote != null) || (this.idLote != null && !this.idLote.equals(other.idLote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dtos.LotesDTO[ idLote=" + idLote + " ]";
    }

	public Long getNuFolioInicial() {
		return nuFolioInicial;
	}

	public void setNuFolioInicial(Long nuFolioInicial) {
		this.nuFolioInicial = nuFolioInicial;
	}

	public Long getNuFolioFinal() {
		return nuFolioFinal;
	}

	public void setNuFolioFinal(Long nuFolioFinal) {
		this.nuFolioFinal = nuFolioFinal;
	}
    
}
