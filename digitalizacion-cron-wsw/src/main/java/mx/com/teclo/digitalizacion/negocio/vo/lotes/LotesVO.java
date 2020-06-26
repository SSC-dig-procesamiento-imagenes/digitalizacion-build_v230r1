package mx.com.teclo.digitalizacion.negocio.vo.lotes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class LotesVO {
    private Long idLote;
    private String cdLote;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date fhCreacionLote;
    private String txRutaAlmacenam;
    private long nuTotImagenes;
    private Long nuTotImgRechazadas;
    private Long nuTotImgAceptadas;
    private EstatusProcesoVO idStatProceso;

    public LotesVO() {
    }

    public LotesVO(Long idLote) {
        this.idLote = idLote;
    }

    public LotesVO(Long idLote, String cdLote, Date fhCreacionLote, long nuTotImagenes) {
        this.idLote = idLote;
        this.cdLote = cdLote;
        this.fhCreacionLote = fhCreacionLote;
        this.nuTotImagenes = nuTotImagenes;
    }

    public Long getIdLote() {
        return idLote;
    }

    public void setIdLote(Long idLote) {
        this.idLote = idLote;
    }

    public String getCdLote() {
        return cdLote;
    }

    public void setCdLote(String cdLote) {
        this.cdLote = cdLote;
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

    public EstatusProcesoVO getIdStatProceso() {
        return idStatProceso;
    }

    public void setIdStatProceso(EstatusProcesoVO idStatProceso) {
        this.idStatProceso = idStatProceso;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLote != null ? idLote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LotesVO)) {
            return false;
        }
        LotesVO other = (LotesVO) object;
        if ((this.idLote == null && other.idLote != null) || (this.idLote != null && !this.idLote.equals(other.idLote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vo.LotesVO[ idLote=" + idLote + " ]";
    }


}
