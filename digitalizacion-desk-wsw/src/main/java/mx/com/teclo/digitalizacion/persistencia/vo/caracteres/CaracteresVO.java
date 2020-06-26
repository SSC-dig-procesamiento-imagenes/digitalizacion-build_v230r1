package mx.com.teclo.digitalizacion.persistencia.vo.caracteres;

import java.math.BigDecimal;


public class CaracteresVO {

    private BigDecimal id;
    private String caracter;
	
    public CaracteresVO() {
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}
    
    
}
