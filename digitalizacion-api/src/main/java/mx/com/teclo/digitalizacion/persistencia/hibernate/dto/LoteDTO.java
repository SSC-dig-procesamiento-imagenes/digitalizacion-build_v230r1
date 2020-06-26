package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TDP004D_LOTES")
public class LoteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3520713307922902000L;

	
	@Id
//	@SequenceGenerator(name="TDG002", sequenceName="SQSDI_REG_TDG002",allocationSize=1)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TDG002")
	@Column(name="ID_LOTE", unique=true, nullable=false)
	private Long  idLote;
	
	@Column(name="CD_LOTE")
	private String cdLote;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
