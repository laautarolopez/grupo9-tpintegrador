package SEM;

public class Inspector {
	private int dni;
	private String zona;
	
	public Inspector(int dni, String zona) {
		this.dni = dni;
		this.zona = zona;
	}
	
	public int getDni() {
		return this.dni;
	}
	
	public String getZona() {
		return this.zona;
	}
	
	public void setZona(String nuevaZona) {
		this.zona = nuevaZona;
	}
}