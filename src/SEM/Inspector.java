package SEM;

public class Inspector {
	private int dni;
	private Zona zona;
	
	public Inspector(int dni, Zona zona) {
		this.dni = dni;
		this.zona = zona;
	}
	
	public int getDni() {
		return this.dni;
	}
	
	public Zona getZona() {
		return this.zona;
	}
	
	public void setZona(Zona nuevaZona) {
		this.zona = nuevaZona;
	}
}