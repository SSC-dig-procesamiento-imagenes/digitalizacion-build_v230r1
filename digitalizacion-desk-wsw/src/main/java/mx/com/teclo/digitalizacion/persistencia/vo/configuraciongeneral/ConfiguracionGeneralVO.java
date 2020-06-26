package mx.com.teclo.digitalizacion.persistencia.vo.configuraciongeneral;

import mx.com.teclo.digitalizacion.persistencia.vo.nomenclaturaimagen.NomenclaturaImagenVO;

public class ConfiguracionGeneralVO {
    
	private Long idConfigGeneral;
    
	private String txNombreRuta;
    
	private NomenclaturaImagenVO idNomenclaturaImg;

	/**
	 * @return the idConfigGeneral
	 */
	public Long getIdConfigGeneral() {
		return idConfigGeneral;
	}

	/**
	 * @param idConfigGeneral the idConfigGeneral to set
	 */
	public void setIdConfigGeneral(Long idConfigGeneral) {
		this.idConfigGeneral = idConfigGeneral;
	}

	/**
	 * @return the txNombreRuta
	 */
	public String getTxNombreRuta() {
		return txNombreRuta;
	}

	/**
	 * @param txNombreRuta the txNombreRuta to set
	 */
	public void setTxNombreRuta(String txNombreRuta) {
		this.txNombreRuta = txNombreRuta;
	}

	/**
	 * @return the idNomenclaturaImg
	 */
	public NomenclaturaImagenVO getIdNomenclaturaImg() {
		return idNomenclaturaImg;
	}

	/**
	 * @param idNomenclaturaImg the idNomenclaturaImg to set
	 */
	public void setIdNomenclaturaImg(NomenclaturaImagenVO idNomenclaturaImg) {
		this.idNomenclaturaImg = idNomenclaturaImg;
	}
}