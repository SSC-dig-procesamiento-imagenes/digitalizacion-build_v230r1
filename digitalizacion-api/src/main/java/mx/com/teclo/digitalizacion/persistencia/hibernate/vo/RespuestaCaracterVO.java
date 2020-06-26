package mx.com.teclo.digitalizacion.persistencia.hibernate.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class RespuestaCaracterVO {
	private String respuesta;
	private BigDecimal id;
	private List<CaracterVO> listCaracterNew ;
	private List<CaracterVO> listCaracterOld ;
	private RespuestaVO plantillasrespuestasId;
	private BigInteger orden;
	
	
	
	public RespuestaVO getPlantillasrespuestasId() {
		return plantillasrespuestasId;
	}
	public void setPlantillasrespuestasId(RespuestaVO plantillasrespuestasId) {
		this.plantillasrespuestasId = plantillasrespuestasId;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}


	public BigInteger getOrden() {
		return orden;
	}
	public void setOrden(BigInteger orden) {
		this.orden = orden;
	}
	public List<CaracterVO> getListCaracterNew() {
		return listCaracterNew;
	}
	public void setListCaracterNew(List<CaracterVO> listCaracterNew) {
		this.listCaracterNew = listCaracterNew;
	}
	public List<CaracterVO> getListCaracterOld() {
		return listCaracterOld;
	}
	public void setListCaracterOld(List<CaracterVO> listCaracterOld) {
		this.listCaracterOld = listCaracterOld;
	}
	
	
	
}
