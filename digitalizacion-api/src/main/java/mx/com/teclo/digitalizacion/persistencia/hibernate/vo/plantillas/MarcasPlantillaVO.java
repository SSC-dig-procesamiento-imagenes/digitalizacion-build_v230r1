package mx.com.teclo.digitalizacion.persistencia.hibernate.vo.plantillas;

public class MarcasPlantillaVO {
	private Long id;
	private	String nombre ; 
	private	String forma ;
	private	String orientacion ;
	private	String tipoMarca ;
	//Cordenada en x de la marca
	private	Integer x ;
	//Cordenada en y de la marca
	private	Integer y ;
	private	Integer width ; 
	private	Integer height ;
	private	String cdColor ;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getForma() {
		return forma;
	}
	public void setForma(String forma) {
		this.forma = forma;
	}
	public String getOrientacion() {
		return orientacion;
	}
	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}
	public String getTipoMarca() {
		return tipoMarca;
	}
	public void setTipoMarca(String tipoMarca) {
		this.tipoMarca = tipoMarca;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getCdColor() {
		return cdColor;
	}
	public void setCdColor(String cdColor) {
		this.cdColor = cdColor;
	}
	
	
	
}
