package mx.com.teclo.digitalizacion.negocio.vo.lotes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class CambiosLotesVO {

    private Long idCambiosLotes;
    private String cdLote;
    private long nuTotImagenes;
    private Long nuTotImgRechazadas;
    private Long nuTotImgAceptadas;
    private Short stActivo;
    private Long idUsrCreacion;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date fhCreacion;
    private Long idUsrModifica;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date fhModificacion;
    private LotesVO idLote;
    private EstatusProcesoVO idStatProceso;

    public CambiosLotesVO() {
    }

    public CambiosLotesVO(Long idCambiosLotes) {
        this.idCambiosLotes = idCambiosLotes;
    }

    public CambiosLotesVO(Long idCambiosLotes, String cdLote, long nuTotImagenes) {
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

    public LotesVO getIdLote() {
        return idLote;
    }

    public void setIdLote(LotesVO idLote) {
        this.idLote = idLote;
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
        hash += (idCambiosLotes != null ? idCambiosLotes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CambiosLotesVO)) {
            return false;
        }
        CambiosLotesVO other = (CambiosLotesVO) object;
        if ((this.idCambiosLotes == null && other.idCambiosLotes != null) || (this.idCambiosLotes != null && !this.idCambiosLotes.equals(other.idCambiosLotes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vo.CambiosLotesVO[ idCambiosLotes=" + idCambiosLotes + " ]";
    }

}
