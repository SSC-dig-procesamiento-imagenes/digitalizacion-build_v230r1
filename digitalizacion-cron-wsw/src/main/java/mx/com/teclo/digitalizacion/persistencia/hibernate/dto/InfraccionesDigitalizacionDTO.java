package mx.com.teclo.digitalizacion.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="INFRACCIONES_DIGITALIZACION")
public class InfraccionesDigitalizacionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6610262027792242328L;
	@Id
	@Basic(optional = false)
	@Column(name = "INFRAC_NUM")
	private String infracNum;
	@Column(name = "INFRAC_IMPRESA")
	private String infracImpresa;
	@Column(name = "INFRAC_NUM_CTRL")
	private String infracNumCtrl;
	@Column(name = "SEC_ID")
	private Long secId;
	@Column(name = "UT_ID")
	private Long utId;
	@Column(name = "INFRAC_CON_PLACA")
	private String infracConPlaca;
	@Column(name = "INFRAC_PLACA_EDO")
	private Long infracPlacaEdo;
	@Column(name = "INFRAC_PLACA")
	private String infracPlaca;
	@Column(name = "INFRAC_LICENCIA")
	private String infracLicencia;
	@Column(name = "TIPO_L_ID")
	private Long tipoLId;
	@Column(name = "VMAR_ID")
	private Long vMarId;
	@Column(name = "ART_MOTIVACION")
	private String artMotivacion;
	@Column(name = "INFRAC_M_FECHA_HORA")
	private Date infracMFechaHora;
	@Column(name = "ART_ID")
	private Long artId;
	@Column(name = "SANCION_ART_ID")
	private Long sancionArtId;
	@Column(name = "EMP_ID")
	private String empId;
	@Column(name = "INFRAC_PAGADA")
	private String infracPagada;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	public InfraccionesDigitalizacionDTO() {
	}


	public String getInfracNum() {
		return infracNum;
	}


	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}


	public String getInfracImpresa() {
		return infracImpresa;
	}

	public void setInfracImpresa(String infracImpresa) {
		this.infracImpresa = infracImpresa;
	}

	public String getInfracNumCtrl() {
		return infracNumCtrl;
	}

	public void setInfracNumCtrl(String infracNumCtrl) {
		this.infracNumCtrl = infracNumCtrl;
	}

	public Long getSecId() {
		return secId;
	}

	public void setSecId(Long secId) {
		this.secId = secId;
	}

	public Long getUtId() {
		return utId;
	}

	public void setUtId(Long utId) {
		this.utId = utId;
	}

	public String getInfracConPlaca() {
		return infracConPlaca;
	}

	public void setInfracConPlaca(String infracConPlaca) {
		this.infracConPlaca = infracConPlaca;
	}

	public Long getInfracPlacaEdo() {
		return infracPlacaEdo;
	}

	public void setInfracPlacaEdo(Long infracPlacaEdo) {
		this.infracPlacaEdo = infracPlacaEdo;
	}

	public String getInfracPlaca() {
		return infracPlaca;
	}

	public void setInfracPlaca(String infracPlaca) {
		this.infracPlaca = infracPlaca;
	}

	public String getInfracLicencia() {
		return infracLicencia;
	}

	public void setInfracLicencia(String infracLicencia) {
		this.infracLicencia = infracLicencia;
	}

	public Long getTipoLId() {
		return tipoLId;
	}

	public void setTipoLId(Long tipoLId) {
		this.tipoLId = tipoLId;
	}

	public Long getvMarId() {
		return vMarId;
	}

	public void setvMarId(Long vMarId) {
		this.vMarId = vMarId;
	}

	public String getArtMotivacion() {
		return artMotivacion;
	}

	public void setArtMotivacion(String artMotivacion) {
		this.artMotivacion = artMotivacion;
	}

	public Date getInfracMFechaHora() {
		return infracMFechaHora;
	}

	public void setInfracMFechaHora(Date infracMFechaHora) {
		this.infracMFechaHora = infracMFechaHora;
	}

	public Long getArtId() {
		return artId;
	}

	public void setArtId(Long artId) {
		this.artId = artId;
	}

	public Long getSancionArtId() {
		return sancionArtId;
	}

	public void setSancionArtId(Long sancionArtId) {
		this.sancionArtId = sancionArtId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getInfracPagada() {
		return infracPagada;
	}

	public void setInfracPagada(String infracPagada) {
		this.infracPagada = infracPagada;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	
}
