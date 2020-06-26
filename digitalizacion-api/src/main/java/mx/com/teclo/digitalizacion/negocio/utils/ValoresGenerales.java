package mx.com.teclo.digitalizacion.negocio.utils;

public enum ValoresGenerales {
	
	ACTIVO(1), INACTIVO(2);
	private int val;
	
	 private ValoresGenerales(int val){
		 this.val = val;
	 }
	
	 public int getVal() {
		 return val;
	 }

}
