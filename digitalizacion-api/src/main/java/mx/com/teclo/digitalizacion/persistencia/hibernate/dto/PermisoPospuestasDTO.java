package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TDP043C_PERM_POSP")
public class PermisoPospuestasDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6028169691323912694L;
	@Id
	@Column(name = "ID_PERM_POSP")
	private Long idPermPosp;
	@JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL")
	@ManyToOne(optional = false)
	private PerfilesDTO idPerfil;
	
	public PermisoPospuestasDTO() {
	}

	public Long getIdPermPosp() {
		return idPermPosp;
	}

	public void setIdPermPosp(Long idPermPosp) {
		this.idPermPosp = idPermPosp;
	}

	public PerfilesDTO getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(PerfilesDTO idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	
}
