package mx.com.teclo.digitalizacion.negocio.vo.validadores;

public class ValidadorBusquedaVO {
	
	private Long idValidador;
	private String cdUsuario;
	public ValidadorBusquedaVO() {
	}
	public Long getIdValidador() {
		return idValidador;
	}
	public void setIdValidador(Long idValidador) {
		this.idValidador = idValidador;
	}
	public String getCdUsuario() {
		return cdUsuario;
	}
	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}
	public ValidadorBusquedaVO(Long idValidador, String cdUsuario) {
		this.idValidador = idValidador;
		this.cdUsuario = cdUsuario;
	}
	
	

}
