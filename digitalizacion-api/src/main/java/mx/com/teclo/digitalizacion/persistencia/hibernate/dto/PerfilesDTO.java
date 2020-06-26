package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAQ026C_SE_PERFILES")
public class PerfilesDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1506837732366820144L;
	@Id
	@Column(name = "ID_PERFIL")
	private Long idPerfil;
	@Column(name = "CD_PERFIL")
	private String cdPerfil;
	
	public PerfilesDTO() {
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getCdPerfil() {
		return cdPerfil;
	}

	public void setCdPerfil(String cdPerfil) {
		this.cdPerfil = cdPerfil;
	}
	
	

}
