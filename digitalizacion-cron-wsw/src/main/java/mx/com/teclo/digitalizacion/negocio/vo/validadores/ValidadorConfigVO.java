package mx.com.teclo.digitalizacion.negocio.vo.validadores;

import java.util.Date;

public class ValidadorConfigVO {

    private Long idValidadorConfig;
    private Date fhValidadorConfig;
    private Short stConfigActiva;
    private ValidadoresVO idValidador;
    private ConfigValidadorVO idConfiguracion;
    private Long cantidadImagenesRestantes;

    public ValidadorConfigVO() {
    }

    public ValidadorConfigVO(Long idValidadorConfig) {
        this.idValidadorConfig = idValidadorConfig;
    }

    public ValidadorConfigVO(Long idValidadorConfig, Date fhValidadorConfig) {
        this.idValidadorConfig = idValidadorConfig;
        this.fhValidadorConfig = fhValidadorConfig;
    }

    public Long getIdValidadorConfig() {
        return idValidadorConfig;
    }

    public void setIdValidadorConfig(Long idValidadorConfig) {
        this.idValidadorConfig = idValidadorConfig;
    }

    public Date getFhValidadorConfig() {
        return fhValidadorConfig;
    }

    public void setFhValidadorConfig(Date fhValidadorConfig) {
        this.fhValidadorConfig = fhValidadorConfig;
    }

    public Short getStConfigActiva() {
        return stConfigActiva;
    }

    public void setStConfigActiva(Short stConfigActiva) {
        this.stConfigActiva = stConfigActiva;
    }

    public ValidadoresVO getIdValidador() {
        return idValidador;
    }

    public void setIdValidador(ValidadoresVO idValidador) {
        this.idValidador = idValidador;
    }

    public ConfigValidadorVO getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(ConfigValidadorVO idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }
    
    public Long getCantidadImagenesRestantes() {
		return cantidadImagenesRestantes;
	}

	public void setCantidadImagenesRestantes(Long cantidadImagenesRestantes) {
		this.cantidadImagenesRestantes = cantidadImagenesRestantes;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idValidadorConfig != null ? idValidadorConfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValidadorConfigVO)) {
            return false;
        }
        ValidadorConfigVO other = (ValidadorConfigVO) object;
        if ((this.idValidadorConfig == null && other.idValidadorConfig != null) || (this.idValidadorConfig != null && !this.idValidadorConfig.equals(other.idValidadorConfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vo.ValidadorConfigVO[ idValidadorConfig=" + idValidadorConfig + " ]";
    }

}
